package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import android.support.annotation.NonNull;

abstract public class Coefficient implements Comparable<Coefficient> {

    @Override
    public boolean equals(Object o) {
        return o instanceof Coefficient && compareTo((Coefficient) o) == 0;
    }

    @Override
    public int compareTo(@NonNull Coefficient o) {
        return getTotal().compareTo(o.getTotal());
    }

    @Override
    abstract public String toString();

    abstract public Fraction getTotal();

    abstract public Coefficient add(Coefficient coefficientOther);

    abstract public Coefficient subtract(Coefficient coefficientOther);

    abstract public Coefficient multiply(Coefficient coefficientOther);

    abstract public Coefficient divide(Coefficient coefficientOther);

    abstract public Coefficient negate();

    abstract public Coefficient abs();

    public Boolean biggerEquals(Coefficient c) {
        return compareTo(c) >= 0;
    }

    public Boolean bigger(Coefficient c) {
        return compareTo(c) > 0;
    }

    public Boolean equals(Coefficient c) {
        return compareTo(c) == 0;
    }

    public Boolean less(Coefficient c) {
        return compareTo(c) < 0;
    }

    public Boolean lessEquals(Coefficient c) {
        return compareTo(c) <= 0;
    }

}
