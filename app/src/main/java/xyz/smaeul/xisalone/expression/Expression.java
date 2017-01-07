package xyz.smaeul.xisalone.expression;

import android.util.Log;

/**
 * Created by Madi on 10/14/16.
 */

public class Expression {

    private Polynomial numerator;
    private Polynomial denominator;

    public Expression() {
        this(new Polynomial());
    }

    public Expression(Polynomial numerator) {
        this.numerator = numerator;
        this.denominator = new Polynomial(new Term(1, 0));
    }

    public Polynomial getDenominator() {
        return denominator;
    }

    public Polynomial getNumerator() {
        return numerator;
    }

    public void add(Term t) {
        Polynomial temp = new Polynomial();

        for (Term term : denominator.getTerms()) {
            temp.add(new Term(t.coefficient * term.coefficient, t.exponent + term.exponent));
        }
        for (Term term : temp.getTerms()) {
            numerator.add(term);
        }
        simplify();
    }

    public void divide(Term t) {
        Log.d("Numerator count", numerator.getNumberOfTerms()+"");
        if(numerator.getNumberOfTerms() > 0){
            denominator.multiply(t);
            simplify();
        }
    }

    private static long gcd(long p, long q) {
        while (q != 0) {
            long temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }

    private static long min(long a, long b) {
        return a <= b ? a : b;
    }

    public void multiply(Term t) {
        numerator.multiply(t);
        simplify();
    }

    public void subtract(Term t) {
        add(new Term(-t.coefficient, t.exponent));
    }

    public void simplify() {
        if (numerator.getNumberOfTerms() > 0 && denominator.getNumberOfTerms() > 0) {
            // Simplify coefficients
            long numerator_gcd = numerator.getTerms().getFirst().coefficient;
            long denominator_gcd = denominator.getTerms().getFirst().coefficient;

            for (Term term : numerator.getTerms()) {
                numerator_gcd = gcd(numerator_gcd, term.coefficient);
            }
            for (Term term : denominator.getTerms()) {
                denominator_gcd = gcd(denominator_gcd, term.coefficient);
            }

            long gcd = gcd(numerator_gcd, denominator_gcd);
            gcd = Math.abs(gcd);

            if (gcd > 1) {
                for (Term term : numerator.getTerms()) {
                    term.coefficient /= gcd;
                }
                for (Term term : denominator.getTerms()) {
                    term.coefficient /= gcd;
                }
            }

            //Simplify exponents
            long numerator_min_exp = numerator.getTerms().getFirst().exponent;
            long denominator_min_exp = denominator.getTerms().getFirst().exponent;

            for (Term term : numerator.getTerms()) {
                numerator_min_exp = min(numerator_min_exp, term.exponent);
            }
            for (Term term : denominator.getTerms()) {
                denominator_min_exp = min(denominator_min_exp, term.exponent);
            }

            long min_exp = min(numerator_min_exp, denominator_min_exp);

            if (min_exp > 0) {
                for (Term term : numerator.getTerms()) {
                    term.exponent -= min_exp;
                }
                for (Term term : denominator.getTerms()) {
                    term.exponent -= min_exp;
                }
            }
        }
    }
}
