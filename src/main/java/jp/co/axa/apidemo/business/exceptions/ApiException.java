package jp.co.axa.apidemo.business.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException {

	/** Exception timestamp **/
	private String timestamp;
	
	/** Returned HTTP status related to the exception**/
	private int status;
	
	/** Error class **/
	private String error;
	
	/** Error message **/
	private String message;
}