package org.manuel.teambuilting.sports.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import org.manuel.teambuilting.rights.EntityAuthorizationManager;
import org.manuel.teambuilting.rights.roles.AuthorizationManager;
import org.manuel.teambuilting.rights.roles.Permission;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@AllArgsConstructor
@Builder
public class AppAuthorizationManagerImpl implements AuthorizationManager {

    @Singular
    private final Map<Class<?>, Map<Permission, EntityAuthorizationManager<?>>> authorizations;

    @Override
    public <T> EntityAuthorizationManager<? super T> getEntityAuthorizationManager(Class<? extends T> clazz, Permission permission) {
        return (EntityAuthorizationManager<T>) authorizations.get(clazz).get(permission);
    }

    @Override
    public <T> Map<Permission, EntityAuthorizationManager<? super T>> getRulesPerPermission(Class<? extends T> clazz) {
        return authorizations.get(clazz).entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(),
                entry -> (EntityAuthorizationManager<T>) (entry.getValue())));
    }

    public static class AppAuthorizationManagerImplBuilder {

        public <T> AppAuthorizationManagerImplBuilder add(final Class<T> clazz, Map<Permission, EntityAuthorizationManager<T>> map) {
            final Map<Permission, EntityAuthorizationManager<?>> collect = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()));
            this.authorization(clazz, collect);
            return this;
        }
    }

}
