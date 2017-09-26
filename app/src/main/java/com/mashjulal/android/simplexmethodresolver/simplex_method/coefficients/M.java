package com.mashjulal.android.simplexmethodresolver.simplex_method.coefficients;

import lombok.Getter;

public class M extends Coefficient {

    private static final int BIG_NUMBER = 1000000;

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
    public Fraction getTotal() {
        return multiplier.multiply(BIG_NUMBER).add(constant);
    }

    @Override
    public M add(Coefficient o) {
        if (o instanceof Number)
            return add((Number) o);
        else if (o instanceof M)
            return add((M) o);
        throw new UnsupportedOperationException();
    }

    private M add(Number o) {
        return new M(multiplier, constant.add(o.getMultiplier()));
    }

    private M add(M o) {
        return new M(multiplier.add(o.multiplier), constant.add(o.constant));
    }

    public M subtract(Fraction fraction) {
        return new M(multiplier, constant.subtract(fraction));
    }

    @Override
    public M subtract(Coefficient o) {
        if (o instanceof Number)
            return subtract((Number) o);
        else if (o instanceof M)
            return subtract((M) o);
        throw new UnsupportedOperationException();
    }

    public M subtract(Number o) {
        return new M(multiplier, constant.subtract(o.getMultiplier()));
    }

    public M subtract(M o) {
        return new M(multiplier.subtract(o.multiplier), constant.subtract(o.constant));
    }

    @Override
    public M multiply(Coefficient o) {
        if (o instanceof Number)
            return multiply((Number) o);
        throw new UnsupportedOperationException();
    }

    public M multiply(Number o) {
        return new M(multiplier.multiply(o.getMultiplier()), constant.multiply(o.getMultiplier()));
    }

    @Override
    public M divide(Coefficient o) {
        if (o instanceof Number)
            return multiply((Number) o);
        throw new UnsupportedOperationException();
    }

    public M divide(Number o) {
        return new M(multiplier.divide(o.getMultiplier()), constant.divide(o.getMultiplier()));
    }

    public M abs() {
        return (multiplier.compareTo(Fraction.ZERO) >= 0) ? this : this.negate();
    }

    public M negate() {
        return new M(multiplier.negate(), constant.negate());
    }
}
