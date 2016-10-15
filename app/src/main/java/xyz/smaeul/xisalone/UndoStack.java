package xyz.smaeul.xisalone;

import java.util.Stack;
import xyz.smaeul.xisalone.expression.*;
/**
 * Created by YANG on 10/15/2016.
 */

public class UndoStack {

    Stack<Term> undoValues;
    Stack<Operator> undoOpeations;

    public UndoStack(){

        undoValues = new Stack<Term>();
        undoOpeations = new Stack<Operator>();
    }

    public Stack<Term> getUndoValues(){

        return undoValues;
    }

    public Stack<Operator> getUndoOpeations(){

        return undoOpeations;
    }

    public void addUndoOperation(Operator o){//adds compliment of operator given

        switch(o){

            case MULTIPLY:

                undoOpeations.push(Operator.DIVIDE);
                break;
            case DIVIDE:

                undoOpeations.push(Operator.MULTIPLY);
                break;
            case ADD:

                undoOpeations.push(Operator.SUBTRACT);
                break;
            case SUBTRACT:

                undoOpeations.push(Operator.ADD);
                break;
        }
    }

    public void addUndoValue(Term t){

        undoValues.push(t);
    }
}