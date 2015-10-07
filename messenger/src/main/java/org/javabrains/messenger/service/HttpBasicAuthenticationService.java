/**
 * 
 */
package org.javabrains.messenger.service;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Chejerla Karthik
 *
 */
public class HttpBasicAuthenticationService {
	
	private final static String USER = "admin";
	private final static String PASSWORD = "p@ssw0rd";
	
	public static boolean doAuthentication(String basicAuthString) {
		String httpBasicAuthToken = "";
		String validToken = getValidToken();

		if (basicAuthString.contains("Basic")) {
			httpBasicAuthToken = basicAuthString.substring(6);
		}
		
		return (httpBasicAuthToken.equals(validToken) ? Boolean.TRUE
				: Boolean.FALSE);
	}

	private static String getValidToken() {
		String token = USER + ":" + PASSWORD;
		byte[] encoded = Base64.encodeBase64(token.getBytes());
		return new String(encoded);
	}

}
