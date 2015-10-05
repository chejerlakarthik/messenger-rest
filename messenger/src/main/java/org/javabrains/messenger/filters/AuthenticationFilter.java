/**
 * 
 */
package org.javabrains.messenger.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.javabrains.messenger.exception.UnauthorizedRequestException;
import org.javabrains.messenger.service.HttpBasicAuthenticationService;

/**
 * @author 539471
 *
 */
@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

	private final String HEADER_AUTHORIZATION = "Authorization";
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		String basicAuthString = requestContext.getHeaders().get(HEADER_AUTHORIZATION).get(0);
		boolean isAuthenticated = HttpBasicAuthenticationService.doAuthentication(basicAuthString);
		
		if(!isAuthenticated){
			throw new UnauthorizedRequestException("UnauthenticatedClient");
		}
	}

}
