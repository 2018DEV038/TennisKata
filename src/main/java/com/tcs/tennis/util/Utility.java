package com.tcs.tennis.util;

public class Utility {

    Utility() {
	}

	/**
	 * 
	 * Play for player1 and player2
	 * 
	 * Random value generated for player1 and player2 Compare and decide winner
	 * 
	 * @return boolean
	 */
	public static boolean play() {

		double player1 = Math.random();
		double player2 = Math.random();

		return player1 > player2;
	}

}
