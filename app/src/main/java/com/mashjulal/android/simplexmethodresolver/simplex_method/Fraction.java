package com.mashjulal.android.simplexmethodresolver.simplex_method;

import android.support.annotation.NonNull;

import java.math.BigInteger;
import java.util.Locale;

public class Fraction implements Comparable<Fraction> {

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

    public Fraction add(int number) {
        return add(new Fraction(number));
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

    public Fraction subtract(int number) {
        return subtract(new Fraction(number));
    }

    public Fraction multiply(Fraction fractionOther) {
        Fraction fractionNew = new Fraction(this.numerator, this.denominator);
        fractionNew.numerator = fractionNew.numerator.multiply(fractionOther.numerator);
        fractionNew.denominator = fractionNew.denominator.multiply(fractionOther.denominator);

        return fractionNew.reduce();
    }

    public Fraction multiply(int number) {
        return multiply(new Fraction(number));
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

    public Fraction divide(int number) {
        return divide(new Fraction(number));
    }

    public Fraction reduce() {
        Fraction fraction = new Fraction(this.numerator, this.denominator);
        fraction.__reduce();
        return fraction;
    }

    public double toDouble() {
        return numerator.intValue() * 1.0 / denominator.intValue();
    }

    public Fraction negate() {
        return new Fraction(this.numerator.negate(), this.denominator);
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
        return compareTo((Fraction) obj) == 0;
    }

    @Override
    public int compareTo(@NonNull Fraction o) {
        int result;
        int numeratorDelta = numerator.compareTo(o.numerator);
        int denominatorDelta = denominator.compareTo(o.denominator);

        if (denominatorDelta == 0) {
            if (numeratorDelta == 0)
                result = 0;
            else if (numeratorDelta > 0)
                result = 1;
            else
                result = -1;
        } else if (denominatorDelta > 0) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }

    public int compareTo(int number) {
        return compareTo(new Fraction(number));
    }

    public Fraction abs() {
        return new Fraction(numerator.abs(), denominator);
    }
}
