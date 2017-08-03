package org.manuel.teambuilting.rights.functions;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.rights.AppRightConstraint;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor(staticName = "yes")
public class IsAdmin implements AppRightConstraint<Object> {

    public void isGranted(final Object object, final AuthenticationJsonWebToken authentication) {
    }

}
