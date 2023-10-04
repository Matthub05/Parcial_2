package com.vistas;

import com.controladores.ControladorClientes;
import com.controladores.ControladorPedidos;
import com.modelos.Cliente;
import com.modelos.Pedido;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VistaPrincipal extends javax.swing.JFrame {

    ControladorPedidos controladorPedidos;
    ControladorClientes controladorClientes;
    VistaFiltrarCliente vistaFiltrarCategoria;

    public VistaPrincipal() {
        controladorPedidos = new ControladorPedidos();
        controladorClientes = new ControladorClientes();

        setLocationRelativeTo(this);
        setTitle("Buscar Productos");

        initComponents();

        actualizarTablaPedidos(null);
        tblPedidos.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
        tblPedidos.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
        tblPedidos.setCellSelectionEnabled(false); // Evitar selecciones en la tabla

        actualizarTablaClientes(null);
        tblClientes.setDefaultEditor(Object.class, null); // Evitar ediciones en la tabla
        tblClientes.getTableHeader().setEnabled(false); // Evitar reorganizaciones de Headers en la tabla
        tblClientes.setCellSelectionEnabled(false); // Evitar selecciones en la tabla

        setCmbxClientes();
    }

    public void setCmbxClientes() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbxCedula.setModel(model);

        ArrayList<Cliente> clientes = controladorClientes.obtenerListaClientes(null);
        model.addElement("Seleccione un cliente"); // Agrega la opción predeterminada

        for (Cliente cliente : clientes) {
            model.addElement(cliente.getCedula());
        }
    }

    public void actualizarTablaPedidos(String id) {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Pedido> pedidos = controladorPedidos.listarPedidos(id, true);
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

    public void actualizarTablaClientes(String cedula) {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<Cliente> listaUsuarios = controladorClientes.obtenerListaClientes(cedula);
        modelo.setColumnIdentifiers(new Object[]{"Cédula", "Nombre", "Email"});
        tblClientes.setModel(modelo);

        for (Cliente usuarioAlmacenado : listaUsuarios) {
            modelo.addRow(new Object[]{
                usuarioAlmacenado.getCedula(),
                usuarioAlmacenado.getNombre(),
                usuarioAlmacenado.getEmail()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        lblId = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnActualizarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbxCedula = new javax.swing.JComboBox<>();
        btnVerPorCliente = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnRegistrarCliente = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        txtBusquedaId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBusquedaCedula = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(20, 20));

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clientes:");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 280, 50));

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

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 370, 270));
        bg.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 200, 20));

        jLabel6.setText("Total:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        jLabel7.setText("Cédula:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));
        bg.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 200, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 100, -1));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        bg.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 100, -1));

        jLabel8.setText("Fecha:");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        btnRegistrar.setText("Registrar nuevo pedido");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 240, -1));

        jLabel9.setText("Id:");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 370, 210));

        jLabel4.setText("Cédula:");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 410, 50, 20));

        jLabel10.setText("Nombre:");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));
        bg.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, 200, -1));

        btnActualizarCliente.setText("Actualizar");
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });
        bg.add(btnActualizarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 530, 100, -1));

        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        bg.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 100, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pedidos:");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 320, 50));

        bg.add(cmbxCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 200, 20));

        btnVerPorCliente.setText("Ver pedidos por cliente");
        btnVerPorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPorClienteActionPerformed(evt);
            }
        });
        bg.add(btnVerPorCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 240, -1));
        bg.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 200, -1));
        bg.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 200, 20));

        jLabel11.setText("Email:");
        bg.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, -1, -1));

        btnRegistrarCliente.setText("Registrar");
        btnRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarClienteActionPerformed(evt);
            }
        });
        bg.add(btnRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 240, -1));
        bg.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 200, 20));

        txtBusquedaId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaIdKeyReleased(evt);
            }
        });
        bg.add(txtBusquedaId, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 280, -1));

        jLabel12.setText("Buscar por ID:");
        bg.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtBusquedaCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaCedulaKeyReleased(evt);
            }
        });
        bg.add(txtBusquedaCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 250, -1));

        jLabel13.setText("Buscar por cédula:");
        bg.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String cedula = cmbxCedula.getSelectedItem().toString();
        try {
            controladorPedidos.actualizarPedido(lblId.getText(),
                    lblFecha.getText(),
                    txtTotal.getText(),
                    cedula);

            actualizarTablaPedidos(null);
            limpiarCamposPedido();
            actualizarVistaFiltro();

            JOptionPane.showMessageDialog(null, "El pedido se ha actualizado con exito");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            controladorPedidos.eliminarPedido(lblId.getText());

            actualizarTablaPedidos(null);
            limpiarCamposPedido();
            JOptionPane.showMessageDialog(null, "Se ha eliminado con exito");

            actualizarVistaFiltro();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            VistaRegistrarProducto vista = new VistaRegistrarProducto(this);
            vista.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void tblPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPedidosMouseClicked
        String id = String.valueOf(tblPedidos.getValueAt(tblPedidos.getSelectedRow(), 0));
        mostrarPedido(id);
    }//GEN-LAST:event_tblPedidosMouseClicked

    public void mostrarPedido(String id) {
        Pedido pedido = controladorPedidos.consultarId(id);
        lblId.setText(pedido.getId());
        lblFecha.setText(pedido.getFecha());
        txtTotal.setText(String.valueOf(pedido.getTotal()));
        cmbxCedula.setSelectedItem(pedido.getCedulaCliente());
    }

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        String cedula = String.valueOf(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
        Cliente categoriaSeleccionada = controladorClientes.consultarCedula(cedula);

        txtCedula.setText(categoriaSeleccionada.getCedula());
        txtNombre.setText(categoriaSeleccionada.getNombre());
        txtEmail.setText(categoriaSeleccionada.getEmail());
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        try {
            controladorClientes.actualizarCliente(txtCedula.getText(),
                    txtNombre.getText(),
                    txtEmail.getText());

            actualizarTablaClientes(null);
            actualizarTablaPedidos(txtBusquedaId.getText());
            setCmbxClientes();
            actualizarVistaFiltro();
            limpiarCamposCliente();

            JOptionPane.showMessageDialog(null, "El cliente se ha actualizado con exito");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        try {
            controladorClientes.eliminarCliente(txtCedula.getText());

            actualizarTablaClientes(null);
            limpiarCamposCliente();
            setCmbxClientes();
            actualizarVistaFiltro();

            JOptionPane.showMessageDialog(null, "El cliente se ha eliminado con exito");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnVerPorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPorClienteActionPerformed
        VistaFiltrarCliente vista = null;
        try {
            vista = new VistaFiltrarCliente(this);
            vistaFiltrarCategoria = vista;
        } catch (Exception ex) {
            Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        vista.setVisible(true);
    }//GEN-LAST:event_btnVerPorClienteActionPerformed

    private void btnRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarClienteActionPerformed
        try {
            controladorClientes.insertarCliente(txtCedula.getText(), txtNombre.getText(), txtEmail.getText());

            actualizarTablaClientes(null);
            setCmbxClientes();
            actualizarVistaFiltro();
            limpiarCamposCliente();
            JOptionPane.showMessageDialog(null, "Cliente registrado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarClienteActionPerformed

    private void txtBusquedaIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaIdKeyReleased
        String busqueda = txtBusquedaId.getText();
        if (busqueda.equals("")) {
            actualizarTablaPedidos(null);
        } else {
            actualizarTablaPedidos(busqueda);
        }
    }//GEN-LAST:event_txtBusquedaIdKeyReleased

    private void txtBusquedaCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCedulaKeyReleased
        String busqueda = txtBusquedaCedula.getText();
        if (busqueda.equals("")) {
            actualizarTablaClientes(null);
        } else {
            actualizarTablaClientes(busqueda);
        }
    }//GEN-LAST:event_txtBusquedaCedulaKeyReleased

    public void limpiarCamposPedido() {
        txtBusquedaId.setText("");
        lblId.setText("");
        lblFecha.setText("");
        txtTotal.setText("");
        cmbxCedula.setSelectedIndex(0);
    }

    private void limpiarCamposCliente() {
        txtBusquedaCedula.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
    }

    public void actualizarVistaFiltro() {
        if (vistaFiltrarCategoria != null) {
            vistaFiltrarCategoria.setCmbxClientes();
            vistaFiltrarCategoria.actualizarTablaPedidos();
            vistaFiltrarCategoria.limpiarCmbx();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarCliente;
    private javax.swing.JButton btnVerPorCliente;
    private javax.swing.JComboBox<String> cmbxCedula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblId;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtBusquedaCedula;
    private javax.swing.JTextField txtBusquedaId;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    public void setLblId(String entrada) {
        lblId.setText(entrada);
    }

}
