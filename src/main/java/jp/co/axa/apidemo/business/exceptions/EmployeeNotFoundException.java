package jp.co.axa.apidemo.business.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4112880035218443614L;

	public EmployeeNotFoundException(final String message) {
		super(message);
	}
}
