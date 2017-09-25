package com.mashjulal.android.simplexmethodresolver.simplex_method;

import com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients.Coefficient;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
class TargetFunction extends Equation {

    private List<Coefficient> coefficients;
    private Coefficient value;

    TargetFunction(List<Coefficient> coefficients, Coefficient value) {
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
            if (c.compareTo(Coefficient.ZERO) != 0) {
                sb.append(" ");
                if (c.abs().compareTo(Coefficient.ONE) == 0) {
                    sb.append(String.format("%s x%s",
                            (c.compareTo(Coefficient.ZERO) > 0) ? "+" : "-", i + 1));
                } else {
                    sb.append(String.format("%s %sx%s",
                            (c.compareTo(Coefficient.ZERO) > 0) ? "+" : "-", c.abs(), i + 1));
                }
            }
        }
        sb.append(" = ").append(value);
        return sb.toString();
    }

    int index(Coefficient c) {
        return coefficients.indexOf(c);
    }
}
