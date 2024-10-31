package org.example.backend.Tokens;

import org.example.backend.Enums.TipoToken;

public class Token {
    private TipoToken tipoToken;
    private int inicio;
    private int longitud;
    private String lexema;
    
    private String parToken;

    public Token(TipoToken tipoToken, int inicio, int longitud, String lexema) {
        this.tipoToken = tipoToken;
        this.inicio = inicio;
        this.longitud = longitud;
        this.lexema = lexema;
    }


    public int getInicio() {
        return inicio;
    }

    public int getLongitud() {
        return longitud;
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(TipoToken tipoToken) {
        this.tipoToken = tipoToken;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getParToken() {
        return parToken;
    }

    public void setParToken(String parToken) {
        this.parToken = parToken;
    }
    
    private String mensajeError;

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    
    
}
