package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

import lombok.ToString;


@ToString
class Coefficient implements Comparable<Coefficient> {

    static final Coefficient ZERO = new Coefficient(0);
    static final Coefficient ONE = new Coefficient(1);
    static final Coefficient INFINITE = new Coefficient(new Fraction(1000000000));

    private Fraction multiplier;

    Coefficient(Fraction multiplier) {
        this.multiplier = multiplier;
    }

    Coefficient(int number) {
        this(new Fraction(number));
    }

    @Override
    public int compareTo(@NonNull Coefficient o) {
        return multiplier.compareTo(o.multiplier);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Coefficient && compareTo((Coefficient) o) == 0;
    }

    @Override
    public String toString() {
        return multiplier.toString();
    }

    Fraction getTotal() {
        return multiplier;
    }

    Coefficient add(Coefficient coefficientOther) {
        return new Coefficient(multiplier.add(coefficientOther.multiplier));
    }

    Coefficient subtract(Coefficient coefficientOther) {
        return new Coefficient(multiplier.subtract(coefficientOther.multiplier));
    }

    Coefficient multiply(Coefficient coefficientOther) {
        return new Coefficient(multiplier.multiply(coefficientOther.multiplier));
    }

    Coefficient divide(Coefficient coefficientOther) {
        return new Coefficient(multiplier.divide(coefficientOther.multiplier));
    }

    Coefficient negate() {
        return new Coefficient(multiplier.negate());
    }

    Coefficient abs() {
        return new Coefficient(multiplier.abs());
    }
}
