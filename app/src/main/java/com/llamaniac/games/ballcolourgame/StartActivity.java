package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button startBtn, highScoreBtn, helpBtn, doneBtn;
    private TextView title;
    private RelativeLayout helpLayout;

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

        startBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);
        helpLayout.setOnClickListener(this);


        helpLayout.setVisibility(View.INVISIBLE);
        helpLayout.bringToFront();

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
                helpLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.doneButton:
                helpLayout.setVisibility(View.INVISIBLE);
                break;

        }
    }

    private void launchActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
    }
}
