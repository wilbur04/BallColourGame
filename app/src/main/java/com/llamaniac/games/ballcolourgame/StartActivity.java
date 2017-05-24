package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button startBtn, highScoreBtn, helpBtn, doneBtn, sDoneButton, soundButton;
    private TextView title, soundOptions;
    private RelativeLayout helpLayout;
    private RelativeLayout soundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");

        startBtn = (Button) findViewById(R.id.startButton);
        highScoreBtn = (Button) findViewById(R.id.highscoreButton);
        helpBtn = (Button) findViewById(R.id.helpButton);
        title = (TextView) findViewById(R.id.Title);
        doneBtn = (Button) findViewById(R.id.doneButton);
        helpLayout = (RelativeLayout) findViewById(R.id.helpScreen);
        sDoneButton = (Button) findViewById(R.id.sDoneButton);
        soundLayout = (RelativeLayout) findViewById(R.id.soundScreen);
        soundOptions = (TextView) findViewById(R.id.soundOptions);
        soundButton = (Button) findViewById(R.id.soundButton);

        startBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);
        helpLayout.setOnClickListener(this);
        sDoneButton.setOnClickListener(this);
        soundLayout.setOnClickListener(this);
        soundOptions.setOnClickListener(this);
        soundButton.setOnClickListener(this);

        helpLayout.setVisibility(View.INVISIBLE);
        helpLayout.bringToFront();
        soundLayout.setVisibility(View.INVISIBLE);
        soundLayout.bringToFront();

        startBtn.setTypeface(customFont);
        highScoreBtn.setTypeface(customFont);
        helpBtn.setTypeface(customFont);
        title.setTypeface(customFont);

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
            case R.id.soundButton:
                Animation fadeIn2 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadein);
                helpLayout.startAnimation(fadeIn2);
                helpLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.sDoneButton:
                Animation fadeOut2 = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fadeout);
                helpLayout.startAnimation(fadeOut2);
                helpLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void launchActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
    }
}
