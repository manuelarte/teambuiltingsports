package org.manuel.teambuilting.authorization;

import org.manuel.teambuilting.authorization.rights.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.authorization.roles.AppRole;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppEntityAuthorization<T> {

    AppPermissionAndRightConstraints<T> geConstraintsFor(final AppRole appRole);

}
