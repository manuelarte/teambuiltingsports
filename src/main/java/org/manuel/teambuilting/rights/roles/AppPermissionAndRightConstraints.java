package org.manuel.teambuilting.rights.roles;


import org.manuel.teambuilting.rights.AppRight;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public interface AppPermissionAndRightConstraints<T> {

    AppRight<T> getRightConstraintsForPermisson(Permission permission);
}
