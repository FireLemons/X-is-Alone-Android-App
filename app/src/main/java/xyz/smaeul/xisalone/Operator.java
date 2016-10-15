package xyz.smaeul.xisalone;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Sai Kovuri on 10/15/2016.
 */

public class Operator {

    public enum CalcToken {
        Add {
            @Override
            public String toString() {
                return "+";
            }
        },
        Subtract {
            @Override
            public String toString() {
                return "-";
            }
        },
        Multiply {
            @Override
            public String toString() {
                return "*";
            }
        },

        Divide {
            @Override
            public String toString() {
                return "/";
            }
        }
    }

    public Stack<Integer> Numbers(int num) {
        Stack<Integer> stack1 = new Stack<Integer>();
        Random random = new Random();
        for (int i = 1; i <= num; i++) {
            stack1.add(random.nextInt(20) + 1);
        }

        return stack1;
    }

    public Stack randomLetter(int num) {
        int pick = new Random().nextInt(CalcToken.values().length);
        Stack st1 = new Stack();
        for (int i = 1; i <= num; i++) {
            st1.add(CalcToken.values()[(int) (Math.random() * CalcToken.values().length)]);
        }
        return st1;
    }

    public void StackNumbers(Stack s1, Stack s2) {
        if (!s1.isEmpty()) {
            System.out.println(s2.push(s1.pop()));
        }
    }

    public void randomLetter(Stack s3, Stack s4) {
        if (!s3.isEmpty()) {
            System.out.println(s4.push(s3.pop()));
        }
    }

}
