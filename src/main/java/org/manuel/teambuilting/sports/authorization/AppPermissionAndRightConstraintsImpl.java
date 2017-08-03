package org.manuel.teambuilting.sports.authorization;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.manuel.teambuilting.rights.AppRightConstraint;
import org.manuel.teambuilting.rights.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.rights.roles.Permission;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor(staticName = "of")
public class AppPermissionAndRightConstraintsImpl<T> implements AppPermissionAndRightConstraints<T> {

    @Singular
    private final Map<Permission, AppRightConstraint<? super T>> permissonsAndRightConstraints;

    @Override
    public AppRightConstraint<? super T> getRightConstraintsForPermisson(final Permission permission) {
        return permissonsAndRightConstraints.get(permission);
    }

    @RequiredArgsConstructor(staticName = "of")
    public static class PermissionAndRightConstraintsImplBuilder<T> {

        private final Map<Permission, AppRightConstraint<? super T>> map;

        public PermissionAndRightConstraintsImplBuilder() {
            this.map = new HashMap<>(Permission.values().length);
        }

        public PermissionAndRightConstraintsImplBuilder<T> create(final AppRightConstraint<T> entityAuthorizationManager) {
            return add(Permission.CREATE, entityAuthorizationManager);
        }

        public PermissionAndRightConstraintsImplBuilder<T> read(final AppRightConstraint<T> entityAuthorizationManager) {
            return add(Permission.READ, entityAuthorizationManager);
        }

        public PermissionAndRightConstraintsImplBuilder<T> update(final AppRightConstraint<T> entityAuthorizationManager) {
            return add(Permission.UPDATE, entityAuthorizationManager);
        }

        public PermissionAndRightConstraintsImplBuilder<T> delete(final AppRightConstraint<T> entityAuthorizationManager) {
            return add(Permission.DELETE, entityAuthorizationManager);
        }

        private PermissionAndRightConstraintsImplBuilder<T> add(final Permission permission, final AppRightConstraint<T> entityAuthorizationManager) {
            if (map.containsKey(permission)) {
                throw new IllegalArgumentException("Permisson already registered");
            }
            map.put(permission, entityAuthorizationManager);
            return this;
        }

        public AppPermissionAndRightConstraintsImpl<T> build() {
            return new AppPermissionAndRightConstraintsImpl<>(map);
        }

    }

}
