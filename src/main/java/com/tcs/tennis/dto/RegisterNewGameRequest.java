package com.tcs.tennis.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


public class RegisterNewGameRequest {
    @NotBlank(message = "First Player Name should not be empty/blank")
    @Valid
    @ApiModelProperty(value = "First Player Name")
    String playerOneName;

    @NotBlank(message = "Second Player Name should not be empty/blank")
    @Valid
    @ApiModelProperty(value = "Second Player Name")
    String playertwoName;

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayertwoName() {
        return playertwoName;
    }

    public void setPlayertwoName(String playertwoName) {
        this.playertwoName = playertwoName;
    }
}
