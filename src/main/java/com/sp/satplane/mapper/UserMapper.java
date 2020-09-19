package com.sp.satplane.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sp.satplane.model.Sex;
import com.sp.satplane.model.User;
import com.sp.satplane.sat.model.SatUser;

@Mapper
public abstract class UserMapper {

  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  public SatUser map(User user) {
    SatUser satUser = new SatUser();
    satUser.setMan(user.getSex() == Sex.MALE);
    satUser.setAdult(user.getAge() >= 18);
    satUser.setOld(user.getAge() >= 65);
    satUser.setHasMotionSickness(user.isHasMotionSickness());
    satUser.setFlyingAbroad(user.isFlyingAbroad());
    satUser.setOftenFlying(user.isOftenFlying());
    satUser.setAfraidToFly(user.isAfraidToFly());
    satUser.setTravelsAlone(user.isTravelsAlone());
    satUser.setOrdersFoodOften(user.isOrdersFoodOften());
    satUser.setWealthy(user.isWealthy());
    satUser.setPoor(user.isPoor());
    satUser.setHasTroubleMoving(user.isHasTroubleMoving());
    return satUser;
  }
}
