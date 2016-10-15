package xyz.smaeul.xisalone.expression;

/**
 * Created by Madi on 10/14/16.
 */

public class Expression {

    private Polynomial numerator;
    private Polynomial denominator;

    public Expression() {
        numerator = new Polynomial();
        denominator = new Polynomial();
    }

    public void add(Term t) {
        for (Term term : denominator.getTerms()) {
            numerator.add(term);
        }
        simplify();
    }

    public void divide(Term t) {
        denominator.multiply(t);
        simplify();
    }

    public void multiply(Term t) {
        numerator.multiply(t);
        simplify();
    }

    public void subtract(Term t) {
        for (Term term : denominator.getTerms()) {
            numerator.subtract(term);
        }
        simplify();
    }

    public void simplify() {
        throw new RuntimeException("not implemented");
    }
}
