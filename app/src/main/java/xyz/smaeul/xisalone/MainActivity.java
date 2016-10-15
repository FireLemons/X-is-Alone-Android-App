package xyz.smaeul.xisalone;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;
import xyz.smaeul.xisalone.expression.*;

import xyz.smaeul.xisalone.expression.Expression;

public class MainActivity extends AppCompatActivity {

    private class Coordinates {
        private float x;
        private float y;
    }

    final Coordinates savedCoordinates = new Coordinates();

    // Number of operations to perform that the user must undo
    private static final int steps = 4;
    // Radius size for circle in pixels: motion outside of this circle is considered input
    private static final float radius = 100f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.operation);
        final View view = findViewById(R.id.activity_main);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                float currentX = event.getAxisValue(0);
                float currentY = event.getAxisValue(1);

                switch (action) {
                    // Set initial x and y coordinate when screen is pressed
                    case (MotionEvent.ACTION_DOWN):
                        savedCoordinates.x = currentX;
                        savedCoordinates.y = currentY;
                        return true;

                    // Track input while moving
                    case (MotionEvent.ACTION_MOVE):
                        // If outside the circle
                        if (Math.pow((savedCoordinates.x - currentX), 2) + Math.pow((savedCoordinates.y - currentY), 2) > Math.pow(radius, 2)) {
                            if (Math.abs(savedCoordinates.x - currentX) > Math.abs(savedCoordinates.y - currentY)) {
                                if (savedCoordinates.x < currentX) {
                                    text.setText("right");
                                } else {
                                    text.setText("left");
                                }
                            } else {
                                if (savedCoordinates.y < currentY) {
                                    text.setText("down");
                                } else {
                                    text.setText("up");
                                }
                            }
                        }
                        return true;

                    // When finger is lifted
                    case (MotionEvent.ACTION_UP):
                        // If outside the circle
                        if (Math.pow((savedCoordinates.x - currentX), 2) + Math.pow((savedCoordinates.y - currentY), 2) > Math.pow(radius, 2)) {
                            if (Math.abs(savedCoordinates.x - currentX) > Math.abs(savedCoordinates.y - currentY)) {
                                if (savedCoordinates.x < currentX) {
                                    text.setText("right");
                                } else {
                                    text.setText("left");
                                }
                            } else {
                                if (savedCoordinates.y < currentY) {
                                    text.setText("down");
                                } else {
                                    text.setText("up");
                                }
                            }
                        } else {
                            text.setText("canceled");
                        }
                        return true;

                    default:
                        return false;
                }
            }
        });

        startGame();
    }

    public void options(View v) {

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
