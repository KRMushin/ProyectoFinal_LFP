/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadorsDML;

import java.util.List;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorColumnas {

    private List<Token> tokens;
    private int indice;
    private StringBuilder stringBuilder;
    
    public AnalizadorColumnas(List<Token> token, int indice) {
        this.stringBuilder = new StringBuilder();
        this.tokens = token;
        this.indice = indice;
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

    public int analizar() throws ErrorSintacticoException {
        siguienteToken();
        if (tokenActual().getLexema().equals(",")) {
            analizarColumnasDobles();
        }
        if (tokenActual().getLexema().equals("(")) {
            siguienteToken();

            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                    tokenActual().setMensajeError("Se esperaba identificador");
                    throw new ErrorSintacticoException(tokenActual());
            }
            siguienteToken();
            if (!tokenActual().getLexema().equals(")")) {
                    tokenActual().setMensajeError("Se esperaba cierre");
                    throw new ErrorSintacticoException(tokenActual());
            }
        }
        
        analizarAgregaciones();
        return indice;

    }

    private void analizarColumnasDobles() throws ErrorSintacticoException {
        siguienteToken();
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        analizarAgregaciones();
    }

    private void analizarAgregaciones() throws ErrorSintacticoException {
        siguienteToken();
        if (!tokenActual().getLexema().equals("FROM")) {
            tokenActual().setMensajeError("Se esperaba FROM");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba Identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        evaluarSiguientesTokens();
        
    }

    private void evaluarSiguientesTokens() throws ErrorSintacticoException {
        siguienteToken();
        switch (tokenActual().getLexema()) {
            case ";" -> {
                // no hace nada simplemente retorna
            }
            case "JOIN" -> {
                evaluarCaseJoin();
            }
            case "WHERE" -> {
                // no hace nada simplemente retorna
            }
            case "GROUP" -> {
                // no hace nada simplemente retorna
            }
            case "ORDER" -> {
                // no hace nada simplemente retorna
            }
            case "LIMIT" -> {
                // no hace nada simplemente retorna
            }
            default -> {
            }
        }

    }

    private void evaluarCaseJoin() throws ErrorSintacticoException {
        siguienteToken();
        
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba Identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba Identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        
        if (!tokenActual().getLexema().equals("ON")) {
            tokenActual().setMensajeError("Se esperaba ON");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba Identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        evaluarSiguienteCaracterJoin();

        if (!tokenActual().getLexema().equals("=")) {
                tokenActual().setMensajeError("Se esperaba =");
                throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba Identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        if (!tokenActual().getLexema().equals(".")) {
            tokenActual().setMensajeError("Se esperaba .");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba Identificador");
            throw new ErrorSintacticoException(tokenActual());
        }
        System.out.println("        JOIN EXISTOSO");

    }

    private boolean evaluarSiguienteCaracterJoin() throws ErrorSintacticoException {
        siguienteToken();
        if (tokenActual().getLexema().equals(".")) {
            siguienteToken();
            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                tokenActual().setMensajeError("Se esperaba Identificador");
                throw new ErrorSintacticoException(tokenActual());
            }
            siguienteToken();
            return true;
        }
        return false;
    }
}