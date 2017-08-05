package org.manuel.teambuilting.authorization.rights;

import org.springframework.security.core.Authentication;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
public interface AppRightConstraint<T> {

    boolean isGranted(T object, Authentication authentication);

    default String getNotGrantedReason() {
        return "User does not have permission to do the operation";
    }
}
