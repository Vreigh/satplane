package com.sp.satplane.sat.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SatSeatVariable {
  BY_WINDOW(1), BY_EMERGENCY_EXIT(2), BY_WING(3),
  MORE_LEG_SPACE(4), NEAR_SEAT_TAKEN(5), BY_TOILET(6);

  private final int index;

  public static final int TOTAL_VARS = 6;

  public int yes() {
    return index;
  }

  public int no() {
    return -index;
  }

  public static SatSeatVariable byNumber(int varNumber) {
    for (SatSeatVariable var : SatSeatVariable.values()) {
      if (varNumber == var.index) {
        return var;
      }
    }
    return null;
  }
}
