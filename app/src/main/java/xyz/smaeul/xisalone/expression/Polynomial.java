package xyz.smaeul.xisalone.expression;

import java.util.LinkedList;

/**
 * Created by Madi on 10/14/16.
 * Refactored by Samuel on 10/15/16
 */

public class Polynomial {

    private LinkedList<Term> terms;

    public Polynomial() {
        terms = new LinkedList<>();
    }

    public void add(Term t) {
        for (Term term : terms) {
            if (term.exponent == t.exponent) {
                term.coefficient += t.coefficient;
                if (term.coefficient == 0) {
                    terms.remove(term);
                }
                return;
            }
        }
        terms.add(t);
    }

    public int getNumberOfTerms() {
        return terms.size();
    }

    public LinkedList<Term> getTerms() {
        return new LinkedList<Term>(terms);
    }

    public void multiply(Term t) {
        for (Term term : terms) {
            term.coefficient *= t.coefficient;
            term.exponent += t.exponent;
            if (term.coefficient == 0) {
                terms.remove(term);
            }
        }
    }

    public void subtract(Term t) {
        add(new Term(-t.coefficient, t.exponent));
    }
}
