package com.quarkus.todo.security.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.HashSet;
import java.util.Set;

//CROSS-SITE REQUEST FORGERY
@Provider
public class CsrfFilter implements ContainerRequestFilter{


    @Override
    public void filter(ContainerRequestContext requestContext) {

        String csrfToken = requestContext.getHeaderString("X-GTTP-CSRF");
        if (csrfToken == null || !isValidCsrfToken(csrfToken)) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                    .entity("CROSS-SITE REQUEST FORGERY DETECTED").build());
        }
    }

    private boolean isValidCsrfToken(String csrfToken) {
        //fetch token from DB  and validate
        String fetchedToken="d#oqqnd";

        if(csrfToken.equals(fetchedToken))
            return true;
        else
            return false;
    }
}
