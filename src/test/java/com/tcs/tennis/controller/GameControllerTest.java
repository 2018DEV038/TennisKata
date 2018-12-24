package com.tcs.tennis.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    GameController gameController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @After
    public void tearDown() {
        mockMvc = null;
    }

    @Test
    public void testGetGameStatusServiceByPassingInvalidArgument() throws Exception {
        this.mockMvc.perform(get("/tennis/PlayerOne"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetGameStatusServiceByCallingWrongUrl() throws Exception {
        this.mockMvc.perform(get("/tennisGameResult"))
                .andExpect(status().isNotFound());

    }


    @Test
       public void testGetGameStatusServiceByCallingBadRequest() throws Exception {
        this.mockMvc.perform(get("/tennis/score"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetGameStatusServiceByCallingSucessfully() throws Exception {
        this.mockMvc.perform(get("/tennis/score").param("gameid","FDASDAD33SDA"))
                .andExpect(status().isOk());
    }



}