/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpdeskapi.server.config;

/**
 *
 * @author Administrador
 */
import java.util.Locale;
public enum ConfigKey {
    
    SERVER_KEYSTORE_FILE,
    SERVER_KEYSTORE_TYPE,
    SERVER_KEYSTORE_PASSWORD,
    SERVER_WEB_CONTENT;
            public String getKey() {
                return name().toLowerCase(Locale.ENGLISH).replaceAll("_", ".");
            }
}
