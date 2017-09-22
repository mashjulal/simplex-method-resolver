package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class stores information about
 * {@link Basis} values: index and coefficient.
 */
@ToString
@Getter
@Setter
class BasisValue implements Comparable<BasisValue> {

    private int index;
    private Coefficient coefficient;

    BasisValue(int index, Coefficient coefficient) {
        this.index = index;
        this.coefficient = coefficient;
    }

    @Override
    public int compareTo(@NonNull BasisValue o) {
        if (index > o.index)
            return 1;
        else if(index < o.index)
            return -1;
        return coefficient.compareTo(o.coefficient);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BasisValue && compareTo((BasisValue) o) == 0;
    }
}
