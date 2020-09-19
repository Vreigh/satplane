package com.sp.satplane.service.mock;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Service;

import com.sp.satplane.model.Seat;
import com.sp.satplane.service.SeatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.asList;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatServiceMock implements SeatService {

  private Set<Integer> byWindowSet = new HashSet<>(asList(1, 6, 7, 12, 13, 18));
  private Set<Integer> byAisleSet = new HashSet<>(asList(3, 4, 9, 10, 15, 16));
  private Set<Integer> byToiletSet = new HashSet<>(asList(4, 5, 6, 10, 11));
  private Set<Integer> byKitchenSet = new HashSet<>(asList(1, 2, 3, 8, 9));
  private Set<Integer> byEmergencyExitSet = new HashSet<>(asList(7, 8, 9, 13, 14, 15));
  private Set<Integer> isTakenSet = new HashSet<>(asList(8, 17, 4));
  private Set<Integer> isLoudSet = new HashSet<>(asList(5, 10, 15));
  private Set<Integer> isSafestSet = new HashSet<>(asList(7, 14, 18));

  public List<Seat> getSeatsOfPlane(Long planeId) {
    List<Seat> mockSeats = new ArrayList<>();

    if (planeId.equals(0L)) {
      fillPredefined(mockSeats, planeId);
    } else {
      if (planeId > 5000L) {
        planeId = 5000L;
      }
      Random r = new Random();
      for (int i = 0; i < planeId; i++) {
        Seat mockSeat = new Seat();
        mockSeat.setId((long) i + 1);
        mockSeat.setPlaneId(planeId);
        mockSeat.setByWindow(r.nextBoolean());
        mockSeat.setByEmergencyExit(r.nextBoolean());
        mockSeat.setByWing(r.nextBoolean());
        mockSeat.setMoreLegSpace(r.nextBoolean());
        mockSeat.setNearSeatTaken(r.nextBoolean());
        mockSeat.setByToilet(r.nextBoolean());
        mockSeat.setByKitchen(r.nextBoolean());
        mockSeat.setLoud(r.nextBoolean());
        mockSeat.setWithTurbulence(r.nextBoolean());
        mockSeat.setByAisle(r.nextBoolean());
        mockSeat.setSafest(r.nextBoolean());
        mockSeats.add(mockSeat);
      }
    }

    return mockSeats;
  }

  private void fillPredefined(List<Seat> mockSeats, Long planeId) {
    for (int i = 1; i < 19; i++) {
      Long id = (long) i;
      Seat mockSeat = new Seat();
      mockSeat.setPlaneId(planeId);
      mockSeat.setId(id);
      if (byWindowSet.contains(i)) {
        mockSeat.setByWindow(true);
      }
      if (byEmergencyExitSet.contains(i)) {
        mockSeat.setByEmergencyExit(true);
      }
      if (i > 6 && i < 13) {
        mockSeat.setByWing(true);
      }
      if (i % 4 == 0) {
        mockSeat.setMoreLegSpace(true);
      }
      if (isTakenSet.contains(i)) {
        mockSeat.setNearSeatTaken(true);
      }
      if (byToiletSet.contains(i)) {
        mockSeat.setByToilet(true);
      }
      if (byKitchenSet.contains(i)) {
        mockSeat.setByKitchen(true);
      }
      if (isLoudSet.contains(i)) {
        mockSeat.setLoud(true);
      }
      if (i > 12) {
        mockSeat.setWithTurbulence(true);
      }
      if (byAisleSet.contains(i)) {
        mockSeat.setByAisle(true);
      }
      if (isSafestSet.contains(i)) {
        mockSeat.setSafest(true);
      }
      mockSeats.add(mockSeat);
    }
  }

  @Override
  public BigDecimal getPlaneBaseTicketPrice(Long planeId) {
    return BigDecimal.valueOf(199.99);
  }

}
