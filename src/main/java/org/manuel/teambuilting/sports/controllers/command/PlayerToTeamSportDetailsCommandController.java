package org.manuel.teambuilting.sports.controllers.command;

import javax.validation.Valid;

import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.services.command.PlayerToTeamSportDetailsCommandService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sports/players")
@AllArgsConstructor
public class PlayerToTeamSportDetailsCommandController {

	private final PlayerToTeamSportDetailsCommandService playerToTeamSportDetailsService;

	@RequestMapping(method = RequestMethod.POST)
	public PlayerToTeamSportDetails savePlayerDetails(@Valid @RequestBody final PlayerToTeamSportDetails playerToTeamSportDetails) {
		return playerToTeamSportDetailsService.save(playerToTeamSportDetails);
	}

	@RequestMapping(path = "/{playerToTeamSportDetailsId}", method = RequestMethod.DELETE)
	public void deletePlayerDetails(@PathVariable final String playerToTeamSportDetailsId) {
		playerToTeamSportDetailsService.delete(playerToTeamSportDetailsId);
	}

}
