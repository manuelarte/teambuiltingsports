package org.manuel.teambuilting.sports.aspects;

import com.auth0.Auth0User;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.manuel.teambuilting.core.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.rights.roles.AppEntityAuthorization;
import org.manuel.teambuilting.rights.roles.AppRole;
import org.manuel.teambuilting.rights.roles.Permission;
import org.manuel.teambuilting.sports.authorization.AppAuthorizationManagerImpl;
import org.manuel.teambuilting.sports.model.UserData;
import org.manuel.teambuilting.sports.services.query.UserService;
import org.manuel.teambuilting.sports.util.Util;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author manuel.doncel.martos
 * @since 9-1-2017
 */
@Aspect
@Component
@AllArgsConstructor
public class UserDataAspect {

	private final UserService userService;
	private final Util util;
	private final AppAuthorizationManagerImpl authorizationManager;

	@Before(
		value="@annotation(org.manuel.teambuilting.core.aspects.UserCanCud) && args(playerIdDependentEntity)")
	public void saveEntityToUserData(final JoinPoint call, final PlayerDependentEntity playerIdDependentEntity) {
		final Auth0User user = util.getUserProfile().get();
		final UserData userData = userService.getOrCreateUserData(user.getUserId());
		if (!Optional.ofNullable(userData.getPlayerId()).isPresent() ||
				!userData.getPlayerId().equals(playerIdDependentEntity.getPlayerId())) {
			throw new UserNotAllowedToModifyEntityException();
		}
        final Class<? extends PlayerDependentEntity> aClass = playerIdDependentEntity.getClass();
        final AppEntityAuthorization<? super PlayerDependentEntity> entityAuthorizationManager = authorizationManager.getEntityAuthorizationFor(aClass);
        entityAuthorizationManager.geConstraintsFor(AppRole.ADMIN).getRightConstraintsForPermisson(Permission.READ).isGranted(playerIdDependentEntity, (AuthenticationJsonWebToken) SecurityContextHolder.getContext().getAuthentication());
	}

}
