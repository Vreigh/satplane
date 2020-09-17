package com.sp.satplane.sat.service.clausegenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatUser;

import static com.sp.satplane.sat.model.SatSeatVariable.*;
import static com.sp.satplane.sat.service.clausegenerator.ClauseGeneratorType.SIMPLE;

@Service
@ForClauseGeneratorType(clauseGeneratorType = SIMPLE)
public class UserClauseGeneratorSimple implements UserClauseGenerator {

  public List<Clause> generateClauses(SatUser user) {
    List<Clause> result = new ArrayList<>();

    if (user.isMan()) {
      result.add(new Clause(100, new int[] { MORE_LEG_SPACE.yes(), NEAR_SEAT_TAKEN.no() }));
      result.add(new Clause(80, new int[] { BY_EMERGENCY_EXIT.yes(), BY_TOILET.no() }));
    } else {
      result.add(new Clause(90, new int[] { BY_WINDOW.yes(), BY_TOILET.yes() }));
      result.add(new Clause(85, new int[] { BY_EMERGENCY_EXIT.no(), BY_WING.no() }));
    }

    if (user.isAdult()) {
      result.add(new Clause(85, new int[] { BY_WING.yes(), NEAR_SEAT_TAKEN.yes() }));
      result.add(new Clause(55, new int[] { BY_EMERGENCY_EXIT.yes(), BY_WING.yes() }));
    } else {
      result.add(new Clause(100, new int[] { BY_WINDOW.yes(), BY_WING.no() }));
      result.add(new Clause(60, new int[] { BY_EMERGENCY_EXIT.no(), MORE_LEG_SPACE.no() }));
    }

    if (user.isTravelsAlone()) {
      result.add(new Clause(95, new int[] { BY_WINDOW.yes(), NEAR_SEAT_TAKEN.yes() }));
      result.add(new Clause(40, new int[] { BY_EMERGENCY_EXIT.yes(), BY_WING.yes() }));
    } else {
      result.add(new Clause(90, new int[] { MORE_LEG_SPACE.yes(), NEAR_SEAT_TAKEN.no() }));
      result.add(new Clause(70, new int[] { BY_WINDOW.yes(), BY_TOILET.yes() }));
    }

    if (user.isOld()) {
      result.add(new Clause(85, new int[] { BY_TOILET.yes(), BY_AISLE.no() }));
      result.add(new Clause(40, new int[] { BY_EMERGENCY_EXIT.yes(), BY_KITCHEN.no() }));
    } else {
      result.add(new Clause(55, new int[] { WITH_TURBULENCE.yes(), MORE_LEG_SPACE.no() }));
      result.add(new Clause(65, new int[] { NEAR_SEAT_TAKEN.yes(), IS_LOUD.yes() }));
    }

    if (user.isHasMotionSickness()) {
      result.add(new Clause(90, new int[] { BY_TOILET.yes(), BY_AISLE.yes() }));
      result.add(new Clause(75, new int[] { WITH_TURBULENCE.no(), BY_KITCHEN.no() }));
    } else {
      result.add(new Clause(55, new int[] { WITH_TURBULENCE.yes(), MORE_LEG_SPACE.no() }));
      result.add(new Clause(70, new int[] { NEAR_SEAT_TAKEN.yes(), IS_LOUD.yes() }));
    }

    if (user.isFlyingAbroad()) {
      result.add(new Clause(85, new int[] { BY_WINDOW.yes(), BY_WING.no() }));
      result.add(new Clause(65, new int[] { MORE_LEG_SPACE.yes(), BY_KITCHEN.no() }));
    } else {
      result.add(new Clause(40, new int[] { BY_AISLE.yes(), BY_EMERGENCY_EXIT.no() }));
      result.add(new Clause(65, new int[] { NEAR_SEAT_TAKEN.yes(), BY_TOILET.no() }));
    }

    if (user.isOftenFlying()) {
      result.add(new Clause(95, new int[] { BY_WINDOW.no(), BY_KITCHEN.yes() }));
      result.add(new Clause(40, new int[] { MORE_LEG_SPACE.yes(), WITH_TURBULENCE.no() }));
    } else {
      result.add(new Clause(90, new int[] { BY_WINDOW.yes(), WITH_TURBULENCE.no() }));
      result.add(new Clause(70, new int[] { NEAR_SEAT_TAKEN.no(), BY_TOILET.yes() }));
    }

    if (user.isAfraidToFly()) {
      result.add(new Clause(100, new int[] { IS_SAFEST.yes(), WITH_TURBULENCE.no() }));
      result.add(new Clause(95, new int[] { BY_EMERGENCY_EXIT.yes(), BY_TOILET.yes() }));
    } else {
      result.add(new Clause(40, new int[] { BY_WINDOW.yes(), BY_WING.yes() }));
      result.add(new Clause(20, new int[] { NEAR_SEAT_TAKEN.yes(), BY_TOILET.no() }));
    }

    if (user.isOrdersFoodOften()) {
      result.add(new Clause(75, new int[] { NEAR_SEAT_TAKEN.no(), BY_KITCHEN.yes() }));
      result.add(new Clause(90, new int[] { MORE_LEG_SPACE.yes(), BY_AISLE.yes() }));
    } else {
      result.add(new Clause(20, new int[] { BY_KITCHEN.no(), BY_WING.yes() }));
      result.add(new Clause(35, new int[] { NEAR_SEAT_TAKEN.yes(), BY_TOILET.no() }));
    }

    return result;
  }
}
