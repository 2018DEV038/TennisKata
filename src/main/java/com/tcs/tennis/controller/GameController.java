package com.tcs.tennis.controller;

import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;
import com.tcs.tennis.service.TennisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
     * @param gameId
     * @return
     */
    @ApiOperation(value = "Tennis Game for two player, Registered player have unique id to Continue the Game",
            response = OutputResponse.class, responseContainer = "ResponseEntity", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "There is no game registered with input Game ID"),
            @ApiResponse(code = 500, message = "Service not available due to technical issues")
    }
    )

    @GetMapping(value = "/score", produces = "application/json")
    public ResponseEntity<OutputResponse> getGameStatus(@RequestParam(value="gameid")  String gameId){
        LOGGER.debug("getTennis Controller called");
        OutputResponse outputResponse = new OutputResponse();
        HttpStatus httpStatus= HttpStatus.OK;
        try {
            if (StringUtils.isEmpty(gameId)) {
                outputResponse.setErrorMessage("Game Id should not be Null/Empty");
                httpStatus = HttpStatus.BAD_REQUEST;
            }else {
                if (null != tennisGame) {
                    outputResponse = tennisService.getScoreDetails(tennisGame);
                } else {
                    outputResponse.setErrorMessage("There is no game registered with input Game ID:" + gameId);
                    httpStatus = HttpStatus.NOT_FOUND;
                }
            }
        }catch(Exception exception){
            LOGGER.error("Exception occurred inside getTennis method. Error Trace:{}", exception.getStackTrace());
            outputResponse.setGameStatus("Service not available due to technical issues");
            httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(outputResponse, httpStatus);
    }



    public void setTennisService(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    public void setTennisGame(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

}
