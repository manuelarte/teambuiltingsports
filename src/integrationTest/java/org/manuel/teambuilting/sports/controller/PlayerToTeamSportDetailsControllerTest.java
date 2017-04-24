package org.manuel.teambuilting.sports.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.model.TeamSport;
import org.manuel.teambuilting.sports.model.sports.FootballPosition;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.math.BigInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Manuel Doncel Martos
 * @since 16/04/2017.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerToTeamSportDetailsControllerTest {

    @Inject
    private WebApplicationContext context;

    @Inject
    private PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCannotSaveTwoTimesSameSportNoUpdating() throws Exception {
        final PlayerToTeamSportDetails playerToTeamSportDetails = PlayerToTeamSportDetails.builder().playerId(new BigInteger("1")).sport(TeamSport.FOOTBALL.getName()).mainPosition(FootballPosition.LB.getAbbreviation()).build();
        playerToTeamSportDetailsRepository.save(playerToTeamSportDetails);

        final PlayerToTeamSportDetails post = PlayerToTeamSportDetails.builder().playerId(new BigInteger("1")).sport(TeamSport.FOOTBALL.getName()).mainPosition(FootballPosition.CAM.getAbbreviation()).build();
        mvc.perform(post("/sports/players/" +  playerToTeamSportDetails.getPlayerId(), "").content(mapper.writeValueAsBytes(post))).andExpect(status().is4xxClientError());
    }

    @Test
    public void testCanSaveSameSportUpdating() throws Exception {
        final PlayerToTeamSportDetails playerToTeamSportDetails = PlayerToTeamSportDetails.builder().playerId(new BigInteger("1")).sport(TeamSport.FOOTBALL.getName()).mainPosition(FootballPosition.LB.getAbbreviation()).build();
        playerToTeamSportDetailsRepository.save(playerToTeamSportDetails);

        mvc.perform(post("/sports/players/" +  playerToTeamSportDetails.getPlayerId(), "").content(mapper.writeValueAsBytes(playerToTeamSportDetails))).andExpect(status().is2xxSuccessful());
    }

}
