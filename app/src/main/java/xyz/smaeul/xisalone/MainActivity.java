package xyz.smaeul.xisalone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;
import xyz.smaeul.xisalone.expression.*;

import xyz.smaeul.xisalone.expression.Expression;

public class MainActivity extends AppCompatActivity implements OnSwipeListener {
    // Number of operations to perform that the user must undo
    private static final int steps = 4;
    // Radius size for circle in pixels: motion outside of this circle is considered input
    private static final float radius = 100f;

    private final TouchListener touchListener = new TouchListener(this, radius);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main).setOnTouchListener(touchListener);
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
    }

    public void options(View v) {

    }

    public void resetExpression() {
        final TextView text = (TextView) findViewById(R.id.operation);
        text.setText("∅");
    }

    public void runGameStep(Operator operator) {

    }

    public void showPreview(Operator operator) {
        final TextView text = (TextView) findViewById(R.id.operation);
        text.setText(operator.toString());
    }

    public void startGame() {
        LinearLayout stack = (LinearLayout) findViewById(R.id.number_stack);
        Random r = new Random();
        for (int i = 0; i < steps; ++i) {
            TextView tv = new TextView(this);
            tv.setPadding(8, 0, 8, 0);
            tv.setText(new Integer(r.nextInt(20)).toString());
            stack.addView(tv);
        }
    }

    public void undo(View v) {

    }
}
