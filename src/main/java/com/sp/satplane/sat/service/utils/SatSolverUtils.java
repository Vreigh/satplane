package com.sp.satplane.sat.service.utils;

import com.sp.satplane.sat.model.SatSeat;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SatSolverUtils {

  public static SatSeat convertSatSolverResultToSatSeat(int[] satSolverResult) {
    SatSeat result = new SatSeat();
    for (int var : satSolverResult) {
      if (var > 0) {
        result.assign(var);
      }
    }
    return result;
  }
}
