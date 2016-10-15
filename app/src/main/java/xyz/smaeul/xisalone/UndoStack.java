package xyz.smaeul.xisalone;

import android.util.Log;

import java.util.Stack;

import xyz.smaeul.xisalone.expression.Term;

/**
 * Created by YANG on 10/15/2016.
 */

public class UndoStack {
    private Stack<Term> undoValues;
    private Stack<Operator> undoOperations;

    public UndoStack() {
        undoValues = new Stack<>();
        undoOperations = new Stack<>();
    }

    public Stack<Term> getUndoValues() {
        return undoValues;
    }

    public Stack<Operator> getUndoOperations() {
        return undoOperations;
    }

    public void addUndoOperation(Operator o) {
        //adds compliment of operator given
        switch (o) {
            case MULTIPLY:
                undoOperations.push(Operator.DIVIDE);
                break;
            case DIVIDE:
                undoOperations.push(Operator.MULTIPLY);
                break;
            case ADD:
                undoOperations.push(Operator.SUBTRACT);
                break;
            case SUBTRACT:
                undoOperations.push(Operator.ADD);
                break;
        }
    }

    public Term popUndoValue(){

        return undoValues.pop();
    }

    public Operator popUndoOperation(){

        return undoOperations.pop();
    }

    public void addUndoValue(Term t) {

        undoValues.push(t);
    }
}