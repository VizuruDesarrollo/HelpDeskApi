/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpdeskapi.server.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Administrador
 */
public class SystemKeyTest {
    @Test
    public void testDefaultValues(){
                    Assertions.assertEquals("8443", SystemKey.PORT.getdefaultValue());
                    Assertions.assertEquals("8443", SystemKey.PORT.getdefaultValue());
    }
        @Test
    public void testGetKey(){
                    Assertions.assertEquals("port", SystemKey.PORT.getKey());
    }
    
}
 