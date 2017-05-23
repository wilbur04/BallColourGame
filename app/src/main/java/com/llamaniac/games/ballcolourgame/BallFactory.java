package com.llamaniac.games.ballcolourgame;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Wilbur on 23/05/2017.
 */

public class BallFactory {
    private ArrayList<Integer> colourlist;

    private BallFactory(){
        colourlist = new ArrayList<>();
        addColour();
    }

    private void addColour(){
        colourlist.add(Color.parseColor("#0000ff"));//blue
        colourlist.add(Color.parseColor("#00c957"));//green
        colourlist.add(Color.parseColor("#ffff00"));//yellow
        colourlist.add(Color.parseColor("#ff0000"));//red
        colourlist.add(Color.parseColor("#7171c6"));//purple
    }

    private void createBall(){
        int rand = getRandom(0,colourlist.size()-1);
        //Ball ball = new Ball(new Color(colourlist.get(rand)));
    }



    private int getRandom(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min +1)+min;
    }


}
