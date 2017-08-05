package org.manuel.teambuilting.sports.aspects;

import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.manuel.teambuilting.core.model.IdAndPlayerDependentEntity;
import org.manuel.teambuilting.rights.AppEntityAuthorization;
import org.manuel.teambuilting.rights.roles.AppCrudPermission;
import org.manuel.teambuilting.rights.roles.AppRole;
import org.manuel.teambuilting.rights.util.AuthenticationUtil;
import org.manuel.teambuilting.sports.authorization.AppAuthorizationManagerImpl;
import org.manuel.teambuilting.sports.services.query.UserService;
import org.manuel.teambuilting.sports.util.Util;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
	public void saveOrUpdateEntityToUserData(final JoinPoint call, final IdAndPlayerDependentEntity playerIdDependentEntity) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Class<? extends IdAndPlayerDependentEntity> aClass = playerIdDependentEntity.getClass();
        final AppRole appRole = AuthenticationUtil.getAppRole(authentication);
        final AppCrudPermission permission = playerIdDependentEntity.getId() == null ?
                AppCrudPermission.CREATE : AppCrudPermission.UPDATE;
		final AppEntityAuthorization<? super IdAndPlayerDependentEntity> entityAuthorizationManager =
                authorizationManager.getEntityAuthorizationFor(aClass);
        entityAuthorizationManager.geConstraintsFor(appRole).getRightConstraintsForPermisson(permission)
                .isGranted(playerIdDependentEntity, (AuthenticationJsonWebToken) authentication);
	}

}
