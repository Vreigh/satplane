package com.sp.satplane.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  private long id;

  private Sex sex;
  private int age;
  private boolean hasMotionSickness;
  private boolean isFlyingAbroad;
  private boolean isOftenFlying;
  private boolean isAfraidToFly;
  private boolean travelsAlone;
  private boolean ordersFoodOften;
  private boolean isWealthy;
  private boolean isPoor;
  private boolean isActive;

}
