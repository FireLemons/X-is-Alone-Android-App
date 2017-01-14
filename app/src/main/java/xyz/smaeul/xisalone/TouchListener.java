package xyz.smaeul.xisalone;

import android.graphics.Point;
import android.graphics.PointF;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

/**
 * Created by Chris on 10/15/16.
 * Refactored by Samuel on 10/15/16.
 */

public class TouchListener implements OnTouchListener {

    private final PointF currentPosition = new PointF();
    private final PointF startPosition;
    private final boolean altControls;
    private final float radius;
    private final OnSwipeListener swipeListener;

    private SwipeDirection lastDirection = SwipeDirection.NONE;

    public TouchListener(OnSwipeListener swipeListener, float radius) {

        this.radius = radius;
        this.swipeListener = swipeListener;
        this.startPosition = new PointF();
        altControls = false;
    }

    public TouchListener(OnSwipeListener swipeListener, float radius, PointF center) {

        this.radius = radius;
        this.swipeListener = swipeListener;
        this.startPosition = center;
        altControls = true;
    }

    private SwipeDirection calculateDirection() {
        if (square(startPosition.x - currentPosition.x) + square(startPosition.y - currentPosition.y) > square(radius)) {
            if (Math.abs(startPosition.x - currentPosition.x) > Math.abs(startPosition.y - currentPosition.y)) {
                if (startPosition.x < currentPosition.x) {
                    return SwipeDirection.RIGHT;
                } else {
                    return SwipeDirection.LEFT;
                }
            } else {
                if (startPosition.y < currentPosition.y) {
                    return SwipeDirection.DOWN;
                } else {
                    return SwipeDirection.UP;
                }
            }
        } else {
            return SwipeDirection.NONE;
        }
    }

    public void reCenter(PointF center){

        startPosition.set(center.x, center.y);
    }

    private float square(float a) {
        return a * a;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        currentPosition.x = event.getAxisValue(0);
        currentPosition.y = event.getAxisValue(1);
        SwipeDirection currentDirection = calculateDirection();

        switch (MotionEventCompat.getActionMasked(event)) {
            // Set initial x and y coordinate when screen is pressed
            case (MotionEvent.ACTION_DOWN):
                if(!altControls){
                    startPosition.x = currentPosition.x;
                    startPosition.y = currentPosition.y;
                }
                Log.d("TouchDown Coordiantes","x =" + event.getAxisValue(0) + " y = " + event.getAxisValue(1));
                Log.d("Zero Coordinates", "x =" + startPosition.x + "y = " + startPosition.y);
                return true;

            // Track input while moving
            case (MotionEvent.ACTION_MOVE):
                if (currentDirection != lastDirection) {
                    lastDirection = currentDirection;
                    swipeListener.onSwipe(currentDirection, false);
                }
                return true;

            // When finger is lifted
            case (MotionEvent.ACTION_UP):
                swipeListener.onSwipe(currentDirection, true);
                return true;

            default:
                return false;
        }
    }
}
