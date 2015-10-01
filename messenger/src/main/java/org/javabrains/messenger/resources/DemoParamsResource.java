package org.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/demoparams")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class DemoParamsResource {
	
	@GET
	public String demonstrateParams(@MatrixParam("matrixParam") String matrix, 
									 @HeaderParam("headerParam") String header,
									 @CookieParam("cookieParam") String cookie){
		return "Matrix Param: " + matrix + " Header Param: " + header + " Cookie Param: " + cookie;
	}
	
	@GET
	@Path("/context")
	public String demoContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
		return "Absolute Uri Path: ->" + uriInfo.getAbsolutePath().toString() + " Cookies: ->" + headers.getCookies().toString();
	}
}
