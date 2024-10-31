/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.example.Entity.Tabla;
import org.example.backend.Enums.TipoToken;
import org.example.backend.Tokens.Token;

/**
 *
 * @author kevin-mushin
 */
public class GraphVIzGenerator {
    
    public void generarDot(List<Tabla> tablas, String rutaArchivoDot) {
    try (FileWriter writer = new FileWriter(rutaArchivoDot)) {
        writer.write("digraph G {\n");
        
        for (Tabla tabla : tablas) {
            writer.write("    " + tabla.getNombreTabla() + " [label=<\n");
            writer.write("        <TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n");
            writer.write("            <TR><TD COLSPAN=\"2\"><B>" + tabla.getNombreTabla() + "</B></TD></TR>\n");

            StringBuilder tokensAcumulados = new StringBuilder();
            String identificador = null;

            for (Token token : tabla.getTokens()) {
                if (token.getTipoToken() == TipoToken.IDENTIFICADOR) {
                    // Si ya hay un identificador previo, escribir la fila con el acumulado de tokens
                    if (identificador != null && tokensAcumulados.length() > 0) {
                        writer.write("            <TR><TD><B>" + identificador + "</B></TD><TD>" + tokensAcumulados.toString().trim() + "</TD></TR>\n");
                        tokensAcumulados.setLength(0); // Limpiar el acumulador de tokens
                    }
                    identificador = token.getLexema(); // Nuevo IDENTIFICADOR
                } else if (token.getLexema().equals(",")) {
                    // Agregar un salto de línea en HTML para separar tokens
                    tokensAcumulados.append("<br/>");
                } else {
                    // Agregar el token acumulado con un espacio
                    tokensAcumulados.append(token.getLexema()).append(" ");
                }
            }

            // Escribir la última fila si hay un identificador y tokens acumulados
            if (identificador != null && tokensAcumulados.length() > 0) {
                writer.write("            <TR><TD><B>" + identificador + "</B></TD><TD>" + tokensAcumulados.toString().trim() + "</TD></TR>\n");
            }

            writer.write("        </TABLE>\n");
            writer.write("    > shape=plaintext];\n");
        }
        
        writer.write("}\n");

    } catch (IOException ex) {
        System.out.println("Error en la generación de imágenes: " + ex);
    }
}



    

    
    public void generarImagenDesdeDot(String rutaArchivoDot, String rutaImagenSalida) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", rutaArchivoDot, "-o", rutaImagenSalida);
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        
        if (exitCode == 0) {
            System.out.println("Imagen generada exitosamente en " + rutaImagenSalida);
        } else {
            System.err.println("Error al generar la imagen. Código de salida: " + exitCode);
        }
    }
    
}

//    public void generarDot(List<Tabla> tablas, String rutaArchivoDot) {
//            try (FileWriter writer = new FileWriter(rutaArchivoDot)) {
//                writer.write("digraph G {\n");
//                for (Tabla tabla : tablas) {
//                    writer.write("    " + tabla.getNombreTabla() + " [label=<\n");
//                    writer.write("        <TABLE BORDER=\"1\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n");
//                    writer.write("            <TR><TD COLSPAN=2><B>" + tabla.getNombreTabla() + "</B></TD></TR>\n");
//                    for (Token token : tabla.getTokens()) {
//                        writer.write("            <TR><TD><B>" + token.getLexema() + "</B></TD><TD>" + token.getParToken() + "</TD></TR>\n");
//                    }
//                    writer.write("        </TABLE>\n");
//                    writer.write("    > shape=plaintext];\n");
//                }
//                writer.write("}\n");
//            } catch (IOException ex) {
//                System.out.println("Error en la generación de imágenes");
//            }
//}




//if (token.getTipoToken() == TipoToken.IDENTIFICADOR) {
//                    // Si ya hay un identificador anterior y tokens acumulados, escribimos la fila
//                    if (identificador != null) {
//                        conL.setLength(0); // Limpiar el acumulador
//                    }
//                    // Actualizamos el nuevo identificador
//                    identificador = token.getLexema();
//                } else {
//                    // Acumular el parToken con salto de línea en HTML si no es un IDENTIFICADOR
//                    conL.append(token.getParToken()).append("<br/>");
//                }