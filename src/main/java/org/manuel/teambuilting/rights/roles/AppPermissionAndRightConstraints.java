package org.manuel.teambuilting.rights.roles;


import org.manuel.teambuilting.rights.functions.AppRightConstraint;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppPermissionAndRightConstraints<T> {

    AppRightConstraint<? super T> getRightConstraintsForPermisson(Permission permission);
}
