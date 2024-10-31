/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.Frontend.ResultadoSintactico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.example.Entity.InsersionTabla;
import org.example.Entity.ModificacionTabla;
import org.example.Entity.Tabla;
import org.example.Util.GraphVIzGenerator;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class ResultadoSintactico extends javax.swing.JFrame {
    
    private JTable tablaReporte;
    private DefaultTableModel tableModel;
    private List<Token> erroresAnalisis;
    private Optional<List<Tabla>> tablasGeneradas;
    private List<ModificacionTabla> insersiones;
    private List<InsersionTabla> insersionesTablas;
    /**
     * Creates new form ResultadoSintactico
     * @param erroresAnalisis
     * @param tablasGeneradas
     * @param modificaciones
     * @param insersiones
     */
    public ResultadoSintactico(List<Token> erroresAnalisis, Optional<List<Tabla>> tablasGeneradas, List<ModificacionTabla> modificaciones, List<InsersionTabla> insersiones) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        this.erroresAnalisis = erroresAnalisis;
        this.tablasGeneradas = tablasGeneradas;
        this.insersiones = modificaciones;
        this.insersionesTablas = insersiones;
        configuracionPanel();
//                    mostrarTablas(tablasGeneradas);
//            generarImagenTabla(tablasGeneradas.get());
    }
    
    private void configuracionPanel(){
        panelContenedor.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        JButton btnReporte1 = new JButton("Reporte Errores Sintacticos");
        JButton btnReporte2 = new JButton("Generar Graficos ");
        JButton btnReporte3 = new JButton("Reporte Modificaciones ");
        JButton btnReporte4 = new JButton("Reporte Insersiones");

        panelBotones.add(btnReporte1);
        panelBotones.add(btnReporte2);
        panelBotones.add(btnReporte3);
        panelBotones.add(btnReporte4);


        tableModel = new DefaultTableModel();
        tablaReporte = new JTable(tableModel);
        tablaReporte.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Ajusta automáticamente el tamaño de columnas

        JScrollPane scrollPane = new JScrollPane(tablaReporte);
         scrollPane.setPreferredSize(new Dimension(panelContenedor.getWidth(), panelContenedor.getHeight()));
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelContenedor.add(panelBotones, BorderLayout.NORTH); 
        panelContenedor.add(scrollPane, BorderLayout.CENTER);   
        
        btnReporte1.addActionListener((ActionEvent e) -> {
            mostrarReporteErrores();
        });

        btnReporte2.addActionListener((ActionEvent e) -> {
            generarTablas();
        });

    btnReporte3.addActionListener((ActionEvent e) -> {
        mostrarReporteModificaciones();  
        });
    btnReporte4.addActionListener((ActionEvent e) -> {
        mostrarReporteInsersiones();  
        });
}

// Método para mostrar el reporte de Tokens
private void mostrarReporteErrores() {
    String[] columnas = {"Token", "Valor", "Linea", "Columna", "Descripcion"};
    if (erroresAnalisis != null && !erroresAnalisis.isEmpty()) {
        Object[][] datos = new Object[erroresAnalisis.size()][6];  // Creamos una matriz para almacenar los datos

        for (int i = 0; i < erroresAnalisis.size(); i++) {
            Token token = erroresAnalisis.get(i);
            datos[i][0] = token.getTipoToken();
            datos[i][1] = token.getLexema(); // Asumiendo que hay un método getExpresionRegular()
            datos[i][2] = token.getInicio();        // Asumiendo que hay un método getLenguaje()
            datos[i][3] = token.getLongitud() + 1;            // Asumiendo que hay un método getTipo()
            datos[i][4] = token.getMensajeError();         // Asumiendo que hay un método getColumna()
        }
    actualizarTabla(columnas, datos);
}
}

private void mostrarReporteModificaciones() {
    String[] columnas = {"Nombre Tabla", "Consulta ", "Tipo Modificacion"};
    if (insersiones != null && !insersiones.isEmpty()) {
        Object[][] datos = new Object[insersiones.size()][4];  // Creamos una matriz para almacenar los datos

        for (int i = 0; i < insersiones.size(); i++) {
            ModificacionTabla mod = insersiones.get(i);
            datos[i][0] = mod.getTablaModificada();
            datos[i][1] = mod.getConsulta();        // Asumiendo que hay un método getLenguaje()
            datos[i][2] = mod.getTipoModificacion();            // Asumiendo que hay un método getTipo()
        }
        actualizarTabla(columnas, datos);
    }
}

