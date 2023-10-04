package com.excepciones;

/**
 *
 * @author Mateo
 */
public class CorreoEnUsoException extends RuntimeException{

    public CorreoEnUsoException() {
        super("El correo se encuentra en uso");
    }
    
}
