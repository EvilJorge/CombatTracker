package com.example.android.combattracker;

/**
 * Created by cpalomares on 9/11/2017.
 */

public class Character {
    private String name; // Character or group name
    private int initiative; // Character's initiative
    private int turnStatus; // Character's turn status (Action, Ready, Delay)

    // Status constants
    public static final int ACTION = 1;
    public static final int READY = 2;
    public static final int DELAY = 3;

    public Character(String charName){
        name = charName;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public int getTurnStatus(){
        return turnStatus;
    }

    public void takeAction(){
        turnStatus = ACTION;
    }

    public void readyAction(){
        turnStatus = READY;
    }

    public void delayAction(){
        turnStatus = DELAY;
    }

    public void clearAction(){
        turnStatus = 0;
    }
}
