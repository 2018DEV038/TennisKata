package com.tcs.tennis.service;

import static org.junit.Assert.assertEquals;

import com.tcs.tennis.domain.Player;
import com.tcs.tennis.domain.TennisGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

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

        playerOne.setScore(1);
        playerTwo.setScore(0);
        tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(1, playerOne.getScore());
    }

    @Test
    public void testToIncreasePlayerTwoScoreCount() {

        playerTwo.setScore(0);
        playerOne.setScore(1);
        tennisServiceImpl.getScoreDetails(tennisGame);
        assertEquals(1, playerTwo.getScore());

    }


}