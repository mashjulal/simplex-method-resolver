package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

class M extends Coefficient implements Comparable<Coefficient>{

    private static final int BIG_NUMBER = 10000;

    private Fraction multiplier;
    private Fraction constant;

    M(Fraction multiplier, Fraction constant) {
        super(multiplier);
        this.multiplier = multiplier;
        this.constant = constant;
    }

    M(int multiplier, Fraction constant) {
        this(new Fraction(multiplier), constant);
    }

    M(Fraction multiplier, int constant) {
        this(multiplier, new Fraction(constant));
    }

    M(int multiplier, int constant) {
        this(new Fraction(multiplier), new Fraction(constant));
    }

    M(Fraction multiplier) {
        this(multiplier, new Fraction(0));
    }

    M(int multiplier) {
        this(new Fraction(multiplier), new Fraction(0));
    }

    @Override
    Fraction getTotal() {
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

    M add(Fraction fraction) {
        return new M(multiplier, constant.add(fraction));
    }

    M add(Coefficient c) {
        return new M(multiplier, constant.add(c.getTotal()));
    }

    M add(int number) {
        return new M(multiplier, constant.add(number));
    }

    M add(M otherM) {
        return new M(multiplier.add(otherM.multiplier), constant.add(otherM.constant));
    }

    M subtract(Fraction fraction) {
        return new M(multiplier, constant.subtract(fraction));
    }

    M subtract(Coefficient c) {
        return new M(multiplier, constant.subtract(c.getTotal()));
    }

    M subtract(int number) {
        return new M(multiplier, constant.subtract(number));
    }

    M subtract(M otherM) {
        return new M(multiplier.subtract(otherM.multiplier), constant.subtract(otherM.constant));
    }

    M multiply(Fraction fraction) {
        return new M(multiplier.multiply(fraction), constant.multiply(fraction));
    }

    M multiply(Coefficient c) {
        return new M(multiplier.multiply(c.getTotal()), constant.multiply(c.getTotal()));
    }

    M multiply(int number) {
        return new M(multiplier.multiply(number), constant.multiply(number));
    }

    M divide(Fraction fraction) {
        return new M(multiplier.divide(fraction), constant.divide(fraction));
    }

    M divide(Coefficient c) {
        return new M(multiplier.divide(c.getTotal()), constant.divide(c.getTotal()));
    }

    M divide(int number) {
        return new M(multiplier.divide(number), constant.divide(number));
    }

    M abs() {
        return (multiplier.compareTo(Fraction.ZERO) >= 0) ? this : this.negate();
    }

    M negate() {
        return new M(multiplier.negate(), constant.negate());
    }
}
