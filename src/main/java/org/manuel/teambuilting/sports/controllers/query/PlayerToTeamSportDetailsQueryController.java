package org.manuel.teambuilting.sports.controllers.query;

import org.manuel.teambuilting.authorization.model.AppPermissionForEntity;
import org.manuel.teambuilting.core.controllers.query.AbstractQueryController;
import org.manuel.teambuilting.core.exceptions.ErrorCode;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.services.query.AppPermissionQueryService;
import org.manuel.teambuilting.sports.services.query.PlayerToTeamSportDetailsQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/sports/players")
public class PlayerToTeamSportDetailsQueryController extends AbstractQueryController<PlayerToTeamSportDetails, String, PlayerToTeamSportDetailsQueryService> {

	private final AppPermissionQueryService<PlayerToTeamSportDetails> permissionQueryService;

	public PlayerToTeamSportDetailsQueryController(final PlayerToTeamSportDetailsQueryService queryService,
			final AppPermissionQueryService<PlayerToTeamSportDetails> permissionQueryService) {
		super(queryService);
		this.permissionQueryService = permissionQueryService;
	}

	@GetMapping
	public Collection<PlayerToTeamSportDetails> findAllPlayerSportDetails(@RequestParam final BigInteger playerId) {
		Assert.notNull(playerId, "The player id cannot be null");
		return queryService.findByPlayerId(playerId);
	}

	@GetMapping(path = "/{sport}")
	public ResponseEntity<PlayerToTeamSportDetails> findPlayerSportDetailsForSport(@RequestParam final BigInteger playerId, @PathVariable("sport") final String sport) {
		Assert.notNull(playerId, "The player id cannot be null");
		Assert.hasLength(sport, "The sport name cannot be null");
		final Optional<PlayerToTeamSportDetails> playerToTeamSportDetails = queryService.findPlayerDetailsForSport(playerId, sport);
		if (playerToTeamSportDetails.isPresent()) {
			return ResponseEntity.ok(playerToTeamSportDetails.get());
		}
		throw new ValidationRuntimeException(ErrorCode.PLAYER_DETAIL_FOR_SPORT_NOT_FOUND, playerId, sport);
	}

	@GetMapping(value = "{id}/permissions")
	public ResponseEntity<AppPermissionForEntity> getAppPermissionForEntity(@PathVariable final String id) {
		final Optional<PlayerToTeamSportDetails> one = queryService.findOne(id);
		if (one.isPresent()) {
			return ResponseEntity.ok(permissionQueryService.getAppPermissionForEntity(one.get()));
		}
		throw new ValidationRuntimeException(ErrorCode.ID_NOT_FOUND, PlayerToTeamSportDetails.class, id);
	}

}
