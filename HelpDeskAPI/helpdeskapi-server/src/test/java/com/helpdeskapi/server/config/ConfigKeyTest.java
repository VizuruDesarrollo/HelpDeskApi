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
public class ConfigKeyTest {
    @Test
    public void testGetKey(){
                    Assertions.assertEquals("server.keystore.file", ConfigKey.SERVER_KEYSTORE_FILE.getKey());
    }
}
