package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import lombok.Getter;

/**
 * Class stores information about equation basis
 * values and perform operations with them.
 */
class Basis implements Iterable<BasisValue> {

    @Getter private List<BasisValue> basisValues;

    Basis(List<Equation> equationList) {
        basisValues = getFirstBasis(equationList);
        sort();
    }

    private static List<BasisValue> getFirstBasis(List<Equation> equationList) {
        List<BasisValue> bv = new ArrayList<>();
        List<BasisValue> coefs;
        final Coefficient ZERO = CoefficientFactory.ZERO;
        for (Equation equation : equationList) {
            coefs = new ArrayList<>();
            for (int coefficientIndex = 0; coefficientIndex < equation.size(); coefficientIndex++) {
                if (!equation.getCoefficients().get(coefficientIndex).equals(ZERO))
                    coefs.add(new BasisValue(coefficientIndex, equation.getValue()));
            }
            bv.add(coefs.get(coefs.size()-1));
        }
        return bv;
    }

    private void sort() {
        Collections.sort(basisValues,
                (bv1, bv2) -> Integer.compare(bv1.getIndex(), bv2.getIndex()));
    }

    void setBasisValue(int index, BasisValue basisValue) {
        basisValues.set(index, basisValue);
//        sort();
    }

    void recalculate(List<Equation> equationList) {
        List<BasisValue> basisValues = new ArrayList<>();
        Coefficient c;
        Coefficient value;

        for (int i = 0; i < this.basisValues.size(); i++) {
            BasisValue bv = this.basisValues.get(i);
            c = equationList.get(i).getCoefficients().get(bv.getIndex());
            value = equationList.get(i).getValue();
            basisValues.add(new BasisValue(
                    bv.getIndex(),
                    value.divide(c)));
        }
        this.basisValues = basisValues;
//        sort();
    }

    List<Integer> getIndexes() {
        List<Integer> indexes = new ArrayList<>();
        for (BasisValue bv : basisValues)
            indexes.add(bv.getIndex());
        return indexes;
    }

    Coefficient getCoefficient(int index) {
        for (BasisValue bv : basisValues) {
            if (bv.getIndex() == index)
                return bv.getCoefficient();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BasisValue bv : basisValues) {
            sb.append(String.format(Locale.getDefault(),
                    "x%d = %s", bv.getIndex() + 1, bv.getCoefficient().toString())).append(", ");
        }
        if (sb.length() != 0)
            sb.replace(sb.length()-2, sb.length(), "");
        return sb.toString();
    }

    @Override
    public Iterator<BasisValue> iterator() {
        return basisValues.iterator();
    }
}
