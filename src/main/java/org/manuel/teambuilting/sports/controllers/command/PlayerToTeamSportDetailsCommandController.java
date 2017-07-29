package org.manuel.teambuilting.sports.controllers.command;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.services.command.PlayerToTeamSportDetailsCommandService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sports/players")
@AllArgsConstructor
public class PlayerToTeamSportDetailsCommandController {

	private final PlayerToTeamSportDetailsCommandService playerToTeamSportDetailsService;

	@PostMapping
	public PlayerToTeamSportDetails savePlayerDetails(@Valid @RequestBody final PlayerToTeamSportDetails playerToTeamSportDetails) {
		return playerToTeamSportDetailsService.save(playerToTeamSportDetails);
	}

	@DeleteMapping(path = "/{playerToTeamSportDetailsId}")
	public void deletePlayerDetails(@PathVariable final String playerToTeamSportDetailsId) {
		playerToTeamSportDetailsService.delete(playerToTeamSportDetailsId);
	}

}
