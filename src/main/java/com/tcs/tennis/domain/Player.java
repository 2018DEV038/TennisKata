package com.tcs.tennis.domain;

public class Player {

    private int score = -1;
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void addScoreByOne() {
        this.score = this.score + 1;
    }

    private String name;
    public String getName() {
        return name;
    }

    public Player(String name) {
       this.name = name;
    }

    public void reset(int score){
        this.score = score;
    }

}
