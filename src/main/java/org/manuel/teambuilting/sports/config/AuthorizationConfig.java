package org.manuel.teambuilting.sports.config;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.rights.EntityAuthorizationManager;
import org.manuel.teambuilting.rights.functions.CollectionEntriesFunction;
import org.manuel.teambuilting.rights.roles.Permission;
import org.manuel.teambuilting.sports.authorization.AppAuthorizationManagerImpl;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.manuel.teambuilting.rights.functions.GenericFunctions.allow;
import static org.manuel.teambuilting.rights.functions.GenericFunctions.deny;
import static org.manuel.teambuilting.rights.functions.GenericFunctions.max;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@Configuration
@AllArgsConstructor
public class AuthorizationConfig {

    private final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;

    @Bean
    public AppAuthorizationManagerImpl appAuthorizationManager() {


        final Map<Permission, EntityAuthorizationManager<PlayerToTeamSportDetails>> rightsForPlayerToTeamSportDetails
                = new HashMap<>();
        rightsForPlayerToTeamSportDetails.put(Permission.READ, EntityAuthorizationManager.of(allow()));
        rightsForPlayerToTeamSportDetails.put(Permission.CREATE, getEntityAuthorizationManagerForCreatePlayerToTeamSportDetails());
        rightsForPlayerToTeamSportDetails.put(Permission.UPDATE, EntityAuthorizationManager.of(allow())); // add restriction only for your player, use decorator
        rightsForPlayerToTeamSportDetails.put(Permission.DELETE, EntityAuthorizationManager.of(deny())); // add restriction only for your player, use decorator

        return AppAuthorizationManagerImpl.builder()
                .add(PlayerToTeamSportDetails.class, rightsForPlayerToTeamSportDetails)
                .build();
    }

    private EntityAuthorizationManager<PlayerToTeamSportDetails> getEntityAuthorizationManagerForCreatePlayerToTeamSportDetails() {
        final CollectionEntriesFunction<PlayerToTeamSportDetails, PlayerToTeamSportDetailsRepository>
                maxOnePlayerToTeamSportDetails =
                new CollectionEntriesFunction<>(max(1), playerToTeamSportDetailsRepository);
        // add restriction only for your player, use decorator
        return EntityAuthorizationManager.of(maxOnePlayerToTeamSportDetails);
    }

}
