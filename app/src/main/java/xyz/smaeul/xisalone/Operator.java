package xyz.smaeul.xisalone;

/**
 * Created by Sai Kovuri on 10/15/2016.
 */

public enum Operator {
    ADD {
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        public String toString() {
            return "×";
        }
    },
    DIVIDE {
        @Override
        public String toString() {
            return "÷";
        }
    }
}
