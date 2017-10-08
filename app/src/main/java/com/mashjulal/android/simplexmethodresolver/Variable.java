package com.mashjulal.android.simplexmethodresolver;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by master on 26.09.17.
 */

public class Variable extends Element {

    @Getter @Setter private Integer value;

    public Variable() {
        this.value = 0;
    }

    public Variable(Integer value) {
        this.value = value;
    }
}
