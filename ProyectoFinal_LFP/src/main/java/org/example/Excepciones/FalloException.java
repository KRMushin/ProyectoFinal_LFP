/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.Excepciones;

/**
 *
 * @author kevin-mushin
 */
public class FalloException extends Exception{

    public FalloException() {
    }

    public FalloException(String message) {
        super(message);
    }

    public FalloException(Throwable cause) {
        super(cause);
    }
    
}
