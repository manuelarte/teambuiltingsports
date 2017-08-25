package org.manuel.teambuilting.sports.config;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.AppAuthorizationManager;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * @author Manuel Doncel Martos
 * @since 05/08/2017.
 */
@AllArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final AppAuthorizationManager authorizationManager;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (hasPermission(authentication, targetType, permission)) {
            return true;
        }
        return false;
    }
}
