package org.manuel.teambuilting.authorization.functions;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.authorization.util.AuthenticationUtil;
import org.springframework.security.core.Authentication;

import java.util.function.Predicate;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor(staticName = "of")
public class IsAppRole implements AppRightConstraint<Object> {

    private final Predicate<AppRole> predicate;

    public boolean isGranted(final Object object, final Authentication authentication) {
        // Get my custom role from the authentication
        final AppRole role = AuthenticationUtil.getAppRole(authentication);
        return predicate.test(role) ? true : false;
    }

}
