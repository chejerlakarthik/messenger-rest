/**
 * 
 */
package org.javabrains.messenger.exception;

/**
 * @author 539471
 * 
 */
public class UnauthorizedRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092287905657086461L;

	public UnauthorizedRequestException(String errorMessage) {
			super(errorMessage);
	}

}
