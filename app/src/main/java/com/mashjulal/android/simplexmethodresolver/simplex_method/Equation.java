package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
class Equation implements Iterable<Coefficient>, Comparable<Equation> {

    private List<Coefficient> coefficients;
    private Coefficient value;

    Equation(List<Coefficient> coefficients, Coefficient value) {
        this.coefficients = coefficients;
        this.value = value;
    }

    int size() {
        return coefficients.size();
    }

    Equation add(Coefficient coefficient) {
        return new Equation(coefficients, value.add(coefficient));
    }

    Equation add(Equation equationOther) {
        List<Coefficient> cofTotalList = new ArrayList<>();
        Coefficient cofThis, cofOther;
        for (int i = 0; i < Math.max(size(), equationOther.size()); i++) {
            cofThis = (i < size()) ?
                    coefficients.get(i) :
                    CoefficientFactory.ZERO;
            cofOther = (i < equationOther.size()) ?
                    equationOther.getCoefficient(i) :
                    CoefficientFactory.ZERO;
            cofTotalList.add(cofThis.add(cofOther));
        }
        Coefficient valueTotal = value.add(equationOther.getValue());
        return new Equation(cofTotalList, valueTotal);
    }

    Equation subtract(Coefficient coefficient) {
        return new Equation(coefficients, value.subtract(coefficient));
    }

    Equation subtract(Equation equationOther) {
        List<Coefficient> cofTotalList = new ArrayList<>();
        Coefficient cofThis, cofOther;
        for (int i = 0; i < Math.max(size(), equationOther.size()); i++) {
            cofThis = (i < size()) ?
                    coefficients.get(i) :
                    CoefficientFactory.ZERO;
            cofOther = (i < equationOther.size()) ?
                    equationOther.getCoefficient(i) :
                    CoefficientFactory.ZERO;
            cofTotalList.add(cofThis.subtract(cofOther));
        }
        Coefficient valueTotal = value.subtract(equationOther.getValue());
        return new Equation(cofTotalList, valueTotal);
    }

    Equation multiply(Coefficient coefficient) {
        List<Coefficient> cofTotalList = new ArrayList<>();
        for (Coefficient cof : coefficients) {
            cofTotalList.add(cof.multiply(coefficient));
        }
        Coefficient valueTotal = value.multiply(coefficient);
        return new Equation(cofTotalList, valueTotal);
    }

    Equation divide(Coefficient coefficient) {
        if (coefficient.equals(CoefficientFactory.ZERO))
            throw new IllegalArgumentException("Division by zero!");

        List<Coefficient> cofTotalList = new ArrayList<>();
        for (Coefficient cof : coefficients) {
            cofTotalList.add(cof.divide(coefficient));
        }
        Coefficient valueTotal = value.divide(coefficient);
        return new Equation(cofTotalList, valueTotal);
    }

    Equation negate() {
        List<Coefficient> cofTotalList = new ArrayList<>();
        for (Coefficient cof : coefficients) {
            cofTotalList.add(cof.negate());
        }
        Coefficient valueTotal = value.negate();
        return new Equation(cofTotalList, valueTotal);
    }

    Coefficient getCoefficient(int i) {
        return coefficients.get(i);
    }

    void remove(int index) {
        if (index >= size())
            return;

        List<Coefficient> cofs = new ArrayList<>();
        for (int i = 0; i < size(); i++)
            if (i != index)
                cofs.add(coefficients.get(i));
        coefficients = cofs;
    }

    void setCoefficient(int i, Coefficient value) {
        coefficients.set(i, value);
    }

    void addCoefficient(Coefficient coefficient) {
        coefficients.add(coefficient);
    }

    Equation express(int cofIndex) {
        Coefficient cof = coefficients.get(cofIndex);
        if (cof.equals(CoefficientFactory.ZERO))
            throw new IllegalArgumentException("Division by zero!");

        List<Coefficient> cofTotalList = new ArrayList<>();
        for (int i = 0; i < coefficients.size(); i++) {
            if (i == cofIndex) {
                cofTotalList.add(CoefficientFactory.ZERO);
            } else {
                cofTotalList.add(coefficients.get(i).negate().divide(cof));
            }
        }
        Coefficient valueTotal = (!cof.equals(CoefficientFactory.ZERO)) ? value.negate().divide(cof) :
                CoefficientFactory.ZERO;
        return new Equation(cofTotalList, valueTotal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Coefficient cof;
        for (int i = 0; i < coefficients.size(); i++) {
            cof = coefficients.get(i);
            if (!cof.equals(CoefficientFactory.ZERO)) {
                sb.append((cof.bigger(CoefficientFactory.ZERO)) ? "+" : "-");
                if (!cof.abs().equals(CoefficientFactory.ONE))
                    sb.append(cof.abs().toString());
                sb.append(String.format(Locale.getDefault(), "x%d", i + 1));
            }
        }
        if (sb.length() != 0 && sb.substring(0, 1).equals("+"))
            sb.delete(0, 1);
        sb.append(String.format(Locale.getDefault(), "=%s", value.toString()));
        return sb.toString();
    }

    @Override
    public Iterator<Coefficient> iterator() {
        List<Coefficient> cofs = new ArrayList<>();
        cofs.addAll(coefficients);
        return cofs.iterator();
    }

    @Override
    public int compareTo(@NonNull Equation o) {
        if (size() != o.size())
            return Integer.compare(size(), o.size());

        for (int i = 0; i < size(); i++) {
            if (getCoefficient(i).compareTo(o.getCoefficient(i)) != 0)
                return -1;
        }
        if (value != o.value)
            return value.compareTo(o.value);
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Equation && compareTo((Equation) o) == 0;
    }
}
