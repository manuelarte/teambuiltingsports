package org.manuel.teambuilting.sports.services.command.impl;

import org.manuel.teambuilting.core.services.command.AbstractCommandService;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.sports.services.command.PlayerToTeamSportDetailsCommandService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
class PlayerToTeamSportDetailsCommandServiceImpl extends AbstractCommandService<PlayerToTeamSportDetails, String, PlayerToTeamSportDetailsRepository> implements
	PlayerToTeamSportDetailsCommandService {

	@Inject
	public PlayerToTeamSportDetailsCommandServiceImpl(final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository) {
		super(playerToTeamSportDetailsRepository);
	}

}
