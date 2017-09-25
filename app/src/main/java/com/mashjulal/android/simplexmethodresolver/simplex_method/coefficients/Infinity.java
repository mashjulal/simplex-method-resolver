package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import android.support.annotation.NonNull;


public class Infinity extends Coefficient {

    @Override
    public int compareTo(@NonNull Coefficient o) {
        return 1;
    }

    @Override
    public String toString() {
        return "\u221E";
    }

    @Override
    public Fraction getTotal() {
        return null;
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
