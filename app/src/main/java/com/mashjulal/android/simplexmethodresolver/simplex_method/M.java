package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;


public class M  extends Coefficient implements Comparable<Coefficient>{

    /**
     *  # Big number constant
     __BIG_NUMBER = 100000000000000
     */
    private static final int BIG_NUMBER = 1000000000;

    private Fraction multiplier;
    private Fraction constant;

    public M(Fraction multiplier, Fraction constant) {
        /**
         *  def __init__(self, c, v):
         """
         Constructor.
         Creates big number of type cM + v.
         :param c: coefficient before M.
         :param v: value after M.
         """
         self.coefficient = Fraction(c)
         self.value = Fraction(v)
         */

        super(multiplier);
        this.multiplier = multiplier;
        this.constant = constant;
    }

    public M add(Fraction fraction) {
        /**
         *  def __add__(self, other):
         if isinstance(other, Fraction) or isinstance(other, int):
         return M(self.coefficient, self.value + other)
         elif isinstance(other, M):
         return M(self.coefficient + other.coefficient,
         self.value + other.value)
         else:
         raise RuntimeError("Unsupported other type: " + type(other))
         */
        return new M(multiplier, constant.add(fraction));
    }

    public M add(int number) {
        return new M(multiplier, constant.add(number));
    }

    public M add(M otherM) {
        return new M(multiplier.add(otherM.multiplier), constant.add(otherM.constant));
    }


    public M subtract(Fraction fraction) {
        /**
         *  def __sub__(self, other):
         if isinstance(other, Fraction):
         return M(self.coefficient, self.value - other)
         elif isinstance(other, M):
         return M(self.coefficient - other.coefficient,
         self.value - other.value)
         else:
         raise RuntimeError("Unsupported other type: " + type(other))
         */
        return new M(multiplier, constant.subtract(fraction));
    }

    public M subtract(int number) {
        return new M(multiplier, constant.subtract(number));
    }

    public M subtract(M otherM) {
        return new M(multiplier.subtract(otherM.multiplier), constant.subtract(otherM.constant));
    }

    public M multiply(Fraction fraction) {
        /**
         *  def __mul__(self, other):
         return M(self.coefficient * other, self.value * other)
         */
        return new M(multiplier.multiply(fraction), constant.multiply(fraction));
    }

    public M multiply(int number) {
        return new M(multiplier.multiply(number), constant.multiply(number));
    }

    public M divide(Fraction fraction) {
        /**
         *  def __truediv__(self, other):
         return M(self.coefficient / other, self.value / other)
         */
        return new M(multiplier.divide(fraction), constant.divide(fraction));
    }

    public M divide(int number) {
        return new M(multiplier.divide(number), constant.divide(number));
    }

    @Override
    public String toString() {
        /**
         *  def __str__(self):
         if self.coefficient != 0:
            if self.value != 0:
                if abs(self.coefficient) == 1:
                    return "({}M {} {})".format(
                        "" if self.coefficient > 0 else "-",
                        ["-", "+"][self.value > 0],
                        abs(self.value))
                else:
                    return "({}M {} {})".format(
                        self.coefficient,
                        ["-", "+"][self.value > 0],
                        abs(self.value))
            else:
                return "{}M".format(
                self.coefficient if self.coefficient != 1 else "")
         else:
            return str(format(abs(self.value)))
         */
        if (multiplier.compareTo(0) != 0) {
            if (constant.compareTo(0) != 0) {
                if (multiplier.abs().compareTo(1) == 0) {
                    return String.format(
                            "(%sM %s %s)",
                            (multiplier.compareTo(0) == 0) ? "" : "-",
                            (constant.compareTo(0) > 0) ? "-" : "+",
                            constant.abs().toString());
                } else {
                    return String.format(
                            "(%sM %s %s)",
                            multiplier.toString(),
                            (constant.compareTo(0) > 0) ? "-" : "+",
                            constant.abs().toString());
                }
            } else {
                return String.format("%sM", (multiplier.compareTo(1) > 0) ? multiplier : "");
            }
        } else {
            return constant.abs().toString();
        }
    }

    @Override
    public int compareTo(@NonNull Coefficient o) {
        /**
         *  def __eq__(self, other):
         if isinstance(other, Fraction) or isinstance(other, int):
         return self.get_total() == other
         elif isinstance(other, M):
         return self.get_total() == other.get_total()

         def __ne__(self, other):
         return not self.__eq__(other)

         def __lt__(self, other):
         if isinstance(other, Fraction) or isinstance(other, int):
         return self.get_total() < other
         elif isinstance(other, M):
         return self.get_total() < other.get_total()

         def __gt__(self, other):
         if isinstance(other, Fraction) or isinstance(other, int):
         return self.get_total() > other
         elif isinstance(other, M):
         return self.get_total() > other.get_total()

         def __le__(self, other):
         if isinstance(other, Fraction) or isinstance(other, int):
         return self.get_total() <= other
         elif isinstance(other, M):
         return self.get_total() <= other.get_total()

         def __ge__(self, other):
         if isinstance(other, Fraction) or isinstance(other, int):
         return self.get_total() >= other
         elif isinstance(other, M):
         return self.get_total() >= other.get_total()
         */
        return getTotal().compareTo(o.getTotal());
    }

    public M abs() {
        /**
         *  def __abs__(self):
         return self if self.coefficient >= 0 else -self
         */
        return (multiplier.compareTo(0) >= 0) ? this : this.negate();
    }

    public M negate() {
        /**
         *  def __neg__(self):
         return M(-self.coefficient, -self.value)
         */
        return new M(multiplier.negate(), constant.negate());
    }

    @Override
    public Fraction getTotal() {
        /**
         *  def get_total(self):
         """
         Returns total value of cM + v.
         :return: value of cM + v.
         """
         return M.__BIG_NUMBER * self.coefficient + self.value
         */
        return multiplier.multiply(BIG_NUMBER).add(constant);
    }
}
