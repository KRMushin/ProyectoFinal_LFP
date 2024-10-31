/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Excepciones;

import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class ErrorSintacticoException extends Exception{
    
    private final Token token;

    public ErrorSintacticoException(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
    
    
    
}
