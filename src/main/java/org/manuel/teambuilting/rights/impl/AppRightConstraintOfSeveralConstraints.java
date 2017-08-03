package org.manuel.teambuilting.rights.impl;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import org.manuel.teambuilting.rights.AppRightConstraint;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor
@Builder
public class AppRightConstraintOfSeveralConstraints<Entity> implements AppRightConstraint<Entity> {

    @Singular
    private final Set<AppRightConstraint<Entity>> chains;

    public static <Entity> AppRightConstraintOfSeveralConstraints<Entity> of(final AppRightConstraint<Entity>... chains) {
        return new AppRightConstraintOfSeveralConstraints<>(chains);
    }

    public AppRightConstraintOfSeveralConstraints(AppRightConstraint<Entity>... appRights) {
        this.chains = Arrays.stream(appRights).collect(Collectors.toSet());
    }

    public void isGranted(final Entity object, final AuthenticationJsonWebToken authentication) {
        chains.forEach(rightCheck -> rightCheck.isGranted(object, authentication));
    }
}