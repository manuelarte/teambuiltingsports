package org.manuel.teambuilting.rights.roles;

/**
 * @author Manuel Doncel Martos
 * @since 01/08/2017.
 */
public interface AppAuthorizationManager {

    <T> AppEntityAuthorization<? super T> getEntityAuthorizationFor(Class<? extends T> entity);
}
