package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.CoefficientFactory;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.M;
import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Number;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
class TargetFunction extends Equation {

    private List<Coefficient> coefficients;
    private Coefficient value;

    private TargetFunction(List<Coefficient> coefficients, Coefficient value) {
        super(coefficients, value);
        this.coefficients = coefficients;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("F");
        Coefficient c;
        for (int i = 0; i < coefficients.size(); i++) {
            c = coefficients.get(i);
            if (!c.equals(CoefficientFactory.ZERO)) {
                sb.append(" ");
                if (c.abs().equals(CoefficientFactory.ONE)) {
                    sb.append(String.format("%s x%s",
                            (c.bigger(CoefficientFactory.ZERO)) ? "+" : "-", i + 1));
                } else {
                    sb.append(String.format("%s %sx%s",
                            (c.bigger(CoefficientFactory.ZERO)) ? "+" : "-", c.abs(), i + 1));
                }
            }
        }
        sb.append(" = ").append(value);
        return sb.toString();
    }

    int index(Coefficient c) {
        return coefficients.indexOf(c);
    }

    static TargetFunction createTargetFunction(List<Integer> sTargetFunctionCoefficients,
                                                      Integer sTargetFunctionConstant,
                                                      IsFakeVariablesList fakeVariables,
                                                      boolean isMax) {
        List<Coefficient> coefficients = new ArrayList<>();
        for (int i = 0; i < fakeVariables.size(); i++) {
            if (i < sTargetFunctionCoefficients.size()) {
                coefficients.add(new Number(sTargetFunctionCoefficients.get(i)).negate());
            } else {
                coefficients.add((fakeVariables.get(i)) ?
                        CoefficientFactory.M :
                        CoefficientFactory.ZERO);
            }
        }
        TargetFunction tf = new TargetFunction(coefficients,
                new Number(sTargetFunctionConstant));

        for (int i = 0; i < tf.size(); i++) {
            if (!(tf.getCoefficient(i) instanceof M) && isMax) {
                tf.setCoefficient(i , tf.getCoefficient(i).negate());
            }
        }
        if (isMax)
            tf.setValue(tf.getValue().negate());

        return tf;
    }

    static TargetFunction createTargetFunction(List<Coefficient> coefficients, Coefficient value) {
        return new TargetFunction(coefficients, value);
    }
}
