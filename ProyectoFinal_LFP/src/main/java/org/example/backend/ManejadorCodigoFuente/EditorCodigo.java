package org.example.backend.ManejadorCodigoFuente;

import org.example.Flex.AnalizadorLexico;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.List;
import org.example.Excepciones.ErroresCodigoException;
import org.example.Excepciones.FalloException;


public class EditorCodigo extends JTextPane {

    private StyledDocument doc;
    private Timer timer;
    private List<Token> tokens;

    public EditorCodigo() {
        this.tokens = new ArrayList<>();
        doc = this.getStyledDocument();

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                analizarEntrada(documentEvent);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                analizarEntrada(documentEvent);
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                // No es necesario para cambios en atributos de estilo.
            }
        });
    }

    private void analizarEntrada(DocumentEvent e) {
        this.tokens.clear();
        StyledDocument doc = (StyledDocument) e.getDocument();
        try {
            String text = doc.getText(0, doc.getLength());
            analizarYColorear(text, doc);
        } catch (FalloException | BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void analizarYColorear(String text, StyledDocument doc) throws FalloException {
            AnalizadorLexico lexer = new AnalizadorLexico(new StringReader(text));
                Token token;

        try {
            while ((token = lexer.yylex()) != null) {
                if (token.getTipoToken() == TipoToken.EOF) {
                    System.out.println("Fin del archivo alcanzado.");
                    break;
                 }
                int inicioAbsoluto = calcularPosicionAbsoluta(text, token.getInicio(), token.getLongitud());
                colorearToken(doc, inicioAbsoluto, token.getLexema(), token.getTipoToken().obtenerColor());
                tokens.add(token);
                System.out.println("Token añadido: " + token.getLexema() + " TIpo " + token.getTipoToken());

            }
        } catch (IOException e) {
             throw new FalloException(e);
        }
    }

    private int calcularPosicionAbsoluta(String text, int linea, int columna) {
        int posicionAbsoluta = 0;
        int contadorLineas = 0;

        for (int i = 0; i < text.length(); i++) {
            if (contadorLineas == linea) {
                posicionAbsoluta += columna;
                break;
            }
            if (text.charAt(i) == '\n') {
                contadorLineas++;
            }
            posicionAbsoluta++;
        }
        return posicionAbsoluta;
    }

    private void colorearToken(StyledDocument doc, int inicio, String lexema, Color color) {
        String[] lineas = lexema.split("\n");
        int posicionActual = inicio;

        for (String linea : lineas) {
            int longitudLinea = linea.length();
            aplicarColor(doc, posicionActual, longitudLinea, color);
            posicionActual += longitudLinea + 1;
        }
    }

    private void aplicarColor(StyledDocument doc, int inicio, int longitud, Color color) {
        String nombreEstilo = "miEstilo_" + color.getRGB();

        Style estilo = doc.getStyle(nombreEstilo);
        if (estilo == null) {
            estilo = doc.addStyle(nombreEstilo, null);
            StyleConstants.setForeground(estilo, color);
        }
        final Style estiloFinal = estilo;
        final int inicioFinal = inicio;
        final int longitudFinal = longitud;

        SwingUtilities.invokeLater(() -> {
            doc.setCharacterAttributes(inicioFinal, longitudFinal, estiloFinal, true);
        });
    }

    public Optional<List<Token>> obtenerReporteLexico() {
        System.out.println(tokens.size());
        if (tokens.isEmpty()) {
            
            return Optional.empty();
        }
        List<Token> tokensError = new ArrayList<>();
        
        for (int i = 0; i < this.tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token != null && (token.getTipoToken() == TipoToken.NO_RECONOCIDO) ) {
                    tokensError.add(token);
            }
        }
        return Optional.of(tokensError);
    }

    public Optional<List<Token>> obtenerCodigosGenerados() throws ErroresCodigoException {
        if (tokens.isEmpty()) {
            return Optional.empty();
        }
        List<Token> tokensLimpios = new ArrayList<>();
        
         for(Token token: this.tokens){
             if (token.getTipoToken() == TipoToken.NO_RECONOCIDO) {
                 throw new ErroresCodigoException();
             }
             tokensLimpios.add(token);
         }
        return Optional.of(tokensLimpios);
    }
}