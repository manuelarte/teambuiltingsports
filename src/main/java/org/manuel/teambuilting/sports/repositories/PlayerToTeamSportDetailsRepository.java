package org.manuel.teambuilting.sports.repositories;

import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.core.repositories.PlayerDependentRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PlayerToTeamSportDetailsRepository extends PlayerDependentRepository<PlayerToTeamSportDetails, String>, MongoRepository<PlayerToTeamSportDetails, String> {

	PlayerToTeamSportDetails findByPlayerIdAndSportIgnoringCase(BigInteger playerId, String sport);

}
