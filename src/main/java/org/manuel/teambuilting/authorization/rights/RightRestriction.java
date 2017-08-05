package org.manuel.teambuilting.authorization.rights;

import java.util.function.Predicate;

/**
 * @author Manuel Doncel Martos
 * @since 01/08/2017.
 */
public interface RightRestriction<Entity> extends Predicate<Entity> {

    String getErrorMessage();

}
