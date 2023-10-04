package com.excepciones;

/**
 *
 * @author Mateo
 */
public class CedulaEnUsoException extends RuntimeException{

    public CedulaEnUsoException() {
        super("La cédula se encuentra en uso");
    }
    
}
