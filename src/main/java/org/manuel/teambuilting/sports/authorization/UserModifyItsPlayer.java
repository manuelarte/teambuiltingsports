package org.manuel.teambuilting.sports.authorization;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.rights.EntityAuthorizationManager;
import org.manuel.teambuilting.sports.repositories.UserDataRepository;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
public class UserModifyItsPlayer<T extends PlayerDependentEntity> extends EntityAuthorizationManager<T> {

    protected final EntityAuthorizationManager<T> entityAuthorizationManager;
    private final UserDataRepository userDataRepository;

    public UserModifyItsPlayer(final EntityAuthorizationManager<T> entityAuthorizationManager,
                               final UserDataRepository userDataRepository) {
        this.entityAuthorizationManager = entityAuthorizationManager;
        this.userDataRepository = userDataRepository;
    }

    public void isGranted(final T object, final AuthenticationJsonWebToken authentication) {
        /*if (notSameUser() || notAdmin()) {
            throw new UserNotAllowedToModifyEntityException("User cannot modify this player");
        }*/
        entityAuthorizationManager.isGranted(object, authentication);
    }

}
