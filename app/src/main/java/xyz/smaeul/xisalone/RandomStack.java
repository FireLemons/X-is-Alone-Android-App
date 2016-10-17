package xyz.smaeul.xisalone;

import java.util.Random;
import java.util.Stack;

import xyz.smaeul.xisalone.expression.Term;

/**
 * Created by Sai Kovuri on 10/14/2016.
 * Refactored twice on 10/15/2016.
 */

public class RandomStack {
    private final static int MAX = 16;

    private final Random rand;
    private final Stack<Term> values;
    private final Stack<Operator> operators;

    public RandomStack(int n) {
        rand = new Random();
        operators = new Stack<>();
        values = new Stack<>();

        for (int i = 0; i < (n / 2) + 1; i++) {
            int num = rand.nextInt(9 + (n % ((n / 2) + 1)));
            if (num > 0) {
                values.push(new Term(num, 0));
            } else {
                values.push(new Term(1, 1));
            }
            operators.push(Operator.values()[rand.nextInt(Operator.values().length)]);
        }
    }

    public Stack<Term> getValues() {
        return values;
    }

    public Stack<Operator> getOperators() {
        return operators;
    }
}
