package com.sp.satplane.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

  private Long id;
  private Long planeId;

  private boolean byWindow;
  private boolean byEmergencyExit;
  private boolean byWing;
  private boolean moreLegSpace;
  private boolean nearSeatTaken;
  private boolean byToilet;
  private boolean byKitchen;
  private boolean isLoud;
  private boolean withTurbulence;
  private boolean byAisle;
  private boolean isSafest;
}
