package xyz.smaeul.xisalone;

import java.util.Random;
import java.util.Stack;
import xyz.smaeul.xisalone.expression.*;

/**
 * Created by Sai Kovuri on 10/14/2016.
 */

public class RandomStack {

    Stack<Term> values;
    Stack<Operator> operators;

    public RandomStack(int n){

        setOperators(n);
        setValues(n);
    }

    private void setValues(int n){

        values = new Stack<Term>();
        Random rand = new Random();

        for(int i = 0; i < n; i++){

            int num = rand.nextInt() % 20;
            if(num >= 0){

                values.push(new Term(num, 0));
            }else{

                values.push(new Term(1, 1));
            }
        }
    }

    private void setOperators(int n){

        operators = new Stack<Operator>();
        Random rand = new Random();

        for(int i = 0; i < n; i++){

            int num = rand.nextInt() % 4;

            switch(num){

                case 0:

                    operators.push(Operator.ADD);
                    break;

                case 1:

                    operators.push(Operator.DIVIDE);
                    break;

                case 2:

                    operators.push(Operator.SUBTRACT);
                    break;

                case 3:
                    operators.push(Operator.MULTIPLY);
                    break;
            }
        }
    }

    public Stack<Term> getValues(){

        return values;
    }

    public Stack<Operator> getOperators(){

        return operators;
    }
}