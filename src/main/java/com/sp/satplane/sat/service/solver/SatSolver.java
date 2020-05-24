package com.sp.satplane.sat.service.solver;

import java.util.List;

import com.sp.satplane.sat.model.Clause;

public interface SatSolver {

  int[] solve(int totalVars, List<Clause> clauses);
}
