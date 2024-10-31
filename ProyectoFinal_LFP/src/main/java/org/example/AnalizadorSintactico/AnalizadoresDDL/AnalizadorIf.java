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
public class AnalizadorIf {
    private List<Token> tokens;
    private int indice;
    private ModificacionTabla modificacion;
    private StringBuilder stringBuilder;

    public AnalizadorIf(List<Token> tokens, int indice) {
        this.tokens = tokens;
        this.indice = indice;
        this.modificacion = new ModificacionTabla();
        this.stringBuilder = new StringBuilder();
    }

    private Token tokenActual() {
        return tokens.get(indice);
    }

    private void siguienteToken() {
        if (indice < tokens.size() - 1) {
            indice++;
            stringBuilder.append(tokenActual().getLexema()).append(" ");
        }
    }
//    public AnalizadorIf(List<Token> tokens, int indice) {
//    }

    public int analizar() throws ErrorSintacticoException {
            siguienteToken();
            if (!tokenActual().getLexema().equals("EXISTS")) {
                 tokenActual().setMensajeError("Se esperaba EXISTS");
                  throw new ErrorSintacticoException(tokenActual());
            }
            siguienteToken();
            if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                 tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR");
                  throw new ErrorSintacticoException(tokenActual());
            }
            modificacion.setTablaModificada(tokenActual().getLexema());
            siguienteToken();
            if (!tokenActual().getLexema().equals("CASCADE")) {
                 tokenActual().setMensajeError("Se esperaba CASCADE");
                  throw new ErrorSintacticoException(tokenActual());
            }
            siguienteToken();
            if (!tokenActual().getLexema().equals(";")) {
                 tokenActual().setMensajeError("Se esperaba ;");
                  throw new ErrorSintacticoException(tokenActual());
            }
            modificacion.setConsulta(stringBuilder.toString());
           return indice;
    }

    public ModificacionTabla getModificacion() {
        return modificacion;
    }
    
}
