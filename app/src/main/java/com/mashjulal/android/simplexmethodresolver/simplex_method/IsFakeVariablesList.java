package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.ArrayList;
import java.util.List;


public class IsFakeVariablesList {

    private List<Boolean> isFakeVariableList = new ArrayList<>();

    public IsFakeVariablesList(final int equationSize, List<String> comparisonSigns) {
        this.isFakeVariableList = createFakeVariables(equationSize, comparisonSigns);
    }

    private static List<Boolean> createFakeVariables(final int equationSize, List<String> comparisonSigns) {
        List<Boolean> fv1 = new ArrayList<>();
        for (int i = 0; i < equationSize; i++) {
            fv1.add(Boolean.FALSE);
        }

        List<Boolean> fv2 = new ArrayList<>();
        for (String sign : comparisonSigns) {
            if (!sign.equals(Constants.ComparisonSigns.SIGN_EQUALS))
                fv2.add(Boolean.FALSE);
        }
        List<Boolean> fv3 = new ArrayList<>();
        for (String sign : comparisonSigns) {
            if (sign.equals(Constants.ComparisonSigns.SIGN_BIGGER_EQUALS)) {
                fv3.add(Boolean.TRUE);
            }
        }
        Boolean fv4 = Boolean.FALSE;

        List<Boolean> fakeVariables = new ArrayList<>();
        fakeVariables.addAll(fv1);
        fakeVariables.addAll(fv2);
        fakeVariables.addAll(fv3);
        fakeVariables.add(fv4);

        return fakeVariables;
    }

    public int size() {
        return isFakeVariableList.size();
    }

    public Boolean get(int index) {
        return isFakeVariableList.get(index);
    }

    public Boolean contains(Boolean b) {
        return isFakeVariableList.contains(b);
    }

    public void remove(int index) {
        isFakeVariableList.remove(index);
    }

    public int indexOf(Boolean b) {
        return isFakeVariableList.indexOf(b);
    }
}
