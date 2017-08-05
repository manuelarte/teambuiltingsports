package org.manuel.teambuilting.authorization.util;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.experimental.UtilityClass;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.springframework.security.core.Authentication;

/**
 * @author Manuel Doncel Martos
 * @since 05/08/2017.
 */
@UtilityClass
public class AuthenticationUtil {

    public static AppRole getAppRole(final Authentication authentication) {
        AppRole toReturn = AppRole.VISITOR;
        if (authentication != null && authentication instanceof AuthenticationJsonWebToken) {
            toReturn = AppRole.FREE;
        }
        return toReturn;
    }

}
