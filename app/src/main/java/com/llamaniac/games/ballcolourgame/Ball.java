package com.llamaniac.games.ballcolourgame;

import android.graphics.Color;

/**
 * Created by jeric on 23/05/2017.
 */

public class Ball {
    Color bColour;

    public Ball(Color colour){
        bColour = colour;
    }

    public Color getColour(){
        return bColour;
    }
}
