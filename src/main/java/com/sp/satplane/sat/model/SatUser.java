package com.sp.satplane.sat.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SatUser {

  private boolean isMan;
  private boolean isAdult;
  private boolean isOld;
  private boolean hasMotionSickness;
  private boolean isFlyingAbroad;
  private boolean isOftenFlying;
  private boolean isAfraidToFly;
  private boolean travelsAlone;
  private boolean isOrdersFoodOften;

}
