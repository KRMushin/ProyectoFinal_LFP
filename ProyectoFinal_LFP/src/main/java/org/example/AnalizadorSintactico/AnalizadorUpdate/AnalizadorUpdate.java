/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadorUpdate;

import java.util.List;
import org.example.Entity.InsersionTabla;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorUpdate {
    
    private List<Token> tokens;
    private InsersionTabla insersion;
    private StringBuilder stringBuilder;
    private int indice;

    public AnalizadorUpdate(List<Token> tokens, int indice) {
        this.tokens = tokens;
        this.indice = indice;
        this.insersion = new InsersionTabla();
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

    public int analizar() throws ErrorSintacticoException {
     siguienteToken(); 

        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                 tokenActual().setMensajeError("Se esperaba IDENTIFICADOR");
                 throw new ErrorSintacticoException(tokenActual());
        }
        
    siguienteToken(); 
        if (!tokenActual().getLexema().equalsIgnoreCase("SET")) {
                 tokenActual().setMensajeError("Se esperaba SET");
                 throw new ErrorSintacticoException(tokenActual());
        }
        System.out.println("llega");
         siguienteToken();
//        analizarAsignacion();

        // Procesar WHERE si está presente
        if (indice < tokens.size() && tokenActual().getLexema().equalsIgnoreCase("WHERE")) {
            siguienteToken();
            analizarCondicion();
        }

        return indice;
    }
    
    private void analizarAsignacion() throws ErrorSintacticoException {
        // Verificar columna
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba IDENTIFICADOR de columna en SET");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        if (!tokenActual().getLexema().equals("=")) {
            tokenActual().setMensajeError("Se esperaba '=' para asignar valor a la columna");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        if (tokenActual().getTipoToken() != TipoToken.TIPO_DE_DATO) {
            tokenActual().setMensajeError("Se esperaba un valor después de '='");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        // Procesar posibles asignaciones adicionales
        if (indice < tokens.size() && tokenActual().getLexema().equals(",")) {
            siguienteToken();
            analizarAsignacion(); // Llamada recursiva para la siguiente asignación
        }
    }

    private void analizarCondicion() throws ErrorSintacticoException {
        // Verificar columna en la condición
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba IDENTIFICADOR de columna en WHERE");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        if (!tokenActual().getLexema().matches("=|<|>|<=|>=|<>")) {
            tokenActual().setMensajeError("Se esperaba un operador de comparación en WHERE");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        if (tokenActual().getTipoToken() != TipoToken.TIPO_DE_DATO) {
            tokenActual().setMensajeError("Se esperaba un valor de dato en WHERE");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();

        // Procesar condiciones adicionales
        if (indice < tokens.size() && 
            (tokenActual().getLexema().equalsIgnoreCase("AND") || tokenActual().getLexema().equalsIgnoreCase("OR"))) {
            siguienteToken();
            analizarCondicion(); // Llamada recursiva para la siguiente condición
        }
    }
}