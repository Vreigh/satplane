package com.sp.satplane.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatSeat;
import com.sp.satplane.sat.model.SatUser;
import com.sp.satplane.sat.service.clausegenerator.UserClauseGenerator;

import lombok.*;

@Service
@RequiredArgsConstructor
public class CommonDiscomfortRatiosService {

  private final UserClauseGenerator userClauseGenerator;
  private final DiscomfortCalculator discomfortCalculator;

  private List<CommonUser> commonUsers;

  @PostConstruct
  private void initialiseCommonUsers() { // heavy sat calculations performed only once
    List<SatUser> users = mockCommonUsers();
    commonUsers = users.stream()
      .map(userClauseGenerator::generateClauses)
      .map(clauses -> {
        int idealDiscomfort = discomfortCalculator.calculateIdealDiscomfort(clauses);
        return new CommonUser(clauses, idealDiscomfort);
      })
      .collect(Collectors.toList());
  }

  public Double getCommonDiscomfortRatio(SatSeat satSeat) {
    return commonUsers.stream()
      .mapToDouble(commonUser -> discomfortCalculator.calculateDiscomfortRatio(satSeat, commonUser.clauses, commonUser.idealDiscomfort))
      .average()
      .getAsDouble();
  }

  private List<SatUser> mockCommonUsers() {
    List<SatUser> mockCommonUsers = new ArrayList<>();
    mockCommonUsers.add(SatUser.builder()
                          .isMan(true)
                          .isAdult(true)
                          .isOftenFlying(true)
                          .travelsAlone(true)
                          .ordersFoodOften(true)
                          .build());
    mockCommonUsers.add(SatUser.builder()
                          .isAdult(true)
                          .isOld(true)
                          .isAfraidToFly(true)
                          .isFlyingAbroad(true)
                          .hasMotionSickness(true)
                          .build());
    return mockCommonUsers;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class CommonUser {
    private List<Clause> clauses;
    private int idealDiscomfort;
  }
}
