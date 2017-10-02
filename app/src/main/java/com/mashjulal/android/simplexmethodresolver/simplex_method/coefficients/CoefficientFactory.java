package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;


public class CoefficientFactory {

    public static final Coefficient ZERO = new Number(0);
    public static final Coefficient ONE = new Number(1);
    public static final Coefficient MINUS_ONE = new Number(-1);
    public static final Coefficient INFINITY = new Infinity();
    public static final Coefficient M = new M(1);
    public static final Coefficient MINUS_M = new M(-1);
}
