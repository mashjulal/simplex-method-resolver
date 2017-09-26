package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import java.math.BigInteger;


public class Infinity extends Coefficient {

    private static final Fraction BIG_NUMBER = new Fraction(BigInteger.valueOf(10000000000L));

    @Override
    public String toString() {
        return "\u221E";
    }

    @Override
    public Fraction getTotal() {
        return BIG_NUMBER;
    }

    @Override
    public Coefficient add(Coefficient coefficientOther) {
        return this;
    }

    @Override
    public Coefficient subtract(Coefficient coefficientOther) {
        return this;
    }

    @Override
    public Coefficient multiply(Coefficient coefficientOther) {
        return this;
    }

    @Override
    public Coefficient divide(Coefficient coefficientOther) {
        return this;
    }

    @Override
    public Coefficient negate() {
        return this;
    }

    @Override
    public Coefficient abs() {
        return this;
    }
}
