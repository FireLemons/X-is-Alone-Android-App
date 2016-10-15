package xyz.smaeul.xisalone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Expression e= new Expression();

        e.add(new Polynomial(1, 4));
        e.add(new Polynomial(2, 8));

        e.setDenominator(new Polynomial(1, 12));

        e.simplify();

        Log.d("##########", Integer.toString(e.getDenominator().getCoefficient()) + " " + Integer.toString(e.getDenominator().getExponent()));
    }
}
