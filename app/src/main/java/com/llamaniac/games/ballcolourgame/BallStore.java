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

    public void addBall(Ball ball){
        store.add(ball);
    }

    public Ball getBallsByIndex(int i){
        return store.get(i);
    }

    public void removeBallByIndex(int i){
        store.remove(i);
    }

    public void emptyLeast(){
        store.clear();
    }

    public boolean islow(){
        if (store.size()<=1){
            return true;
        }
        return false;
    }




}
