package com.mashjulal.android.simplexmethodresolver.simplex_method;

/**
 * Created by Master on 18.09.2017.
 */

import lombok.Getter;
import lombok.Setter;

/**
 * Class stores information about
 * {@link Basis} values: index and coefficient.
 */
public class BasisValue {

    @Getter @Setter private int index;

    @Getter @Setter private Coefficient coefficient;

    public BasisValue(int index, Coefficient coefficient) {
        this.index = index;
        this.coefficient = coefficient;
    }
}
