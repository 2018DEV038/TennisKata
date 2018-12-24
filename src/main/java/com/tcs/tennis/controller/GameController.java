package com.tcs.tennis.controller;

import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;
import com.tcs.tennis.dto.RegisterNewGameRequest;
import com.tcs.tennis.dto.RegisterNewGameResponse;
import com.tcs.tennis.service.TennisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@RestController
@RequestMapping("/tennis")
@Api(value="Tennis Game")
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    @Autowired
    private TennisService tennisService;

    /**
     *
     * Player will play the game and get corresponding score details for Every service
     *
     * @param gameId
     * @return
     */
    @ApiOperation(value = "Register first before starting game. Registered player have game id to Continue the Game",
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
                TennisGame tennisGame = tennisService.retrieveGameDetails(gameId);
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

    /**
     *
     * Register player details by providing Name and get Unique Id to log on Game
     *
     * @param registerNewGameRequest
     * @param errors
     * @return
     */
    @ApiOperation(value = " Register player details and get unique game id ",
            response = OutputResponse.class, responseContainer = "ResponseEntity", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "There is no game registered with input Game ID"),
            @ApiResponse(code = 500, message = "Service not available due to technical issues")

    }
    )
    @PostMapping(path = "/registerGame", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterNewGameResponse> registerNewGame (@Valid @RequestBody RegisterNewGameRequest registerNewGameRequest, @ApiIgnore Errors errors) {
        RegisterNewGameResponse response = new RegisterNewGameResponse();
        HttpStatus httpStatus= HttpStatus.OK;
        try {
            if (errors.hasErrors()) {
                response.setErrorMessage(errors.getFieldError().getDefaultMessage());
                httpStatus = HttpStatus.BAD_REQUEST;
            }else {
                String gameId = tennisService.registerGame(registerNewGameRequest.getPlayerOneName(), registerNewGameRequest.getPlayertwoName());
                response.setGameId(gameId);
            }
        }catch(Exception exception){
            LOGGER.error("Exception occurred inside getTennis method. Error Trace:{}", exception.getStackTrace());
            response.setErrorMessage("Service not available due to technical issues");
            httpStatus= HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, httpStatus);
    }


}
