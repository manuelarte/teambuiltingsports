package org.manuel.teambuilting.authorization.rights;


import org.manuel.teambuilting.authorization.permissions.AppCrudPermission;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppPermissionAndRightConstraints<T> {

    AppRightConstraint<? super T> getRightConstraintsForPermisson(AppCrudPermission permission);
}
