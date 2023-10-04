package com.controladores;

import com.excepciones.CamposVaciosException;
import com.excepciones.ElementoNoSeleccionadoException;
import com.modelos.Cliente;
import com.daos.DaoCliente;
import com.excepciones.CedulaEnUsoException;
import com.excepciones.ClienteNoEncontradoException;
import com.excepciones.CorreoEnUsoException;
import com.utils.GeneralUtils;
import java.util.ArrayList;

public class ControladorClientes {

    private DaoCliente daoCliente;

    public ControladorClientes() {
        daoCliente = new DaoCliente();
    }

    public void insertarCliente(String cedula, String nombre, String email) {
        if (GeneralUtils.estaVacio(cedula)
                || GeneralUtils.estaVacio(nombre)
                || GeneralUtils.estaVacio(email)) {
            throw new CamposVaciosException();
        }
        if (daoCliente.consultarCliente(cedula, true) != null) {
            throw new CedulaEnUsoException();
        }
        if (daoCliente.consultarCliente(email, false) != null) {
            throw new CorreoEnUsoException();
        }
        Cliente cliente = new Cliente(cedula, nombre, email);
        daoCliente.insertarCliente(cliente);
    }

    public ArrayList<Cliente> obtenerListaClientes(String cedula) {
        return (cedula != null)
                ? daoCliente.obtenerListaClientes(cedula)
                : daoCliente.obtenerListaClientes();
    }

    public Cliente consultarCedula(String cedula) {
        return daoCliente.consultarCliente(cedula, true);
    }

    public void actualizarCliente(String cedula, String nombre, String email) {
        if (GeneralUtils.estaVacio(cedula)
                || GeneralUtils.estaVacio(nombre)
                || GeneralUtils.estaVacio(email)) {
            throw new CamposVaciosException();
        }
        
        Cliente cliente = daoCliente.consultarCliente(cedula, true);
        
        if (cliente == null) {
            throw new ClienteNoEncontradoException();
        }
        
        if (!cliente.getEmail().equals(email)
                && daoCliente.consultarCliente(email, false) != null) {
            throw new CorreoEnUsoException();
        }
        daoCliente.actualizarCliente(nombre, email, cedula);
    }

    public void eliminarCliente(String cedula) {
        if (GeneralUtils.estaVacio(cedula)) {
            throw new ElementoNoSeleccionadoException();
        }
        if (daoCliente.consultarCliente(cedula, true) == null) {
            throw new ClienteNoEncontradoException();
        }
        daoCliente.eliminarCliente(cedula);
    }
}
