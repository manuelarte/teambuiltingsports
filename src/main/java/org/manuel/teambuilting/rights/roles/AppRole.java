package org.manuel.teambuilting.rights.roles;

import org.manuel.teambuilting.rights.AppPermission;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
public enum AppRole {

    /**
     * Not registered
     */
    VISITOR,
    /**
     * Registered with the Free account
     */
    FREE,
    /**
     * Registered with the Gold Account
     */
    GOLD,
    /**
     * Registered with the Premium Account
     */
    PREMIUM,
    /**
     * Admin of the platform
     */
    ADMIN;

    public boolean hasPermission(final AppPermission permission) {
        return true;
    }
}
