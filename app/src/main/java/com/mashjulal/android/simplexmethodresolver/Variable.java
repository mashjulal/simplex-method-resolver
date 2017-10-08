package com.mashjulal.android.simplexmethodresolver;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(prefix = "m")
class Variable{

    private Integer mValue;

    Variable() {
        mValue = 0;
    }

    @Override
    public String toString() {
        return mValue.toString();
    }
}
