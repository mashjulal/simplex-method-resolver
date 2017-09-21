package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;


public class Equation implements Iterable<Coefficient> {

    @Getter @Setter private List<Coefficient> coefficients;
    @Getter @Setter private Coefficient value;

    public Equation(List<Coefficient> coefficients, Coefficient value) {
        this.coefficients = coefficients;
        this.value = value;
    }

    public int size() {
        return coefficients.size() + 1;
    }

    public Equation add(Coefficient coefficient) {
        /**
         *  if isinstance(other, Coefficient):
         return Equation(
         self.get_coefficients() + [self.get_value() + other])
         */
        return new Equation(coefficients, value.add(coefficient));
    }

    public Equation add(Equation equationOther) {
        /**
         *          elif isinstance(other, Equation):
         return Equation(
         [c + other[i] for i, c in enumerate(self.coefficients)])
         */
        List<Coefficient> cofTotalList = new ArrayList<>();
        Coefficient cofThis, cofOther;
        for (int i = 0; i < size(); i++) {
            cofThis = coefficients.get(i);
            cofOther = equationOther.getCoefficient(i);
            cofTotalList.add(cofThis.add(cofOther));
        }
        Coefficient valueTotal = value.add(equationOther.getValue());
        return new Equation(cofTotalList, valueTotal);
    }

    public Equation subtract(Coefficient coefficient) {
        /**
         *  def __sub__(self, other):
           if isinstance(other, Coefficient):
     return Equation(
         self.get_coefficients() + [self.get_value() - other])
         */
        return new Equation(coefficients, value.subtract(coefficient));
    }

    public Equation subtract(Equation equationOther) {
        /**
         *  elif isinstance(other, Equation):
         return Equation(
         [c - other[i] for i, c in enumerate(self.coefficients)])
         */
        List<Coefficient> cofTotalList = new ArrayList<>();
        Coefficient cofThis, cofOther;
        for (int i = 0; i < size(); i++) {
            cofThis = coefficients.get(i);
            cofOther = equationOther.getCoefficient(i);
            cofTotalList.add(cofThis.subtract(cofOther));
        }
        Coefficient valueTotal = value.subtract(equationOther.getValue());
        return new Equation(cofTotalList, valueTotal);
    }

    public Equation multiply(Coefficient coefficient) {
        /**
         *  def __mul__(self, other):
         if isinstance(other, Coefficient):
         return Equation(
         [other * c for c in self.coefficients])
         else:
         raise RuntimeError("Unsupported operation.")
         */
        List<Coefficient> cofTotalList = new ArrayList<>();
        for (Coefficient cof : coefficients) {
            cofTotalList.add(cof.multiply(coefficient));
        }
        Coefficient valueTotal = value.multiply(coefficient);
        return new Equation(cofTotalList, valueTotal);
    }

    public Equation divide(Coefficient coefficient) {
        /**
         * def __truediv__(self, other):
         if isinstance(other, Coefficient):
         return Equation(
         [c / other for c in self.coefficients])
         else:
         raise RuntimeError("Unsupported operation.")
         */
        List<Coefficient> cofTotalList = new ArrayList<>();
        for (Coefficient cof : coefficients) {
            cofTotalList.add(cof.multiply(coefficient));
        }
        Coefficient valueTotal = value.divide(coefficient);
        return new Equation(cofTotalList, valueTotal);
    }

    public Equation negate() {
        /**
         *  def __neg__(self):
         return Equation([-c for c in self.coefficients])
         */
        List<Coefficient> cofTotalList = new ArrayList<>();
        for (Coefficient cof : coefficients) {
            cofTotalList.add(cof.negate());
        }
        Coefficient valueTotal = value.negate();
        return new Equation(cofTotalList, valueTotal);
    }

    public Coefficient getCoefficient(int i) {
        /**
         *  def __getitem__(self, item):
         return self.coefficients[item]
         */
        return coefficients.get(i);
    }

    public void remove(int i) {
        /**
         *  def __delitem__(self, key):
         del self.coefficients[key]
         */
        coefficients.remove(i);
    }

    public void setCoefficient(int i, Coefficient value) {
        /**
         *  def __setitem__(self, key, value):
         self.coefficients[key] = value
         */
        coefficients.set(i, value);
    }

    public void addCoefficient(Coefficient coefficient) {
        /**
         *  def add_coefficient(self, c):
         """
         Adds coefficient to end of coefficient list.
         :param c: variable coefficient.
         :return: None.
         """
         self.coefficients.append(c)
         */
        coefficients.add(coefficient);
    }

    public Equation express(int cofIndex) {
        /**
         *  def express(self, indx):
         """
         Returns expressed equation where each coefficient
         is divided by coefficient with index indx.
         :param indx: index of coefficient.
         :return: expressed equation.
         """
         r_c = self.coefficients[indx]
         return Equation([-c / r_c if i != indx else 0
         for i, c in enumerate(self.coefficients)])
         */
        List<Coefficient> cofTotalList = new ArrayList<>();
        Coefficient cof = coefficients.get(cofIndex);
        for (int i = 0; i < coefficients.size(); i++) {
            if (i != cofIndex) {
                cofTotalList.add(coefficients.get(i).negate().divide(cof));
            }
        }
        Coefficient valueTotal = value.negate().divide(cof);
        return new Equation(cofTotalList, valueTotal);
    }

    @Override
    public String toString() {
        /**
         *  def __str__(self):
         rep = ""
         for i, c in enumerate(self.get_coefficients()):
            if c != 0:
                rep += "{} ".format("+" if c > 0 else "-")
                if abs(c) != 1:
                    rep += "{}".format(abs(c))
                rep += "x{} ".format(i + 1)
         if rep[0] == "+":
            rep = rep[2:]
         return rep + "= {}".format(self.get_value())
         */
        StringBuilder sb = new StringBuilder();
        Coefficient cof;
        for (int i = 0; i < coefficients.size(); i++) {
            cof = coefficients.get(i);
            if (!cof.equals(new Coefficient(0))) {
                sb.append((cof.compareTo(0) > 0) ? "+" : "-");
                if (cof.abs().compareTo(1) != 0)
                    sb.append(cof.abs().toString());
                sb.append(String.format(Locale.getDefault(), "x%d ", i + 1));
            }
        }
        if (sb.substring(0, 1).equals("+"))
            sb.delete(0, 2);
        return sb.toString();
    }

    @Override
    public Iterator<Coefficient> iterator() {
        List<Coefficient> cofs = new ArrayList<>();
        Collections.copy(cofs, coefficients);
        cofs.add(value);
        return cofs.iterator();
    }
}
