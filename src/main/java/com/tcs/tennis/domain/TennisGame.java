package com.tcs.tennis.domain;

import org.springframework.stereotype.Component;

@Component
public class TennisGame {

    private Player playerOne;
    private Player playerTwo;

    public TennisGame(Player player1, Player player2){
        this.playerOne = player1;
        this.playerTwo = player2;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

}
