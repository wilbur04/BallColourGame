package com.llamaniac.games.ballcolourgame;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Wilbur on 23/05/2017.
 */

public enum BallStore {
    INSTANCE;

    private ArrayList<Ball> store ;

    private BallStore(){
        store = new ArrayList<Ball>();
    }

    private void addBall(Ball ball){
        store.add(ball);
    }

    private Ball getBallsByIndex(int i){
        return store.get(i);
    }

    private void removeBallByIndex(int i){
        store.remove(i);
    }




}
