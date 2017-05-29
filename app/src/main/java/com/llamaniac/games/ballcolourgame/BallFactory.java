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
    private ArrayList<ArrayList<Integer>> bigList;
    private int previousColour;


    public BallFactory(){
        colourlist = new ArrayList<>();
        redList = new ArrayList<>();
        yellowList = new ArrayList<>();
        blueList = new ArrayList<>();
        greenList = new ArrayList<>();
        bigList = new ArrayList<>();
        addColour();
        createColourList(0);
        previousColour = 0;
    }

    public void createColourList(int level) {
        if (level > 6 ){
            level = 6;
        }
        for (int y=0; y <= bigList.size()-1; y++) {
            for (int x = 0; x <= level; x++) {
                colourlist.add(bigList.get(y).get(x));
            }
        }System.out.println(colourlist);
    }

    public void clearList(){
        if (!colourlist.isEmpty()) {
            colourlist.clear();
        }
    }


    public void removeColour() {
        if (bigList.size() >= 1) {
            System.out.println(bigList.size());
            int rand = getRandom(0,bigList.size()-1);
            bigList.remove(rand);
        }
    }


    private void addColour(){

        redList.add((Color.parseColor("#ff3333")));
        redList.add((Color.parseColor("#990000")));
        redList.add((Color.parseColor("#ff8080")));
        redList.add((Color.parseColor("#ffcccc")));
        redList.add((Color.parseColor("#cc0000")));
        redList.add((Color.parseColor("#ff6699")));
        bigList.add(redList);

        yellowList.add(Color.parseColor("#ffff00"));
        yellowList.add(Color.parseColor("#808000"));
        yellowList.add(Color.parseColor("#ffbf00"));
        yellowList.add(Color.parseColor("#ff9900"));
        yellowList.add(Color.parseColor("#cc9900"));
        yellowList.add(Color.parseColor("#804d00"));
        yellowList.add(Color.parseColor("#ffecb3"));
        bigList.add(yellowList);

        blueList.add(Color.parseColor("#0000ff"));
        blueList.add(Color.parseColor("#66ffff"));
        blueList.add(Color.parseColor("#333399"));
        blueList.add(Color.parseColor("#b3b3e6"));
        blueList.add(Color.parseColor("#000066"));
        blueList.add(Color.parseColor("#0099cc"));
        blueList.add(Color.parseColor("#00394d"));
        bigList.add(blueList);

        greenList.add(Color.parseColor("#00c957"));
        greenList.add(Color.parseColor("#b3ffb3"));
        greenList.add(Color.parseColor("#99e600"));
        greenList.add(Color.parseColor("#cccc00"));
        greenList.add(Color.parseColor("#999966"));
        greenList.add(Color.parseColor("#80ffcc"));
        greenList.add(Color.parseColor("#003300"));
        bigList.add(greenList);
    }


    public void createBall(){
        ArrayList<Integer> tempColors = new ArrayList<>();
        for (int a: colourlist) {
            if (previousColour == 0) {
                tempColors.add(a);
            } else if (previousColour != a) {
                tempColors.add(a);
            }
        }

        int rand = getRandom(0,tempColors.size()-1);
        Ball ball = new Ball(tempColors.get(rand));
        BallStore.INSTANCE.addBall(ball);
        previousColour = ball.getColour();
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

    public boolean isColor(int col, String color) {
        if (color.equals("yellow")) {
            if (yellowList.contains(col)) {
                return true;
            }
        } else if (color.equals("blue")) {
            if (blueList.contains(col)) {
                return true;
            }
        } else if (color.equals("green")) {
            if (greenList.contains(col)) {
                return true;
            }
        } else if (color.equals("red")) {
            if (redList.contains(col)) {
                return true;
            }
        }
        return false;
    }


}
