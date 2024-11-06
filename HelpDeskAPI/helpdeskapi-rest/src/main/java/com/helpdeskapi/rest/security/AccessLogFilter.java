/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpdeskapi.rest.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrador
 */
@Provider
public class AccessLogFilter implements ContainerRequestFilter{
          private static final Logger LOGGER = LoggerFactory.getLogger("access-log");
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
                String user ="TODO";
                String method = containerRequestContext.getMethod();
                String path = containerRequestContext.getUriInfo().getPath();
                
                LOGGER.info("{}=>{} {}",user,method,path);
                        
    }
    
}
