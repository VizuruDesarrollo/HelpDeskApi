package com.helpdeskapi.rest.security;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
@Provider
public class CorsFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
                        responseContext.getHeaders().add("My-Custom-Header", "Value");

                        responseContext.getHeaders().add("Access-control-Allow-Origin","*");
                        responseContext.getHeaders().add("Access-control-Allow-Methods","GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH");
                        

    }
    
}
