package com.daos;

import com.excepciones.ClienteEnPedidosException;
import com.excepciones.ConexionNoInicializadaException;
import com.modelos.Cliente;
import com.singleton.DatabaseSingleton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;

public class DaoCliente {

    private Connection connection;

    public DaoCliente() {
        connection = DatabaseSingleton.getInstance().getConnection();
    }

    public ArrayList<Cliente> obtenerListaClientes() {
        ArrayList<Cliente> retornoClientes = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente usuarioObtenido = new Cliente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("email"));

                retornoClientes.add(usuarioObtenido);
            }
            return retornoClientes;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public ArrayList<Cliente> obtenerListaClientes(String cedula) {
        ArrayList<Cliente> retornoClientes = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM clientes WHERE cedula LIKE CONCAT('%',?,'%')");
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente usuarioObtenido = new Cliente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("email"));

                retornoClientes.add(usuarioObtenido);
            }
            return retornoClientes;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Cliente> buscarCoincidencias(String where) {
        ArrayList<Cliente> retornoClientes = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT * FROM clientes WHERE cedula LIKE CONCAT('%',?,'%')");
            ps.setString(1, where);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente usuarioObtenido = new Cliente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("email"));

                retornoClientes.add(usuarioObtenido);
            }
            return retornoClientes;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insertarCliente(Cliente cliente) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO clientes (cedula, nombre, email) VALUES (?, ?, ?)");
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());

            ps.execute();

        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

    public Cliente consultarCliente(String argumento, boolean esConsultaCedula) {
        try {

            PreparedStatement ps = null;

            if (esConsultaCedula) {
                ps = connection.prepareStatement("SELECT * FROM clientes WHERE cedula = ?");
            } else {
                ps = connection.prepareStatement("SELECT * FROM clientes WHERE email = ?");
            }

            ps.setString(1, argumento);

            ResultSet rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Cliente usuarioObtenido = new Cliente(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("email"));
                    return usuarioObtenido;
                }
            } else {
                return null;
            }

        } catch (ConexionNoInicializadaException e) {
            throw new ConexionNoInicializadaException();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        return null;
    }

    public void actualizarCliente(String nombre, String email, String cedula) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET nombre = ?, email = ? WHERE cedula = ?");
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, cedula);

            ps.execute();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

    public void eliminarCliente(String cedula) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM clientes WHERE cedula = ?");
            ps.setString(1, cedula);

            ps.execute();
        } catch (SQLIntegrityConstraintViolationException x) {
            throw new ClienteEnPedidosException();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

}
