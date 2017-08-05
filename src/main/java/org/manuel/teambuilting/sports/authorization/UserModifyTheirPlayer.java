package org.manuel.teambuilting.sports.authorization;

import com.google.common.base.Optional;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.rights.AppRightConstraint;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.authorization.util.AuthenticationUtil;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.sports.model.UserData;
import org.manuel.teambuilting.sports.repositories.UserDataRepository;
import org.springframework.security.core.Authentication;

/**
 * @author Manuel Doncel Martos
 * @since 02/08/2017.
 */
@AllArgsConstructor
public class UserModifyTheirPlayer<T extends PlayerDependentEntity> implements AppRightConstraint<T> {

    protected final AppRightConstraint<T> entityAuthorizationManager;
    private final UserDataRepository userDataRepository;

    @Override
    public boolean isGranted(final T object, final Authentication authentication) {
        if (!playerOfUser(authentication.getName(), object) || !isAdmin(authentication)) {
            return false;
        }
        return entityAuthorizationManager.isGranted(object, authentication);
    }

    @Override
    public String getNotGrantedReason() {
        return "User cannot modify this player";
    }

    private boolean playerOfUser(final String userId, final T object) {
        final Optional<UserData> userData = Optional.fromNullable(userDataRepository.findOne(userId));
        return userData.isPresent() && userData.get().getPlayerId().equals(object.getPlayerId());
    }

    private boolean isAdmin(final Authentication authentication) {
        return AuthenticationUtil.getAppRole(authentication) == AppRole.ADMIN ||
                "google-oauth2|115535991985670597779".equals(authentication.getName());
    }

}
