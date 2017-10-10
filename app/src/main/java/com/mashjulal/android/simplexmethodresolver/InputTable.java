package com.mashjulal.android.simplexmethodresolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;


@Getter
@Accessors(prefix = "m")
class InputTable implements Iterable<InputRow> {

    private List<InputRow> mInput = new ArrayList<>();
    private int mVariablesCount = 2;

    InputTable(int size, int variablesCount) {
        mVariablesCount = variablesCount;
        setSize(size);
    }

    void setSize(int finalSize) {
        int size = mInput.size();
        if (finalSize > size)
            addRows(finalSize);
        else if (finalSize < size)
            removeRows(finalSize);
    }

    void setVariableCount(int finalSize) {
        mVariablesCount = finalSize;
        for (InputRow inputRow : mInput)
            inputRow.setSize(finalSize);
    }

    private void addRows(int finalSize) {
        while (mInput.size() < finalSize) {
            InputRow inputRow = new InputRow();
            for (int j = 0; j < mVariablesCount; j++)
                inputRow.addVariable(new Variable());
            mInput.add(inputRow);
        }
    }

    private void removeRows(int finalSize) {
        while (finalSize > mInput.size()) {
            mInput.remove(mInput.size() - 1);
        }
    }

    InputRow get(int i) {
        return mInput.get(i);
    }

    int size() {
        return mInput.size();
    }

        @Override
        public Iterator<InputRow> iterator() {
            return mInput.iterator();
        }
}
