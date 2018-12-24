package com.tcs.tennis.service;

import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;

public interface TennisService {

	public OutputResponse getScoreDetails(TennisGame tennisGame);
	
}
