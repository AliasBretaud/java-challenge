package jp.co.axa.apidemo.business.exceptions.handler;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.co.axa.apidemo.business.exceptions.ApiException;
import jp.co.axa.apidemo.business.exceptions.EmployeeNotFoundException;

@ControllerAdvice
public class ExceptionHelper {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		HttpStatus status = HttpStatus.NOT_FOUND;
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
