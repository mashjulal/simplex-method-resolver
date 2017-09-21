package com.mashjulal.android.simplexmethodresolver.simplex_method;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertEquals;

/**
 * Created by master on 19.09.17.
 */

public class BasisTest {

    private List<Equation> getEquationList() {
        List<Equation> equations = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            List<Coefficient> cofs = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                cofs.add(new Coefficient(random.nextInt(5)));
            }
            equations.add(new Equation(cofs, new Coefficient(0)));
        }
        return equations;
    }

    @Test
    public void init_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        System.out.println(basis.toString());
    }

    @Test
    public void replaceValue_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        basis.replaceValue(0, 1, new Coefficient(10));
        assertEquals(new Coefficient(10), basis.getCoefficient(1));
    }

    @Test
    public void recalculate_isCorrect() throws Exception {

    }


    @Test
    public void getIndexes_isCorrect() throws Exception {

    }

    @Test
    public void getCoefficient_isCorrect() throws Exception {

    }

    @Test
    public void toString_isCorrect() throws Exception {

    }




}
