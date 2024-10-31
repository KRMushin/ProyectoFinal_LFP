/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadoresDDL;

import java.util.List;
import org.example.Entity.ModificacionTabla;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorModificadores {
    private List<Token> tokens;
    private int indice;
    private ModificacionTabla modificacion;
    private StringBuilder stringBuilder;

    private Token tokenActual() {
        return tokens.get(indice);
    }

    private void siguienteToken() {
        if (indice < tokens.size() - 1) {
            indice++;
            stringBuilder.append(tokenActual().getLexema()).append(" ");
        }
    }
    
    public AnalizadorModificadores(List<Token> tokens, int indice) {
        this.tokens = tokens;
        this.indice = indice;
        this.modificacion = new ModificacionTabla();
        this.stringBuilder = new StringBuilder();
    }

    public int analizar() throws ErrorSintacticoException {
        modificacion.setTablaModificada(tokenActual().getLexema());
        siguienteToken();
        Token token = tokenActual();
        
        switch (token.getLexema()) {
            case "ADD" -> {
                analizarADD();
            }
            case "DROP" -> {
                analizarDROP();
            }
            case "ALTER" ->{
                analizarAlter();
            }
            default ->  throw new ErrorSintacticoException(token);
        }
    modificacion.setConsulta(stringBuilder.toString());
    modificacion.setTipoModificacion(token.getLexema());
    return indice;
    }
    
    private void analizarADD() throws ErrorSintacticoException {
        siguienteToken();
        Token token = tokenActual();
        
        switch (token.getLexema().toUpperCase()) {
            case "COLUMN" -> {
                analizarAddColumn();
            }
            case "CONSTRAINT" -> {
                analizarAddConstraint();
            }
            default -> {
                token.setMensajeError("Se esperaba 'COLUMN' o 'CONSTRAINT' después de 'ADD'");
                throw new ErrorSintacticoException(token);
            }
        }
    }

    private void analizarAddColumn() throws ErrorSintacticoException {
        siguienteToken();

        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();
        if (tokenActual().getTipoToken() != TipoToken.TIPO_DE_DATO) {
            tokenActual().setMensajeError("Se esperaba un TIPO DE DATO");
            throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();
        if (!tokenActual().getLexema().equals(";")) {
            tokenActual().setMensajeError("Se esperaba un ;");
            throw new ErrorSintacticoException(tokenActual());
        }
    }

    private void analizarDROP() throws ErrorSintacticoException {
    siguienteToken();
    Token token = tokenActual();

    if (!token.getLexema().equalsIgnoreCase("COLUMN")) {
        token.setMensajeError("Se esperaba 'COLUMN' después de 'DROP'");
        throw new ErrorSintacticoException(token);
    }

    siguienteToken();
    if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
        tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR para el nombre de la columna a eliminar");
        throw new ErrorSintacticoException(tokenActual());
    }

    String columnaAEliminar = tokenActual().getLexema();
    System.out.println("Eliminando columna: " + columnaAEliminar);

    siguienteToken();
    if (!tokenActual().getLexema().equals(";")) {
        tokenActual().setMensajeError("Se esperaba ';' al final de la declaración de DROP COLUMN");
        throw new ErrorSintacticoException(tokenActual());
    }
        System.out.println("    ELIMINACION DE COLUMNA EXITOSA");
}

    private void analizarAlter() throws ErrorSintacticoException {
    siguienteToken();
    Token token = tokenActual();

    if (!token.getLexema().equalsIgnoreCase("COLUMN")) {
        token.setMensajeError("Se esperaba 'COLUMN' después de 'ALTER'");
        throw new ErrorSintacticoException(token);
    }

    siguienteToken();
    if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
        tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR para el nombre de la columna a modificar");
        throw new ErrorSintacticoException(tokenActual());
    }

    String columnaAModificar = tokenActual().getLexema();
    System.out.println("Modificando columna: " + columnaAModificar);

    siguienteToken();
    if (!tokenActual().getLexema().equalsIgnoreCase("TYPE")) {
        tokenActual().setMensajeError("Se esperaba 'TYPE' después del nombre de la columna en ALTER COLUMN");
        throw new ErrorSintacticoException(tokenActual());
    }
    siguienteToken();
    String tipoToken = tokenActual().getLexema();
    if (tokenActual().getTipoToken() != TipoToken.TIPO_DE_DATO){
        tokenActual().setMensajeError("Se esperaba un TIPO DE DATO para la columna modificada");
        throw new ErrorSintacticoException(tokenActual());
    }
    
    siguienteToken();
    if (!tokenActual().getLexema().equals("(")) {
        tokenActual().setMensajeError("Se esperaba ( ");
        throw new ErrorSintacticoException(tokenActual());
    }
   siguienteToken();
        System.out.println("    tipo de dato " + tipoToken);
        if (tipoTokenEsDatoBooleano(tipoToken)) {
            if (tokenActual().getTipoToken() != TipoToken.BOOLEANO) {
                tokenActual().setMensajeError("Se esperaba un dato boleano");
                throw new ErrorSintacticoException(tokenActual());
            }
        }else if (tipoTokenEsDatoNumerico(tipoToken)) {
                evaluarNumero(); 
        }else if (tipoTokenEsDatoTexto(tipoToken)) {
              if (tokenActual().getTipoToken() != TipoToken.CADENA) {
                tokenActual().setMensajeError("Se esperaba una CADENA");
                throw new ErrorSintacticoException(tokenActual());
            }
        }else if (tipoTokenEsDatoFecha(tipoToken)) {
            if (tokenActual().getTipoToken() != TipoToken.FECHA) {
                tokenActual().setMensajeError("Se esperaba una FECHA");
                throw new ErrorSintacticoException(tokenActual());
            }
        }else{
                throw new ErrorSintacticoException(tokenActual());
        }
        
     siguienteToken();
    if (!tokenActual().getLexema().equals(")")) {
        tokenActual().setMensajeError("Se esperaba ) ");
        throw new ErrorSintacticoException(tokenActual());
    }
            
    String nuevoTipoDato = tokenActual().getLexema();
    System.out.println("Nuevo tipo de dato para " + columnaAModificar + ": " + nuevoTipoDato);

    siguienteToken();
    if (!tokenActual().getLexema().equals(";")) {
        tokenActual().setMensajeError("Se esperaba ';' al final de la declaración de ALTER COLUMN");
        throw new ErrorSintacticoException(tokenActual());
    }
        System.out.println("    EXITO EN LA MODIFICACION DE TABLAS");
}

    private void analizarAddConstraint() throws ErrorSintacticoException {
        siguienteToken();
        if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
            tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR para el nombre de la restricción");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        Token token = tokenActual();
        
        if (token.getTipoToken() == TipoToken.TIPO_DE_DATO) {
            evaluarConstraintDato();

        }else if (token.getLexema().equals("UNIQUE")) {
            evaluarConstraintUnique();

        }else if (token.getLexema().equals("FOREIGN")) {
            evaluarConstraintKey();

        }else {
            tokenActual().setMensajeError("Token invalido");
            throw new ErrorSintacticoException(tokenActual());
        }
    }

    private void evaluarConstraintUnique() throws ErrorSintacticoException {
            siguienteToken();
        if (!tokenActual().getLexema().equals("(")) {
            tokenActual().setMensajeError("Se esperaba ( ");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR");
                throw new ErrorSintacticoException(tokenActual());
            }
            siguienteToken();
    if (!tokenActual().getLexema().equals(")")) {
        tokenActual().setMensajeError("Se esperaba ) ");
        throw new ErrorSintacticoException(tokenActual());
    }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals(";")) {
                tokenActual().setMensajeError("Se esperaba ';' al final de la declaración de CONSTRAINT");
                throw new ErrorSintacticoException(tokenActual());
            }
         System.out.println("   CONSTRAINT UNIQUE VALIDO");   
    }

    private void evaluarConstraintDato() throws ErrorSintacticoException {
        siguienteToken();
        if (!tokenActual().getLexema().equals(";")) {
            tokenActual().setMensajeError("Tipo de restricción no reconocido después de 'CONSTRAINT'");
            throw new ErrorSintacticoException(tokenActual());
        }
        System.out.println("CONSTRAINT TIPO_DATO EXITOSO");
    }

    private void evaluarConstraintKey() throws ErrorSintacticoException {
            siguienteToken();
            if ( !tokenActual().getLexema().equalsIgnoreCase("KEY")) {
                tokenActual().setMensajeError("Se esperaba 'KEY' después de 'FOREIGN'");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals("(")) {
                tokenActual().setMensajeError("Se esperaba ( ");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                tokenActual().setMensajeError("Se esperaba el nombre de la columna en la restricción");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals(")")) {
                tokenActual().setMensajeError("Se esperaba ( ");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals("REFERENCES")) {
                tokenActual().setMensajeError("Se esperaba la referncia REFERENCES");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals("(")) {
                tokenActual().setMensajeError("Se esperaba ( ");
                throw new ErrorSintacticoException(tokenActual());
            }
            siguienteToken();
            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals(")")) {
                tokenActual().setMensajeError("Se esperaba ) ");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            if (!tokenActual().getLexema().equals(";")) {
                tokenActual().setMensajeError("Se esperaba ';' al final de la declaración de CONSTRAINT");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            System.out.println("ADD CONSTRAINT ejecutado correctamente.");
    }
    /*setters y gettes*/
    public ModificacionTabla getModificacion() {
        return modificacion;
    }
    
    private void evaluarNumero() throws ErrorSintacticoException {
    if (tokenActual().getTipoToken() != TipoToken.ENTERO) {
        tokenActual().setMensajeError("Se esperaba un número");
        throw new ErrorSintacticoException(tokenActual());
    }

    siguienteToken();

    if (tokenActual().getLexema().equals(",")) {
        siguienteToken();
        if (tokenActual().getTipoToken() != TipoToken.ENTERO) {
            tokenActual().setMensajeError("Se esperaba un segundo número después de ','");
            throw new ErrorSintacticoException(tokenActual());
        }
        System.out.println("Número con precisión y escala decimal: ");
    } else {
        System.out.println("Número entero: ");
    }
}

    private boolean tipoTokenEsDatoBooleano(String t) {
        return t.equalsIgnoreCase("BOOLEAN");
    }
    private boolean tipoTokenEsDatoNumerico(String t) {
        return t.equalsIgnoreCase("INTEGER") || t.equalsIgnoreCase("BIGINT") || t.equalsIgnoreCase("VARCHAR") || t.equalsIgnoreCase("DECIMAL") ||
                t.equalsIgnoreCase("NUMERIC") || t.equalsIgnoreCase("INT") || t.equalsIgnoreCase("SERIAL");
    }
    private boolean tipoTokenEsDatoTexto(String t) {
        return t.equalsIgnoreCase("TEXT");
    }
    private boolean tipoTokenEsDatoFecha(String t) {
        return t.equalsIgnoreCase("DATE");
    }
}
