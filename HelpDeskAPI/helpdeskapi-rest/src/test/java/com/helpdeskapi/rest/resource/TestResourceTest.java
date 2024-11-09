package com.helpdeskapi.rest.resource;

import jakarta.ws.rs.core.Application;

import java.util.logging.LogManager;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helpdeskapi.rest.ApiApplication;

import jakarta.ws.rs.core.Response;


public class TestResourceTest extends JerseyTest {
 
    static {
        LogManager.getLogManager().reset();
    }

    @Override
    protected Application configure() {
        return new ApiApplication();
    }

    @Test
    public void test() {
        Response response = target("/Test/Hello").request().get();
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Hola", response.readEntity(String.class));
        String Response= response.getHeaderString("Access-control-Allow-Origin");
        Assertions.assertEquals("*", Response);
        Assertions.assertEquals("GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH", response.getHeaderString("Access-control-Allow-Methods"));
        
       
}
}