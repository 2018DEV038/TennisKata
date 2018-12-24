package com.tcs.tennis.controller;

import com.tcs.tennis.dto.OutputResponse;
import com.tcs.tennis.domain.Player;
import com.tcs.tennis.domain.TennisGame;
import com.tcs.tennis.service.TennisService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    private MockMvc mockMvc;

    @Mock
    TennisService tennisService;

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
    public void testRegisterGameWithInValidRequestPlayerOneEmpty() throws Exception {
        String json = "{  \"playerOneName\":\"\",\"playertwoName\":\"David\"}";
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
                "First Player Name should not be empty/blank"));

    }

    @Test
    public void testRegisterGameWithInValidRequestPlayerOneBlank() throws Exception {
        String json = "{  \"playerOneName\":\"   \",\"playertwoName\":\"David\"}";
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
                "First Player Name should not be empty/blank"));

    }

    @Test
    public void testRegisterGameWithInValidRequestPlayerTwoEmpty() throws Exception {
        String json = "{  \"playerOneName\":\"David\",\"playertwoName\":\"\"}";
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
                "Second Player Name should not be empty/blank"));

    }
    @Ignore
    @Test
    public void testRegisterGameWithInValidRequestBothPlayerEmpty() throws Exception {
        String json = "{  \"playerOneName\":\"\",\"playertwoName\":\"\"}";
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
                "First Player Name should not be empty/blank"));

    }

    @Test
    public void testRegisterGameWithInValidRequestPlayerTwoBlank() throws Exception {
        String json = "{  \"playerOneName\":\"David\",\"playertwoName\":\"   \"}";
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
                "Second Player Name should not be empty/blank"));

    }

    /**
     * To Check Response Exception
     *
     * @throws Exception
     */
    @Test
    public void testRegisterGameWithValidRequestRunTimeException() throws Exception {
        String json = "{  \"playerOneName\":\"Alex\",\"playertwoName\":\"David\"}";
        when(tennisService.registerGame(any(), any())).thenThrow(RuntimeException.class);
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is5xxServerError()).andExpect(jsonPath("$.errorMessage").value("Service not available due to technical issues"));;
    }

    /**
     * To Check Response status 200
     *
     * @throws Exception
     */
    @Test
    public void testRegisterGameWithValidRequest() throws Exception {
        String json = "{  \"playerOneName\":\"Alex\",\"playertwoName\":\"David\"}";
        when(tennisService.registerGame(any(), any())).thenReturn("FDASDAD33SDA");
        this.mockMvc.perform(post("/tennis/registerGame").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(jsonPath("$.gameId").value("FDASDAD33SDA"));;
    }

    @Test
    public void testgetGameStatusWithInValidRequestGameIdEmpty() throws Exception {
        this.mockMvc.perform(get("/tennis/score").param("gameid",""))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errorMessage").value(
                "Game Id should not be Null/Empty"));

    }

    @Test
    public void testgetGameStatusWithInValidRequestGameIdNull() throws Exception {
        this.mockMvc.perform(get("/tennis/score?")).andExpect(status().isBadRequest());
    }

    @Test
    public void testgetGameStatusWithValidRequestGameIdNotFound() throws Exception {

        when(tennisService.retrieveGameDetails(any())).thenReturn(null);
        this.mockMvc.perform(get("/tennis/score").param("gameid","FDASDAD33SDA"))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.errorMessage").value(
                "There is no game registered with input Game ID:FDASDAD33SDA"));

    }

    @Test
    public void testgetGameStatusWithValidRequestGameId() throws Exception {
        TennisGame tennisGame = new TennisGame(new Player("Alex"),new Player("David"));

        when(tennisService.retrieveGameDetails(any())).thenReturn(tennisGame);
        when(tennisService.getScoreDetails(any())).thenReturn(new OutputResponse());
        this.mockMvc.perform(get("/tennis/score").param("gameid","FDASDAD33SDA"))
                .andExpect(status().isOk());

    }

}