package com.mashjulal.android.simplexmethodresolver;

import com.mashjulal.android.simplexmethodresolver.simplex_method.Fraction;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class FractionClassUnitTest {

    @Test
    public void init_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(2, 1);
        Fraction fraction2 = new Fraction(2);
        assertTrue(fraction1.equals(fraction2));

        fraction1 = new Fraction(0, 1);
        fraction2 = new Fraction(0);
        assertTrue(fraction1.equals(fraction2));

        fraction1 = new Fraction(-1, 1);
        fraction2 = new Fraction(-1);
        assertTrue(fraction1.equals(fraction2));
    }

    @Test
    public void add_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);

        Fraction result = fraction1.add(fraction2);
        assertEquals(new Fraction(1), result);

        fraction2 = new Fraction(1, 3);
        result = fraction1.add(fraction2);
        assertEquals(new Fraction(5, 6), result);

        fraction2 = new Fraction(0);
        result = fraction1.add(fraction2);
        assertEquals(fraction1, result);

        fraction2 = new Fraction(-1, 2);
        result = fraction1.add(fraction2);
        assertEquals(new Fraction(0), result);

        fraction1 = new Fraction(-1, 2);
        result = fraction1.add(fraction2);
        assertEquals(new Fraction(-1), result);

        fraction2 = new Fraction(-1, 3);
        result = fraction1.add(fraction2);
        assertEquals(new Fraction(-5, 6), result);
    }

    @Test
    public void reduce_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 1);
        assertEquals(new Fraction(1, 1), fraction.reduce());

        fraction = new Fraction(2, 2);
        assertEquals(new Fraction(1, 1), fraction.reduce());

        fraction = new Fraction(10, 5);
        assertEquals(new Fraction(2, 1), fraction.reduce());

        fraction = new Fraction(11, 5);
        assertEquals(new Fraction(11, 5), fraction.reduce());

        fraction = new Fraction(0);
        assertEquals(new Fraction(0, 1), fraction.reduce());

        fraction = new Fraction(-10, 5);
        assertEquals(new Fraction(-2, 1), fraction.reduce());

        fraction = new Fraction(-11, 5);
        assertEquals(new Fraction(-11, 5), fraction.reduce());
    }

    @Test
    public void sub_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);

        Fraction result = fraction1.subtract(fraction2);
        assertEquals(new Fraction(0), result);

        fraction2 = new Fraction(1, 3);
        result = fraction1.subtract(fraction2);
        assertEquals(new Fraction(1, 6), result);

        fraction1 = new Fraction(1, 3);
        fraction2 = new Fraction(1, 2);
        result = fraction1.subtract(fraction2);
        assertEquals(new Fraction(-1, 6), result);

        fraction2 = new Fraction(0);
        result = fraction1.subtract(fraction2);
        assertEquals(fraction1, result);

        fraction1 = new Fraction(-1, 2);
        fraction2 = new Fraction(-1, 2);
        result = fraction1.subtract(fraction2);
        assertEquals(new Fraction(0), result);

        fraction2 = new Fraction(1, 2);
        result = fraction1.subtract(fraction2);
        assertEquals(new Fraction(-1), result);
    }

    @Test
    public void mul_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);

        Fraction result = fraction1.multiply(fraction2);
        assertEquals(new Fraction(1, 4), result);

        fraction2 = new Fraction(1, 3);
        result = fraction1.multiply(fraction2);
        assertEquals(new Fraction(1, 6), result);

        fraction2 = new Fraction(0);
        result = fraction1.multiply(fraction2);
        assertEquals(fraction2, result);

        fraction2 = new Fraction(-1, 2);
        result = fraction1.multiply(fraction2);
        assertEquals(new Fraction(-1, 4), result);

        fraction1 = new Fraction(-1, 2);
        result = fraction1.multiply(fraction2);
        assertEquals(new Fraction(1, 4), result);

        fraction2 = new Fraction(-1, 3);
        result = fraction1.multiply(fraction2);
        assertEquals(new Fraction(1, 6), result);
    }

    @Test
    public void div_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);

        Fraction result = fraction1.divide(fraction2);
        assertEquals(new Fraction(1), result);

        fraction2 = new Fraction(1, 3);
        result = fraction1.divide(fraction2);
        assertEquals(new Fraction(3, 2), result);

        fraction1 = new Fraction(0);
        result = fraction1.divide(fraction2);
        assertEquals(fraction1, result);

        fraction1 = new Fraction(1, 2);
        fraction2 = new Fraction(-1, 2);
        result = fraction1.divide(fraction2);
        assertEquals(new Fraction(-1), result);

        fraction1 = new Fraction(-1, 2);
        result = fraction1.divide(fraction2);
        assertEquals(new Fraction(1), result);

        fraction2 = new Fraction(-1, 3);
        result = fraction1.divide(fraction2);
        assertEquals(new Fraction(3, 2), result);
    }

    @Test
    public void toDouble_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);
        assertEquals(0.5, fraction.toDouble());

        fraction = new Fraction(1);
        assertEquals(1.0, fraction.toDouble());

        fraction = new Fraction(0);
        assertEquals(0.0, fraction.toDouble());

        fraction = new Fraction(-1);
        assertEquals(-1.0, fraction.toDouble());

        fraction = new Fraction(-1, 2);
        assertEquals(-0.5, fraction.toDouble());
    }

    @Test
    public void toString_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);
        assertEquals("1/2", fraction.toString());

        fraction = new Fraction(1);
        assertEquals("1", fraction.toString());

        fraction = new Fraction(0);
        assertEquals("0", fraction.toString());

        fraction = new Fraction(-1);
        assertEquals("-1", fraction.toString());

        fraction = new Fraction(-1, 2);
        assertEquals("-1/2", fraction.toString());
    }

    @Test
    public void equals_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);
        assertTrue(fraction1.equals(fraction2));

        fraction2 = new Fraction(1);
        assertFalse(fraction1.equals(fraction2));

        fraction2 = new Fraction(2, 4);
        assertTrue(fraction1.equals(fraction2));

        assertFalse(fraction1.equals(new Object()));

        fraction1 = new Fraction(0);
        fraction2 = new Fraction(0);
        assertTrue(fraction1.equals(fraction2));
    }
}
