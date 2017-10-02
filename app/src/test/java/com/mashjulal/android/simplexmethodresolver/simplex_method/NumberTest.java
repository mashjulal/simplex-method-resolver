package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Fraction;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class NumberTest {

    @Test
    public void getTotal() throws Exception {
        Number cof = new Number(1);
        assertEquals(Fraction.ONE, cof.getTotal());

        cof = new Number(new Fraction(1, 2));
        assertEquals(new Fraction(1, 2), cof.getTotal());

        cof = new Number(new Fraction(-1, 2));
        assertEquals(new Fraction(-1, 2), cof.getTotal());

        cof = new Number(new Fraction(0));
        assertEquals(new Fraction(0), cof.getTotal());
    }

    @Test
    public void add() throws Exception {
        Coefficient c1 = new Number(new Fraction(1, 2));
        Coefficient c2 = new Number(new Fraction(1, 2));

        Coefficient result = c1.add(c2);
        assertEquals(CoefficientFactory.ONE, result);

        c2 = new Number(new Fraction(1, 3));
        result = c1.add(c2);
        assertEquals(new Number(new Fraction(5, 6)), result);

        c2 = CoefficientFactory.ZERO;
        result = c1.add(c2);
        assertEquals(c1, result);

        c2 = new Number(new Fraction(-1, 2));
        result = c1.add(c2);
        assertEquals(CoefficientFactory.ZERO, result);

        c1 = new Number(new Fraction(-1, 2));
        result = c1.add(c2);
        assertEquals(new Number(new Fraction(-1)), result);

        c2 = new Number(new Fraction(-1, 3));
        result = c1.add(c2);
        assertEquals(new Number(new Fraction(-5, 6)), result);
    }

    @Test
    public void subtract() throws Exception {
        Coefficient c1 = new Number(new Fraction(1, 2));
        Coefficient c2 = new Number(new Fraction(1, 2));

        Coefficient result = c1.subtract(c2);
        assertEquals(CoefficientFactory.ZERO, result);
    }

    @Test
    public void multiply() throws Exception {
        Coefficient c1 = new Number(new Fraction(1, 2));
        Coefficient c2 = new Number(new Fraction(1, 2));

        Coefficient result = c1.multiply(c2);
        assertEquals(new Number(new Fraction(1, 4)), result);
    }

    @Test
    public void divide() throws Exception {
        Coefficient c1 = new Number(new Fraction(1, 2));
        Coefficient c2 = new Number(new Fraction(1, 2));

        Coefficient result = c1.divide(c2);
        assertEquals(CoefficientFactory.ONE, result);
    }

    @Test
    public void negate() throws Exception {
        Number c = new Number(new Fraction(1, 2));

        Number result = c.negate();
        assertEquals(new Number(new Fraction(-1, 2)), result);

        result = result.negate();
        assertEquals(c, result);
    }

    @Test
    public void abs() throws Exception {
        Number c = new Number(new Fraction(1, 2));

        Number result = c.abs();
        assertEquals(c, result);

        result = c.negate().abs();
        assertEquals(c, result);
    }

    @Test
    public void compareTo() throws Exception {
        Coefficient c1 = new Number(new Fraction(1, 3));
        Coefficient c2 = new Number(new Fraction(1, 3));
        assertEquals(0, c1.compareTo(c2));

        c2 = CoefficientFactory.ONE;
        assertEquals(-1, c1.compareTo(c2));

        c2 = CoefficientFactory.ZERO;
        assertEquals(1, c1.compareTo(c2));

        c1 = CoefficientFactory.ONE;
        c2 = CoefficientFactory.ONE;
        assertEquals(0, c1.compareTo(c2));
    }

}