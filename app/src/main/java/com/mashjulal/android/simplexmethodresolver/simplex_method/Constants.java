package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Infinity;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.M;

public class Constants {

    public static class Coefficients {
        public static final Coefficient ZERO = new Number(0);
        public static final Coefficient ONE = new Number(1);
        public static final Coefficient MINUS_ONE = new Number(-1);
        public static final Coefficient INFINITY = new Infinity();
        public static final Coefficient M = new M(1);
        public static final Coefficient MINUS_M = new M(-1);
    }

    public static class ComparisonSigns {
        public static final String SIGN_BIGGER = ">";
        public static final String SIGN_BIGGER_EQUALS = ">=";
        public static final String SIGN_EQUALS = "=";
        public static final String SIGN_LESS_EQUALS = "<=";
        public static final String SIGN_LESS = "<";
    }

}
