package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, restart;
    private int score, curColor;
    private int activeColour; // your current active colour
    private boolean gameOver;

    private TextView scoreView;
    private TextView livesView;
    private int lives = 3;
    private int activeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        restart = (Button) findViewById(R.id.restart);
        scoreView = (TextView) findViewById(R.id.score);
        livesView = (TextView) findViewById(R.id.lives);
        button.setOnClickListener(this);
        restart.setOnClickListener(this);
        this.score = 0;

        activeColour = Color.parseColor("#ffff00");

        livesView.setText("Lives: " + lives);

        BallFactory bf = new BallFactory();
        for (int i = 0; i < 100; i++) {
            bf.createBall();
        }

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        if (!gameOver && lives >0) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (curColor == activeColour){
                                        lives--;
                                        livesView.setText("Lives: " + lives);
                                    }
                                    curColor = BallStore.INSTANCE.getBallsByIndex(0).getColour();
                                    BallStore.INSTANCE.removeBallByIndex(0);
                                    while (BallStore.INSTANCE.getBallsByIndex(0).getColour() == curColor) {
                                        BallStore.INSTANCE.removeBallByIndex(0);
                                    }
                                    button.setBackgroundColor(curColor);

                                    // change color of button
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button:
                    if (!gameOver) {
                        if (curColor == activeColour) {
                            curColor = BallStore.INSTANCE.getBallsByIndex(0).getColour();
                            BallStore.INSTANCE.removeBallByIndex(0);
                            button.setBackgroundColor(curColor);
                            score++;
                            scoreView.setText("" + score);
                            System.out.println(score);
                        } else {
                            lives--;
                            livesView.setText("Lives: " + lives);
                            if (lives == 0) {
                                this.gameOver = true;
                                restart.setVisibility(View.VISIBLE);
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

}
