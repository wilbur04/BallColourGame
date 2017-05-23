package com.llamaniac.games.ballcolourgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        restart = (Button) findViewById(R.id.restart);
        button.setOnClickListener(this);
        restart.setOnClickListener(this);

        BallFactory bf = new BallFactory();
        for (int i = 0; i < 100; i++) {
            bf.createBall();
        }

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int color = BallStore.INSTANCE.getBallsByIndex(0).getColour();
                                BallStore.INSTANCE.removeBallByIndex(0);
                                button.setBackgroundColor(color);
                                // change color of button
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                //if button color = active color
                    //score++
                    //change color
                //else
                    //game over -> restart button becomes visible
                break;

            case R.id.restart:
                Intent mIntent = getIntent();
                finish();
                startActivity(mIntent);
                break;
        }
    }

}
