package com.excepciones;

/**
 *
 * @author Mateo
 */
public class ClienteEnPedidosException extends RuntimeException{

    public ClienteEnPedidosException() {
        super("El cliente se encuentra asosiado en un pedido");
    }
    
}
