package org.manuel.teambuilting.sports.repositories;

import org.manuel.teambuilting.core.repositories.PlayerDependentRepository;
import org.manuel.teambuilting.sports.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Doncel Martos
 * @since 16/04/2017.
 */
@Repository
public interface UserDataRepository extends PlayerDependentRepository<UserData>, MongoRepository<UserData, String> {
}
