package org.manuel.teambuilting.authorization.functions;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.springframework.security.core.Authentication;

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
    public boolean isGranted(final Entity object, final Authentication authentication) {
        return predicate.test(function.apply(object)) ? true : false;
    }

}
