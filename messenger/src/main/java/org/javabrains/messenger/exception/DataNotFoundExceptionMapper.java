package org.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.javabrains.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements
		ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(404,exception.getMessage(),"http://javabrains.koushik.org");
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}

}
