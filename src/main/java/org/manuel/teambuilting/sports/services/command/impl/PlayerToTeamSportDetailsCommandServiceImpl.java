package org.manuel.teambuilting.sports.services.command.impl;

import org.manuel.teambuilting.core.services.command.AbstractCommandService;
import org.manuel.teambuilting.core.exceptions.ErrorCode;
import org.manuel.teambuilting.core.exceptions.ValidationRuntimeException;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.sports.services.command.PlayerToTeamSportDetailsCommandService;
import org.manuel.teambuilting.sports.services.query.PlayerToTeamSportDetailsQueryService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
class PlayerToTeamSportDetailsCommandServiceImpl extends AbstractCommandService<PlayerToTeamSportDetails, String, PlayerToTeamSportDetailsRepository> implements
	PlayerToTeamSportDetailsCommandService {

    private final PlayerToTeamSportDetailsQueryService playerToTeamSportDetailsQueryService;

	@Inject
	public PlayerToTeamSportDetailsCommandServiceImpl(final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository,
                                                      final PlayerToTeamSportDetailsQueryService playerToTeamSportDetailsQueryService) {
		super(playerToTeamSportDetailsRepository);
		this.playerToTeamSportDetailsQueryService = playerToTeamSportDetailsQueryService;
	}

	@Override
	protected void beforeSave(final PlayerToTeamSportDetails playerToTeamSportDetails) {
        final Optional<PlayerToTeamSportDetails> previousEntry = playerToTeamSportDetailsQueryService.findPlayerDetailsForSport(playerToTeamSportDetails.getPlayerId(), playerToTeamSportDetails.getSport());
        if (previousEntry.isPresent() && !previousEntry.get().getId().equals(playerToTeamSportDetails.getId())) {
            throw new ValidationRuntimeException(ErrorCode.SPORT_DETAILS_FOR_PLAYER_ALREADY_REGISTERED, playerToTeamSportDetails.getPlayerId(), playerToTeamSportDetails.getSport());
        }
    }

}
