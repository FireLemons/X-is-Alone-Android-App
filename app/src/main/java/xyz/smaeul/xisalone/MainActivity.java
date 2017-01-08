package xyz.smaeul.xisalone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view){

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void openMenu(View view){

        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}
