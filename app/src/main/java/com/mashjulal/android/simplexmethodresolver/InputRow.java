package com.mashjulal.android.simplexmethodresolver;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@Getter
class InputRow {

    private List<Variable> mVariables;
    private String mSign;
    private Variable mValue;

    InputRow() {
        mVariables = new ArrayList<>();
        mSign = "";
        mValue = new Variable();
    }

    public InputRow(List<Variable> variables, String sign, Variable value) {
        mVariables = variables;
        mSign = sign;
        mValue = value;
    }

    void addVariable(Variable variable) {
        mVariables.add(variable);
    }

    int size() {
        return mVariables.size();
    }

    void removeLastVariable() {
        mVariables.remove(size()-1);
    }
}
