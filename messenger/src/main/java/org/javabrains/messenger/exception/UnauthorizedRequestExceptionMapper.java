/**
 * 
 */
package org.javabrains.messenger.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.javabrains.messenger.model.ErrorMessage;

/**
 * @author 539471
 *
 */
@Provider
public class UnauthorizedRequestExceptionMapper implements
		ExceptionMapper<UnauthorizedRequestException> {

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(UnauthorizedRequestException exception) {
		ErrorMessage errorMessage = new ErrorMessage(401,exception.getMessage(),"You are not supposed to make this request");
		return Response.status(Status.UNAUTHORIZED)
						.entity(errorMessage)
						.header("Content-Type", MediaType.APPLICATION_JSON)
						.build();
	}

}
