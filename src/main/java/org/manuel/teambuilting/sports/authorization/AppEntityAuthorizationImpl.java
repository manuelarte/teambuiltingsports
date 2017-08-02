package org.manuel.teambuilting.sports.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.manuel.teambuilting.rights.AppRight;
import org.manuel.teambuilting.rights.roles.AppEntityAuthorization;
import org.manuel.teambuilting.rights.roles.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.rights.roles.AppRole;

import java.util.Map;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor
@Builder
public class AppEntityAuthorizationImpl<T> implements AppEntityAuthorization<T> {

    @Singular
    private final Map<AppRole, AppPermissionAndRightConstraints<T>> constraints;

    @Override
    public AppPermissionAndRightConstraints<T> geConstraintsFor(final AppRole appRole) {
        return constraints.get(appRole);
    }

    public static class AppEntityAuthorizationImplBuilder<T> {

        private AppRole appRole;

        public PermissonMapBuilder<T> forRole(final AppRole appRole) {
            this.appRole = appRole;
            return PermissonMapBuilder.of(this);
        }

        private AppEntityAuthorizationImplBuilder addIt(final AppPermissionAndRightConstraints<T> constraints) {
            this.constraint(appRole, constraints);
            return this;
        }

        @RequiredArgsConstructor(staticName = "of")
        public static class PermissonMapBuilder<T> {
            private final AppEntityAuthorizationImplBuilder<T> parent;
            private AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder builder = new AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder();

            public PermissonMapBuilder<T> create(final AppRight<T> entityRights) {
                builder = builder.create(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> read(final AppRight<T> entityRights) {
                builder = builder.read(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> update(final AppRight<T> entityRights) {
                builder = builder.update(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> delete(final AppRight<T> entityRights) {
                builder = builder.delete(entityRights);
                return this;
            }

            public PermissonMapBuilder<T> andForRole(final AppRole appRole) {
                parent.addIt(builder.build());
                return parent.forRole(appRole);
            }

            public AppEntityAuthorizationImpl<T> build() {
                parent.addIt(builder.build());
                return parent.build();
            }

        }
    }

}
