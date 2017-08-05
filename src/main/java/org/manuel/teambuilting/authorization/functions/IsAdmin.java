package org.manuel.teambuilting.authorization.functions;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.authorization.util.AuthenticationUtil;
import org.springframework.security.core.Authentication;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor(staticName = "yes")
public class IsAdmin implements AppRightConstraint<Object> {

    public boolean isGranted(final Object object, final Authentication authentication) {
        return AuthenticationUtil.getAppRole(authentication) == AppRole.ADMIN;
    }

}
