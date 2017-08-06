package org.manuel.teambuilting.sports.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.manuel.teambuilting.authorization.AppAuthorizationManager;
import org.manuel.teambuilting.authorization.AppEntityAuthorization;
import org.manuel.teambuilting.authorization.permissions.AppCrudPermission;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.authorization.util.AuthenticationUtil;
import org.manuel.teambuilting.core.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.core.model.IdAndPlayerDependentEntity;
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

	private final AppAuthorizationManager authorizationManager;

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
        if (!entityAuthorizationManager.geConstraintsFor(appRole).getRightConstraintsForPermisson(permission)
                .isGranted(playerIdDependentEntity, authentication)) {
            throw new UserNotAllowedToModifyEntityException(entityAuthorizationManager.geConstraintsFor(appRole).getRightConstraintsForPermisson(permission)
            .getNotGrantedReason());
        }
	}

}
