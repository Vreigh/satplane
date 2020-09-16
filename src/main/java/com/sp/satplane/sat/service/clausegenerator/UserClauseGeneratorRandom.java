package com.sp.satplane.sat.service.clausegenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sp.satplane.sat.model.Clause;
import com.sp.satplane.sat.model.SatUser;

import static com.sp.satplane.sat.model.SatSeatVariable.TOTAL_VARS;
import static java.lang.Math.abs;

@Service
@Primary
public class UserClauseGeneratorRandom implements UserClauseGenerator {

  public List<Clause> generateClauses(SatUser user) {
    List<Clause> result = new ArrayList<>();

    Random r = new Random();
    for (int i = 0; i < 50; i++) {
      Clause randomClause = new Clause();
      randomClause.setWeight(r.nextInt(100) + 10);

      int var1 = getRandomVariableInt(r);
      int var2 = var1;
      while (abs(var1) == abs(var2)) {
        var2 = getRandomVariableInt(r);
      }

      randomClause.setVarsVector(new int[] { var1, var2 });
      result.add(randomClause);
    }

    return result;
  }

  private int getRandomVariableInt(Random r) {
    int value = r.nextInt(TOTAL_VARS) + 1;
    return r.nextBoolean() ? value : -value;
  }
}
