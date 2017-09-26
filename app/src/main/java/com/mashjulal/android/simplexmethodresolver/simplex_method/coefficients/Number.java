package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import lombok.Getter;


public class Number extends Coefficient {

    @Getter private Fraction multiplier;

    public Number(Fraction multiplier) {
        this.multiplier = multiplier;
    }

    public Number(int number) {
        this(new Fraction(number));
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

    private Number add(Number o) {
        return new Number(
                multiplier.add(o.multiplier));
    }

    private M add(M o) {
        return new M(
                o.getMultiplier(),
                multiplier.add(o.getConstant()));
    }

    @Override
    public Coefficient subtract(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return subtract((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return subtract((M) coefficientOther);
        throw new UnsupportedOperationException();
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

    @Override
    public Coefficient multiply(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return multiply((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return multiply((M) coefficientOther);
        throw new UnsupportedOperationException();
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

    @Override
    public Coefficient divide(Coefficient coefficientOther) {
        if (coefficientOther instanceof Number)
            return divide((Number) coefficientOther);
        else if (coefficientOther instanceof M)
            return divide((M) coefficientOther);
        throw new UnsupportedOperationException();
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
