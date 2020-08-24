package com.sp.satplane.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatSeat;
import com.sp.satplane.sat.model.SatSeatVariable;
import com.sp.satplane.sat.service.solver.SatSolver;
import com.sp.satplane.sat.service.utils.SatSolverUtils;
import com.sp.satplane.sat.service.weightcalculator.WeightCalculator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscomfortCalculator {

  private final SatSolver satSolver;
  private final WeightCalculator weightCalculator;

  public int calculateIdealDiscomfort(List<Clause> userClauses) {
    int[] satSolverResult = satSolver.solve(SatSeatVariable.TOTAL_VARS, userClauses);
    SatSeat idealSeat = SatSolverUtils.convertSatSolverResultToSatSeat(satSolverResult);
    return weightCalculator.calculateDiscomfort(idealSeat, userClauses);
  }

  public Double calculateDiscomfortRatio(SatSeat satSeat, List<Clause> userClauses, int idealDiscomfort) {
    int realDiscomfort = weightCalculator.calculateDiscomfort(satSeat, userClauses);
    if (idealDiscomfort == 0) {
      if (realDiscomfort == 0) {
        return 0.5;
      }
      return 1.0;
    }
    return realDiscomfort / (double) (idealDiscomfort + realDiscomfort);
  }


}
