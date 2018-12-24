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
		String scoreDescription = calculateScoreDescription(tennisGame.getPlayerOne().getScore(), tennisGame.getPlayerTwo().getScore());
		OutputResponse outputResponse = mappingOutputResponse(tennisGame, scoreDescription, isPlayerOneWonCurrentService);
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


	/**
	 *
	 * logic used to calculate score Description
	 *
	 * @param playerOneScore
	 * @param playerTwoScore
	 * @return
	 */
	private String calculateScoreDescription(int playerOneScore, int playerTwoScore) {
		logger.debug("calculateScoreDescription called");

		if (!(playerOneScore <= 3 && playerTwoScore <= 3 && playerOneScore + playerTwoScore != 6)) {
			if (Math.abs(playerOneScore - playerTwoScore) >= 2) {
				return "WON_THE_SET";
			} else if (playerOneScore == playerTwoScore) {
				return " deuce";
			} else {
				return " advantage ";
			}
		}

		return null;
	}
	/**
	 *
	 * Identify Current Service Winner
	 *
	 * @param playerOneName
	 * @param playerTwoName
	 * @param isPlayerOneWonCurrentService
	 * @return
	 */
	private String getcurrentServiceWinner(String playerOneName, String playerTwoName,
										   boolean isPlayerOneWonCurrentService) {
		logger.debug("getcurrentServiceWinner called");

		if (isPlayerOneWonCurrentService)
			return playerOneName;
		else
			return playerTwoName;

	}

	/**
	 *
	 * Mapping all fields to display in output
	 *
	 * @param tennisGame
	 * @param scoreDescription
	 * @param isPlayerOneWonCurrentService
	 * @return
	 */
	private OutputResponse mappingOutputResponse(TennisGame tennisGame, String scoreDescription,
												 boolean isPlayerOneWonCurrentService) {
		logger.debug("mappingOutputResponse called");

		String currentServiceWinner = getcurrentServiceWinner(tennisGame.getPlayerOne().getName(),
				tennisGame.getPlayerTwo().getName(),
				isPlayerOneWonCurrentService);
		StringBuilder pointDetails = new StringBuilder();
		pointDetails.append("POINT_GOES_TO");
		pointDetails.append(currentServiceWinner);
		OutputResponse outputResponse = new OutputResponse ();
		outputResponse.setCurrentService(pointDetails.toString());
		outputResponse.setPlayers(tennisGame.getPlayersName());

		if (tennisGame.getPlayerOne().getScore() == 0 && tennisGame.getPlayerTwo().getScore() == 0) {
			outputResponse.setGameStatus("GAME_STARTED");
			outputResponse.setCurrentService("GET_READY");
		} else {
			outputResponse.setGameStatus("GAME_IN_PROGRESS");
		}

		return outputResponse;
	}


}
