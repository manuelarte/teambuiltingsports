package org.manuel.teambuilting.sports.services.query.impl;

import org.manuel.teambuilting.core.services.query.AbstractQueryService;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.sports.services.query.PlayerToTeamSportDetailsQueryService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 *
 */
@Service
class PlayerToTeamSportDetailsQueryServiceImpl extends AbstractQueryService<PlayerToTeamSportDetails, String, PlayerToTeamSportDetailsRepository> implements
	PlayerToTeamSportDetailsQueryService {

	@Inject
	public PlayerToTeamSportDetailsQueryServiceImpl(final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository) {
		super(playerToTeamSportDetailsRepository);
	}

	@Override
	public Collection<PlayerToTeamSportDetails> findByPlayerId(final BigInteger playerId) {
		return repository.findByPlayerId(playerId);
	}

	public Optional<PlayerToTeamSportDetails> findPlayerDetailsForSport(final BigInteger playerId, final String sport) {
		return Optional.ofNullable(repository.findByPlayerIdAndSportIgnoringCase(playerId, sport));
	}
}
