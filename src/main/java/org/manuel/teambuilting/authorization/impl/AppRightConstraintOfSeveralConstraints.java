package org.manuel.teambuilting.authorization.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.springframework.security.core.Authentication;

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

    public boolean isGranted(final Entity object, final Authentication authentication) {
        return !chains.stream().filter(rightCheck -> !rightCheck.isGranted(object, authentication)).findAny().isPresent();
    }
}
