package com.sp.satplane.sat.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SatSeat {

  private Set<Integer> assigned = new HashSet<>();

  public SatSeat(Collection<SatSeatVariable> vars) {
    for (SatSeatVariable var : vars) {
      assigned.add(var.yes());
    }
  }

  public SatSeat(SatSeatVariable... vars) {
    this(Arrays.asList(vars));
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
