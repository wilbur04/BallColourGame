package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, restart;
    private int score, curColor;
    private int activeColour1, activeColour2, activeColour3; // your current active colour
    private boolean gameOver;

    private TextView scoreView;
    private TextView livesView;
    private ImageView currColourCircle1, currColourCircle2,currColourCircle3;
    private ImageView tickMark, crossMark;
    private int lives;
    private BallFactory bf;
    private boolean  isColour2active, isColour3active;
    private Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        restart = (Button) findViewById(R.id.restart);
        scoreView = (TextView) findViewById(R.id.score);
        livesView = (TextView) findViewById(R.id.lives);
        currColourCircle1 = (ImageView) findViewById(R.id.curColour1);
        currColourCircle2 = (ImageView) findViewById(R.id.curColour2);
        currColourCircle3 = (ImageView) findViewById(R.id.curColour3);
        tickMark = (ImageView) findViewById(R.id.tickMark);
        crossMark = (ImageView) findViewById(R.id.crossMark);

        button.setOnClickListener(this);
        restart.setOnClickListener(this);

        this.score = 0;
        this.lives = 3;

        activeColour1 = Color.parseColor("#ffff00");
        activeColour2 = Color.parseColor("#ffffff");
        activeColour3 = Color.parseColor("#ffffff");
        isColour2active = false;
        isColour3active = false;


        livesView.setText("Lives: " + lives);

        createBalls(0);


        t = new Thread() {
            int scoreStore =0, level=0;

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if (!gameOver && lives > 0) {
                            Thread.sleep(1000);
                            handler.sendEmptyMessage(0);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!gameOver) {
                                        if (score == scoreStore + 2) {
                                            scoreStore = score;
                                            level++;
                                            createBalls(level);
                                        }
                                        if (curColor == activeColour1 || curColor == activeColour2 || curColor == activeColour3) {
                                            lives--;
                                            livesView.setText("Lives: " + lives);
                                            if (lives == 0) {
                                                gameOver = true;
                                                restart.setVisibility(View.VISIBLE);
                                                t.interrupt();
                                            }
                                        }
                                        curColor = BallStore.INSTANCE.getBallsByIndex(0).getColour();
                                        BallStore.INSTANCE.removeBallByIndex(0);
                                        button.setBackgroundColor(curColor);
                                        // change color of button
                                    }
                                }

                            });
                        }
                    }
                } catch (InterruptedException e) {
                    try {
                        throw new InterruptedException();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    private void createBalls(int level){
        BallStore.INSTANCE.emptyLeast();
        bf = new BallFactory();
        bf.createColourList(level);

        for (int i = 0; i < 100; i++) {
            bf.createBall();
        }

        activeColour1 = bf.getActiveColour();
        currColourCircle1.setBackgroundColor(activeColour1);
        if (isColour2active) {
            activeColour2 = bf.getActiveColour();
            currColourCircle2.setBackgroundColor(activeColour2);
        }if (isColour3active) {
            activeColour3 = bf.getActiveColour();
            currColourCircle3.setBackgroundColor(activeColour3);
        }
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    if (!gameOver) {
                        if (curColor == activeColour1 || curColor == activeColour2 || curColor == activeColour3 ) {
                            button.setClickable(false);
                            tickMark.setVisibility(View.VISIBLE);
                            score++;
                            scoreView.setText("" + score);
                            curColor = Color.parseColor("#f5f5f5");

                            if (score == 4){  // todo change
                                isColour2active =true;
                                currColourCircle2.setVisibility(View.VISIBLE);
                            }if (score == 8){ //todo change
                                isColour3active = true;
                                currColourCircle3.setVisibility(View.VISIBLE);
                            }

                        } else {
                            crossMark.setVisibility(View.VISIBLE);
                            button.setClickable(false);
                            lives--;
                            livesView.setText("Lives: " + lives);
                            if (lives == 0) {
                                this.gameOver = true;
                                restart.setVisibility(View.VISIBLE);
                                t.interrupt();
                            }
                        }

                    }

                    break;

                case R.id.restart:
                    restart.setVisibility(View.INVISIBLE);
                    finish();
                    startActivity(getIntent());
                    break;
               }

        }


    public Handler handler = new Handler(){
      public void handleMessage(Message m){
          tickMark.setVisibility(View.INVISIBLE);
          crossMark.setVisibility(View.INVISIBLE);
          button.setClickable(true);
      }
    };
}
