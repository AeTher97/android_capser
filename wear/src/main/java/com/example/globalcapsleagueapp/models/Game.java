package com.example.globalcapsleagueapp.models;

public class Game {

    private int playerPoints;
    private int playerSinks;

    private int opponentPoints;
    private int opponentSinks;

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public int getPlayerSinks() {
        return playerSinks;
    }

    public void setPlayerSinks(int playerSinks) {
        this.playerSinks = playerSinks;
    }

    public int getOpponentPoints() {
        return opponentPoints;
    }

    public void setOpponentPoints(int opponentPoints) {
        this.opponentPoints = opponentPoints;
    }

    public int getOpponentSinks() {
        return opponentSinks;
    }

    public void setOpponentSinks(int opponentSinks) {
        this.opponentSinks = opponentSinks;
    }
}
