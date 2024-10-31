/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.AnalizadorSintactico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.AnalizadorSintactico.AnalizadorUpdate.AnalizadorUpdate;
import org.example.AnalizadorSintactico.AnalizadoresDDL.AnalizadorModificadores;
import org.example.AnalizadorSintactico.AnalizadoresDDL.AnalizadorDatabase;
import org.example.AnalizadorSintactico.AnalizadoresDDL.AnalizadorIf;
import org.example.AnalizadorSintactico.AnalizadoresDDL.AnalizadorTable;
import org.example.AnalizadorSintactico.AnalizadorsDML.AnalizadorColumnas;
import org.example.AnalizadorSintactico.AnalizadorsDML.AnalizadorInsercion;
import org.example.AnalizadorSintactico.AnalizadorsDML.AnalizadorSelect;
import org.example.Entity.Database;
import org.example.Entity.InsersionTabla;
import org.example.Entity.ModificacionTabla;
import org.example.Entity.Tabla;
import org.example.Excepciones.ErrorSintacticoException;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class SQLAnalizador {
    
    private List<Token> erroresGenerados;
    private int indice;
    private List<Token> tokens;
    private List<Tabla> tablasCreadas;
    private List<Database> schemasCreados;
    private List<ModificacionTabla> modificaciones;
    private List<InsersionTabla> insersionesHechas;

    public SQLAnalizador(List<Token> tokens) {
        this.tokens = tokens;
        this.indice = 0;
        this.erroresGenerados = new ArrayList<>();
        this.tablasCreadas = new ArrayList<>();
        this.schemasCreados = new ArrayList<>();
        this.modificaciones = new ArrayList<>();
        this.insersionesHechas = new ArrayList<>();
    }
    /* cambiar token*/
    private void siguienteToken(){
        if (indice < tokens.size()) {
            indice++;
        }
    }
   /* token actual*/ 
   private Token tokenActual(){
       return tokens.get(indice);
   }
    
    public void analizarSintaxis(){
        
        while (indice < tokens.size()) {
            Token token = tokenActual();
            if (token.getTipoToken() == TipoToken.EOF) {
                System.out.println("Fin del archivo alcanzado.");
                break;
            }
            switch (token.getLexema().toUpperCase()) {
                case "CREATE" -> {
                  analizarDDLCreate();  
                }
                case "ALTER" , "DROP"->{
                      analizarDDLAlter();
                }
                case "INSERT", "SELECT" -> {
                      analizarDML();
                }
                case "UPDATE" ->{
                     analizarDCL();
                     System.out.println("ip +1 ");
                }
                default -> {
                    erroresGenerados.add(token);
                    buscarProximoPuntoYcoma();
                }
            }
        }
    }
    /*      ANALISIS  DE CREATE DATABASE */
    private void analizarDDLCreate() {
        siguienteToken();
        Token token = tokenActual();
        switch (token.getLexema().toUpperCase()) {
            case "DATABASE" ->{
                try {
                        AnalizadorDatabase analizadorDatabase = new AnalizadorDatabase(tokens, indice);
                        indice = analizadorDatabase.analizar();
                        schemasCreados.add(analizadorDatabase.getBaseCreada());
                } catch (ErrorSintacticoException ex) {
                     erroresGenerados.add(ex.getToken());
                     buscarProximoPuntoYcoma(); // En caso de error, buscar punto y coma

                }
            }
            case "TABLE" ->{
                  AnalizadorTable analizadorTabla = new AnalizadorTable(tokens, indice);
                try {
                    indice = analizadorTabla.analizar();
                    tablasCreadas.add(analizadorTabla.getTabla());
                } catch (ErrorSintacticoException ex) {
                         erroresGenerados.add(ex.getToken());
                          buscarProximoPuntoYcoma(); // En caso de error, buscar punto y coma

                }
            }
            default -> {
                buscarProximoPuntoYcoma();
                erroresGenerados.add(tokenActual());
            }
        }
    }
    
    private void analizarDDLAlter() {
        siguienteToken();
        Token token = tokenActual();
        if (!token.getLexema().equalsIgnoreCase("TABLE")) {
                erroresGenerados.add(tokenActual());
                buscarProximoPuntoYcoma();
                return;
        }
        siguienteToken();
        if (tokenActual().getTipoToken() == TipoToken.IDENTIFICADOR) {
            AnalizadorModificadores analizador = new AnalizadorModificadores(tokens, indice);
            try {
                indice = analizador.analizar();
                modificaciones.add(analizador.getModificacion());
            } catch (ErrorSintacticoException ex) {
                erroresGenerados.add(ex.getToken());
                buscarProximoPuntoYcoma();
            }
        }else if(tokenActual().getLexema().equals("IF")){
            AnalizadorIf analizadorIf = new AnalizadorIf(tokens, indice);
            try {
                indice = analizadorIf.analizar();
                modificaciones.add(analizadorIf.getModificacion());
            } catch (ErrorSintacticoException ex) {
                erroresGenerados.add(tokenActual());
                buscarProximoPuntoYcoma();
            }
        }else{
                erroresGenerados.add(tokenActual());
                buscarProximoPuntoYcoma();
        }
    }

    private void analizarDML() {
        siguienteToken();
        Token token = tokenActual();
        
        if (token.getLexema().equals("INTO")) {
                try {
                      AnalizadorInsercion analizador = new AnalizadorInsercion(tokens,indice);
                      indice = analizador.analizar();
                      System.out.println(analizador.getInsersion().getNombreTabla());
                      insersionesHechas.add(analizador.getInsersion());
                } catch (ErrorSintacticoException ex) {
                    erroresGenerados.add(ex.getToken());
                    buscarProximoPuntoYcoma();
                }
        }else if (token.getLexema().equals("*")) {
                AnalizadorSelect analizador = new AnalizadorSelect(tokens , indice);
                indice = analizador.analizar();
            
        }else if (esTokenValidoColumna(token)) {
                AnalizadorColumnas analizador = new AnalizadorColumnas(tokens,indice);
            try {
                indice = analizador.analizar();
            } catch (ErrorSintacticoException ex) {
                erroresGenerados.add(ex.getToken());
            }
            
        }else{
                    erroresGenerados.add(tokenActual());
                    buscarProximoPuntoYcoma();
        }
    }
    
    private boolean esTokenValidoColumna(Token token) {
        TipoToken t = token.getTipoToken();
        return t == TipoToken.IDENTIFICADOR ||t == TipoToken.FUNCION_AGREGACION;
    }

    private void analizarDCL() {
        try {
            AnalizadorUpdate analizador = new AnalizadorUpdate(tokens, indice);
            indice = analizador.analizar();
        } catch (ErrorSintacticoException ex) {
            erroresGenerados.add(ex.getToken());
        }
    }

    private void buscarProximoPuntoYcoma() {
        while (indice < tokens.size() && !tokenActual().getLexema().equals(";")) {
            siguienteToken();
        }
        if (indice < tokens.size() && tokenActual().getLexema().equals(";")) {
            siguienteToken();
        }
    }

    /* GETTERS Y SETTERS DE TOKENS */

    public List<ModificacionTabla> getModificaciones() {
        return modificaciones;
    }
    
    public List<Token> getErroresGenerados() {
        return erroresGenerados;
    }

    public void setErroresGenerados(List<Token> erroresGenerados) {
        this.erroresGenerados = erroresGenerados;
    }
    
    public Optional<List<Tabla>> obtnerTablasValidas(){
         if (tablasCreadas.isEmpty()) {
            return Optional.empty();
        }
         return Optional.of(tablasCreadas);
    }

    public List<InsersionTabla> getInsersionesHechas() {
        return insersionesHechas;
    }
    
    
    

    
    
 }
