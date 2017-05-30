package com.llamaniac.games.ballcolourgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ScoresActivity extends AppCompatActivity  implements View.OnClickListener{
    private TextView score_title, name1, name2, name3, name4, name5, name6, name7, name8, name9, name10,
            score1, score2, score3, score4, score5, score6, score7, score8, score9, score10;
    public static Handler handler;
    private Typeface customFont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        customFont = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        score_title = (TextView) findViewById(R.id.scores_title);
        score_title.setTypeface(customFont);
        init();

        if (isNetworkAvailable()) {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("get");
        } else {
            name1.setText("ERROR");
            name2.setText("NO INTERNET ACCESS");
            name3.setText("CANNOT GET SCORES");
        }

        handler = new Handler(){
            public void handleMessage(Message m){
                SharedPreferences prefs = getSharedPreferences("global", MODE_PRIVATE);
                String scores = prefs.getString("scores", "");
                String[] scoresData = scores.split("#");
                String linedScores = "";
                String[][] scoreArray = new String[10][2];
                for (int i=0; i<10; i++) {
                    for (int j=0; j<2; j++) {
                        scoreArray[i][j] = "-";
                    }
                }
                int count = 0;
                for (String s: scoresData) {
                    String tempArray[] = s.split("\\s+");
                    scoreArray[count][0] = tempArray[0];
                    scoreArray[count][1] = tempArray[1];
                    count++;
                }

                name1.setText(scoreArray[0][0]);
                score1.setText(scoreArray[0][1]);

                name2.setText(scoreArray[1][0]);
                score2.setText(scoreArray[1][1]);

                name3.setText(scoreArray[2][0]);
                score3.setText(scoreArray[2][1]);

                name4.setText(scoreArray[3][0]);
                score4.setText(scoreArray[3][1]);

                name5.setText(scoreArray[4][0]);
                score5.setText(scoreArray[4][1]);

                name6.setText(scoreArray[5][0]);
                score6.setText(scoreArray[5][1]);

                name7.setText(scoreArray[6][0]);
                score7.setText(scoreArray[6][1]);

                name8.setText(scoreArray[7][0]);
                score8.setText(scoreArray[7][1]);

                name9.setText(scoreArray[8][0]);
                score9.setText(scoreArray[8][1]);

                name10.setText(scoreArray[9][0]);
                score10.setText(scoreArray[9][1]);

            }
        };
    }


    private void init() {
        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        name3 = (TextView) findViewById(R.id.name3);
        name4 = (TextView) findViewById(R.id.name4);
        name5 = (TextView) findViewById(R.id.name5);
        name6 = (TextView) findViewById(R.id.name6);
        name7 = (TextView) findViewById(R.id.name7);
        name8 = (TextView) findViewById(R.id.name8);
        name9 = (TextView) findViewById(R.id.name9);
        name10 = (TextView) findViewById(R.id.name10);

        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        score3 = (TextView) findViewById(R.id.score3);
        score4 = (TextView) findViewById(R.id.score4);
        score5 = (TextView) findViewById(R.id.score5);
        score6 = (TextView) findViewById(R.id.score6);
        score7 = (TextView) findViewById(R.id.score7);
        score8 = (TextView) findViewById(R.id.score8);
        score9 = (TextView) findViewById(R.id.score9);
        score10 = (TextView) findViewById(R.id.score10);


        name1.setTypeface(customFont);
        name2.setTypeface(customFont);
        name3.setTypeface(customFont);
        name4.setTypeface(customFont);
        name5.setTypeface(customFont);
        name6.setTypeface(customFont);
        name7.setTypeface(customFont);
        name8.setTypeface(customFont);
        name9.setTypeface(customFont);
        name10.setTypeface(customFont);
        score1.setTypeface(customFont);
        score2.setTypeface(customFont);
        score3.setTypeface(customFont);
        score4.setTypeface(customFont);
        score5.setTypeface(customFont);
        score6.setTypeface(customFont);
        score7.setTypeface(customFont);
        score8.setTypeface(customFont);
        score9.setTypeface(customFont);
        score10.setTypeface(customFont);

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

    @Override
    protected void onStop(){
        super.onStop();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
