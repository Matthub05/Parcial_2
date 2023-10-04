package com.modelos;

public class Pedido {

    private String id;
    private String fecha;
    private double total;
    private String cedulaCliente;
    
    public Pedido(String id, String fecha, double total, String cedulaCliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.cedulaCliente = cedulaCliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

}
