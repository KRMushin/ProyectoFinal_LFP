/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadoresDDL;

import java.util.ArrayList;
import java.util.List;
import org.example.Entity.Tabla;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorTable {

    private final List<Token> tokens;
    private int indice;
    private Tabla tabla;
    private List<Token> tokensTable;
    
    public AnalizadorTable(List<Token> tokens, int indice) {
        this.tokens = tokens;
        this.indice = indice;
        this.tabla = new Tabla();
        this.tokensTable = new ArrayList<>();
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
        if (TipoToken.IDENTIFICADOR != token.getTipoToken()) {
            throw new ErrorSintacticoException(tokenActual());
        }
                    tokensTable.add(tokenActual());
                    tabla.setNombreTabla(tokenActual().getLexema());
        siguienteToken();
        evaluarSiguiente("(");
        analizarDefinicionDeTabla();
        evaluarSiguiente(")");
        evaluarSiguiente(";");
                        tabla.setTokens(tokensTable);
        return indice;
        
    }

    private void evaluarSiguiente(String tokenEsperado) throws ErrorSintacticoException {
        if (!tokenActual().getLexema().equals(tokenEsperado)) {
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();
    }

    private void analizarDefinicionDeTabla() throws ErrorSintacticoException {
        while (!")".equals(tokenActual().getLexema())) {
            if ("CONSTRAINT".equals(tokenActual().getLexema())) {
                analizarRestriccion();
            } else {
                analizarColumna();
            }

            // Avanza si hay una coma, indicando más definiciones; de lo contrario, sale del bucle
            if (tokenActual().getLexema().equals(",")) {
                siguienteToken();
            } else {
                break;
            }
        }
    }

    private void analizarTipoDato() throws ErrorSintacticoException {
        switch (tokenActual().getLexema()) {
            case "SERIAL", "INTEGER", "BIGINT", "DATE", "TEXT", "BOOLEAN" -> {
                           tokensTable.add(tokenActual());
                System.out.println("Tipo de dato: " + tokenActual().getLexema());
                siguienteToken();
                System.out.println(tokenActual().getTipoToken());
            }
            case "VARCHAR", "DECIMAL", "NUMERIC" -> {
                System.out.println("Tipo de dato: " + tokenActual().getLexema());
                                                       tokensTable.add(tokenActual());
                siguienteToken();
                evaluarSiguiente("(");
                if (tokenActual().getTipoToken() == TipoToken.ENTERO) {
                                                       tokensTable.add(tokenActual());
                    System.out.println("Longitud o Precisión: " + tokenActual().getLexema());
                    siguienteToken();
                    if (tokenActual().getLexema().equals(",")) { // Para DECIMAL y NUMERIC con dos valores
                                                       tokensTable.add(tokenActual());
                        siguienteToken();
                        if (tokenActual().getTipoToken() == TipoToken.ENTERO) {
                            System.out.println("Escala: " + tokenActual().getLexema());
                                                       tokensTable.add(tokenActual());
                                siguienteToken();
                        } else {
                            throw new ErrorSintacticoException(tokenActual());
                        }
                    }
                } else {
                    throw new ErrorSintacticoException(tokenActual());
                }
                evaluarSiguiente(")");
            }
            default -> throw new ErrorSintacticoException(tokenActual());
        }
    }
    
    private void analizarColumna() throws ErrorSintacticoException {
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            throw new ErrorSintacticoException(tokenActual());
        }
                tokensTable.add(tokenActual());
        String nombreColumna = tokenActual().getLexema();
        System.out.println("Columna: " + nombreColumna);
        
        siguienteToken();
        analizarTipoDato();

        // Verificar si la columna tiene una restricción como PRIMARY KEY, NOT NULL o UNIQUE
        if (tokenActual().getTipoToken() == TipoToken.LlaveEspecial) {
            String restriccion = tokenActual().getLexema();
            System.out.println("Restricción: " + restriccion);
            siguienteToken();
            if (tokenActual().getTipoToken() == TipoToken.LlaveEspecial) {
                siguienteToken();
            }
        }
    }
    
    private void analizarRestriccion() throws ErrorSintacticoException {
        evaluarSiguiente("CONSTRAINT");
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            throw new ErrorSintacticoException(tokenActual());
        }
        String nombreRestriccion = tokenActual().getLexema();
        System.out.println("Restricción: " + nombreRestriccion);
        
        siguienteToken();
        evaluarSiguiente("FOREIGN");
        evaluarSiguiente("KEY");
        evaluarSiguiente("(");
        if (tokenActual().getTipoToken() == TipoToken.IDENTIFICADOR) {
            System.out.println("Columna clave externa: " + tokenActual().getLexema());
            siguienteToken();
        } else {
            throw new ErrorSintacticoException(tokenActual());
        }
        evaluarSiguiente(")");
        evaluarSiguiente("REFERENCES");
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            throw new ErrorSintacticoException(tokenActual());
        }
        String tablaReferencia = tokenActual().getLexema();
        System.out.println("Tabla de referencia: " + tablaReferencia);

        siguienteToken();
        evaluarSiguiente("(");
        if (tokenActual().getTipoToken() == TipoToken.IDENTIFICADOR) {
            System.out.println("Columna de referencia: " + tokenActual().getLexema());
            siguienteToken();
        } else {
            throw new ErrorSintacticoException(tokenActual());
        }
        evaluarSiguiente(")");
    }

    public Tabla getTabla() {
        return tabla;
    }
    
    
    
}