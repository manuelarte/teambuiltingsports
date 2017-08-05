package org.manuel.teambuilting.rights.util;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.experimental.UtilityClass;
import org.manuel.teambuilting.rights.roles.AppRole;
import org.springframework.security.core.Authentication;

/**
 * @author Manuel Doncel Martos
 * @since 05/08/2017.
 */
@UtilityClass
public class AuthenticationUtil {

    public static AppRole getAppRoleFor(final Authentication authentication) {
        AppRole toReturn = AppRole.VISITOR;
        if (authentication != null && authentication instanceof AuthenticationJsonWebToken) {
            toReturn = AppRole.FREE;
        }
        return toReturn;
    }

}
