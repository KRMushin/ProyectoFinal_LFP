/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.Frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import org.example.AnalizadorSintactico.SQLAnalizador;
import org.example.Entity.InsersionTabla;
import org.example.Entity.ModificacionTabla;
import org.example.Entity.Tabla;
import org.example.Excepciones.ErroresCodigoException;
import org.example.Frontend.Reportes.VistaReporteLexico;
import org.example.Frontend.ResultadoSintactico.ResultadoSintactico;
import org.example.Util.GraphVIzGenerator;
import org.example.backend.ManejadorCodigoFuente.EditorCodigo;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class VistaPrincipal extends javax.swing.JFrame {
     
    private final EditorCodigo editorCodigo;

    public VistaPrincipal() {
        initComponents();
        this.editorCodigo = new EditorCodigo();
        configuracionFrame();
        detectorDeCursor();
    }

    public void ObtnerReportesLexicos(){
        Optional<List<Token>> tokens = editorCodigo.obtenerReporteLexico();
        if (tokens.isEmpty()) {
            mostrarMensaje("Aun no hay tokens analizados :) ");
            return;
        }
        if (tokens.get().size() == 0) {
            mostrarMensaje("No hubieron errores en el analisis  :) ");
            return;
        }
        
        for (int i = 0; i < tokens.get().size(); i++) {
            Token token = tokens.get().get(i);
            if (token != null) {
                System.out.println(token.getLexema() + " Type " + token.getTipoToken());
            }
        }
        VistaReporteLexico vista = new VistaReporteLexico(tokens.get());
        vista.setVisible(true);
    }
    
    public void realizarAnalisisSintactico() throws IOException{
        try {
            Optional<List<Token>> tokensGenerados = editorCodigo.obtenerCodigosGenerados();
            if (tokensGenerados.isEmpty()) {
                mostrarMensaje(" Sin codigo por analizar");
                return;
            }
            
            
            SQLAnalizador sql = new SQLAnalizador(tokensGenerados.get());
            sql.analizarSintaxis();
            
            List<Token> erroresAnalisis = sql.getErroresGenerados();
            List<ModificacionTabla> modificaciones = sql.getModificaciones();
            List<InsersionTabla> insersiones = sql.getInsersionesHechas();
            
            Optional<List<Tabla>> tablasGeneradas = sql.obtnerTablasValidas();
            System.out.println(" ANALISIS COMPLETADO");
            ResultadoSintactico resultado = new ResultadoSintactico(erroresAnalisis, tablasGeneradas, modificaciones, insersiones);
            resultado.setVisible(true);
            
            
        } catch (ErroresCodigoException ex) {
                mostrarMensaje("El codigo contiene errores porfavor corrijalos para continuar");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        barraBotones = new javax.swing.JPanel();
        analizar = new javax.swing.JButton();
        cursorPos = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        listaNumeros = new javax.swing.JScrollPane();
        numerosFila = new javax.swing.JPanel();
        scrollContenedor = new javax.swing.JScrollPane();
        panelReportes = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        barraBotones.setBackground(new java.awt.Color(255, 255, 51));

        analizar.setText("ANALIZAR");
        analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarActionPerformed(evt);
            }
        });

        cursorPos.setForeground(new java.awt.Color(0, 0, 0));
        cursorPos.setText("Vacio");

        jButton4.setText("Cargar Archivo");

        javax.swing.GroupLayout barraBotonesLayout = new javax.swing.GroupLayout(barraBotones);
        barraBotones.setLayout(barraBotonesLayout);
        barraBotonesLayout.setHorizontalGroup(
            barraBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBotonesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(cursorPos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(51, 51, 51)
                .addComponent(analizar)
                .addGap(15, 15, 15))
        );
        barraBotonesLayout.setVerticalGroup(
            barraBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(barraBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cursorPos)
                    .addComponent(analizar)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listaNumeros.setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout numerosFilaLayout = new javax.swing.GroupLayout(numerosFila);
        numerosFila.setLayout(numerosFilaLayout);
        numerosFilaLayout.setHorizontalGroup(
            numerosFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        numerosFilaLayout.setVerticalGroup(
            numerosFilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        listaNumeros.setViewportView(numerosFila);

        panelReportes.setBackground(new java.awt.Color(255, 255, 51));

        jButton1.setText("Reportes Lexicos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reportes Sintactivos");

        jButton3.setText("Generar Grafico");

        javax.swing.GroupLayout panelReportesLayout = new javax.swing.GroupLayout(panelReportes);
        panelReportes.setLayout(panelReportesLayout);
        panelReportesLayout.setHorizontalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelReportesLayout.setVerticalGroup(
            panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addComponent(panelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addComponent(listaNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 392, Short.MAX_VALUE))))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addComponent(barraBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listaNumeros, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(scrollContenedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarActionPerformed
        try {
            realizarAnalisisSintactico();
        } catch (IOException ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_analizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                ObtnerReportesLexicos();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analizar;
    private javax.swing.JPanel barraBotones;
    private javax.swing.JLabel cursorPos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane listaNumeros;
    private javax.swing.JPanel numerosFila;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelReportes;
    private javax.swing.JScrollPane scrollContenedor;
    // End of variables declaration//GEN-END:variables
    /*              METODOS DE CONFIGURACION DEL PANEL */
    
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    private void configuracionFrame(){
        
           this.setExtendedState(JFrame.MAXIMIZED_BOTH);
           this.panelContenedor.setLayout(new BorderLayout());
           scrollContenedor.setPreferredSize(new Dimension(100, 0));
           scrollContenedor.setViewportView(editorCodigo);
           numerosFila.setLayout(new BoxLayout(numerosFila, BoxLayout.Y_AXIS));
           numerosFila.setAlignmentY(Component.TOP_ALIGNMENT);
           numerosFila.setBackground(Color.LIGHT_GRAY); 
           this.listaNumeros.setPreferredSize(new Dimension(40, 0));
           this.panelContenedor.add(barraBotones, BorderLayout.NORTH);
           this.panelContenedor.add(scrollContenedor, BorderLayout.CENTER);
           this.panelContenedor.add(listaNumeros,BorderLayout.WEST);
           this.panelContenedor.add(panelReportes,BorderLayout.SOUTH);
    }
   
    private void detectorDeCursor(){
        this.editorCodigo.addCaretListener(this::actualizarPosicionCursor);
        
    }
    
    private void actualizarPosicionCursor(CaretEvent e) {
            int posicion = e.getDot(); // Obtener la posición del cursor
            int linea = editorCodigo.getDocument().getDefaultRootElement().getElementIndex(posicion) + 1;
            int startOffset = editorCodigo.getDocument().getDefaultRootElement().getElement(linea - 1).getStartOffset();
            int columna = posicion - startOffset + 1;
            cursorPos.setText(" Línea: " + linea + " Columna: " + columna);
            actualizarNumerosDeLinea();
}
 
    private void actualizarNumerosDeLinea() {
            numerosFila.removeAll(); 
            int totalLineas = editorCodigo.getDocument().getDefaultRootElement().getElementCount();

            for (int i = 1; i <= totalLineas; i++) {
                JLabel labelNumero = new JLabel(String.valueOf(i));
                numerosFila.add(labelNumero);
            }
            numerosFila.revalidate();
            numerosFila.repaint();
    }

//    private void mostrarTablas(Optional<List<Tabla>> tables) {
//    tables.ifPresentOrElse(tab -> {
//        if (tab.isEmpty()) {
//            mostrarMensaje("No se encontraron tablas válidas");
//        } else {
//            for (Tabla table : tab) {
//                List<Token> tokens = table.getTokens();
//                for (Token token : tokens) {
//                    System.out.println(token.getLexema()); 
//                }
//            }
//        }
//    }, () -> mostrarMensaje("No se encontraron tablas válidas")); // Mensaje si Optional está vacío
//}
//
//    private void generarImagenTabla(List<Tabla> get) {
//    GraphVIzGenerator generator = new GraphVIzGenerator();
//    String rutaDot = "/home/kevin-mushin/SistemasCunoc/SS_2024/Lenguajes/ProyectoFinal/diagram.dot";
//    String rutaImagen = "/home/kevin-mushin/SistemasCunoc/SS_2024/Lenguajes/ProyectoFinal/diagram.png";
//    
//            try {
//                List<Token> tt =get.getFirst().getTokens();
//                generator.generarDot(tt, rutaDot);
//                generator.generarImagenDesdeDot(rutaDot, rutaImagen);
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//
//    }

}



















