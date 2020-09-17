package com.sp.satplane.service.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.sp.satplane.model.Seat;
import com.sp.satplane.service.SeatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatServiceMock implements SeatService {

  public List<Seat> getSeatsOfPlane(Long planeId) {
    List<Seat> mockSeats = new ArrayList<>();

    if (planeId.equals(0L)) {
      fillPredefined(mockSeats, planeId);
    } else {
      if(planeId > 500L) {
        planeId = 500L;
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
        mockSeat.setIsLoud(r.nextBoolean());
        mockSeat.setWithTurbulence(r.nextBoolean());
        mockSeat.setByAisle(r.nextBoolean());
        mockSeat.setIsSafest(r.nextBoolean());
        mockSeats.add(mockSeat);
      }
    }

    return mockSeats;
  }

  private void fillPredefined(List<Seat> mockSeats, Long planeId) {
    Seat mockSeat1 = new Seat();
    mockSeat1.setId(1L);
    mockSeat1.setPlaneId(planeId);
    mockSeat1.setByWindow(true);
    mockSeat1.setByEmergencyExit(false);
    mockSeat1.setByWing(true);
    mockSeat1.setMoreLegSpace(false);
    mockSeat1.setNearSeatTaken(false);
    mockSeat1.setByToilet(false);
    mockSeat1.setByKitchen(true);
    mockSeat1.setIsLoud(false);
    mockSeat1.setWithTurbulence(true);
    mockSeat1.setByAisle(true);
    mockSeat1.setIsSafest(false);
    mockSeats.add(mockSeat1);

    Seat mockSeat2 = new Seat();
    mockSeat2.setId(2L);
    mockSeat2.setPlaneId(planeId);
    mockSeat2.setByWindow(false);
    mockSeat2.setByEmergencyExit(false);
    mockSeat2.setByWing(false);
    mockSeat2.setMoreLegSpace(true);
    mockSeat2.setNearSeatTaken(true);
    mockSeat2.setByToilet(false);
    mockSeat2.setByKitchen(true);
    mockSeat2.setIsLoud(true);
    mockSeat2.setWithTurbulence(false);
    mockSeat2.setByAisle(false);
    mockSeat2.setIsSafest(false);
    mockSeats.add(mockSeat2);

    Seat mockSeat3 = new Seat();
    mockSeat3.setId(3L);
    mockSeat3.setPlaneId(planeId);
    mockSeat3.setByWindow(false);
    mockSeat3.setByEmergencyExit(true);
    mockSeat3.setByWing(true);
    mockSeat3.setMoreLegSpace(false);
    mockSeat3.setNearSeatTaken(false);
    mockSeat3.setByToilet(true);
    mockSeat3.setByKitchen(true);
    mockSeat3.setIsLoud(true);
    mockSeat3.setWithTurbulence(false);
    mockSeat3.setByAisle(true);
    mockSeat3.setIsSafest(true);
    mockSeats.add(mockSeat3);
  }

  @Override
  public BigDecimal getPlaneBaseTicketPrice(Long planeId) {
    return BigDecimal.valueOf(199.99);
  }

}
