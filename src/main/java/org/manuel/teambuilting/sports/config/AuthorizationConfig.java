package org.manuel.teambuilting.sports.config;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.AppAuthorizationManager;
import org.manuel.teambuilting.authorization.AppEntityAuthorization;
import org.manuel.teambuilting.authorization.functions.CollectionEntriesFunction;
import org.manuel.teambuilting.authorization.impl.AppAuthorizationManagerImpl;
import org.manuel.teambuilting.authorization.impl.AppEntityAuthorizationImpl;
import org.manuel.teambuilting.authorization.impl.AppPermissionAndRightConstraintsImpl;
import org.manuel.teambuilting.authorization.impl.AppRightConstraintOfSeveralConstraints;
import org.manuel.teambuilting.authorization.rights.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.sports.authorization.UserModifyTheirPlayer;
import org.manuel.teambuilting.sports.model.PlayerToTeamSportDetails;
import org.manuel.teambuilting.sports.repositories.PlayerToTeamSportDetailsRepository;
import org.manuel.teambuilting.sports.repositories.UserDataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.manuel.teambuilting.authorization.functions.GenericFunctions.allow;
import static org.manuel.teambuilting.authorization.functions.GenericFunctions.max;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@Configuration
@AllArgsConstructor
public class AuthorizationConfig {

    private final PlayerToTeamSportDetailsRepository playerToTeamSportDetailsRepository;
    private final UserDataRepository userDataRepository;

    @Bean
    public AppAuthorizationManager appAuthorizationManager() {

        final AppPermissionAndRightConstraints<PlayerToTeamSportDetails> visitorRoleForPlayerToTeamSportDetails
                = new AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder()
                .read(AppRightConstraintOfSeveralConstraints.of(allow()))
                .build();

        final AppPermissionAndRightConstraints<PlayerToTeamSportDetails> rightsForPlayerToTeamSportDetails
                = new AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder()
                .create(new UserModifyTheirPlayer<>(getEntityAuthorizationManagerForCreatePlayerToTeamSportDetails(), userDataRepository))
                .read(AppRightConstraintOfSeveralConstraints.of(allow()))
                .update(new UserModifyTheirPlayer<>(AppRightConstraintOfSeveralConstraints.of(allow()), userDataRepository))
                .delete(new UserModifyTheirPlayer<>(AppRightConstraintOfSeveralConstraints.of(allow()), userDataRepository))
                .build();

        final AppEntityAuthorization<PlayerToTeamSportDetails> rolePermissionAndRightConstraintsMap
                = AppEntityAuthorizationImpl.<PlayerToTeamSportDetails>builder()
                .constraint(AppRole.VISITOR, visitorRoleForPlayerToTeamSportDetails)
                .constraint(AppRole.ADMIN, rightsForPlayerToTeamSportDetails)
                .constraint(AppRole.FREE, rightsForPlayerToTeamSportDetails)
                .constraint(AppRole.GOLD, rightsForPlayerToTeamSportDetails)
                .constraint(AppRole.PREMIUM, rightsForPlayerToTeamSportDetails)
                .build();

        test();

        return AppAuthorizationManagerImpl.builder()
                .forEntity(PlayerToTeamSportDetails.class, rolePermissionAndRightConstraintsMap)
                .build();
    }

    private AppRightConstraint<PlayerToTeamSportDetails> getEntityAuthorizationManagerForCreatePlayerToTeamSportDetails() {
        final CollectionEntriesFunction<PlayerToTeamSportDetails, PlayerToTeamSportDetailsRepository>
                maxOnePlayerToTeamSportDetails =
                new CollectionEntriesFunction<>(max(1), playerToTeamSportDetailsRepository);
        return AppRightConstraintOfSeveralConstraints.of(maxOnePlayerToTeamSportDetails);
    }

    private AppEntityAuthorization<?> test() {
        //EntityAuthorizationBuilder.forEntity(PlayerToTeamSportDetails.class);

        return AppEntityAuthorizationImpl.builder()
            .forRole(AppRole.ADMIN).create(null).read(null).update(null).delete(null)
            .andForRole(AppRole.FREE).create(null).read(null).update(null).delete(null)
            .andForRole(AppRole.PREMIUM).create(null).read(null).update(null).delete(null)
            .andForRole(AppRole.GOLD).create(null).read(null).update(null).delete(null).build();
    }

}
