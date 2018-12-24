package com.tcs.tennis.service;

import com.tcs.tennis.domain.Player;
import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;
import com.tcs.tennis.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TennisServiceImpl implements TennisService{

	   private final Logger logger = LoggerFactory.getLogger(this.getClass());


	/**
	 * getScoreDetails Service to calculate add scoOre point, Game Status,Game  Score and Current Service Result
	 * 
	 */
	public OutputResponse getScoreDetails(TennisGame tennisGame) {
		boolean isPlayerOneWonCurrentService = Utility.play();
		addPlayerScoreCount(tennisGame.getPlayerOne(), tennisGame.getPlayerTwo(), isPlayerOneWonCurrentService);
		OutputResponse outputResponse = new OutputResponse();
		return outputResponse;

	}

	/**
	 *
	 * Add score to individual player based on Play service Output
	 *
	 * @param playerOne
	 * @param playerTwo
	 * @param isPlayerOneWon
	 */
	private void addPlayerScoreCount(Player playerOne, Player playerTwo, boolean isPlayerOneWon) {
		logger.debug("addPlayerScoreCount called");

		if (isPlayerOneWon) {
			playerOne.addScoreByOne();
		} else {
			playerTwo.addScoreByOne();
		}
		if (playerOne.getScore() == -1 || playerTwo.getScore() == -1) {
			playerOne.setScore(0);
			playerTwo.setScore(0);
		}
	}

}
