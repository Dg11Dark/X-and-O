package com.example.user.xo;

import android.widget.Toast;

/**
 * Created by User on 26/02/2018.
 */

public class GameClass {

    private int[] ArrInt;
    private String[] ArrStr;
    private String CurrentPlayer;
    private boolean Draw;
    private final int Size = 9;
    private int Moves;
    private String Winner;
    private boolean GameOver;
    private int turn = (int) (Math.random() * 2 + 1);
    private String tav = "";


    public GameClass() {

        this.ArrInt = new int[Size];
        this.ArrStr = new String[Size];
        if(turn % 2 == 0){this.CurrentPlayer ="X";}
        if(turn % 2 == 1){this.CurrentPlayer ="O";}
        this.Draw = false;
        this.Moves = 0;
        this.Winner = "";
        this.GameOver = false;

        for (int i = 0; i < 9; i++)
            this.ArrInt[i] = 0;
        for (int i = 0; i < 9; i++)
            this.ArrStr[i] = "";
    }

    public void setMoves(int loc) {

        if (turn % 2 == 0) {
            if (ArrStr[loc].equals("")) {
                CurrentPlayer = ("O");
                tav="X";
                ArrStr[loc] = ("X");
                turn++;
                Moves++;
            }
        }else {
            if (ArrStr[loc].equals("")) {
                CurrentPlayer = ("X");
                tav="O";
                ArrStr[loc] = ("O");
                turn++;
                Moves++;
            }
        }
    }

    public boolean checkGameOver() {
        if ((ArrStr[0].equals(tav) && ArrStr[3].equals(tav) && ArrStr[6].equals(tav)) ||
                    (ArrStr[1].equals(tav) && ArrStr[4].equals(tav) && ArrStr[7].equals(tav)) ||
                    (ArrStr[2].equals(tav) && ArrStr[5].equals(tav) && ArrStr[8].equals(tav)) ||
                    (ArrStr[0].equals(tav) && ArrStr[1].equals(tav) && ArrStr[2].equals(tav)) ||
                    (ArrStr[3].equals(tav) && ArrStr[4].equals(tav) && ArrStr[5].equals(tav)) ||
                    (ArrStr[6].equals(tav) && ArrStr[7].equals(tav) && ArrStr[8].equals(tav)) ||
                    (ArrStr[0].equals(tav) && ArrStr[4].equals(tav) && ArrStr[8].equals(tav)) ||
                    (ArrStr[2].equals(tav) && ArrStr[4].equals(tav) && ArrStr[6].equals(tav)) ) {

                return true;

        }else if(Moves==9){
           Draw=true;
           return true;
        }else {

            return false;
        }
    }

    public String getWinner() {
        if(checkGameOver()) {
            if(Draw){
                return "its a Draw!";
            }else {
                return tav;
            }
        }else {
            return "";
        }
    }

    public String getCurrentPlayer() {
        return CurrentPlayer;
    }

    public String getTav() {
        return tav;
    }
}