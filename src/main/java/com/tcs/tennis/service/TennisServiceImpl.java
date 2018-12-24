package com.tcs.tennis.service;

import com.tcs.tennis.domain.Player;
import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;
import com.tcs.tennis.util.Constants;
import com.tcs.tennis.util.PointDescriptionEnum;
import com.tcs.tennis.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TennisServiceImpl implements TennisService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Map<String, TennisGame> TENNIS_GAME_MAP = new HashMap<>();

	/**
	 * getScoreDetails Service to calculate add score point, Game Status,Game  Score and Current Service Result
	 *
	 */
	public OutputResponse getScoreDetails(TennisGame tennisGame) {
		logger.debug("getScoreDetails called");
		boolean isPlayerOneWonCurrentService = Utility.play();
		addPlayerScoreCount(tennisGame.getPlayerOne(), tennisGame.getPlayerTwo(), isPlayerOneWonCurrentService);
		String scoreDescription = calculateScoreDescription(tennisGame.getPlayerOne().getScore(), tennisGame.getPlayerTwo().getScore());
		OutputResponse outputResponse = mappingOutputResponse(tennisGame, scoreDescription, isPlayerOneWonCurrentService);
		resetGame(tennisGame.getPlayerOne(), tennisGame.getPlayerTwo(), outputResponse);

		return outputResponse;

	}

	/**
	 *
	 * Generate unique Id for player set  and Stored in HashMap static variable
	 *
	 *
	 */
	public String registerGame(String playerOneName, String playerTwoName){

		TennisGame tennisGame = new TennisGame(new Player(playerOneName), new Player(playerTwoName));
		String uniqueId = UUID.randomUUID().toString();
		synchronized (TENNIS_GAME_MAP) {
			if(null == TENNIS_GAME_MAP.get(uniqueId)) {
				TENNIS_GAME_MAP.put(uniqueId, tennisGame);
			}
		}
		return uniqueId;
	}

	/**
	 *
	 * get player details based on unique value
	 *
	 */
	public TennisGame retrieveGameDetails(String gameId){
		return TENNIS_GAME_MAP.get(gameId);
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
				return Constants.WON_THE_SET;
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
		pointDetails.append(Constants.POINT_GOES_TO);
		pointDetails.append(currentServiceWinner);
		OutputResponse outputResponse = new OutputResponse ();
		outputResponse.setCurrentService(pointDetails.toString());
		outputResponse.setPlayers(tennisGame.getPlayersName());
		outputResponse.setScore(getScoreValue(tennisGame.getPlayerOne(), tennisGame.getPlayerTwo(), scoreDescription, currentServiceWinner));

		if (tennisGame.getPlayerOne().getScore() == 0 && tennisGame.getPlayerTwo().getScore() == 0) {
			outputResponse.setGameStatus(Constants.GAME_STARTED);
			outputResponse.setCurrentService(Constants.GET_READY);
		} else {
			outputResponse.setGameStatus(Constants.GAME_IN_PROGRESS);
		}

		return outputResponse;
	}

	/**
	 *
	 * Score Value for all different logics
	 *
	 * @param playerOne
	 * @param playerTwo
	 * @param scoreDescription
	 * @param playerName
	 * @return
	 */
	private String getScoreValue(Player playerOne, Player playerTwo, String scoreDescription, String playerName) {
		logger.debug("getScoreValue called");

		StringBuilder score = new StringBuilder();
		if (!StringUtils.isEmpty(scoreDescription)) {
			score.append(playerName);
			score.append(scoreDescription);

		} else {
			score.append(PointDescriptionEnum.get(playerOne.getScore()));
			score.append(" - ");
			score.append(PointDescriptionEnum.get(playerTwo.getScore()));
		}
		return score.toString();
	}

	/**
	 * Finally Reset method called to reset score value
	 *
	 * @param playerOne
	 * @param playerTwo
	 * @param outputResponse
	 */
	private void resetGame(Player playerOne, Player playerTwo, OutputResponse outputResponse) {
		logger.debug("resetGame called");

		if (outputResponse.getScore().contains(Constants.WON)) {
			outputResponse.setGameStatus(Constants.GAME_OVER);
			playerOne.reset(-1);
			playerTwo.reset(-1);
		}

	}

}
