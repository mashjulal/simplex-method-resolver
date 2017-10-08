package com.mashjulal.android.simplexmethodresolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 26.09.17.
 */

public class InputRow {

    private List<Variable> mVariables;
    private String mSign;
    private Variable mValue;

    public InputRow() {
        this.mVariables = new ArrayList<>();
        this.mSign = "";
        this.mValue = new Variable();
    }

    public InputRow(List<Variable> variables, String sign, Variable value) {
        mVariables = variables;
        mSign = sign;
        mValue = value;
    }

    public void addVariable(Variable variable) {
        mVariables.add(variable);
    }
}
