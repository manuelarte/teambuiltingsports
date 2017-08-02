package org.manuel.teambuilting.sports.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.manuel.teambuilting.rights.AppRight;
import org.manuel.teambuilting.rights.roles.AppAuthorizationManager;
import org.manuel.teambuilting.rights.roles.AppEntityAuthorization;
import org.manuel.teambuilting.rights.roles.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.rights.roles.AppRole;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor
@Builder
public class AppAuthorizationManagerImpl implements AppAuthorizationManager {

    @Singular
    private final Map<Class<?>, AppEntityAuthorization<?>> authorizations;

    @Override
    public <T> AppEntityAuthorization<? super T> getEntityAuthorizationFor(final Class<? extends T> clazz) {
        return (AppEntityAuthorization<T>) authorizations.get(clazz);
    }

    public static class AppAuthorizationManagerImplBuilder {

        public <T> AppAuthorizationManagerImplBuilder forEntity(final Class<T> clazz,
                final AppEntityAuthorization<T> appEntityAuthorization) {
            this.authorization(clazz, appEntityAuthorization);
            return this;
        }

    }

    public static class RoleMapBuilder<T> {

        private Map<AppRole, AppPermissionAndRightConstraints<? super T>> rolesAndRights = new HashMap<>();
        private Class<T> entity;
        private AppRole appRole;

        public static <T> RoleMapBuilder<T> forEntity(final Class<T> entity) {
            return new RoleMapBuilder<>(entity);
        }

        private RoleMapBuilder(final Class<T> entity) {
            this.entity = entity;
        }

        public PermissonMapBuilder<T> forRole(final AppRole appRole) {
            this.appRole = appRole;
            return PermissonMapBuilder.of(this);
        }

        private void addIt(final AppPermissionAndRightConstraints<T> map) {
            this.rolesAndRights.put(appRole, map);
        }

        @RequiredArgsConstructor(staticName = "of")
        public static class PermissonMapBuilder<T> {
            private final RoleMapBuilder<T> parent;
            private AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder builder = new AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder();

            public PermissonMapBuilder<T> create(final AppRight<T> entityRights) {
                builder.create(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> read(final AppRight<T> entityRights) {
                builder.read(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> update(final AppRight<T> entityRights) {
                builder.update(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> delete(final AppRight<T> entityRights) {
                builder.delete(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> andForRole(final AppRole appRole) {
                final AppPermissionAndRightConstraintsImpl<T> of = builder.build();
                parent.addIt(of);
                return new RoleMapBuilder(parent.entity).forRole(appRole);
            }

            public <Y> RoleMapBuilder<Y> andForEntity(final Class<Y> entity) {
                return parent.forEntity(entity);
            }

        }
    }

}
