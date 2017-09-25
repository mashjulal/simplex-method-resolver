package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import android.support.annotation.NonNull;

import lombok.Getter;

public class M extends Coefficient {

    private static final int BIG_NUMBER = 10000;

    @Getter private Fraction multiplier;
    @Getter private Fraction constant;

    public M(Fraction multiplier, Fraction constant) {
        this.multiplier = multiplier;
        this.constant = constant;
    }

    public M(int multiplier, Fraction constant) {
        this(new Fraction(multiplier), constant);
    }

    public M(Fraction multiplier, int constant) {
        this(multiplier, new Fraction(constant));
    }

    public M(int multiplier, int constant) {
        this(new Fraction(multiplier), new Fraction(constant));
    }

    public M(Fraction multiplier) {
        this(multiplier, new Fraction(0));
    }

    public M(int multiplier) {
        this(new Fraction(multiplier), new Fraction(0));
    }

    @Override
    public Fraction getTotal() {
        return multiplier.multiply(BIG_NUMBER).add(constant);
    }

    @Override
    public String toString() {
        if (multiplier.compareTo(Fraction.ZERO) != 0) {
            if (constant.compareTo(Fraction.ZERO) != 0) {
                if (multiplier.abs().compareTo(Fraction.ONE) == 0) {
                    return String.format(
                            "(%sM %s %s)",
                            (multiplier.compareTo(Fraction.ONE) == 0) ? "" : "-",
                            (constant.compareTo(Fraction.ZERO) > 0) ? "+" : "-",
                            constant.abs().toString());
                } else {
                    return String.format(
                            "(%sM %s %s)",
                            multiplier.toString(),
                            (constant.compareTo(Fraction.ZERO) > 0) ? "+" : "-",
                            constant.abs().toString());
                }
            } else {
                if (multiplier.abs().compareTo(Fraction.ONE) == 0) {
                    return String.format("%sM", (multiplier.compareTo(Fraction.ONE) == 0) ? "" : "-");
                } else {
                    return String.format("%sM", multiplier);
                }

            }
        } else {
            return constant.abs().toString();
        }
    }

    @Override
    public int compareTo(@NonNull Coefficient o) {
        return getTotal().compareTo(o.getTotal());
    }

    public M add(Fraction fraction) {
        return new M(multiplier, constant.add(fraction));
    }

    public M add(Coefficient c) {
        return new M(multiplier, constant.add(c.getTotal()));
    }

    public M add(int number) {
        return new M(multiplier, constant.add(number));
    }

    public M add(M otherM) {
        return new M(multiplier.add(otherM.multiplier), constant.add(otherM.constant));
    }

    public M subtract(Fraction fraction) {
        return new M(multiplier, constant.subtract(fraction));
    }

    public M subtract(Coefficient c) {
        return new M(multiplier, constant.subtract(c.getTotal()));
    }

    public M subtract(int number) {
        return new M(multiplier, constant.subtract(number));
    }

    public M subtract(M otherM) {
        return new M(multiplier.subtract(otherM.multiplier), constant.subtract(otherM.constant));
    }

    public M multiply(Fraction fraction) {
        return new M(multiplier.multiply(fraction), constant.multiply(fraction));
    }

    public M multiply(Coefficient c) {
        return new M(multiplier.multiply(c.getTotal()), constant.multiply(c.getTotal()));
    }

    public M multiply(int number) {
        return new M(multiplier.multiply(number), constant.multiply(number));
    }

    public M divide(Fraction fraction) {
        return new M(multiplier.divide(fraction), constant.divide(fraction));
    }

    public M divide(Coefficient c) {
        return new M(multiplier.divide(c.getTotal()), constant.divide(c.getTotal()));
    }

    public M divide(int number) {
        return new M(multiplier.divide(number), constant.divide(number));
    }

    public M abs() {
        return (multiplier.compareTo(Fraction.ZERO) >= 0) ? this : this.negate();
    }

    public M negate() {
        return new M(multiplier.negate(), constant.negate());
    }
}
