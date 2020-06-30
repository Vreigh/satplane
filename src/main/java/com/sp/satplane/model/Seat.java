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

  private Boolean byWindow;
  private Boolean byEmergencyExit;
  private Boolean byWing;
  private Boolean moreLegSpace;
  private Boolean nearSeatTaken;
  private Boolean byToilet;
  private Boolean byKitchen;
  private Boolean isLoud;
  private Boolean withTurbulence;
  private Boolean byAisle;
  private Boolean isSafest;
}
