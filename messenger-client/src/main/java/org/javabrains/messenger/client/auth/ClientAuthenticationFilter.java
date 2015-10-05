/**
 * 
 */
package org.javabrains.messenger.client.auth;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.apache.commons.codec.binary.Base64;



/**
 * @author 539471
 *
 */
@Provider
public class ClientAuthenticationFilter implements ClientRequestFilter {

	private final String user;
	private final String password;
	
	/**
	 *  No-arg constructor
	 */
	public ClientAuthenticationFilter(){
		this.user = "admin";
		this.password = "p@ssw0rd";
	}
	
	/**
	 * @param user
	 * @param password
	 */
	public ClientAuthenticationFilter(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String,Object> headers = requestContext.getHeaders();
		final String basicAuthTokenEncoded = generateBasicAuthenticationToken();
		headers.add("Authorization", basicAuthTokenEncoded);
	}

	private String generateBasicAuthenticationToken() {
		String token = this.user + ":" + this.password;
		byte[] encoded = Base64.encodeBase64(token.getBytes());
		return "Basic " + new String(encoded);
	}
}
