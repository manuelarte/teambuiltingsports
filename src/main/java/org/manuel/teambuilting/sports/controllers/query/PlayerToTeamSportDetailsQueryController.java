package org.manuel.teambuilting.sports.controllers.query;

import org.manuel.teambuilting.exceptions.ErrorCode;
import org.manuel.teambuilting.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.services.query.PlayerToTeamSportDetailsQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/sports/players/{playerId}")
public class PlayerToTeamSportDetailsQueryController {

	private final PlayerToTeamSportDetailsQueryService queryService;

	@Inject
	public PlayerToTeamSportDetailsQueryController(final PlayerToTeamSportDetailsQueryService queryService) {
		this.queryService = queryService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Collection<PlayerToTeamSportDetails> findAllPlayerSportDetails(@PathVariable final BigInteger playerId) {
		Assert.notNull(playerId);
		return queryService.findByPlayerId(playerId);
	}

	@RequestMapping(path = "/{sport}", method = RequestMethod.GET)
	public ResponseEntity<PlayerToTeamSportDetails> findPlayerSportDetailsForSport(@PathVariable final BigInteger playerId, @PathVariable("sport") final String sport) {
		Assert.notNull(playerId);
		Assert.hasLength(sport);
		final Optional<PlayerToTeamSportDetails> playerToTeamSportDetails = queryService.findPlayerDetailsForSport(playerId, sport);
		if (playerToTeamSportDetails.isPresent()) {
			return ResponseEntity.ok(playerToTeamSportDetails.get());
		}
		throw new ValidationRuntimeException(ErrorCode.PLAYER_DETAIL_FOR_SPORT_NOT_FOUND, playerId, sport);
	}

}
