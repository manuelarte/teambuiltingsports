package org.manuel.teambuilting.authorization.functions;

import lombok.experimental.UtilityClass;
import org.manuel.teambuilting.core.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.authorization.rights.RightRestriction;

import java.util.Collection;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@UtilityClass
public class GenericFunctions {

    public static <Entity> RightRestriction<Collection<Entity>> max(final int maxNumber) {
        return new RightRestriction<Collection<Entity>>() {

            @Override
            public String getErrorMessage() {
                return "There are more entries than " + maxNumber;
            }

            @Override
            public boolean test(Collection<Entity> entities) {
                return entities.size() < maxNumber;
            }
        };
    }

    public static <T> AppRightConstraint<T> allow() {
        return (o1, o2) -> returnVoid();
    }

    public static <T> AppRightConstraint<T> deny() {
        return (o1, o2) -> new UserNotAllowedToModifyEntityException();
    }

    private static void returnVoid(){}

}
