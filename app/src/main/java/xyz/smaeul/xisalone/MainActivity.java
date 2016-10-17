package xyz.smaeul.xisalone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.Random;

import xyz.smaeul.xisalone.expression.Expression;
import xyz.smaeul.xisalone.expression.Polynomial;
import xyz.smaeul.xisalone.expression.Term;

public class MainActivity extends AppCompatActivity implements OnSwipeListener {
    // Radius size for circle in pixels: motion outside of this circle is considered input
    private static final float radius = 100f;

    private final TouchListener touchListener = new TouchListener(this, radius);

    private int difficulty = 1;
    private LinearLayout numberStack;
    private Expression leftSide;
    private Expression rightSide;
    private RandomStack randomStack;
    private UndoStack undoStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main).setOnTouchListener(touchListener);
        numberStack = (LinearLayout) findViewById(R.id.number_stack);
        startGame();
    }

    @Override
    public void onSwipe(SwipeDirection direction, boolean released) {
        switch (direction) {
            case NONE:
                resetExpression();
                break;
            case LEFT:
                if (released) {
                    runGameStep(Operator.SUBTRACT);
                } else {
                    showPreview(Operator.SUBTRACT);
                }
                break;
            case RIGHT:
                if (released) {
                    runGameStep(Operator.ADD);
                } else {
                    showPreview(Operator.ADD);
                }
                break;
            case UP:
                if (released) {
                    runGameStep(Operator.MULTIPLY);
                } else {
                    showPreview(Operator.MULTIPLY);
                }
                break;
            case DOWN:
                if (released) {
                    runGameStep(Operator.DIVIDE);
                } else {
                    showPreview(Operator.DIVIDE);
                }
                break;
        }
        if(released){

            clearPreview();
        }
    }

    public void resetExpression() {
        final TextView text = (TextView) findViewById(R.id.operation);
        text.setText("");
    }

    public void runGameStep(Operator operator) {
        TextView stackView = (TextView) numberStack.getChildAt(0);
        Term term = randomStack.getValues().pop();
        numberStack.removeViewAt(0);
        switch (operator) {
            case ADD:
                leftSide.add(term);
                rightSide.add(term);
                undoStack.addUndoOperation(Operator.ADD);
                break;
            case SUBTRACT:
                leftSide.subtract(term);
                rightSide.subtract(term);
                undoStack.addUndoOperation(Operator.SUBTRACT);
                break;
            case MULTIPLY:
                leftSide.multiply(term);
                rightSide.multiply(term);
                undoStack.addUndoOperation(Operator.MULTIPLY);
                break;
            case DIVIDE:
                if(!leftSide.getNumerator().getTerms().isEmpty()) {
                    leftSide.divide(term);
                }
                if(!rightSide.getNumerator().getTerms().isEmpty()) {
                    rightSide.divide(term);
                }
                undoStack.addUndoOperation(Operator.DIVIDE);
                break;
        }

        undoStack.addUndoValue(term);
        updateDisplay();

        if (numberStack.getChildCount() == 0) {
            checkWin();
            startGame();
        } else {
            numberStack.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    public void showPreview(Operator operator) {
        final TextView text = (TextView) findViewById(R.id.operation);
        text.setText(operator.toString());
    }

    public void clearPreview(){

        final TextView text = (TextView) findViewById(R.id.operation);
        text.setText("");
    }

    public void startGame() {
        leftSide = new Expression(new Polynomial(new Term(1, 1)));
        rightSide = new Expression(new Polynomial(new Term(new Random().nextInt(10), 0)));
        randomStack = new RandomStack(difficulty);
        undoStack = new UndoStack();

        LinearLayout numberStack = (LinearLayout) findViewById(R.id.number_stack);
        Iterator<Operator> operatorIterator = randomStack.getOperators().iterator();
        Iterator<Term> termIterator = randomStack.getValues().iterator();

        // Run the algebra simulation forwards
        while (operatorIterator.hasNext()) {
            Operator operator = operatorIterator.next();
            Term term = termIterator.next();
            switch (operator) {
                case ADD:
                    leftSide.add(term);
                    rightSide.add(term);
                    break;
                case SUBTRACT:
                    leftSide.subtract(term);
                    rightSide.subtract(term);
                    break;
                case MULTIPLY:
                    leftSide.multiply(term);
                    rightSide.multiply(term);
                    break;
                case DIVIDE:
                    if(!leftSide.getNumerator().getTerms().isEmpty()) {

                        leftSide.divide(term);
                    }
                    if(!rightSide.getNumerator().getTerms().isEmpty()) {

                        rightSide.divide(term);
                    }
                    break;
            }
            TextView textView = new TextView(this);
            textView.setPadding(16, 0, 16, 0);
            textView.setText(new Polynomial(term).toHTML());
            numberStack.addView(textView, 0);
        }
        numberStack.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorAccent));
        updateDisplay();
    }

    public void undo(View v) {

        if(undoStack != null){

            if(!undoStack.getUndoValues().isEmpty()) {

                Term term = undoStack.popUndoValue();
                Operator operator = undoStack.popUndoOperation();

                TextView textView = new TextView(this);
                textView.setPadding(16, 0, 16, 0);
                textView.setText(new Polynomial(term).toHTML());
                numberStack.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.ghost));
                numberStack.addView(textView, 0);
                numberStack.getChildAt(0).setBackgroundColor(getResources().getColor(R.color.colorAccent));

                switch (operator) {
                    case ADD:
                        leftSide.add(term);
                        rightSide.add(term);
                        break;
                    case SUBTRACT:
                        leftSide.subtract(term);
                        rightSide.subtract(term);
                        break;
                    case MULTIPLY:
                        leftSide.multiply(term);
                        rightSide.multiply(term);
                        break;
                    case DIVIDE:

                        if(!leftSide.getNumerator().getTerms().isEmpty()) {
                            leftSide.divide(term);
                        }
                        if(!rightSide.getNumerator().getTerms().isEmpty()) {
                            rightSide.divide(term);
                        }
                        break;
                }

                updateDisplay();
                randomStack.getValues().push(term);
            }
        }
    }

    public void checkWin() {

        String scoreChange = "";

        if ((leftSide.getNumerator().isBareX() && leftSide.getDenominator().isIdentity() &&
                rightSide.getNumerator().isConstant() && rightSide.getDenominator().isIdentity()) ||
                (leftSide.getNumerator().isConstant() && leftSide.getDenominator().isIdentity() &&
                        rightSide.getNumerator().isBareX() && rightSide.getDenominator().isIdentity())) {
            scoreChange = "+1";
            difficulty++;
        } else {
            if (difficulty > 1) {

                difficulty--;
                scoreChange = "-1";
            }
        }
        Toast toast = Toast.makeText(this,  scoreChange + " Score:" + Integer.toString(difficulty - 1), Toast.LENGTH_SHORT);
        toast.show();
    }

    /*ublic void showEndRoundMessage(int n, String text){

        setContentView(R.layout.win_lose_screen);
        TextView message = (TextView) findViewById(R.id.endGameMessage);
        message.setText(text);
        difficulty = n;
    }

    public void restart(View view){

        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main).setOnTouchListener(touchListener);
        difficulty = 1;
        startGame();
    }*/

    private void updateDisplay() {
        TextView leftNumerator = (TextView) findViewById(R.id.left_numerator);
        TextView leftDenominator = (TextView) findViewById(R.id.left_denominator);
        TextView rightNumerator = (TextView) findViewById(R.id.right_numerator);
        TextView rightDenominator = (TextView) findViewById(R.id.right_denominator);

        String leftBottomText = leftSide.getDenominator().toHTML();
        String rightBottomText = rightSide.getDenominator().toHTML();

        leftNumerator.setText(Html.fromHtml(leftSide.getNumerator().toHTML()));
        if (leftBottomText.equals("1")) {
            findViewById(R.id.left_bar).setVisibility(View.GONE);
            leftDenominator.setVisibility(View.GONE);
        } else {
            findViewById(R.id.left_bar).setVisibility(View.VISIBLE);
            leftDenominator.setVisibility(View.VISIBLE);
            leftDenominator.setText(Html.fromHtml(leftBottomText));
        }
        rightNumerator.setText(Html.fromHtml(rightSide.getNumerator().toHTML()));
        if (rightBottomText.equals("1")) {
            findViewById(R.id.right_bar).setVisibility(View.GONE);
            rightDenominator.setVisibility(View.GONE);
        } else {
            findViewById(R.id.right_bar).setVisibility(View.VISIBLE);
            rightDenominator.setVisibility(View.VISIBLE);
            rightDenominator.setText(Html.fromHtml(rightBottomText));
        }
    }
}
