package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;


public class CoefficientFactory {

    public static Coefficient getZero() {
        return new Number(0);
    }

    public static Coefficient getOne() {
        return new Number(1);
    }

    public static Coefficient getMinusOne() {
        return new Number(-1);
    }

    public static Coefficient getInfinity() {
        return new Infinity();
    }

    public static Coefficient getOneM() {
        return new M(1);
    }

    public static Coefficient getMinusOneM() {
        return new M(-1);
    }
}
