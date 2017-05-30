package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button startBtn, highScoreBtn, helpBtn, doneBtn, sDoneButton, optionsBtn, createUsernameBtn;
    private TextView title, options, usernameView;
    private RelativeLayout helpLayout;
    private RelativeLayout optionsLayout;
    private RelativeLayout usernameLayout;
    private Switch musicSwitch, soundSwitch, sillySoundSwitch;
    private String prefsName;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private boolean hasUsername;
    private String username;
    private EditText createUsername;
    private Snackbar snackbar;

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
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.usernameScreen);
        usernameLayout = (RelativeLayout) findViewById(R.id.usernameScreen);
        createUsername = (EditText) findViewById(R.id.usernameCreateField);
        createUsernameBtn = (Button) findViewById(R.id.createUsernameButton);
        createUsernameBtn.setOnClickListener(this);
        usernameView = (TextView) findViewById(R.id.usernameField);
        snackbar = snackbar.make(layout, "snackbar", Snackbar.LENGTH_LONG);
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
        highScoreBtn.setOnClickListener(this);

        helpLayout.bringToFront();
        optionsLayout.bringToFront();

        startBtn.setTypeface(customFont);
        highScoreBtn.setTypeface(customFont);
        helpBtn.setTypeface(customFont);
        title.setTypeface(customFont);
        optionsBtn.setTypeface(customFont);

        hasUsername = prefs.getBoolean("hasUsername", false);
        if (hasUsername){
            username = prefs.getString("username","player");
        } else {
            usernameLayout.setVisibility(View.VISIBLE);
        }
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
                musicSwitch.setChecked(!prefs.getBoolean("musicPrefs", false));
                soundSwitch.setChecked(!prefs.getBoolean("mute", false));
                sillySoundSwitch.setChecked(prefs.getBoolean("sillySounds",false));
                usernameView.setText(username);

                break;
            case R.id.sDoneButton:
                Animation fadeOut2 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeout);
                optionsLayout.startAnimation(fadeOut2);
                optionsLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.highscoreButton:
                launchActivity(ScoresActivity.class);
                break;
            case R.id.nDoneButton:
                if (createUsername.getText().toString().equals("")){
                    snackbar.setText("Please enter a name");
                    snackbar.show();
                } else {
                    editor.putString("username", createUsername.getText().toString());
                    editor.putBoolean("hasUsername", true);
                    editor.commit();
                    Animation fadeOut3 = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fadeout);
                    usernameLayout.startAnimation(fadeOut3);
                    usernameLayout.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.createUsernameButton:
                System.out.println("i should allow yu to create a new username");
                Animation fadeOut4 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeout);
                optionsLayout.startAnimation(fadeOut4);
                optionsLayout.setVisibility(View.INVISIBLE);
                Animation fadeIn3 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadein);
                usernameLayout.startAnimation(fadeIn3);
                usernameLayout.setVisibility(View.VISIBLE);
                createUsername.setText(username);
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
