package org.manuel.teambuilting.sports.config;

import org.manuel.teambuilting.exceptions.ExceptionMessage;
import org.manuel.teambuilting.exceptions.UserNotAllowedToModifyEntity;
import org.manuel.teambuilting.exceptions.ValidationRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author manuel.doncel.martos
 * @since 13-1-2017
 */
@Component
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler({ValidationRuntimeException.class})
	public ResponseEntity<Object> validationExceptionHandler(final ValidationRuntimeException exception, final WebRequest request) {
		final HttpStatus status = HttpStatus.BAD_REQUEST;
		final ExceptionMessage errorMessage = new ExceptionMessage(status, exception.getErrorCode(), exception.getMessage(), exception.getDeveloperMessage());
		return new ResponseEntity<Object>(errorMessage, status);
	}

	@ExceptionHandler({UserNotAllowedToModifyEntity.class})
	public ResponseEntity<Object> userNotAllowedToModifyEntityExceptionHandler(final UserNotAllowedToModifyEntity exception, final WebRequest request) {
		final HttpStatus status = HttpStatus.FORBIDDEN;
		final ExceptionMessage errorMessage = new ExceptionMessage(status, "NOT ALLOWED", "NOT ALLOWED", "");
		return new ResponseEntity<Object>(errorMessage, status);
	}
}
