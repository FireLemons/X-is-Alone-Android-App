package xyz.smaeul.xisalone;

import android.util.Log;

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
        int k;

        for(k = 1; k * (k + 1) / 2 < n; k++);

        for (int i = 0; i < k; i++) {
            int num = rand.nextInt(9 + k - k * (k + 1) / 2 + n);//0;
            if (num > 0) {
                values.push(new Term(num, 0));
            } else {
                values.push(new Term(1, 1));
            }
            operators.push(Operator.values()[rand.nextInt(Operator.values().length)]);
            Log.d("Operator", operators.lastElement().toString());
        }
    }

    public Stack<Term> getValues() {
        return values;
    }

    public Stack<Operator> getOperators() {
        return operators;
    }
}
