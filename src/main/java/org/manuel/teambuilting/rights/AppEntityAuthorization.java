package org.manuel.teambuilting.rights;

import org.manuel.teambuilting.rights.roles.AppRole;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppEntityAuthorization<T> {

    AppPermissionAndRightConstraints<T> geConstraintsFor(final AppRole appRole);

}
