package com.sp.satplane.sat.service.clausegenerator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatUser;

import static com.sp.satplane.sat.model.SatSeatVariable.*;

@Service
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

    return result;
  }
}
