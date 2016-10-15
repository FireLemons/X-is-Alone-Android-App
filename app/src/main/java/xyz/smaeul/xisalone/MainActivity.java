package xyz.smaeul.xisalone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int steps = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
