package com.sp.satplane.sat.model;

import java.util.HashSet;
import java.util.Set;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SatSeat {

  private Set<Integer> assigned = new HashSet<>();

  public SatSeat(SatSeatVariable... vars) {
    for (SatSeatVariable var : vars) {
      assigned.add(var.yes());
    }
  }

  public boolean isAssigned(SatSeatVariable var) {
    return assigned.contains(var.yes());
  }

  public boolean isAssigned(int varNumber) {
    return assigned.contains(varNumber);
  }

  public void assign(int varNumber) {
    assigned.add(varNumber);
  }
}
