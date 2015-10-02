/**
 * 
 */
package org.javabrains.messenger.exception;

/**
 * @author Yashoda
 *
 */
public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3728715321608969355L;

	/**
	 * @param message
	 */
	public DataNotFoundException(String message) {
		super(message);
	}
}
