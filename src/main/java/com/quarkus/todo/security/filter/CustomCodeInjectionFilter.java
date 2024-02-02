package com.quarkus.todo.security.filter;

import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Provider
public class CustomCodeInjectionFilter implements ContainerRequestFilter {

    public static final String INVALID_CHARACTERS = "[<>]"+"|"+"&lt;"+"|"+"&gt;";

    public static final Pattern pattern = Pattern.compile(INVALID_CHARACTERS, Pattern.CASE_INSENSITIVE);

    @Override
    public void filter(ContainerRequestContext requestContext) {

        boolean isCrossSiteScript = validateRequestHeaders(requestContext) ;//|| validateRequestParameters(request);
        if(isCrossSiteScript){
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                    .entity("WARNING: XSS SCRIPT INJECTION").build());
        }

    }

    private boolean validateRequestHeaders(ContainerRequestContext requestContext){
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        return headers != null &&
                headers.entrySet().stream()
                        .anyMatch(valueSet -> valueSet.getValue().stream()
                                .anyMatch(value -> validateAgainstPattern(value) || validateAgainstPattern(valueSet.getKey())));
    }
    private boolean validateAgainstPattern(String value){
        Matcher matcher=pattern.matcher(value);
        return matcher.find();
    }


}
