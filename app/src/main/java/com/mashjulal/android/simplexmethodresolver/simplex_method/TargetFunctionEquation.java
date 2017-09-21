package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


public class TargetFunctionEquation extends Equation {

    @Getter @Setter private List<Coefficient> coefficients;
    @Getter @Setter private Coefficient value;

    public TargetFunctionEquation(List<Coefficient> coefficients, Coefficient value) {
        super(coefficients, value);
        this.coefficients = coefficients;
        this.value = value;
    }

    @Override
    public String toString() {
        /**
         *  def __str__(self):
         rep = "F"
         for i, c in enumerate(self.get_coefficients()):
         if c != 0:
         rep += " {} {}x{}".format("+" if c > 0 else "-", abs(c), i+1)
         return rep + " = {}".format(self.get_value())
         */
        StringBuilder sb = new StringBuilder();
        sb.append("F");
        Coefficient c;
        for (int i = 0; i < coefficients.size(); i++) {
            c = coefficients.get(i);
            if (c.compareTo(0) != 0) {
                sb.append(" ").append(String.format("%s %sx%s",
                        (c.compareTo(0) > 0) ? "+" : "-", c.abs(), i + 1));
            }
        }
        sb.append(" =").append(value);
        return sb.toString();
    }

    public int index(Coefficient c) {
        /**
         *  def index(self, item):
         """
         Returns index of item.
         :param item: value.
         :return: index.
         """
         for i in range(len(self.get_coefficients())):
         if self.coefficients[i] == item:
         return i
         */
        return coefficients.indexOf(c);
    }
}
