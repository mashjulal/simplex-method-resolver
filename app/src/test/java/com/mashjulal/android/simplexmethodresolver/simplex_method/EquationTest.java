package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Fraction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;


public class EquationTest {

    private Equation getEquation() {
        return new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(42));
    }

    @Test
    public void size_isCorrect() throws Exception {
        Equation equation = getEquation();
        assertEquals(6, equation.size());

        equation = new Equation(new ArrayList<>(), new Coefficient(0));
        assertEquals(0, equation.size());
    }

    @Test
    public void add_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Equation
        result = e.add(getEquation());
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(10),
                        new Coefficient(-6),
                        new Coefficient(2),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(84)), result);

        result = e.add(new Equation(new ArrayList<>(), new Coefficient(0)));
        assertEquals(e, result);

        result = e.add(getEquation().negate());
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(0)), result);

        // Constant
        result = e.add(new Coefficient(10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(52)), result);

        result = e.add(new Coefficient(0));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(42)), result);

        result = e.add(new Coefficient(-10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(32)), result);
    }

    @Test
    public void subtract_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Equation
        result = e.subtract(getEquation());
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(0)), result);


        result = e.subtract(new Equation(new ArrayList<>(), new Coefficient(0)));
        assertEquals(e, result);

        result = e.subtract(getEquation().negate());
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(10),
                        new Coefficient(-6),
                        new Coefficient(2),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(84)), result);

        // Constant
        result = e.subtract(new Coefficient(10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(32)), result);


        result = e.subtract(new Coefficient(0));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(42)), result);

        result = e.subtract(new Coefficient(-10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(5),
                        new Coefficient(-3),
                        new Coefficient(1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(52)), result);
    }

    @Test
    public void multiply_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Constant
        result = e.multiply(new Coefficient(10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(50),
                        new Coefficient(-30),
                        new Coefficient(10),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(420)), result);

        result = e.multiply(new Coefficient(0));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(0)), result);

        result = e.multiply(new Coefficient(-10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(-50),
                        new Coefficient(30),
                        new Coefficient(-10),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(-420)), result);
    }

    @Test
    public void divide_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        // Constant
        result = e.divide(new Coefficient(10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(new Fraction(5, 10)),
                        new Coefficient(new Fraction(-3, 10)),
                        new Coefficient(new Fraction(1, 10)),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(new Fraction(42, 10))), result);

        try {
            result = e.divide(new Coefficient(0));
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        result = e.divide(new Coefficient(-10));
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(new Fraction(-5, 10)),
                        new Coefficient(new Fraction(3, 10)),
                        new Coefficient(new Fraction(-1, 10)),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(new Fraction(-42, 10))), result);
    }

    @Test
    public void negate_isCorrect() throws Exception {
        Equation e = getEquation();
        Equation result;

        result = e.negate();
        assertEquals(new Equation(
                Arrays.asList(
                        new Coefficient(-5),
                        new Coefficient(3),
                        new Coefficient(-1),
                        new Coefficient(0),
                        new Coefficient(0),
                        new Coefficient(0)),
                new Coefficient(-42)), result);

        result = result.negate();
        assertEquals(e, result);

        e = new Equation(new ArrayList<>(), new Coefficient(0));
        assertEquals(e, e.negate());
    }

    @Test
    public void getCoefficient_isCorrect() throws Exception {
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
    public void setCoefficient_isCorrect() throws Exception {
        Equation e = getEquation();
        Coefficient c = new Coefficient(32523);
        for (int i = 0; i < e.size(); i++) {
            Coefficient cc = e.getCoefficient(i);
            e.getCoefficients().set(i, c);
            assertNotSame(cc, e.getCoefficient(i));
        }
    }

    @Test
    public void addCoefficient_isCorrect() throws Exception {
        Equation eInit = getEquation();

        Equation e = new Equation(new ArrayList<>(), eInit.getValue());
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
            if (c.equals(Coefficient.ZERO))
                continue;

            for (int j = 0; j < cofs.size(); j++) {
                if (j == i) {
                    cofs.set(j, Coefficient.ZERO);
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