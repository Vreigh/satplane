package com.sp.satplane.sat.service.clausegenerator;

import java.util.List;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatUser;

public interface UserClauseGenerator {

  List<Clause> generateClauses(SatUser user);
}
