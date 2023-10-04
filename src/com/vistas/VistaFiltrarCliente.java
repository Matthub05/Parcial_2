package com.vistas;

import com.controladores.ControladorClientes;
import com.controladores.ControladorPedidos;
import com.modelos.Cliente;
import com.modelos.Pedido;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class VistaFiltrarCliente extends javax.swing.JFrame {

    ControladorPedidos controladorPedidos;
    ControladorClientes controladorClientes;
    VistaPrincipal vistaPrincipal;

    public VistaFiltrarCliente(VistaPrincipal vistaPrincipal) {
        controladorPedidos = new ControladorPedidos();
        controladorClientes = new ControladorClientes();
        this.vistaPrincipal = vistaPrincipal;

        this.setLocationRelativeTo(this);
        setTitle("Buscar Productos");

        initComponents();

        setCmbxClientes();
        
        actualizarTablaPedidos();
        tblPedidos.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
        tblPedidos.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
        tblPedidos.setCellSelectionEnabled(false); // Evitar selecciones en la tabla
    }

    public void setCmbxClientes() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbxClientes.setModel(model);

        ArrayList<Cliente> clientes = controladorClientes.obtenerListaClientes(null);
        model.addElement("Seleccione un cliente"); // Agrega la opción predeterminada

        for (Cliente cliente : clientes) {
            model.addElement(cliente.getCedula() + " - " + cliente.getNombre());
        }
    }

    public void actualizarTablaPedidos() {
        String cedula = (cmbxClientes.getSelectedIndex() == 0)
                ? null
                : cmbxClientes.getSelectedItem().toString().split(" ")[0];
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Pedido> pedidos = controladorPedidos.listarPedidos(cedula, false);
        modelo.setColumnIdentifiers(new Object[]{"ID", "Fecha", "Total", "Cliente"});
        tblPedidos.setModel(modelo);

        for (Pedido pedido : pedidos) {
            modelo.addRow(new Object[]{
                pedido.getId(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getCedulaCliente()
            });
        }
    }

    public void limpiarCmbx() {
        cmbxClientes.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbxClientes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 370, 270));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar por cliente");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 370, 50));

        cmbxClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbxClientesItemStateChanged(evt);
            }
        });
        bg.add(cmbxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 370, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbxClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbxClientesItemStateChanged
        actualizarTablaPedidos();
    }//GEN-LAST:event_cmbxClientesItemStateChanged

    private void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        vistaPrincipal.mostrarPedido(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblPedidosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JComboBox<String> cmbxClientes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
