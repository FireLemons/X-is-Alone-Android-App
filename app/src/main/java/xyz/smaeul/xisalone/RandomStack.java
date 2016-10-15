package xyz.smaeul.xisalone;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Sai Kovuri on 10/14/2016.
 */

public class RandomStack {

    public void NumbersOperators() {
        Operator op = new Operator();
        op.randomLetter(15);
        op.Numbers(15);
        Stack s2 = new Stack();
        Stack s3 = new Stack();
        op.StackNumbers(op.Numbers(15), s2);
        op.randomLetter(op.randomLetter(15), s3);
    }
}


