package xyz.smaeul.xisalone;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by Chris on 10/15/16.
 * Refactored by Samuel on 10/15/16.
 */

public class TouchListener implements OnTouchListener {
    private class Position {
        private float x = 0f;
        private float y = 0f;
    }

    private final Position currentPosition = new Position();
    private final Position startPosition = new Position();

    private final float radius;
    private final OnSwipeListener swipeListener;

    private SwipeDirection lastDirection = SwipeDirection.NONE;

    public TouchListener(OnSwipeListener swipeListener, float radius) {
        this.radius = radius;
        this.swipeListener = swipeListener;
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
                startPosition.x = currentPosition.x;
                startPosition.y = currentPosition.y;
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
