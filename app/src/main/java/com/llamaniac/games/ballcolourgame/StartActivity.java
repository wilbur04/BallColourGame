package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button startBtn, highScoreBtn, helpBtn;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        startBtn = (Button) findViewById(R.id.startButton);
        highScoreBtn = (Button) findViewById(R.id.highscoreButton);
        helpBtn = (Button) findViewById(R.id.helpButton);
        title = (TextView) findViewById(R.id.Title);
        startBtn.setOnClickListener(this);
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

        }
    }

    private void launchActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
    }
}
