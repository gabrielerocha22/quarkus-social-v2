package com.git.gabriele.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserEqualsFollowerExceptionMapper implements ExceptionMapper<UserEqualsFollowerException> {

    @Override
    public Response toResponse(UserEqualsFollowerException exception) {
        return Response.status(Response.Status.CONFLICT).entity(exception.getMessage()).build();
    }
}
