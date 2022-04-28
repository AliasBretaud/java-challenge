package jp.co.axa.apidemo.business.exceptions.handler;

import java.sql.Timestamp;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.co.axa.apidemo.business.exceptions.ApiException;
import jp.co.axa.apidemo.business.exceptions.EmployeeNotFoundException;

/**
 * Global exception handling. Wraps specifics exceptions raised in the application into a generic error
 * @author Florian
 *
 */
@ControllerAdvice
public class ExceptionHelper {
	
	/**
	 * Send exception in HTTP response when EmployeeNotFoundException is raised
	 * @param ex
	 * 			Exception
	 * @return HTTP response with 404 status and exception details
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		HttpStatus status = HttpStatus.NOT_FOUND;
	    ApiException apiException = buildApiException(status, ex);
	    return new ResponseEntity<Object>(apiException, status);
	}
	
	/**
	 * Send exception in HTTP response when non valid requests is detected
	 * @param ex
	 * 			Exception
	 * @return HTTP response with 400 status and exception details
	 */
	@ExceptionHandler(value = {
			ConstraintViolationException.class,
			HttpMessageNotReadableException.class,
			HttpMediaTypeNotSupportedException.class,
	})
	public ResponseEntity<Object> handleInvalidRequestException (Exception ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
	    ApiException apiException = buildApiException(status, ex);
	    return new ResponseEntity<Object>(apiException, status);
	}
	
	/**
	 * Builds Exception to return via the API
	 * @param status
	 * 			Error HTTP status
	 * @param ex
	 * 			Returned exception
	 * @return
	 * 			Builded/converted API exception
	 */
	private ApiException buildApiException(final HttpStatus status, final Exception ex) {
		return new ApiException(new Timestamp(System.currentTimeMillis()).toString(),
	    		status.value(),	ex.getClass().getName(), ex.getMessage());
	}
	
}
