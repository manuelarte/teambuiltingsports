package org.manuel.teambuilting.sports.aspects;

import com.auth0.Auth0User;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.sports.model.UserData;
import org.manuel.teambuilting.sports.services.query.UserService;
import org.manuel.teambuilting.sports.util.Util;
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

	@Before(
		value="@annotation(org.manuel.teambuilting.sports.aspects.UserCanCud) && args(playerIdDependentEntity)")
	public void saveEntityToUserData(final JoinPoint call, final PlayerDependentEntity playerIdDependentEntity) {
		final Auth0User user = util.getUserProfile().get();
		final UserData userData = userService.getOrCreateUserData(user.getUserId());
		if (!Optional.ofNullable(userData.getPlayerId()).isPresent() ||
				!userData.getPlayerId().equals(playerIdDependentEntity.getPlayerId())) {
			throw new UserNotAllowedToModifyEntityException();
		}
	}

}
