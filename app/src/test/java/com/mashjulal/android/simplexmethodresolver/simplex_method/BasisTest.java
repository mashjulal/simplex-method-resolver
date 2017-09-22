package com.mashjulal.android.simplexmethodresolver.simplex_method;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;


public class BasisTest {

    private List<Equation> getEquationList() {
        List<Equation> equations = new ArrayList<>();

        equations.add(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(42)));
        equations.add(new Equation(
                Arrays.asList(
                        new Coefficient(0),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(1)));
        equations.add(new Equation(
                Arrays.asList(
                        new Coefficient(10),
                        new Coefficient(4),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(-1),
                        new Coefficient(1)),
                new Coefficient(60)));

        return equations;
    }

    @Test
    public void replaceValue_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        basis.setBasisValue(0, new BasisValue(1, new Coefficient(10)));
        assertEquals(new BasisValue(1, new Coefficient(10)), basis.getBasisValues().get(0));

        BasisValue oldBasisValue = basis.getBasisValues().get(0);
        basis.setBasisValue(0, new BasisValue(2, new Coefficient(10)));
        assertNotSame(oldBasisValue, basis.getBasisValues().get(0));
    }

    @Test
    public void recalculate_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        basis.recalculate(getEquationList());
    }

    @Test
    public void getIndexes_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        List<Integer> indexes = basis.getIndexes();
        assertEquals(Arrays.asList(2, 3, 5), indexes);
    }

    @Test
    public void getCoefficient_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        for (BasisValue basisValue : basis) {
            assertEquals(basisValue.getCoefficient(), basis.getCoefficient(basisValue.getIndex()));
        }
    }

    @Test
    public void toString_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        assertEquals("x3 = 42, x4 = 1, x6 = 60", basis.toString());
    }




}
