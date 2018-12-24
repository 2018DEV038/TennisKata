package com.tcs.tennis.service;

import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;

public interface TennisService {

	public String registerGame(String playerOneName, String playerTwoName);
	public TennisGame retrieveGameDetails(String gameId);
	public OutputResponse getScoreDetails(TennisGame tennisGame);
	
}
