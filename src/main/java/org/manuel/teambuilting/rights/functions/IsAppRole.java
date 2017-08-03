package org.manuel.teambuilting.rights.functions;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.rights.AppRightConstraint;
import org.manuel.teambuilting.rights.roles.AppRole;

import java.util.function.Predicate;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor(staticName = "of")
public class IsAppRole implements AppRightConstraint<Object> {

    private final Predicate<AppRole> predicate;

    public void isGranted(final Object object, final AuthenticationJsonWebToken authentication) {
        // Get my custom role from the authentication
        final AppRole role = AppRole.FREE;
        if (!predicate.test(role)) {
            throw new UserNotAllowedToModifyEntityException("Your role is not allowed to perform the operation");
        }
    }

}
