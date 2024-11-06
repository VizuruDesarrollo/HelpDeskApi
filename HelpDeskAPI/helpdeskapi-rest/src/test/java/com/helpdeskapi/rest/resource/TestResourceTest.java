/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpdeskapi.rest.resource;

import com.helpdeskapi.rest.ApiApplication;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import java.util.logging.LogManager;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Administrador
 */
public class TestResourceTest extends JerseyTest{
    static{
    LogManager.getLogManager().reset();
    }
    protected Application cofigure(){
        return new ApiApplication();
    }
    @Test
    public void test(){
            Response response=target("/test").request().get();
            Assertions.assertEquals(200, response.getStatus());
    }
}
