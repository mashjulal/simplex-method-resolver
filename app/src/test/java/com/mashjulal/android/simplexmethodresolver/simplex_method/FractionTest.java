package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Fraction;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class FractionTest {

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
    public void addFraction_isCorrect() throws Exception {
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
    public void addInteger_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);
        int number = 1;

        Fraction result = fraction.add(number);
        assertEquals(new Fraction(3, 2), result);

        number = 0;
        result = fraction.add(number);
        assertEquals(fraction, result);

        number = -1;
        result = fraction.add(number);
        assertEquals(new Fraction(-1, 2), result);
    }

    @Test
    public void subtractFraction_isCorrect() throws Exception {
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
    public void subtractInteger_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);
        int number = 1;

        Fraction result = fraction.subtract(number);
        assertEquals(new Fraction(-1, 2), result);

        number = 0;
        result = fraction.subtract(number);
        assertEquals(fraction, result);

        number = -1;
        result = fraction.subtract(number);
        assertEquals(new Fraction(3, 2), result);
    }

    @Test
    public void multiplyFraction_isCorrect() throws Exception {
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
    public void multiplyInteger_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);
        int number = 1;

        Fraction result = fraction.multiply(number);
        assertEquals(fraction, result);

        number = 0;
        result = fraction.multiply(number);
        assertEquals(new Fraction(0), result);

        number = -1;
        result = fraction.multiply(number);
        assertEquals(new Fraction(-1, 2), result);
    }

    @Test
    public void divideFraction_isCorrect() throws Exception {
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
    public void divideInteger_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);
        int number = 1;

        Fraction result = fraction.divide(number);
        assertEquals(fraction, result);

        try {
            number = 0;
            fraction.divide(number);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        number = -1;
        result = fraction.divide(number);
        assertEquals(new Fraction(-1, 2), result);
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

    @Test
    public void negate_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);

        fraction = fraction.negate();
        assertEquals(new Fraction(-1, 2), fraction);

        fraction = fraction.negate();
        assertEquals(new Fraction(1, 2), fraction);

        fraction = new Fraction(0).negate();
        assertEquals(new Fraction(0), fraction);
    }

    @Test
    public void compareTo_isCorrect() throws Exception {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);
        assertEquals(0, fraction1.compareTo(fraction2));

        fraction2 = new Fraction(1);
        assertEquals(-1, fraction1.compareTo(fraction2));

        fraction2 = new Fraction(2, 4);
        assertEquals(0, fraction1.compareTo(fraction2));

        fraction2 = new Fraction(0);
        assertEquals(1, fraction1.compareTo(fraction2));

        fraction1 = new Fraction(0);
        fraction2 = new Fraction(0);
        assertEquals(0, fraction1.compareTo(fraction2));
    }

    @Test
    public void abs_isCorrect() throws Exception {
        Fraction fraction = new Fraction(1, 2);

        assertEquals(new Fraction(1, 2), fraction.abs());

        fraction = new Fraction(-1, 2);
        assertEquals(new Fraction(1, 2), fraction.abs());

        fraction = new Fraction(0);
        assertEquals(new Fraction(0), fraction.abs());
    }

}
