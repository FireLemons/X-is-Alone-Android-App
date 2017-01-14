package xyz.smaeul.xisalone;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class OptionsActivity extends AppCompatActivity {

    private SharedPreferences settings;
    private SharedPreferences.Editor settingEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        CheckBox altControls = (CheckBox)findViewById(R.id.checkbox_controls);
        altControls.setText(Html.fromHtml("<h1>" + getResources().getString(R.string.alt_controls) + "</h1></br><p>" + getResources().getString(R.string.alt_controls_description)+ "</p>"));

        settings = getSharedPreferences(getString(R.string.preference_file), MODE_PRIVATE);

        if(settings != null){

            altControls.setChecked(settings.getBoolean("controls", true));
            settingEditor = settings.edit();
        }
    }

    @Override
    protected void onStop(){

        super.onStop();
        settingEditor.commit();
    }

    public void toggleControls(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch(view.getId()){
            case R.id.checkbox_controls:
                if(checked){
                    settingEditor.putBoolean("controls", true);
                }else{
                    settingEditor.putBoolean("controls", false);
                }

                break;
        }
    }
}