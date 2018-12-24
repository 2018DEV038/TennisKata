package com.tcs.tennis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tennis")
public class GameController {


    @GetMapping(value = "/score")
    public int getGameStatus(@RequestParam(value="scoreValue") int playerScore){

        return playerScore;
    }
}
