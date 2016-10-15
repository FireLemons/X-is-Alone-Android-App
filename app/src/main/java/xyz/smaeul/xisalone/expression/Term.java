package xyz.smaeul.xisalone.expression;

/**
 * Created by Samuel on 10/15/16.
 */

public class Term {

    int coefficient;
    int exponent;

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }
}
