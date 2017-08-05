package org.manuel.teambuilting.authorization.rights;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@FunctionalInterface
public interface AppRightConstraint<T> {

    void isGranted(T object, AuthenticationJsonWebToken authentication);
}
