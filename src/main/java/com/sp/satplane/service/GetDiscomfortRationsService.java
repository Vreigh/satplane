package com.sp.satplane.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatSeat;
import com.sp.satplane.sat.model.SatSeatVariable;
import com.sp.satplane.sat.model.SatUser;
import com.sp.satplane.sat.service.clausegenerator.UserClauseGenerator;
import com.sp.satplane.sat.service.solver.SatSolver;
import com.sp.satplane.sat.service.utils.SatSolverUtils;
import com.sp.satplane.sat.service.weightcalculator.WeightCalculator;

import lombok.RequiredArgsConstructor;

// mock, TODO: implement
@Service
@RequiredArgsConstructor
public class GetDiscomfortRationsService {

  private final UserClauseGenerator userClauseGenerator;
  private final SatSolver satSolver;
  private final WeightCalculator weightCalculator;

  public List<Double> getDiscomfortRatios(Long userId, Long planeId) {
    List<SatSeat> satSeats = mockSatSeats();
    SatUser satUser = mockSatUser();

    List<Clause> userClauses = userClauseGenerator.generateClauses(satUser);

    int[] satSolverResult = satSolver.solve(SatSeatVariable.TOTAL_VARS, userClauses);
    SatSeat idealSeat = SatSolverUtils.convertSatSolverResultToSatSeat(satSolverResult);
    int idealDiscomfort = weightCalculator.calculateDiscomfort(idealSeat, userClauses);

    return satSeats.stream()
      .map(seat -> calculateDR(seat, userClauses, idealDiscomfort))
      .collect(Collectors.toList());

  }

  private Double calculateDR(SatSeat satSeat, List<Clause> userClauses, int idealDiscomfort) {
    int realDiscomfort = weightCalculator.calculateDiscomfort(satSeat, userClauses);
    if (idealDiscomfort == 0) {
      if (realDiscomfort == 0) {
        return 0.5;
      }
      return 1.0;
    }
    return realDiscomfort / (double) (idealDiscomfort + realDiscomfort);
  }

  private List<SatSeat> mockSatSeats() {
    List<SatSeat> mockSatSeats = new ArrayList<>();
    mockSatSeats.add(new SatSeat(SatSeatVariable.BY_WINDOW, SatSeatVariable.BY_WING));
    mockSatSeats.add(new SatSeat(SatSeatVariable.MORE_LEG_SPACE, SatSeatVariable.NEAR_SEAT_TAKEN));
    mockSatSeats.add(new SatSeat(SatSeatVariable.BY_WING, SatSeatVariable.BY_TOILET, SatSeatVariable.BY_EMERGENCY_EXIT));
    return mockSatSeats;
  }

  private SatUser mockSatUser() {
    SatUser mockSatUser = new SatUser();
    mockSatUser.setAdult(true);
    mockSatUser.setTravelsAlone(true);
    return mockSatUser;
  }
}
