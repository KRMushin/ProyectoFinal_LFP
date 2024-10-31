/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadorsDML;

import java.util.List;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorSelect {

    private List<Token> tokens;
    private int indice;
    private StringBuilder stringBuilder;
    
    public AnalizadorSelect(List<Token> tokens, int indice) {
        this.tokens = tokens;
        this.indice = indice;
        this.stringBuilder = new StringBuilder();
    }
    
    private Token tokenActual() {
        return tokens.get(indice);
    }

    private void siguienteToken() {
        if (indice < tokens.size() - 1) {
            indice++;
            stringBuilder.append(tokenActual().getLexema());
        }
    }

    public int analizar() {
        siguienteToken();
        return 0;
        
        
        
    }
    
}
