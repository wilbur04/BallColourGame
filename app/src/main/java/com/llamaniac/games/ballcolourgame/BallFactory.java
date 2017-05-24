package com.llamaniac.games.ballcolourgame;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Wilbur on 23/05/2017.
 */

public class BallFactory {
    private ArrayList<Integer> colourlist;
    private ArrayList<Integer> yellowList , blueList, greenList, redList;





    public BallFactory(){
        colourlist = new ArrayList<>();
        redList = new ArrayList<>();
        yellowList = new ArrayList<>();
        blueList = new ArrayList<>();
        greenList = new ArrayList<>();
        addColour();
        createColourList(0);
    }

    public void createColourList(int level) {
        if (level <=6 ){
            for (int x=0; x <= level; x++) {
                colourlist.add(redList.get(x));
                colourlist.add(yellowList.get(x));
                colourlist.add(blueList.get(x));
                colourlist.add(greenList.get(x));
            }
        }

    }

    public void createColourList() {
                colourlist.add(redList.get(0));
                colourlist.add(yellowList.get(0));
                colourlist.add(blueList.get(0));
                colourlist.add(greenList.get(0));
    }

    private void addColour(){

        redList.add((Color.parseColor("#ff0000")));
        redList.add((Color.parseColor("#990000")));
        redList.add((Color.parseColor("#ff8080")));
        redList.add((Color.parseColor("#ffcccc")));
        redList.add((Color.parseColor("#cc0000")));
        redList.add((Color.parseColor("#ff6699")));
        redList.add((Color.parseColor("#ffe6e6")));

        yellowList.add(Color.parseColor("#ffff00"));
        yellowList.add(Color.parseColor("#808000"));
        yellowList.add(Color.parseColor("#ffbf00"));
        yellowList.add(Color.parseColor("#ff9900"));
        yellowList.add(Color.parseColor("#cc9900"));
        yellowList.add(Color.parseColor("#804d00"));
        yellowList.add(Color.parseColor("#ffecb3"));

        blueList.add(Color.parseColor("#0000ff"));
        blueList.add(Color.parseColor("#66ffff"));
        blueList.add(Color.parseColor("#333399"));
        blueList.add(Color.parseColor("#b3b3e6"));
        blueList.add(Color.parseColor("#000066"));
        blueList.add(Color.parseColor("#0099cc"));
        blueList.add(Color.parseColor("#00394d"));

        greenList.add(Color.parseColor("#00c957"));
        greenList.add(Color.parseColor("#b3ffb3"));
        greenList.add(Color.parseColor("#99e600"));
        greenList.add(Color.parseColor("#cccc00"));
        greenList.add(Color.parseColor("#999966"));
        greenList.add(Color.parseColor("#80ffcc"));
        greenList.add(Color.parseColor("#003300"));
    }



    public void createBall(){
        int rand = getRandom(0,colourlist.size()-1);
        Ball ball = new Ball(colourlist.get(rand));
        BallStore.INSTANCE.addBall(ball);
    }


    private int getRandom(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min +1)+min;
    }

    public int getActiveColour(){
        int rand = getRandom(0,colourlist.size()-1);
        int colour = colourlist.get(rand);
        colourlist.remove(rand);
        return  colour;
    }


}
