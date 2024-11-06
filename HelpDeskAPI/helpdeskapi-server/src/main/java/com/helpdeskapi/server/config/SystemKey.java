/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpdeskapi.server.config;



/**
 *
 * @author Administrador
 */
public enum SystemKey implements Key {
    PORT("8443"),
    MODE("dev");
    
    private final String defaultValue;
    SystemKey(String defaultValue){
    this.defaultValue=defaultValue;
    }
    public String getdefaultValue(){
    return defaultValue;
    }
  
}