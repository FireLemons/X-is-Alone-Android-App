package xyz.smaeul.xisalone;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        CheckBox altControls = (CheckBox)findViewById(R.id.checkbox_controls);
        altControls.setText(Html.fromHtml("<h1>" + getResources().getString(R.string.alt_controls) + "</h1></br><p>" + getResources().getString(R.string.alt_controls_description)+ "</p>"));
    }

    public void toggleControls(View view){


    }
}