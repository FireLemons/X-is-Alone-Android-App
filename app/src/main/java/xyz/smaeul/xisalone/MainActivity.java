package xyz.smaeul.xisalone;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    class Coords {
        float x;
        float y;
    }

    final Coords initialCoords = new Coords();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Expression e= new Expression();

        final TextView t = (TextView) findViewById(R.id.text_anchor);

        View view = findViewById(R.id.activity_main);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                // Radius size for circle in pixels
                // Outside of this circle is considered input
                final float radius = 100;

                switch(action) {
                    // Set initial x and y coordinate when screen is pressed
                    case (MotionEvent.ACTION_DOWN):
                        initialCoords.x = event.getAxisValue(0);
                        initialCoords.y = event.getAxisValue(1);
                        return true;

                    // Track input while moving
                    case (MotionEvent.ACTION_MOVE):
                        // If outside the circle
                        if(Math.pow((initialCoords.x - event.getAxisValue(0)), 2) + Math.pow((initialCoords.y - event.getAxisValue(1)), 2) > Math.pow(radius, 2)) {
                            // Left or Right
                            if(Math.abs(initialCoords.x - event.getAxisValue(0)) > Math.abs(initialCoords.y - event.getAxisValue(1))){
                                if((initialCoords.x - event.getAxisValue(0)) < 0){
                                    // Right
                                }
                                else{
                                    // Left
                                }
                            }
                            // Up or Down
                            else{
                                if((initialCoords.y - event.getAxisValue(1)) < 0){
                                    // Down
                                }
                                else{
                                    // Up
                                }
                            }
                        }

                        // If inside the circle
                        else {
                        }
                        return true;

                    // When finger is lifted
                    case (MotionEvent.ACTION_UP):
                        return true;
                    default:
                        return false;
                }
            }
        });
        
    }
}
