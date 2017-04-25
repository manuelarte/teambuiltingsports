package org.manuel.teambuilting.sports.aspects;

import com.auth0.authentication.result.UserProfile;
import com.auth0.spring.security.api.Auth0JWTToken;

import java.util.Optional;

import javax.inject.Inject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.manuel.teambuilting.core.model.PlayerDependentEntity;
import org.manuel.teambuilting.exceptions.UserNotAllowedToModifyEntityException;
import org.manuel.teambuilting.sports.config.Auth0Client;
import org.manuel.teambuilting.sports.model.UserData;
import org.manuel.teambuilting.sports.services.query.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author manuel.doncel.martos
 * @since 9-1-2017
 */
@Aspect
@Component
public class UserDataAspect {

	private final UserService userService;
	private final Auth0Client auth0Client;

	@Inject
	public UserDataAspect(final UserService userService, final Auth0Client auth0Client) {
		this.userService = userService;
		this.auth0Client = auth0Client;
	}

	@Before(
		value="@annotation(org.manuel.teambuilting.sports.aspects.UserCanCud) && args(playerIdDependentEntity)")
	public void saveEntityToUserData(final JoinPoint call, final PlayerDependentEntity playerIdDependentEntity) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserProfile user = auth0Client.getUser((Auth0JWTToken) auth);
		final UserData userData = userService.getOrCreateUserData(user.getId());
		if (!Optional.ofNullable(userData.getPlayerId()).isPresent() ||
				!userData.getPlayerId().equals(playerIdDependentEntity.getPlayerId())) {
			throw new UserNotAllowedToModifyEntityException();
		}
	}

}
