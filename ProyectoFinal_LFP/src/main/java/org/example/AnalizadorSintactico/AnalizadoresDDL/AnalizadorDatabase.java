/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadoresDDL;

import java.util.List;
import org.example.Entity.Database;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorDatabase {
    private final List<Token> tokens;
    private Database baseCreada;
    private int indice;

    public AnalizadorDatabase(List<Token> tokens, int indice) {
        this.tokens = tokens;
        this.indice = indice;
        this.baseCreada = new Database();
    }
    
    private Token tokenActual() {
        return tokens.get(indice);
    }

    private void siguienteToken() {
        if (indice < tokens.size() - 1) {
            indice++;
        }
    }

    public int analizar() throws ErrorSintacticoException {
        siguienteToken();
        Token token = tokenActual();
        
        if (token.getTipoToken() == TipoToken.IDENTIFICADOR) {
            System.out.println("comando valido : tabla creada con exito");
            baseCreada.setNombre(tokenActual().getLexema());
            siguienteToken();
            evaluarToken(";");
        }else{
                throw new ErrorSintacticoException(this.tokenActual());
        }
        return indice;
    }

    private void evaluarToken(String tokenEsperado) throws ErrorSintacticoException {
            if (!this.tokenActual().getLexema().equals(tokenEsperado)) {
                throw new ErrorSintacticoException(this.tokenActual());
            }
            siguienteToken(); // avanzar
    }

    public Database getBaseCreada() {
        return baseCreada;
    }

    public void setBaseCreada(Database baseCreada) {
        this.baseCreada = baseCreada;
    }
    
    
}
