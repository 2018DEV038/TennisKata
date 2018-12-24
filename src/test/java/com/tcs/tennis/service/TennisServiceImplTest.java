package com.tcs.tennis.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcs.tennis.domain.Player;
import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.dto.OutputResponse;
import com.tcs.tennis.util.Constants;
import com.tcs.tennis.util.Utility;

import mockit.MockUp;

@RunWith(SpringRunner.class)
public class TennisServiceImplTest {

    @Mock
    Player playerOne;
    @Mock
    Player playerTwo;
    @Mock
    TennisGame tennisGame;
    @InjectMocks
    TennisServiceImpl tennisServiceImpl ;


    @Before
    public void beforeTennisServiceImplTest() {
        playerOne = new Player("YAN");
        playerTwo = new Player("ZOHA");
        tennisServiceImpl = new TennisServiceImpl();
        tennisGame= new TennisGame(playerOne, playerTwo);

    }

    @After
    public void afterTennisServiceImplTest() {
        playerOne = null;
        playerTwo = null;
        tennisServiceImpl = null;
        tennisGame = null;
    }

    @Test
    public void testToIncreasePlayerOneScoreCount() {
        playerOneWon();

        playerOne.setScore(0);
        playerTwo.setScore(0);
        tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(1, playerOne.getScore());
    }


    @Test
    public void testToIncreasePlayerTwoScoreCount() {
        playerTwoWon();
        playerTwo.setScore(0);
        playerOne.setScore(0);
        tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(1, playerTwo.getScore());

    }




    @Test
    public void testToVerifyFirstServiceForPlayerOneScoreZero() {
        playerOneWon();
        playerOne.setScore(-1);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(0, playerOne.getScore());
        assertEquals("LOVE - LOVE", outputResponse.getScore());

    }

    @Test
    public void testToVerifyFirstServiceForPlayerTwoScoreZero() {
        playerTwoWon();
        playerTwo.setScore(-1);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(0, playerTwo.getScore());
        assertEquals("LOVE - LOVE", outputResponse.getScore());

    }

    @Test
    public void testTocalculateScoreDescriptionForNull() {
        playerOneWon();
        playerOne.setScore(0);
        playerTwo.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("FIFTEEN - LOVE", outputResponse.getScore());

    }

    @Test
    public void testTocalculateScoreDescriptionForWonPlayerOne() {
        playerOneWon();
        playerOne.setScore(5);
        playerTwo.setScore(4);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("YAN WON THE SET ", outputResponse.getScore());

    }

    @Test
    public void testTocalculateScoreDescriptionForWonPlayerTwo() {
        playerTwoWon();
        playerOne.setScore(4);
        playerTwo.setScore(5);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("ZOHA WON THE SET ", outputResponse.getScore());

    }


    @Test
    public void testTocalculateScoreDescriptionForDeuce() {
        playerOneWon();
        playerOne.setScore(4);
        playerTwo.setScore(5);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("YAN deuce", outputResponse.getScore());
    }


    @Test
    public void testTocalculateScoreDescriptionForAdvantageForPlayerTwo() {
        playerTwoWon();
        playerOne.setScore(7);
        playerTwo.setScore(7);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("ZOHA advantage ", outputResponse.getScore());
    }

    @Test
    public void testTocalculateScoreDescriptionForAdvantageForPlayerOne() {
        playerOneWon();
        playerOne.setScore(5);
        playerTwo.setScore(5);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("YAN advantage ", outputResponse.getScore());

    }



    @Test
    public void testToCurrentServiceWinnerForPlayerOne() {
        playerOneWon();
        playerOne.setScore(5);
        playerTwo.setScore(5);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("Point Goes to YAN", outputResponse.getCurrentService());

    }

    @Test
    public void testToCurrentServiceWinnerForPlayerTwo() {
        playerTwoWon();
        playerOne.setScore(1);
        playerTwo.setScore(1);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("Point Goes to ZOHA", outputResponse.getCurrentService());

    }


    @Test
    public void testToVerifyMappingOutputResponse() {
        playerOne.setScore(2);
        playerTwo.setScore(3);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertNotNull(outputResponse);

    }


    @Test
    public void testToCheckOutputResponseObjectPointDescriptionToPlayerOne() {
        playerOneWon();
        playerOne.setScore(3);
        playerTwo.setScore(2);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("Point Goes to YAN", outputResponse.getCurrentService());

    }


    @Test
    public void testToCheckOutputResponseObjectScoreLOVE() {
        playerTwoWon();
        playerOne.setScore(0);
        playerTwo.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("LOVE - FIFTEEN", outputResponse.getScore());
    }

    @Test
    public void testToCheckOutputResponseObjectScoreFIFTEEN () {
        playerTwoWon();
        playerOne.setScore(0);
        playerTwo.setScore(0);
        OutputResponse outputResponse =  tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("LOVE - FIFTEEN", outputResponse.getScore());

    }


    @Test
    public void testToCheckOutputResponseObjectScoreTHIRTY () {
        playerOneWon();
        playerOne.setScore(1);
        playerTwo.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("THIRTY - LOVE", outputResponse.getScore());


    }

    @Test
    public void testToCheckOutputResponseObjectScoreFORTY () {
        playerOneWon();
        playerOne.setScore(2);
        playerTwo.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("FORTY - LOVE", outputResponse.getScore());

    }

    @Test
    public void testToCheckOutputResponseObjectGameStatusValueGameInProgress() {
        playerTwoWon();
        playerOne.setScore(3);
        playerTwo.setScore(2);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(Constants.GAME_IN_PROGRESS, outputResponse.getGameStatus());

    }

    @Test
    public void testToCheckOutputResponseObjectLPlayerName() {
        playerTwoWon();
        playerTwo.setScore(-1);
        playerOne.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("YAN VS ZOHA", outputResponse.getPlayers());

    }

    @Test
    public void testToCheckOutputResponseObjectGameStatusValueGameStarted() {
        playerTwoWon();
        playerTwo.setScore(-1);
        playerOne.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(Constants.GAME_STARTED, outputResponse.getGameStatus());

    }

    @Test
    public void testToCheckOutputResponseObjectCurrentServiceGetReadyStatus() {
        playerTwoWon();
        playerTwo.setScore(-1);
        playerOne.setScore(0);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(Constants.GET_READY, outputResponse.getCurrentService());

    }

    @Test
    public void testToCheckOutputResponseObjectGameStatusValueGameOver() {
        playerOneWon();
        playerOne.setScore(4);
        playerTwo.setScore(3);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(Constants.GAME_OVER, outputResponse.getGameStatus());

    }

    @Test
    public void testToVerifyResetGame() {
        playerOneWon();
        playerOne.setScore(4);
        playerTwo.setScore(3);
        OutputResponse outputResponse = tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals("YAN WON THE SET ", outputResponse.getScore());

    }

    private void playerOneWon(){
        new MockUp<Utility>() {
            @mockit.Mock
            public boolean play() {
                return true;
            }
        };
    }

    private void playerTwoWon(){
        new MockUp<Utility>() {
            @mockit.Mock
            public boolean play() {
                return false;
            }
        };
    }
}
