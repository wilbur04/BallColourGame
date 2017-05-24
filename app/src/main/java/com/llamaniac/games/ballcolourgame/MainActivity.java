package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, restart, home;
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
    private int speed;
    private MediaPlayer mpSuccess, mpWrong;
    private View homeContainer, restartContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        restart = (Button) findViewById(R.id.restart);
        home = (Button) findViewById(R.id.home);
        scoreView = (TextView) findViewById(R.id.score);
        livesView = (TextView) findViewById(R.id.lives);
        currColourCircle1 = (ImageView) findViewById(R.id.curColour1);
        currColourCircle2 = (ImageView) findViewById(R.id.curColour2);
        currColourCircle3 = (ImageView) findViewById(R.id.curColour3);
        tickMark = (ImageView) findViewById(R.id.tickMark);
        crossMark = (ImageView) findViewById(R.id.crossMark);
        homeContainer = findViewById(R.id.homeContainer);
        restartContainer = findViewById(R.id.restartContainer);

        button.setOnClickListener(this);
        restart.setOnClickListener(this);
        home.setOnClickListener(this);


        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        scoreView.setTypeface(customFont);
        livesView.setTypeface(customFont);
        restart.setTypeface(customFont);
        home.setTypeface(customFont);

        this.score = 0;
        this.lives = 3;
        this.speed = 1000;

        activeColour1 = Color.parseColor("#ffff00");
        activeColour2 = Color.parseColor("#ffffff");
        activeColour3 = Color.parseColor("#ffffff");
        isColour2active = false;
        isColour3active = false;

        livesView.setText("Lives " + lives);

        createBalls(0);

        mpSuccess = MediaPlayer.create(this, R.raw.success);
        mpWrong = MediaPlayer.create(this, R.raw.wrong);

        t = new Thread() {
            int scoreStore = 0, level = 0;

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if (BallStore.INSTANCE.islow()){
                            createBalls(level);
                        }
                        if (!gameOver && lives > 0) {
                            Thread.sleep(speed);
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
                                            livesView.setText("Lives " + lives);
                                            if (lives == 0) {
                                                gameOver = true;
                                                restore(restartContainer);
                                                restore(homeContainer);
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
                    if (speed > 500) {
                        this.speed = speed - 10;
                    }else{

                    }
                    if (!gameOver) {
                        if (curColor == activeColour1 || curColor == activeColour2 || curColor == activeColour3 ) {
                            button.setClickable(false);
                            restore(tickMark);
                            mpSuccess.start();
                            score++;
                            scoreView.setText("" + score);
                            curColor = Color.parseColor("#f5f5f5");

                            if (score == 4){  // todo change
                                isColour2active = true;
                                restore(currColourCircle2);
                            }if (score == 8){ //todo change
                                isColour3active = true;
                                restore(currColourCircle3);
                            }

                        } else {
                            mpWrong.start();
                            restore(crossMark);
                            button.setClickable(false);
                            lives--;
                            livesView.setText("Lives " + lives);
                            if (lives == 0) {
                                this.gameOver = true;
                                restore(restartContainer);
                                restore(homeContainer);
                                t.interrupt();
                            }
                        }
                    }

                    break;

                case R.id.restart:
                    hide(restartContainer);
                    hide(homeContainer);
                    finish();
                    startActivity(getIntent());
                    break;

                case R.id.home:
                    launchActivity(StartActivity.class);
                    break;

                }
        }


    public Handler handler = new Handler(){
      public void handleMessage(Message m){
          hide(tickMark);
          hide(crossMark);
          button.setClickable(true);
      }
    };

    private void launchActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        this.startActivity(intent);
    }

    private void hide(View v) {
        v.setVisibility(View.INVISIBLE);
    }
    private void restore(View v) {
        v.setVisibility(View.VISIBLE);
    }
}
