package com.excepciones;

/**
 *
 * @author Mateo
 */
public class ClienteNoEncontradoException extends RuntimeException{

    public ClienteNoEncontradoException() {
        super("No se ha encontrado el cliente");
    }
    
}
