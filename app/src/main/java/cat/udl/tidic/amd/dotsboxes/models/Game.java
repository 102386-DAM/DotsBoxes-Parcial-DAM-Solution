package cat.udl.tidic.amd.dotsboxes.models;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static String TAG = "GameModel";

    public Player playerRed, playerBlue;
    public Board board;

    public Game(Board board) {
        this.board = board;
        playerRed = new Player("red", Color.RED);
        playerBlue = new Player("blue", Color.BLUE);
    }

    public void nextPlayer(){

        if (currentPlayer().equals(playerRed)){
            playerRed.setPlaying(false);
            playerBlue.setPlaying(true);
        }else{
            playerRed.setPlaying(true);
            playerBlue.setPlaying(false);
        }

    }

    public Player currentPlayer(){
        if (playerRed.isPlaying()){
            return playerRed;
        }else if (playerBlue.isPlaying()){
            return playerBlue;
        }else{
            return null;
        }
    }







}
