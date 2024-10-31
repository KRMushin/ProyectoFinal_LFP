/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Entity;

import java.util.List;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class Tabla {
    
    private String nombreTabla;
    private List<Token> tokens;

    public Tabla() {
    }

    public Tabla(String nombreTabla, List<Token> tokens) {
        this.nombreTabla = nombreTabla;
        this.tokens = tokens;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
    
    
    
}
