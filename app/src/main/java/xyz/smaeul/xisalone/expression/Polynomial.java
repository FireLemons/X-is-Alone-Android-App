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

    public String toHTML() {
        // Array for term index sorted in reverse order by exponent
        int listSize = terms.size();
        int[] list = new int[listSize];
        for(int i = 0; i < listSize; i++){
            list[i] = i;
        }
        // Sort by exponent; Bubble sort
        boolean listChanged = true;
        while(listChanged == true){
            listChanged = false;
            for(int i = 0; i < (listSize - 1); i++){
                if((terms.get(list[i])).getExponent() < (terms.get(list[i + 1])).getExponent()){
                    listChanged = true;
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }
            }
        }
        // Create HTML
        String output = new String();
        for(int i = 0; i < listSize; i++){
            if(terms.get(list[i]).getCoefficient() > 0 && i == 0){
                output = output.concat("" + terms.get(list[i]).getCoefficient());
                if(terms.get(list[i]).getExponent() > 0) {
                    output = output.concat("x");
                }
                if(terms.get(list[i]).getExponent() > 1) {
                    output = output.concat("<sup>" + terms.get(list[i]).getExponent() + "</sup> ");
                }
                else {
                    output = output.concat(" ");
                }
            }
            else if(terms.get(list[i]).getCoefficient() < 0 && i == 0){
                output = output.concat("-" + Math.abs(terms.get(list[i]).getCoefficient()));
                if(terms.get(list[i]).getExponent() > 0) {
                    output = output.concat("x");
                }
                if(terms.get(list[i]).getExponent() > 1) {
                    output = output.concat("<sup>" + terms.get(list[i]).getExponent() + "</sup> ");
                }
                else {
                    output = output.concat(" ");
                }
            }
            else if(terms.get(list[i]).getCoefficient() > 0){
                output = output.concat("+ " + terms.get(list[i]).getCoefficient());
                if(terms.get(list[i]).getExponent() > 0) {
                    output = output.concat("x");
                }
                if(terms.get(list[i]).getExponent() > 1) {
                    output = output.concat("<sup>" + terms.get(list[i]).getExponent() + "</sup> ");
                }
                else {
                    output = output.concat(" ");
                }
            }
            else if(terms.get(list[i]).getCoefficient() < 0){
                output = output.concat("- " + Math.abs(terms.get(list[i]).getCoefficient()));
                if(terms.get(list[i]).getExponent() > 0) {
                    output = output.concat("x");
                }
                if(terms.get(list[i]).getExponent() > 1) {
                    output = output.concat("<sup>" + terms.get(list[i]).getExponent() + "</sup> ");
                }
                else {
                    output = output.concat(" ");
                }
            }
        }
        return(output);
    }
}
