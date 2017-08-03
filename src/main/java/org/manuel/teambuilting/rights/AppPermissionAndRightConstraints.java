package org.manuel.teambuilting.rights;


import org.manuel.teambuilting.rights.roles.Permission;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppPermissionAndRightConstraints<T> {

    AppRightConstraint<? super T> getRightConstraintsForPermisson(Permission permission);
}
