/**
 * 
 */
package org.manuel.teambuilting.sports.controllers.query;

import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.sports.model.TeamSport;
import org.manuel.teambuilting.sports.model.TeamSportPosition;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.manuel.teambuilting.core.exceptions.ErrorCode.SPORT_NOT_FOUND;

/**
 * @author Manuel Doncel Martos
 *
 */
@RestController
@RequestMapping("/sports")
public class SportQueryController {

	public SportQueryController() {
	}

	@GetMapping
	public List<TeamSport> getSportsAvailable() {
		return Arrays.asList(TeamSport.values());
	}

	@GetMapping(path = "/{teamSportname}")
	public List<TeamSportPosition> getTeamSport(@PathVariable("teamSportname") @NotNull final String teamSportName) {
		final Optional<TeamSport> sport = Arrays.stream(TeamSport.values())
				.filter(teamSport -> teamSport.getName().equals(teamSportName)).findFirst();
		if (sport.isPresent()) {
			return Arrays.asList(sport.get().getSportPositions());
		} else {
			throw new ValidationRuntimeException(SPORT_NOT_FOUND, teamSportName);
		}
	}

}
