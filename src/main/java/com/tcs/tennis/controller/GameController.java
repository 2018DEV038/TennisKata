package com.tcs.tennis.controller;

import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.service.TennisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tennis")
@Api(value="Tennis Game")
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private TennisService tennisService;

    @Autowired
    private TennisGame tennisGame;
    /**
     *
     * Player will play the game and get corresponding score details for Every service
     *
     */
    @ApiOperation(value = "Tennis Game for two player, Registered player have unique id to Continue the Game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "URL not found"),
            @ApiResponse(code = 500, message = "Service not available due to technical issues")
    }
    )

    @GetMapping(value = "/score")
    public int getGameStatus(@RequestParam(value="scoreValue") int playerScore){

        tennisService.getScoreDetails(tennisGame);
        return playerScore;
    }


    public void setTennisService(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    public void setTennisGame(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

}
