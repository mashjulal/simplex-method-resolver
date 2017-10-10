package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class EstimatedAttitude implements Iterable<Coefficient> {

    private List<Coefficient> coefficients = new ArrayList<>();

    EstimatedAttitude(List<Equation> equations, int equationIndex) {
        for (Equation e : equations) {
            Coefficient value = e.getValue(), elem = e.getCoefficient(equationIndex);

            boolean elemLessEqualsZero = elem.lessEquals(Constants.Coefficients.ZERO);
            boolean valueLessZero = value.less(Constants.Coefficients.ZERO);
            boolean elemBiggerZero = !elemLessEqualsZero;
            boolean valueBiggerZero = value.bigger(Constants.Coefficients.ZERO);
            boolean elemLessZero = elem.less(Constants.Coefficients.ZERO);

            if (elemLessEqualsZero
                    || (valueLessZero && elemBiggerZero)
                    || (valueBiggerZero && elemLessZero)) {
                coefficients.add(Constants.Coefficients.INFINITY);
            } else {
                coefficients.add(value.divide(elem));
            }
        }
    }

    Coefficient get(int i) {
        return coefficients.get(i);
    }

    @Override
    public Iterator<Coefficient> iterator() {
        return coefficients.iterator();
    }

    Coefficient min() {
        Coefficient minEstAtt = coefficients.get(0);
        for (Coefficient c : coefficients)
            if (minEstAtt.bigger(c))
                minEstAtt = c;
        return minEstAtt;
    }

    int indexOf(Coefficient c) {
        return coefficients.indexOf(c);
    }
}
