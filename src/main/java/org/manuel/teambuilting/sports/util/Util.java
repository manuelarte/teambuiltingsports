package org.manuel.teambuilting.sports.util;

import com.auth0.Auth0Client;
import com.auth0.Auth0User;
import com.auth0.Tokens;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import lombok.AllArgsConstructor;
import org.manuel.teambuilting.rights.roles.AppRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author manuel.doncel.martos
 * @since 11-3-2017
 */
@Component
@AllArgsConstructor
public class Util {

	private final Auth0Client auth0Client;

	public Optional<Auth0User> getUserProfile() {
		Optional<Auth0User> toReturn = Optional.empty();
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AuthenticationJsonWebToken) {
			final String token = ((AuthenticationJsonWebToken) auth).getToken();
			toReturn = Optional.of(auth0Client.getUserProfile(new Tokens(token, null, "JWT", null)));
		}
		return toReturn;
	}

	public AppRole getYourAppRole() {
		return getAppRoleFor(SecurityContextHolder.getContext().getAuthentication());
	}

	public AppRole getAppRoleFor(final Authentication authentication) {
		AppRole toReturn = AppRole.VISITOR;
		if (authentication != null && authentication instanceof AuthenticationJsonWebToken) {
			toReturn = AppRole.FREE;
		}
		return toReturn;
	}

}
