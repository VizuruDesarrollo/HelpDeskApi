/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpdeskapi.server.config;

import java.util.Locale;

/**
 *
 * @author Administrador
 */
public enum SystemKey {
    PORT("8443"),
    MODE("dev");
    
    private final String defaultValue;
    SystemKey(String defaultValue){
    this.defaultValue=defaultValue;
    }
    public String defaultValue(){
    return defaultValue;
    }
    public String getKey(){
    return name().toLowerCase(Locale.ENGLISH).replaceAll("_", ".");
    }
}
