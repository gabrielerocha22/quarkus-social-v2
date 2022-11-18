package com.git.gabriele.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundUserExceptionMapper implements ExceptionMapper<NotFoundUserException> {

    @Override
    public Response toResponse(NotFoundUserException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }
}