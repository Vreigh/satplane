package com.sp.satplane.service.mock;

import org.springframework.stereotype.Service;

import com.sp.satplane.model.Sex;
import com.sp.satplane.model.User;
import com.sp.satplane.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceMock implements UserService {

  @Override
  public User getUser(Long userId) {
    if (userId == 1) {
      return User.builder()
        .id(1L)
        .sex(Sex.MALE)
        .age(25)
        .hasMotionSickness(false)
        .isFlyingAbroad(false)
        .isOftenFlying(true)
        .isAfraidToFly(false)
        .travelsAlone(true)
        .ordersFoodOften(true)
        .build();
    } else {
      return User.builder()
        .id(2L)
        .sex(Sex.FEMALE)
        .age(55)
        .hasMotionSickness(true)
        .isFlyingAbroad(true)
        .isOftenFlying(false)
        .isAfraidToFly(true)
        .travelsAlone(false)
        .ordersFoodOften(false)
        .build();
    }
  }
}
