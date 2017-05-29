package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button startBtn, highScoreBtn, helpBtn, doneBtn, sDoneButton, optionsBtn;
    private TextView title, options;
    private RelativeLayout helpLayout;
    private RelativeLayout optionsLayout;
    private Switch musicSwitch, soundSwitch, sillySoundSwitch;
    private String prefsName;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        prefsName = "ballPrefsFile";
        prefs = getSharedPreferences(prefsName, MODE_PRIVATE);
        editor = prefs.edit();


        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");

        startBtn = (Button) findViewById(R.id.startButton);
        highScoreBtn = (Button) findViewById(R.id.highscoreButton);
        helpBtn = (Button) findViewById(R.id.helpButton);
        title = (TextView) findViewById(R.id.Title);
        doneBtn = (Button) findViewById(R.id.doneButton);
        helpLayout = (RelativeLayout) findViewById(R.id.helpScreen);
        sDoneButton = (Button) findViewById(R.id.sDoneButton);
        optionsLayout = (RelativeLayout) findViewById(R.id.optionsScreen);
        options = (TextView) findViewById(R.id.options);
        optionsBtn = (Button) findViewById(R.id.optionsButton);
        musicSwitch = (Switch) findViewById(R.id.musicSwitch);
        musicSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("musicPrefs",!musicSwitch.isChecked());
                editor.commit();
            }
        });

        soundSwitch = (Switch) findViewById(R.id.allSoundSwitch);
        soundSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("mute",!soundSwitch.isChecked());
                editor.commit();
            }
        });

        sillySoundSwitch = (Switch) findViewById(R.id.sillySoundSwitch);
        sillySoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("sillySounds", sillySoundSwitch.isChecked());
                editor.commit();
            }
        });

        startBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);
        helpLayout.setOnClickListener(this);
        sDoneButton.setOnClickListener(this);
        optionsLayout.setOnClickListener(this);
        options.setOnClickListener(this);
        optionsBtn.setOnClickListener(this);

        helpLayout.bringToFront();
        optionsLayout.bringToFront();

        startBtn.setTypeface(customFont);
        highScoreBtn.setTypeface(customFont);
        helpBtn.setTypeface(customFont);
        title.setTypeface(customFont);
        optionsBtn.setTypeface(customFont);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                launchActivity(MainActivity.class);
                break;
            case R.id.helpButton:
                Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadein);
                helpLayout.startAnimation(fadeIn);
                helpLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.doneButton:
                Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeout);
                helpLayout.startAnimation(fadeOut);
                helpLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.optionsButton:
                Animation fadeIn2 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadein);
                optionsLayout.startAnimation(fadeIn2);
                optionsLayout.setVisibility(View.VISIBLE);
                musicSwitch.setChecked(!prefs.getBoolean("musicPrefs", true));
                soundSwitch.setChecked(!prefs.getBoolean("mute", true));
                sillySoundSwitch.setChecked(prefs.getBoolean("sillySounds",false));

                break;
            case R.id.sDoneButton:
                Animation fadeOut2 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeout);
                optionsLayout.startAnimation(fadeOut2);
                optionsLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }


    private void launchActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
    }

    @Override
    protected void onStop(){
        super.onStop();


    }
}
