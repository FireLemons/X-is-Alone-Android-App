package xyz.smaeul.xisalone.expression;

/**
 * Created by Samuel on 10/15/16.
 */

public class Term {

    long coefficient;
    long exponent;

    public Term(long coefficient, long exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public long getCoefficient() {
        return coefficient;
    }

    public long getExponent() {
        return exponent;
    }
}
