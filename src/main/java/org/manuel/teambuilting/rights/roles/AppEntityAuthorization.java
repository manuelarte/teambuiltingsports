package org.manuel.teambuilting.rights.roles;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppEntityAuthorization<T> {

    AppPermissionAndRightConstraints<T> geConstraintsFor(final AppRole appRole);

}
