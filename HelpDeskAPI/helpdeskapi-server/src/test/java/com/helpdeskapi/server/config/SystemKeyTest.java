package com.helpdeskapi.server.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SystemKeyTest {
    @Test
    public void testDefaultValues(){
        Assertions.assertEquals("8443", SystemKey.PORT.defaultValue());
        Assertions.assertEquals("dev", SystemKey.MODE.defaultValue());
    }
    @Test
    public void testGetKey(){
        Assertions.assertEquals("port", SystemKey.PORT.getKey());
    }

}
