# TennisKata
Tennis Game for two player


# Description

About this Kata

This Kata is about implementing a simple tennis game. I came up with it while thinking about Wii tennis, where they
have simplified tennis, so each set is one game.

The scoring system is rather simple:

1. Each player can have either of these points in one game 0 15 30 40

2. If you have 40 and you win the ball you win the game, however there are special rules.

3. If both have 40 the players are deuce. a. If the game is in deuce, the winner of a ball will have advantage
and game ball. b. If the player with advantage wins the ball he wins the game c. If the player without advantage wins they are back at deuce.


#Components
1.Java - 1.8
2.Maven - 4.0.0
3.IDE - Eclipse,IntelliJ,SpringSuite
4.Junit - 4.12
5.jmockit - 1.24


#Step to Run application
1, Import the project as Existing Maven project in IDE
2, Do maven clean and maven install for the imported project(Either from IDE or from command terminal)
3, Execute SpringBootWebApplication.java in package "com.tcs.tennis"
4, Verify server port number(8080) in console after the application startup
5, Once Application started launch swagger-UI in browser using the url given in the next step
6, swagger-UI URL : http://localhost:<portNumber>/swagger-ui.html


Once After launch Application

 HOW TO PLAY ::
 ###First Register User and get Unique game id.
 ###Use same game id to unlock the Tennis Game


7, In swagger "click on game-controller"
8, POST and GET service will get enabled to trigger the services.
9, POST Service - To register the game with the given players. <Enter Player1 and player2>. Name should not be empty
10,GET Service - To get the score details according to the points scored by the players  in every serve
10, Click on POST service -->Try it out -  provide the player name as input in the json structure(replace "string" with player names)
11, After clicking on execute the service will be triggered and the game will be registered with a id for the given players.Copy the Game id from the response body which was 	generated after the registration
12, Click on GET -->Try it out - Provide the registered game id as input for this service.
13, After clicking on execute the game will be started and each click on execute will be considered as a each serve from players and the score varies in the response body for 	each serve from the players.
14,Once any of the player won, then the game will reset for next game.Then it continues.
15,Enjoy the GAME !!
16,You can view API Document in URL : http://localhost:<portNumber>/v2/api-docs


Description About implementation :

Used Hashmap to store players details.
It's help to play for multiple Users. <same game in different player in different service>

