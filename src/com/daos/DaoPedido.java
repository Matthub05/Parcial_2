package com.daos;

import com.modelos.Pedido;
import com.singleton.DatabaseSingleton;
import com.utils.GeneralUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;

public class DaoPedido {

    private Connection connection;

    public DaoPedido() {
        connection = DatabaseSingleton.getInstance().getConnection();
    }

    public ArrayList<Pedido> obtenerListaPedidos() {
        ArrayList<Pedido> retornoPedidos = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT p.id, p.fechaPedido, p.total, c.nombre FROM pedidos as p INNER JOIN clientes as c ON p.cedulaCliente = c.cedula");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getString("p.id"),
                        rs.getString("p.fechaPedido"),
                        Double.parseDouble(rs.getString("p.total")),
                        rs.getString("c.nombre"));
                retornoPedidos.add(pedido);
            }
            return retornoPedidos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public ArrayList<Pedido> obtenerListaPedidos(String busqueda, boolean consultaId) {
        ArrayList<Pedido> retornoPedidos = new ArrayList<>();

        try {
            PreparedStatement ps = null;
            
            if (consultaId) {
                ps = connection.prepareStatement("SELECT p.id, p.fechaPedido, p.total, c.nombre FROM pedidos as p INNER JOIN clientes as c ON p.cedulaCliente = c.cedula WHERE p.id LIKE CONCAT('%',?,'%')");
            } else {
                ps = connection.prepareStatement("SELECT p.id, p.fechaPedido, p.total, c.nombre FROM pedidos as p INNER JOIN clientes as c ON p.cedulaCliente = c.cedula WHERE c.cedula = ?");
            }
            
            ps.setString(1, busqueda);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getString("p.id"),
                        rs.getString("p.fechaPedido"),
                        Double.parseDouble(rs.getString("p.total")),
                        rs.getString("c.nombre"));
                retornoPedidos.add(pedido);
            }
            return retornoPedidos;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void insertarPedido(String fecha, String total, String cedulaCliente) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pedidos (id, fechaPedido, total, cedulaCliente) VALUES (?, ?, ?, ?)");
            ps.setString(1, GeneralUtils.generarSku("p"));
            ps.setString(2, fecha);
            ps.setString(3, total);
            ps.setString(4, cedulaCliente);

            ps.execute();

        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

    public Pedido consultarPedido(String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM pedidos WHERE id = ?");

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Pedido pedido = new Pedido(
                        rs.getString("id"),
                        rs.getString("fechaPedido"),
                        Double.parseDouble(rs.getString("total")),
                        rs.getString("cedulaCliente"));
                    return pedido;
                }

            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.print(ex);
        }
        return null;
    }

    public void actualizarPedido(String fecha, String total, String cedulaCliente, String id) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE pedidos SET fechaPedido = ?, total = ?, cedulaCliente = ? WHERE id = ?");
            ps.setString(1, fecha);
            ps.setString(2, total);
            ps.setString(3, cedulaCliente);
            ps.setString(4, id);

            ps.execute();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

    public void eliminarPedido(String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM pedidos WHERE id = ?");
            ps.setString(1, id);

            ps.execute();
        } catch (SQLException ex) {
            System.err.print(ex);
        }
    }

}
