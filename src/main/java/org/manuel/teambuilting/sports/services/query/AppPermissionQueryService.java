package org.manuel.teambuilting.sports.services.query;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.AppAuthorizationManager;
import org.manuel.teambuilting.authorization.model.AppPermissionForEntity;
import org.manuel.teambuilting.authorization.permissions.AppCrudPermission;
import org.manuel.teambuilting.authorization.rights.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.authorization.util.AuthenticationUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 05/08/2017.
 */
@Service
@AllArgsConstructor
public class AppPermissionQueryService<T> {

    private final AppAuthorizationManager authorizationManager;

    public AppPermissionForEntity getAppPermissionForEntity(final T entity) {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        final AppRole appRole = AuthenticationUtil.getAppRole(SecurityContextHolder.getContext().getAuthentication());
        final Class<? super T> aClass = (Class<? super T>) entity.getClass();
        final AppPermissionAndRightConstraints<? super T> appPermissionAndRightConstraints = authorizationManager.getEntityAuthorizationFor(aClass).geConstraintsFor(appRole);
        final Set<AppCrudPermission> collect = Arrays.stream(AppCrudPermission.values()).filter(permission ->
                appPermissionAndRightConstraints.getRightConstraintsForPermisson(permission) != null &&
                        appPermissionAndRightConstraints.getRightConstraintsForPermisson(permission)
                            .isGranted(entity, authentication))
                .collect(Collectors.toSet());
        return AppPermissionForEntity.builder().permissions(collect).build();
    }
}
