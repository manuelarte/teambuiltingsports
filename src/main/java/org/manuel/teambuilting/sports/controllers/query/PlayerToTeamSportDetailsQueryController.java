package org.manuel.teambuilting.sports.controllers.query;

import org.manuel.teambuilting.core.exceptions.ErrorCode;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.services.query.PlayerToTeamSportDetailsQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/sports/players")
public class PlayerToTeamSportDetailsQueryController {

	private final PlayerToTeamSportDetailsQueryService queryService;

	@Inject
	public PlayerToTeamSportDetailsQueryController(final PlayerToTeamSportDetailsQueryService queryService) {
		this.queryService = queryService;
	}

	@GetMapping
	public Collection<PlayerToTeamSportDetails> findAllPlayerSportDetails(@RequestParam final BigInteger playerId) {
		Assert.notNull(playerId);
		return queryService.findByPlayerId(playerId);
	}

	@GetMapping(path = "/{sport}")
	public ResponseEntity<PlayerToTeamSportDetails> findPlayerSportDetailsForSport(@RequestParam final BigInteger playerId, @PathVariable("sport") final String sport) {
		Assert.notNull(playerId);
		Assert.hasLength(sport);
		final Optional<PlayerToTeamSportDetails> playerToTeamSportDetails = queryService.findPlayerDetailsForSport(playerId, sport);
		if (playerToTeamSportDetails.isPresent()) {
			return ResponseEntity.ok(playerToTeamSportDetails.get());
		}
		throw new ValidationRuntimeException(ErrorCode.PLAYER_DETAIL_FOR_SPORT_NOT_FOUND, playerId, sport);
	}

}
