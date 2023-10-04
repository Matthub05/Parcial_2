package com.vistas;

import com.controladores.ControladorClientes;
import com.controladores.ControladorPedidos;
import com.modelos.Cliente;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class VistaRegistrarProducto extends javax.swing.JFrame {

    ControladorPedidos controlador;
    ControladorClientes controladorClientes;
    VistaPrincipal vistaPrincipal;

    public VistaRegistrarProducto(VistaPrincipal vistaPrincipal) {
        controlador = new ControladorPedidos();
        controladorClientes = new ControladorClientes();
        this.vistaPrincipal = vistaPrincipal;

        this.setLocationRelativeTo(null);
        setTitle("Registrar Producto");

        initComponents();
        setCmbxCategoria();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar unicamente la ventana actual
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        cmbxCedulas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar Pedido");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 350, 40));

        jLabel6.setText("Total:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel7.setText("Cédula:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        bg.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 240, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 310, 30));

        bg.add(cmbxCedulas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        String cedula = cmbxCedulas.getSelectedItem().toString().split(" - ")[0];
        try {
            controlador.insertarPedido(txtTotal.getText(), cedula);

            this.dispose();
            vistaPrincipal.actualizarTablaPedidos(null);
            vistaPrincipal.limpiarCamposPedido();
            vistaPrincipal.actualizarVistaFiltro();
            JOptionPane.showMessageDialog(null, "Producto registrado");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void setCmbxCategoria() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cmbxCedulas.setModel(model);

        ArrayList<Cliente> clientes = controladorClientes.obtenerListaClientes(null);
        model.addElement("Seleccione un cliente"); // Agrega la opción predeterminada

        for (Cliente cliente : clientes) {
            model.addElement(cliente.getCedula() + " - " + cliente.getNombre());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbxCedulas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
