package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Fraction;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;


public class EquationTest {

    private Equation getEquation() {
        List<Coefficient> coefficientList = new ArrayList<>();
        coefficientList.add(new Number(5));
        coefficientList.add(new Number(-3));
        coefficientList.add(new Number(1));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));

        return new Equation(coefficientList, new Number(42));
    }

    private Equation getEquation(int constant, int... variables) {
        List<Coefficient> coefficientList = new ArrayList<>();
        for (int number : variables)
            coefficientList.add(new Number(number));
        return new Equation(coefficientList, new Number(constant));
    }

    @Test
    public void size_isCorrect() throws Exception {
        Equation equation = getEquation();
        assertEquals(6, equation.size());

        equation = new Equation(new ArrayList<Coefficient>(), new Number(0));
        assertEquals(0, equation.size());
    }

    @Test
    public void add_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Equation
        result = e.add(getEquation());

        assertEquals(getEquation(84, 10, -6, 2, 0, 0, 0), result);

        result = e.add(new Equation(new ArrayList<Coefficient>(), new Number(0)));
        assertEquals(e, result);

        result = e.add(getEquation().negate());
        assertEquals(getEquation(0, 0, 0, 0, 0, 0, 0), result);

        // Constant
        result = e.add(new Number(10));
        assertEquals(getEquation(52, 5, -3, 1, 0, 0, 0), result);

        result = e.add(new Number(0));
        assertEquals(getEquation(42, 5, -3, 1, 0, 0, 0), result);

        result = e.add(new Number(-10));
        assertEquals(getEquation(32, 5, -3, 1, 0, 0, 0), result);
    }

    @Test
    public void subtract_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Equation
        result = e.subtract(getEquation());
        assertEquals(getEquation(0, 0, 0, 0, 0, 0, 0), result);


        result = e.subtract(new Equation(new ArrayList<Coefficient>(), new Number(0)));
        assertEquals(e, result);

        result = e.subtract(getEquation().negate());
        assertEquals(getEquation(84, 10, -6, 2, 0, 0, 0), result);

        // Constant
        result = e.subtract(new Number(10));
        assertEquals(getEquation(32, 5, -3, 1, 0, 0, 0), result);


        result = e.subtract(new Number(0));
        assertEquals(getEquation(42, 5, -3, 1, 0, 0, 0), result);

        result = e.subtract(new Number(-10));
        assertEquals(getEquation(52, 5, -3, 1, 0, 0, 0), result);
    }

    @Test
    public void multiply_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Constant
        result = e.multiply(new Number(10));
        assertEquals(getEquation(420, 50, -30, 10, 0, 0, 0), result);

        result = e.multiply(new Number(0));
        assertEquals(getEquation(0, 0, 0, 0, 0, 0, 0), result);

        result = e.multiply(new Number(-10));
        assertEquals(getEquation(-420, -50, 30, -10, 0, 0, 0), result);
    }

    @Test
    public void divide_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Constant
        result = e.divide(new Number(10));

        List<Coefficient> coefficientList = new ArrayList<>();
        coefficientList.add(new Number(new Fraction(5, 10)));
        coefficientList.add(new Number(new Fraction(-3, 10)));
        coefficientList.add(new Number(new Fraction(1, 10)));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        assertEquals(new Equation(
                coefficientList,
                new Number(new Fraction(42, 10))), result);

        try {
            e.divide(new Number(0));
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        result = e.divide(new Number(-10));
        coefficientList = new ArrayList<>();
        coefficientList.add(new Number(new Fraction(-5, 10)));
        coefficientList.add(new Number(new Fraction(3, 10)));
        coefficientList.add(new Number(new Fraction(-1, 10)));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        coefficientList.add(new Number(0));
        assertEquals(new Equation(
                coefficientList,
                new Number(new Fraction(-42, 10))), result);
    }

    @Test
    public void negate_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        result = e.negate();
        assertEquals(getEquation(-42, -5, 3, -1, 0, 0, 0), result);

        result = result.negate();
        assertEquals(e, result);

        e = new Equation(new ArrayList<Coefficient>(), new Number(0));
        assertEquals(e, e.negate());
    }

    @Test
    public void getNumber_isCorrect() throws Exception {
        Equation e = getEquation();
        for (int i = 0; i < e.size(); i++) {
            assertEquals(e.getCoefficients().get(i), e.getCoefficient(i));
        }
    }

    @Test
    public void remove_isCorrect() throws Exception {
        Equation e = getEquation();

        int s = e.size();
        for (int i = 0; i < s; i++) {
            e.remove(0);
            assertEquals(
                    new Equation(getEquation().getCoefficients().subList(i+1, s), e.getValue()),
                    e);
        }
    }

    @Test
    public void setNumber_isCorrect() throws Exception {
        Equation e = getEquation();
        Number c = new Number(32523);
        for (int i = 0; i < e.size(); i++) {
            Coefficient cc = e.getCoefficient(i);
            e.getCoefficients().set(i, c);
            assertNotSame(cc, e.getCoefficient(i));
        }
    }

    @Test
    public void addCoefficient_isCorrect() throws Exception {
        Equation eInit = getEquation();

        Equation e = new Equation(new ArrayList<Coefficient>(), eInit.getValue());
        for (int i = 0; i < eInit.size(); i++) {
            e.addCoefficient(eInit.getCoefficient(i));
            assertEquals(new Equation(eInit.getCoefficients().subList(0, i+1), eInit.getValue()), e);
        }
    }

    @Test
    public void express_isCorrect() throws Exception {
        Equation e = getEquation();
        List<Coefficient> cofs = getEquation().getCoefficients();
        Coefficient c;
        for (int i = 0; i < e.size(); i++) {
            Collections.copy(cofs, e.getCoefficients());
            c = e.getCoefficient(i);
            if (c.equals(Constants.Coefficients.ZERO))
                continue;

            for (int j = 0; j < cofs.size(); j++) {
                if (j == i) {
                    cofs.set(j, Constants.Coefficients.ZERO);
                } else {
                    cofs.set(j, cofs.get(j).negate().divide(c));
                }
            }
            assertEquals(
                    new Equation(cofs, e.getValue().negate().divide(c)),
                    e.express(i));
        }
    }

    @Test
    public void toString_isCorrect() throws Exception {
        Equation e = getEquation();
        assertEquals("5x1-3x2+x3=42", e.toString());

        e = e.negate();
        assertEquals("-5x1+3x2-x3=-42", e.toString());
    }
}