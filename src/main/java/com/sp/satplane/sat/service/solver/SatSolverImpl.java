package com.sp.satplane.sat.service.solver;

import java.util.List;

import org.sat4j.core.VecInt;
import org.sat4j.maxsat.SolverFactory;
import org.sat4j.maxsat.WeightedMaxSatDecorator;
import org.sat4j.pb.PseudoOptDecorator;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;
import org.sat4j.tools.OptToSatAdapter;
import org.springframework.stereotype.Service;

import com.sp.satplane.sat.exception.SolveException;
import com.sp.satplane.sat.model.Clause;

@Service
public class SatSolverImpl implements SatSolver {

  @Override
  public int[] solve(int totalVars, List<Clause> clauses) {
    WeightedMaxSatDecorator maxSatSolver = new WeightedMaxSatDecorator(SolverFactory.newDefault());
    ModelIterator solver = new ModelIterator(new OptToSatAdapter(new PseudoOptDecorator(maxSatSolver)));

    solver.newVar(totalVars);
    solver.setExpectedNumberOfClauses(clauses.size());

    try {
      for (Clause clause : clauses) {
        maxSatSolver.addSoftClause(clause.getWeight(), new VecInt(clause.getVarsVector()));
      }

      if (solver.isSatisfiable()) {
        return solver.model();
      } else {
        throw new SolveException("Unsatisfiable");
      }
    } catch (TimeoutException e) {
      throw new SolveException("timeout");
    } catch (ContradictionException e) {
      throw new SolveException("contradiction");
    }
  }

}
