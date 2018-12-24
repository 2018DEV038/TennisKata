package com.tcs.tennis.dto;

public class OutputResponse {

	private String gameStatus;
	private String players;
	private String currentService;
	private String score;
	private String errorMessage;

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public String getCurrentService() {
		return currentService;
	}

	public void setCurrentService(String currentService) {
		this.currentService = currentService;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
