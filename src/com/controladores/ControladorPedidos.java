package com.controladores;

import com.daos.DaoPedido;
import com.excepciones.CamposVaciosException;
import com.excepciones.DoubleInvalidoException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.modelos.Pedido;
import com.utils.GeneralUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ControladorPedidos {

    private DaoPedido daoPedido;

    public ControladorPedidos() {
        daoPedido = new DaoPedido();
    }

    public void insertarPedido(String total, String cedulaCliente) {
        if (GeneralUtils.estaVacio(total)
                || cedulaCliente.equals("Seleccione un cliente")) {
            throw new CamposVaciosException();
        }

        if (!GeneralUtils.esDouble(total)
                || Double.valueOf(total) < 0) {
            throw new DoubleInvalidoException();
        }
        
        final SimpleDateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = FORMATO.format(Calendar.getInstance().getTime());
        
        daoPedido.insertarPedido(fecha, total, cedulaCliente);
    }
    
    public ArrayList<Pedido> listarPedidos(String busqueda, boolean consultaId) {
        return (busqueda != null)
                ? daoPedido.obtenerListaPedidos(busqueda, consultaId)
                : daoPedido.obtenerListaPedidos();
    }

    public Pedido consultarId(String id) {
        return daoPedido.consultarPedido(id);
    }

    public void actualizarPedido(String id, String fecha, String total, String cedulaCliente) {
        if (GeneralUtils.estaVacio(id)) {
            throw new ElementoNoSeleccionadoException();
        }

        if (GeneralUtils.estaVacio(fecha)
                || GeneralUtils.estaVacio(total)
                || cedulaCliente.equals("Seleccione un cliente")) {
            throw new CamposVaciosException();
        }

        if (!GeneralUtils.esDouble(total)
                || Double.valueOf(total) < 0) {
            throw new DoubleInvalidoException();
        }
        daoPedido.actualizarPedido(fecha, total, cedulaCliente, id);
    }

    public void eliminarPedido(String id) {
        if (GeneralUtils.estaVacio(id)) {
            throw new ElementoNoSeleccionadoException();
        }
        daoPedido.eliminarPedido(id);
    }
}
