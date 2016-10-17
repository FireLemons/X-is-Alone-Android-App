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

    public Polynomial(Term t) {
        this();
        terms.add(t);
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

    public boolean isBareX() {
        return terms.size() == 1 && terms.getFirst().coefficient == 1 && terms.getFirst().exponent == 1;
    }

    public boolean isConstant() {
        return terms.size() == 1 && terms.getFirst().exponent == 0;
    }

    public boolean isIdentity() {
        return isConstant() && terms.getFirst().coefficient == 1;
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

    public String toHTML() {
        // Array for term index sorted in reverse order by exponent
        int listSize = terms.size();
        if (listSize == 0) {
            return "0";
        }
        int[] list = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            list[i] = i;
        }
        // Sort by exponent; Bubble sort
        boolean listChanged = true;
        while (listChanged == true) {
            listChanged = false;
            for (int i = 0; i < (listSize - 1); i++) {
                if ((terms.get(list[i])).getExponent() < (terms.get(list[i + 1])).getExponent()) {
                    listChanged = true;
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }
            }
        }
        // Create HTML
        String output = new String();
        for (int i = 0; i < listSize; i++) {
            int coefficient = terms.get(list[i]).getCoefficient();
            int exponent = terms.get(list[i]).getExponent();

            if (i > 0 && coefficient > 0) {
                output = output.concat("+");
            }
            if (coefficient > 1 || coefficient < -1 || exponent == 0) {
                output = output.concat(Integer.valueOf(coefficient).toString());
            }
            if (exponent > 0) {
                if(coefficient == -1){
                    output = output.concat("-");
                }
                output = output.concat("x");
            }
            if (exponent > 1) {
                output = output.concat("<sup><small>" + exponent + "</small></sup> ");
            }
        }
        return (output);
    }
}
