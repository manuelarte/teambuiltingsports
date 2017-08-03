package org.manuel.teambuilting.rights.functions;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.rights.AppRightConstraint;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor(staticName = "of")
public class CustomEntryFunction<Entity, T>
        implements AppRightConstraint<Entity> {

    private final Function<Entity, T> function;
    private final Predicate<T> predicate;

    @Override
    public void isGranted(final Entity object, final AuthenticationJsonWebToken authentication) {
        if (!predicate.test(function.apply(object))) {
            throw new UserNotAllowedToModifyEntityException("CustomEntryFunction not passed");
        }
    }

}
