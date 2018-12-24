package com.tcs.tennis.domain;

public class TennisGame {

    private Player playerOne;
    private Player playerTwo;
    public final String players;

    public TennisGame(Player player1, Player player2){
        this.playerOne = player1;
        this.playerTwo = player2;
        this.players = getPlayersName();
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public String getPlayersName() {
        StringBuilder playersName = new StringBuilder(playerOne.getName());
        playersName.append(" VS ").append(playerTwo.getName());
        return playersName.toString();
    }
}
