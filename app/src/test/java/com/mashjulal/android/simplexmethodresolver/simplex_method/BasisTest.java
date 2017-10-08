package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;


public class BasisTest {

    private List<Equation> getEquationList() {
        List<Equation> equations = new ArrayList<>();

        List<Coefficient> coefficientList = new ArrayList<>();
        coefficientList.add(new Number(5));
        coefficientList.add(new Number(-3));
        coefficientList.add(new Number(1));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        equations.add(new Equation(coefficientList, new Number(42)));

        coefficientList = new ArrayList<>();
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(1));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(1));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        equations.add(new Equation(coefficientList, new Number(1)));

        coefficientList = new ArrayList<>();
        coefficientList.add(new Number(10));
        coefficientList.add(new Number(4));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(-1));
        coefficientList.add(new Number(0));
        equations.add(new Equation(coefficientList, new Number(60)));

        return equations;
    }

    @Test
    public void replaceValue_isCorrect() throws Exception {
        Basis basis = new Basis(getEquationList());
        basis.setBasisValue(0, new BasisValue(1, new Number(10)));
        assertEquals(new BasisValue(1, new Number(10)), basis.getBasisValues().get(0));

        BasisValue oldBasisValue = basis.getBasisValues().get(0);
        basis.setBasisValue(0, new BasisValue(2, new Number(10)));
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
