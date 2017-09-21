package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

/**
 * Created by master on 19.09.17.
 */

public class Coefficient implements Comparable<Coefficient> {

    private Fraction multiplier;

    public Coefficient(Fraction multiplier) {
        this.multiplier = multiplier;
    }

    public Coefficient(int number) {
        this(new Fraction(number));
    }

    public Fraction getTotal() {
        return multiplier;
    }

    public Coefficient add(Coefficient coefficientOther) {
        /**
         *          elif isinstance(other, Equation):
         return Equation(
         [c + other[i] for i, c in enumerate(self.coefficients)])
         */
        return new Coefficient(multiplier.add(coefficientOther.multiplier));
    }

    public Coefficient subtract(Coefficient coefficientOther) {
        /**
         *  def __sub__(self, other):
         if isinstance(other, Coefficient):
         return Equation(
         self.get_coefficients() + [self.get_value() - other])
         */
        return new Coefficient(multiplier.subtract(coefficientOther.multiplier));
    }

    public Coefficient multiply(Coefficient coefficientOther) {
        /**
         *  def __mul__(self, other):
         if isinstance(other, Coefficient):
         return Equation(
         [other * c for c in self.coefficients])
         else:
         raise RuntimeError("Unsupported operation.")
         */
        return new Coefficient(multiplier.multiply(coefficientOther.multiplier));
    }

    public Coefficient divide(Coefficient coefficientOther) {
        /**
         * def __truediv__(self, other):
         if isinstance(other, Coefficient):
         return Equation(
         [c / other for c in self.coefficients])
         else:
         raise RuntimeError("Unsupported operation.")
         */
        return new Coefficient(multiplier.divide(coefficientOther.multiplier));
    }

    public Coefficient negate() {
        return new Coefficient(multiplier.negate());
    }

    public Coefficient abs() {
        return new Coefficient(multiplier.abs());
    }

    @Override
    public int compareTo(@NonNull Coefficient o) {
        return multiplier.compareTo(o.multiplier);
    }

    public int compareTo(int o) {
        return compareTo(new Coefficient(o));
    }
}
