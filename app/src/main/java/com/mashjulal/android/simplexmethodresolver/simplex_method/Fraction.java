package com.mashjulal.android.simplexmethodresolver.simplex_method;

import java.math.BigInteger;
import java.util.Locale;

/**
 * Created by Master on 17.09.2017.
 */

public class Fraction {

    private BigInteger numerator;
    private BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
    }

    public Fraction(BigInteger numerator) {
        this.numerator = numerator;
        this.denominator = BigInteger.ONE;
    }

    public Fraction(int numerator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.ONE;
    }

    public Fraction add(Fraction otherFraction) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        if (fractionNew.denominator.equals(otherFraction.denominator)) {
            fractionNew.numerator = fractionNew.numerator.add(otherFraction.numerator);
        } else {
            BigInteger gcd = fractionNew.denominator.gcd(otherFraction.denominator);
            BigInteger lcm = fractionNew.denominator
                    .multiply(otherFraction.denominator.divide(gcd));
            BigInteger newNumeratorThis = fractionNew.numerator
                    .multiply(lcm.divide(fractionNew.denominator));
            BigInteger newNumeratorOther = otherFraction.numerator
                    .multiply(lcm.divide(otherFraction.denominator));
            fractionNew.numerator = newNumeratorThis.add(newNumeratorOther);
            fractionNew.denominator = lcm;
        }
        return fractionNew.reduce();
    }

    public Fraction subtract(Fraction otherFraction) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        if (fractionNew.denominator.equals(otherFraction.denominator)) {
            fractionNew.numerator = fractionNew.numerator.subtract(otherFraction.numerator);
        } else {
            BigInteger gcd = fractionNew.denominator.gcd(otherFraction.denominator);
            BigInteger lcm = fractionNew.denominator
                    .multiply(otherFraction.denominator.divide(gcd));
            BigInteger newNumeratorThis = fractionNew.numerator
                    .multiply(lcm.divide(fractionNew.denominator));
            BigInteger newNumeratorOther = otherFraction.numerator
                    .multiply(lcm.divide(otherFraction.denominator));
            fractionNew.numerator = newNumeratorThis.subtract(newNumeratorOther);
            fractionNew.denominator = lcm;
        }
        return fractionNew.reduce();
    }

    public Fraction multiply(Fraction otherFraction) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        fractionNew.numerator = fractionNew.numerator.multiply(otherFraction.numerator);
        fractionNew.denominator = fractionNew.denominator.multiply(otherFraction.denominator);

        return fractionNew.reduce();
    }

    public Fraction divide(Fraction otherFraction) {
        if (otherFraction.numerator.equals(BigInteger.ZERO))
            throw new IllegalArgumentException("Division by zero!");

        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        fractionNew.numerator = fractionNew.numerator.multiply(otherFraction.denominator);
        fractionNew.denominator = fractionNew.denominator.multiply(otherFraction.numerator);

        if (fractionNew.denominator.compareTo(BigInteger.ZERO) < 0) {
            fractionNew.numerator = fractionNew.numerator.negate();
            fractionNew.denominator = fractionNew.denominator.negate();
        }
        return fractionNew.reduce();
    }

    public Fraction reduce() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        BigInteger newNumerator = this.numerator.divide(gcd);
        BigInteger newDenominator = this.denominator.divide(gcd);

        return new Fraction(newNumerator, newDenominator);
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
