package com.sp.satplane.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sp.satplane.mapper.SeatMapper;
import com.sp.satplane.mapper.UserMapper;
import com.sp.satplane.model.Seat;
import com.sp.satplane.model.Ticket;
import com.sp.satplane.model.User;
import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatSeat;
import com.sp.satplane.sat.model.SatUser;
import com.sp.satplane.sat.service.clausegenerator.UserClauseGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.ObjectUtils.max;

@Service
@RequiredArgsConstructor
public class GetTicketsService {

  private static final int MIN_SEATS_TO_PRESENT = 5;

  private final UserService userService;
  private final SeatService seatService;
  private final UserClauseGenerator userClauseGenerator;
  private final DiscomfortCalculator discomfortCalculator;
  private final CommonDiscomfortRatiosService commonDiscomfortRatiosService;

  public List<Ticket> getTickets(Long userId, Long planeId) {
    User user = userService.getUser(userId);
    List<Seat> seats = seatService.getSeatsOfPlane(planeId);
    BigDecimal ticketBasePrice = seatService.getPlaneBaseTicketPrice(planeId);
    if (isNull(user) || isEmpty(seats) || isNull(ticketBasePrice)) {
      throw new IllegalArgumentException("No data for provided identifiers");
    }

    SatUser satUser = UserMapper.INSTANCE.map(user);
    List<SatSeat> satSeats = SeatMapper.INSTANCE.map(seats);

    List<Clause> userClauses = userClauseGenerator.generateClauses(satUser);

    int idealDiscomfort = discomfortCalculator.calculateIdealDiscomfort(userClauses);

    List<SeatSummary> seatSummaryList = resolveSeatDataList(seats, satSeats, userClauses, idealDiscomfort);

    List<SeatSummary> seatsToPresent = filterAndSortSeatsToPresent(seatSummaryList);
    seatsToPresent.forEach(SeatSummary::calculateIncentive);

    return mapSeatSummariesToTickets(seatsToPresent, ticketBasePrice);
  }

  private List<SeatSummary> resolveSeatDataList(List<Seat> seats, List<SatSeat> satSeats,
                                                List<Clause> userClauses, int idealDiscomfort) {
    List<SeatSummary> result = new ArrayList<>();
    for (int i = 0; i < seats.size(); i++) {
      Long seatId = seats.get(i).getId();
      Double discomfortRatio = discomfortCalculator.calculateDiscomfortRatio(satSeats.get(i), userClauses, idealDiscomfort);
      Double commonDiscomfortRatio = commonDiscomfortRatiosService.getCommonDiscomfortRatio(satSeats.get(i));
      Double fitRatio = commonDiscomfortRatio / discomfortRatio;
      result.add(new SeatSummary(seatId, discomfortRatio, commonDiscomfortRatio, fitRatio));
    }
    return result;
  }

  private List<SeatSummary> filterAndSortSeatsToPresent(List<SeatSummary> seats) {
    List<SeatSummary> finalList = new ArrayList<>();
    int minSeatsToPresent = max(seats.size() / 2, MIN_SEATS_TO_PRESENT);
    seats.stream()
      .sorted(Comparator.comparing(SeatSummary::getFitRatio).reversed())
      .forEach(seat -> {
        if (seat.fitRatio > 1.0 || finalList.size() < minSeatsToPresent) {
          finalList.add(seat);
        }
      });
    return finalList;
  }

  private List<Ticket> mapSeatSummariesToTickets(List<SeatSummary> seatsToPresent, BigDecimal basePrice) {
    if (seatsToPresent.isEmpty()) {
      return emptyList();
    }
    SeatSummary bestSeat = seatsToPresent.stream().max(Comparator.comparing(SeatSummary::getIncentive)).get();
    SeatSummary worstSeat = seatsToPresent.stream().min(Comparator.comparing(SeatSummary::getIncentive)).get();
    double bestWorstDiff = bestSeat.incentive - worstSeat.incentive;

    return seatsToPresent.stream()
      .map(seat -> {
        BigDecimal finalPercentage;
        if (bestWorstDiff > 0) {
          double incentiveRatio = (seat.incentive - worstSeat.incentive) / bestWorstDiff;
          finalPercentage = BigDecimal.valueOf(2.0 - incentiveRatio);
        } else {
          finalPercentage = BigDecimal.valueOf(1.5);
        }
        return new Ticket(seat.id, basePrice.multiply(finalPercentage).setScale(2, BigDecimal.ROUND_CEILING));
      }).collect(Collectors.toList());

  }

  @Getter
  @Setter
  @NoArgsConstructor
  private static class SeatSummary {
    private Long id;
    private Double discomfortRatio;
    private Double commonDiscomfortRatio;
    private Double fitRatio;
    private Double incentive;

    SeatSummary(Long id, Double discomfortRatio, Double commonDiscomfortRatio, Double fitRatio) {
      this.id = id;
      this.discomfortRatio = discomfortRatio;
      this.commonDiscomfortRatio = commonDiscomfortRatio;
      this.fitRatio = fitRatio;
    }

    void calculateIncentive() {
      this.incentive = max(discomfortRatio * commonDiscomfortRatio, commonDiscomfortRatio * commonDiscomfortRatio);
    }
  }

}
