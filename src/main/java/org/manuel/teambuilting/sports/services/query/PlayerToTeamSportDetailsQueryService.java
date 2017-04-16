package org.manuel.teambuilting.sports.services.query;

import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.services.query.BaseQueryService;
import org.manuel.teambuilting.core.services.query.PlayerDependentQueryService;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author Manuel Doncel Martos
 *
 */
public interface PlayerToTeamSportDetailsQueryService extends BaseQueryService<PlayerToTeamSportDetails, String>, PlayerDependentQueryService<PlayerToTeamSportDetails, String> {

	Optional<PlayerToTeamSportDetails> findPlayerDetailsForSport(BigInteger playerId, String sport);

}
