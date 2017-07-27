/**
 * 
 */
package org.manuel.teambuilting.sports.controllers.query;

import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.sports.model.TeamSport;
import org.manuel.teambuilting.sports.model.TeamSportPosition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(method = RequestMethod.GET)
	public List<TeamSport> getSportsAvailable() {
		return Arrays.asList(TeamSport.values());
	}

	@RequestMapping(path = "/{teamSportname}", method = RequestMethod.GET)
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
