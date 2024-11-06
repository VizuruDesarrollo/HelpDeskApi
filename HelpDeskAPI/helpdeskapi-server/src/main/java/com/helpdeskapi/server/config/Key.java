/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.helpdeskapi.server.config;

import static java.util.Locale.ENGLISH;

/**
 *
 * @author Administrador
 */
public interface Key {
    String name();
    default String getKey(){
            return name().toLowerCase(ENGLISH).replaceAll("_",".");
        
    }
}