private void mostrarReporteInsersiones() {
    String[] columnas = {"Nombre Tabla", "Consulta "};
    if (insersionesTablas != null && !insersionesTablas.isEmpty()) {
        Object[][] datos = new Object[insersionesTablas.size()][2];  // Creamos una matriz para almacenar los datos

    for (int i = 0; i < insersionesTablas.size(); i++) {
            InsersionTabla mod = insersionesTablas.get(i);
            System.out.println(mod.getNombreTabla());
            datos[i][0] = mod.getNombreTabla();
            datos[i][1] = mod.getConsulta();        // Asumiendo que hay un método getLenguaje()
        }
        actualizarTabla(columnas, datos);
    }
}

//// Método para mostrar el reporte de Optimización
//private void mostrarReporteOptimizacion() {
//    String[] columnas = {"Token", "ExpresionRegular", "Lenguaje", "Tipo", "Fila", "Columna"};
//    if (reporteOptimizacion != null && !reporteOptimizacion.isEmpty()) {
//        Object[][] datos = new Object[reporteOptimizacion.size()][6];  // Creamos una matriz para almacenar los datos
//
//        for (int i = 0; i < reporteOptimizacion.size(); i++) {
//            Token token = reporteOptimizacion.get(i);
//            datos[i][0] = token.getLexema();
//            datos[i][1] = token.getExpresionRegular();        // Asumiendo que hay un método getLenguaje()
//            datos[i][2] = token.getTipoToken().toString();            // Asumiendo que hay un método getTipo()
//            datos[i][3] = token.getContexto();            // Asumiendo que hay un método getFila()
//            datos[i][4] = token.getFila();            // Asumiendo que hay un método getFila()
//            datos[i][5] = token.getColumna();         // Asumiendo que hay un método getColumna()
//
//        }
//        actualizarTabla(columnas, datos);
//    }
//}

// Método para mostrar el reporte de Errores
private void actualizarTabla(String[] columnas, Object[][] datos) {
    tableModel.setDataVector(datos, columnas);  // Actualiza el modelo de la tabla con las nuevas columnas y datos
    for (int i = 0; i < tablaReporte.getColumnModel().getColumnCount(); i++) {
        tablaReporte.getColumnModel().getColumn(i).setPreferredWidth(panelContenedor.getWidth() / columnas.length);
    }

    tablaReporte.revalidate(); 
    this.pack();

}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelContenedor.setBackground(new java.awt.Color(255, 255, 0));
        panelContenedor.setForeground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1264, Short.MAX_VALUE)
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables

    private void generarTablas() {
        if (tablasGeneradas.isEmpty()) {
            mostrarMensaje("No se generaron tablas en el analisis");
           return;
        }
        List<Tabla > tablasG = tablasGeneradas.get();
        mostrarTokensPorTabla(tablasGeneradas.get());

        GraphVIzGenerator g = new GraphVIzGenerator();
        String rutaDot = "/home/kevin-mushin/SistemasCunoc/SS_2024/Lenguajes/ProyectoFinal/ImagenesTablas/grafico.dot";
        String ruta = "/home/kevin-mushin/SistemasCunoc/SS_2024/Lenguajes/ProyectoFinal/ImagenesTablas/grafico.png";

        
        try {
            g.generarDot(tablasG, rutaDot);
            g.generarImagenDesdeDot(rutaDot, ruta);
        } catch (IOException | InterruptedException ex) {
                System.out.println("error en la generacion de tablsa");
        }
        
    }
        private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
        private void mostrarTokensPorTabla(List<Tabla> tablas) {
    for (Tabla tabla : tablas) {
        System.out.println("Tabla------------------------------------: " + tabla.getNombreTabla());
        for (Token token : tabla.getTokens()) {
            System.out.println("    Token: " + token.getLexema());
        }
    }
}

    

}
