package com.mashjulal.android.simplexmethodresolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@Getter
@Setter
class InputRow implements Iterable<Variable> {

    private List<Variable> mVariables;
    private String mSign;
    private Variable mValue;

    InputRow() {
        mVariables = new ArrayList<>();
        mSign = "";
        mValue = new Variable();
    }

    void addVariable(Variable variable) {
        mVariables.add(variable);
    }

    private int size() {
        return mVariables.size();
    }

    private void removeLastVariable() {
        mVariables.remove(size()-1);
    }

    void setSize(int finalSize) {
        int size = mVariables.size();
        if (finalSize > size)
            addVariables(finalSize);
        else if (finalSize < size)
            removeVariables(finalSize);
    }

    private void addVariables(int finalSize) {
        while (mVariables.size() < finalSize)
                addVariable(new Variable());
    }

    private void removeVariables(int finalSize) {
        while (mVariables.size() > finalSize) {
            removeLastVariable();
        }
    }

    @Override
    public Iterator<Variable> iterator() {
        return mVariables.iterator();
    }
}
