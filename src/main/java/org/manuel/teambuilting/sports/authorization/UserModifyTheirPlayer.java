package org.manuel.teambuilting.sports.authorization;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import com.google.common.base.Optional;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.core.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.authorization.util.AuthenticationUtil;
import org.manuel.teambuilting.sports.model.UserData;
import org.manuel.teambuilting.sports.repositories.UserDataRepository;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
@AllArgsConstructor
public class UserModifyTheirPlayer<T extends PlayerDependentEntity> implements AppRightConstraint<T> {

    protected final AppRightConstraint<T> entityAuthorizationManager;
    private final UserDataRepository userDataRepository;

    public void isGranted(final T object, final AuthenticationJsonWebToken authentication) {
        if (!playerOfUser(authentication.getName(), object) || !isAdmin(authentication)) {
            throw new UserNotAllowedToModifyEntityException("User cannot modify this player");
        }
        entityAuthorizationManager.isGranted(object, authentication);
    }

    private boolean playerOfUser(final String userId, final T object) {
        final Optional<UserData> userData = Optional.fromNullable(userDataRepository.findOne(userId));
        return userData.isPresent() && userData.get().getPlayerId().equals(object.getPlayerId());
    }

    private boolean isAdmin(final AuthenticationJsonWebToken authentication) {
        return AuthenticationUtil.getAppRole(authentication) == AppRole.ADMIN ||
                "google-oauth2|115535991985670597779".equals(authentication.getName());
    }

}
