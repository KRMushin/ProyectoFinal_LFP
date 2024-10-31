/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico.AnalizadorsDML;

import java.util.List;
import org.example.Entity.InsersionTabla;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorInsercion {
    
    private List<Token> tokens;
    private InsersionTabla insersion;
    private StringBuilder stringBuilder;
    private int indice;

    public AnalizadorInsercion(List<Token> tokens, int indice) {
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
             tokenActual().setMensajeError("Se esperaba un IDENTIFICADOR'");
             throw new ErrorSintacticoException(tokenActual());
        }
        
        insersion.setNombreTabla(tokenActual().getLexema());
        
        siguienteToken();
        if (!tokenActual().getLexema().equals("(")) {
             tokenActual().setMensajeError("Se esperaba un ('");
             throw new ErrorSintacticoException(tokenActual());
        }
        
         do {
                siguienteToken();
         
                if (tokenActual().getTipoToken() != TipoToken.IDENTIFICADOR) {
                        tokenActual().setMensajeError("Se esperaba el nombre de una columna");
                        throw new ErrorSintacticoException(tokenActual());
                }
                siguienteToken();
                
        } while (tokenActual().getLexema().equals(","));

        if (!tokenActual().getLexema().equals(")")) {
                tokenActual().setMensajeError("Se esperaba un cierre");
                throw new ErrorSintacticoException(tokenActual());
        }
        siguienteToken();
        
        if (!tokenActual().getLexema().equals("VALUES")) {
                tokenActual().setMensajeError("Se esperaba un identificador de Valores");
                throw new ErrorSintacticoException(tokenActual());
        }
        
        analizarValoresValues();
       
        if (!tokenActual().getLexema().equals(";")) {
                tokenActual().setMensajeError("Se esperaba un ';' al final de los valores");
                throw new ErrorSintacticoException(tokenActual());
         }
        siguienteToken();
        insersion.setConsulta(stringBuilder.toString());
        return indice;
    }
    
    private boolean esExpresionValida() throws ErrorSintacticoException {
            Token tok = tokenActual();

            if (esTipoDatoValido(tok)) {
                return true;
            }

            if (tok.getLexema().equals("(")) {
                siguienteToken();
                if (!esExpresionValida()) {
                    tok.setMensajeError("Expresión inválida dentro de paréntesis");
                    throw new ErrorSintacticoException(tok);
                }
                if (!tokenActual().getLexema().equals(")")) {
                    tok.setMensajeError("Se esperaba un cierre de paréntesis");
                    throw new ErrorSintacticoException(tok);
                }
                return true;
            }

            if (esOperador(tok)) {
                siguienteToken();
                return esExpresionValida();
            }

            return false;
      }

    private boolean esTipoDatoValido(Token token) {
        TipoToken tipo = token.getTipoToken();
        return tipo == TipoToken.ENTERO || tipo == TipoToken.DECIMAL ||
               tipo == TipoToken.FECHA || tipo == TipoToken.CADENA ||
               tipo == TipoToken.BOOLEANO;
    }

    private boolean esOperador(Token token) {
        String lexema = token.getLexema();
        return lexema.equals("+") || lexema.equals("-") || lexema.equals("*") ||
               lexema.equals("/") || lexema.equals("<") || lexema.equals(">") ||
               lexema.equals("=") || lexema.equals("AND") || lexema.equals("OR");
    }

    private void analizarValoresValues() throws ErrorSintacticoException {
    do {
        siguienteToken();

        if (!tokenActual().getLexema().equals("(")) {
            tokenActual().setMensajeError("Se esperaba una apertura '(' para la tupla de valores");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        do {
            siguienteToken();
            
            if (!esExpresionValida()) {
                tokenActual().setMensajeError("Se esperaba un dato válido (entero, decimal, cadena, fecha, etc.)");
                throw new ErrorSintacticoException(tokenActual());
            }
            
            siguienteToken();
            
        } while (tokenActual().getLexema().equals(",") || 
                 (tokenActual().getTipoToken() == TipoToken.LOGICO) || 
                 (tokenActual().getTipoToken() == TipoToken.RELACIONAL) || 
                 (tokenActual().getTipoToken() == TipoToken.ARITMETICO));
        
        if (!tokenActual().getLexema().equals(")")) {
            tokenActual().setMensajeError("Se esperaba un cierre ')' al final de la tupla de valores");
            throw new ErrorSintacticoException(tokenActual());
        }
        
        siguienteToken();
        
    } while (tokenActual().getLexema().equals(",")); 
}

    public InsersionTabla getInsersion() {
        return insersion;
    }

    
}
