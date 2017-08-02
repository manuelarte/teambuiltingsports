package org.manuel.teambuilting.sports.authorization;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.manuel.teambuilting.rights.AppRight;
import org.manuel.teambuilting.rights.roles.AppPermissionAndRightConstraints;
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
    private final Map<Permission, AppRight<T>> permissonsAndRightConstraints;

    @Override
    public AppRight<T> getRightConstraintsForPermisson(final Permission permission) {
        return permissonsAndRightConstraints.get(permission);
    }

    @RequiredArgsConstructor(staticName = "of")
    public static class PermissionAndRightConstraintsImplBuilder<T> {

        private final Map<Permission, AppRight<T>> map;

        public PermissionAndRightConstraintsImplBuilder() {
            this.map = new HashMap<>(Permission.values().length);
        }

        public PermissionAndRightConstraintsImplBuilder<T> create(final AppRight<T> entityAuthorizationManager) {
            return add(Permission.CREATE, entityAuthorizationManager);
        }

        public PermissionAndRightConstraintsImplBuilder<T> read(final AppRight<T> entityAuthorizationManager) {
            return add(Permission.READ, entityAuthorizationManager);
        }

        public PermissionAndRightConstraintsImplBuilder<T> update(final AppRight<T> entityAuthorizationManager) {
            return add(Permission.UPDATE, entityAuthorizationManager);
        }

        public PermissionAndRightConstraintsImplBuilder<T> delete(final AppRight<T> entityAuthorizationManager) {
            return add(Permission.DELETE, entityAuthorizationManager);
        }

        private PermissionAndRightConstraintsImplBuilder<T> add(final Permission permission, final AppRight<T> entityAuthorizationManager) {
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
