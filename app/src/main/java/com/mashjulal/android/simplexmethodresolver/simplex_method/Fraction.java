package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.math.BigInteger;
import java.util.Locale;

public class Fraction {

    private BigInteger numerator;
    private BigInteger denominator;

    private Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.__reduce();
    }

    public Fraction(int numerator, int denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public Fraction(int numerator) {
        this(BigInteger.valueOf(numerator), BigInteger.ONE);
    }

    private void __reduce() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        this.numerator = this.numerator.divide(gcd);
        this.denominator = this.denominator.divide(gcd);
    }

    public Fraction add(Fraction fractionOther) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        if (fractionNew.denominator.equals(fractionOther.denominator)) {
            fractionNew.numerator = fractionNew.numerator.add(fractionOther.numerator);
        } else {
            BigInteger gcd = fractionNew.denominator.gcd(fractionOther.denominator);
            BigInteger lcm = fractionNew.denominator
                    .multiply(fractionOther.denominator.divide(gcd));
            BigInteger newNumeratorThis = fractionNew.numerator
                    .multiply(lcm.divide(fractionNew.denominator));
            BigInteger newNumeratorOther = fractionOther.numerator
                    .multiply(lcm.divide(fractionOther.denominator));
            fractionNew.numerator = newNumeratorThis.add(newNumeratorOther);
            fractionNew.denominator = lcm;
        }
        return fractionNew.reduce();
    }

    public Fraction subtract(Fraction fractionOther) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        if (fractionNew.denominator.equals(fractionOther.denominator)) {
            fractionNew.numerator = fractionNew.numerator.subtract(fractionOther.numerator);
        } else {
            BigInteger gcd = fractionNew.denominator.gcd(fractionOther.denominator);
            BigInteger lcm = fractionNew.denominator
                    .multiply(fractionOther.denominator.divide(gcd));
            BigInteger newNumeratorThis = fractionNew.numerator
                    .multiply(lcm.divide(fractionNew.denominator));
            BigInteger newNumeratorOther = fractionOther.numerator
                    .multiply(lcm.divide(fractionOther.denominator));
            fractionNew.numerator = newNumeratorThis.subtract(newNumeratorOther);
            fractionNew.denominator = lcm;
        }
        return fractionNew.reduce();
    }

    public Fraction multiply(Fraction fractionOther) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        fractionNew.numerator = fractionNew.numerator.multiply(fractionOther.numerator);
        fractionNew.denominator = fractionNew.denominator.multiply(fractionOther.denominator);

        return fractionNew.reduce();
    }

    public Fraction divide(Fraction fractionOther) {
        if (fractionOther.numerator.equals(BigInteger.ZERO))
            throw new IllegalArgumentException("Division by zero!");

        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        fractionNew.numerator = fractionNew.numerator.multiply(fractionOther.denominator);
        fractionNew.denominator = fractionNew.denominator.multiply(fractionOther.numerator);

        if (fractionNew.denominator.compareTo(BigInteger.ZERO) < 0) {
            fractionNew.numerator = fractionNew.numerator.negate();
            fractionNew.denominator = fractionNew.denominator.negate();
        }
        return fractionNew.reduce();
    }

    public Fraction reduce() {
        Fraction fraction = new Fraction(this.numerator, this.denominator);
        fraction.__reduce();
        return fraction;
    }

    public double toDouble() {
        return numerator.intValue() * 1.0 / denominator.intValue();
    }

    @Override
    public String toString() {
        String str = String.format(Locale.getDefault(), "%d", this.numerator);
        if (!this.denominator.equals(BigInteger.ONE))
            str += String.format(Locale.getDefault(), "/%d", this.denominator);
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fraction))
            return false;
        Fraction other = (Fraction) obj;
        return this.numerator.equals(other.numerator) &&
                this.denominator.equals(other.denominator);
    }
}
