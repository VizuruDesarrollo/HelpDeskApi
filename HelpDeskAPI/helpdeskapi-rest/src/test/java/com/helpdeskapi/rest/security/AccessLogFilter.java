package com.helpdeskapi.rest.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
@Provider
public class AccessLogFilter implements ContainerRequestFilter{
    private static final Logger LOGGER = LoggerFactory.getLogger("access-log");
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String user ="TODO";
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();
        LOGGER.info("{} => {} {}", user,method,path);

    }
    
}
