package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import android.support.annotation.NonNull;


public class Number extends Coefficient {

    private Fraction multiplier;

    public Number(Fraction multiplier) {
        this.multiplier = multiplier;
    }

    public Number(int number) {
        this(new Fraction(number));
    }

    @Override
    public int compareTo(@NonNull Coefficient o) {
        int result;

        if (o instanceof Number)
            result = compareTo((Number) o);
        else if (o instanceof M)
            result = compareTo((M) o);
        else if (o instanceof Infinity)
            result = -1;
        else
            throw new UnsupportedOperationException();
        return result;
    }

    private int compareTo(Number o) {
        return multiplier.compareTo(o.multiplier);
    }

    private int compareTo(M o) {
        if (o.getMultiplier().equals(0))
            return multiplier.compareTo(o.getConstant());
        return -1;
    }

    @Override
    public String toString() {
        return multiplier.toString();
    }

    public Fraction getTotal() {
        return multiplier;
    }

    @Override
    public Coefficient add(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return add((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return add((M) coefficientOther);
        throw new UnsupportedOperationException();
    }

    @Override
    public Coefficient subtract(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return subtract((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return subtract((M) coefficientOther);
        throw new UnsupportedOperationException();
    }

    @Override
    public Coefficient multiply(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return multiply((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return multiply((M) coefficientOther);
        throw new UnsupportedOperationException();
    }

    @Override
    public Coefficient divide(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return divide((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return divide((M) coefficientOther);
        throw new UnsupportedOperationException();
    }

    private Number add(Number o) {
        return new Number(
                multiplier.add(o.multiplier));
    }

    private M add(M o) {
        return new M(
                o.getMultiplier(),
                multiplier.add(o.getConstant()));
    }

    private Number subtract(Number o) {
        return new Number(
                multiplier.subtract(o.multiplier));
    }

    private M subtract(M o) {
        return new M(
                o.getMultiplier().negate(),
                multiplier.subtract(o.getConstant()));
    }

    private Number multiply(Number o) {
        return new Number(
                multiplier.multiply(o.multiplier));
    }

    private M multiply(M o) {
        return new M(
                multiplier.multiply(o.getMultiplier()),
                multiplier.multiply(o.getConstant()));
    }

    private Number divide(Number o) {
        return new Number(
                multiplier.divide(o.multiplier));
    }

    private M divide(M o) {
        return new M(
                multiplier.divide(o.getMultiplier()),
                multiplier.divide(o.getConstant()));
    }

    public Number negate() {
        return new Number(multiplier.negate());
    }

    public Number abs() {
        return new Number(multiplier.abs());
    }
}
