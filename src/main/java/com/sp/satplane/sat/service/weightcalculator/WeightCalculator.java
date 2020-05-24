package com.sp.satplane.sat.service.weightcalculator;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatSeat;

@Service
public class WeightCalculator {

  public int calculateDiscomfort(SatSeat seat, List<Clause> clauses) {
    return clauses.stream()
      .mapToInt(clause -> checkDiscomfort(seat, clause))
      .sum();
  }

  private int checkDiscomfort(SatSeat seat, Clause clause) {
    if (checkSeatMatches(seat, clause)) {
      return 0;
    } else {
      return clause.getWeight();
    }
  }

  private boolean checkSeatMatches(SatSeat seat, Clause clause) {
    for (int var : clause.getVarsVector()) {
      if (var > 0 && seat.isAssigned(var)) {
        return true;
      }
      if (var < 0 && !seat.isAssigned(-var)) {
        return true;
      }
    }
    return false;
  }

}
