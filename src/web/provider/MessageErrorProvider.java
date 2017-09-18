package web.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import javabeens.ErrorMessage;

@Provider
public class MessageErrorProvider implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		
		return Response.serverError().entity(new ErrorMessage(e.getMessage())).build();
	}
}  
