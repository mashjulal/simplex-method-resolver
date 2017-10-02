package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Fraction;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.M;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class MTest {
    @Test
    public void add_isCorrect() throws Exception {
        M m = new M(1);
        M result;

        // Number
        Coefficient c = new Number(new Fraction(3, 4));
        result = m.add(c);
        assertEquals(new M(1, new Fraction(3, 4)), result);

        // M
        M m2 = new M(5, 6);
        result = m.add(m2);
        assertEquals(new M(6, 6), result);
    }

    @Test
    public void subtract_isCorrect() throws Exception {
        M m = new M(1);
        M result;

        // Number
        Coefficient c = new Number(new Fraction(3, 4));
        result = m.subtract(c);
        assertEquals(new M(1, new Fraction(-3, 4)), result);

        // M
        M m2 = new M(5, 6);
        result = m.subtract(m2);
        assertEquals(new M(-4, -6), result);
    }

    @Test
    public void multiply_isCorrect() throws Exception {
        M m = new M(1);
        M result;

        // Number
        Coefficient c = new Number(new Fraction(3, 4));
        result = m.multiply(c);
        assertEquals(new M(new Fraction(3, 4), 0), result);
    }

    @Test
    public void divide_isCorrect() throws Exception {
        M m = new M(1);
        M result;

        // Number
        Coefficient c = new Number(new Fraction(3, 4));
        result = m.divide(c);
        assertEquals(new M(new Fraction(4, 3)), result);
    }

    @Test
    public void toString_isCorrect() throws Exception {
        M m = new M(1);
        assertEquals("M", m.toString());

        m = new M(1, 2);
        assertEquals("(M + 2)", m.toString());

        m = new M(new Fraction(1, 2));
        assertEquals("1/2M", m.toString());

        m = new M(new Fraction(1, 2), 3);
        assertEquals("(1/2M + 3)", m.toString());

        m = new M(0, 3);
        assertEquals("3", m.toString());

        m = new M(0);
        assertEquals("0", m.toString());

        m = new M(-1);
        assertEquals("-M", m.toString());

        m = new M(-4, -5);
        assertEquals("(-4M - 5)", m.toString());

        m = new M(4, -5);
        assertEquals("(4M - 5)", m.toString());

        m = new M(-1, new Fraction(-6, 7));
        assertEquals("(-M - 6/7)", m.toString());

        m = new M(-1, new Fraction(6, 7));
        assertEquals("(-M + 6/7)", m.toString());
    }

    @Test
    public void compareTo_isCorrect() throws Exception {
        M m1 = new M(new Fraction(1, 3));
        M m2 = new M(new Fraction(1, 3));
        assertEquals(0, m1.compareTo(m2));

        m2 = new M(1);
        assertEquals(-1, m1.compareTo(m2));

        m2 = new M(0);
        assertEquals(1, m1.compareTo(m2));

        m1 = new M(1);
        m2 = new M(1, 1);
        assertEquals(-1, m1.compareTo(m2));

        m1 = new M(1);
        m2 = new M(1, -1);
        assertEquals(1, m1.compareTo(m2));
    }

    @Test
    public void abs_isCorrect() throws Exception {
        M m = new M(1, 1);

        Coefficient result = m.abs();
        assertEquals(m, result);

        result = m.negate().abs();
        assertEquals(m, result);
    }

    @Test
    public void negate_isCorrect() throws Exception {
        M m = new M(new Fraction(1, 2));

        M result = m.negate();
        assertEquals(new M(new Fraction(-1, 2)), result);

        result = result.negate();
        assertEquals(m, result);
    }

    @Test
    public void getTotal_isCorrect() throws Exception {
        M m = new M(1);

        Fraction result = m.getTotal();
        assertEquals(new Fraction(1000000), result);

        m = new M(1, 2);
        result = m.getTotal();
        assertEquals(new Fraction(1000002), result);

        m = new M(new Fraction(1, 2), 2);
        result = m.getTotal();
        assertEquals(new Fraction(500002), result);

        m = new M(0, 2);
        result = m.getTotal();
        assertEquals(new Fraction(2), result);

        m = new M(1, new Fraction(1, 2));
        result = m.getTotal();
        assertEquals(new Fraction(2000001, 2), result);

        m = new M(0);
        result = m.getTotal();
        assertEquals(Fraction.ZERO, result);
    }

}