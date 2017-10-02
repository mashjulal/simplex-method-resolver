package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import com.mashjulal.android.simplexmethodresolver.simplex_method.Constants;

import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.Assert.assertEquals;


public class InfinityTest {
    @Test
    public void toString_isCorrect() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        assertEquals("\u221E", infinity.toString());
    }

    @Test
    public void getTotal() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        assertEquals(new Fraction(BigInteger.valueOf(10000000000L)), infinity.getTotal());
    }

    @Test
    public void add() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        infinity = infinity.add(new Number(1));
        assertEquals(Constants.Coefficients.INFINITY, infinity);

    }

    @Test
    public void subtract() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        infinity = infinity.subtract(new Number(1));
        assertEquals(Constants.Coefficients.INFINITY, infinity);
    }

    @Test
    public void multiply() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        infinity = infinity.multiply(new Number(1));
        assertEquals(Constants.Coefficients.INFINITY, infinity);
    }

    @Test
    public void divide() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        infinity = infinity.divide(new Number(1));
        assertEquals(Constants.Coefficients.INFINITY, infinity);
    }

    @Test
    public void negate() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        infinity = infinity.negate();
        assertEquals(Constants.Coefficients.INFINITY, infinity);
    }

    @Test
    public void abs() throws Exception {
        Coefficient infinity = Constants.Coefficients.INFINITY;
        infinity = infinity.abs();
        assertEquals(Constants.Coefficients.INFINITY, infinity);
    }

}