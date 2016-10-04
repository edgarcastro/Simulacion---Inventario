/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.vista;

import inventario.controlador.CMayorista;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edgarcastro
 */
public class VMayorista extends javax.swing.JFrame {

    /**
     * Creates new form VMayorista
     */
    public VMayorista() {
        initComponents();
        this.lInventario.setText("");
        this.lPedido.setText("");
        this.lDiaActual.setText("");
        this.lNumClientes.setText("");
    }
    
    public void actualizarCliente(List clientes){
        this.lNumClientes.setText(""+clientes.size());
    }
    
    public void mostrarDia(int dia){
        this.lDiaActual.setText(""+dia);
    }
    
    public void mostrarStock(int cantidad){
        this.lInventario.setText(""+cantidad);
    }
    
    public void mostrarOrdenes(Object[][] datos, int tam) {
        tOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Id Minorista", "Cantidad", "Dias de espera", "Aceptado", "Entregado"
            }));
        DefaultTableModel temp = (DefaultTableModel) tOrdenes.getModel();
        for (int i = 0; i < tam; i++) {
            temp.addRow(datos[i]);
        }
    }
    
    public void mostrarMinoristas(List<String> minoristas) {
        tMinoristas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Id", "Minorista"
            }));
        DefaultTableModel temp = (DefaultTableModel) tMinoristas.getModel();
        Object[] fila = new Object[]{};
        for (int i = 0; i < minoristas.size(); i++) {
            if(minoristas.get(i).equals("H"))
                fila = new Object[]{i,"Humano"};
            if(minoristas.get(i).equals("S"))
                fila = new Object[]{i,"Simulado"};
            temp.addRow(fila);
        }
    }
    
    public void mostrarMiPedido(int cantidad, int diasEspera){
        if(cantidad == 0 && diasEspera == 0)
            this.cTengoPedido.setSelected(false);
        else{
            this.cTengoPedido.setSelected(true);
            this.lPedido.setText("<html>Cantidad: "+cantidad+"<br>Dias de Espera: "+diasEspera+"</html>");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lPuerto = new javax.swing.JLabel();
        tPuerto = new javax.swing.JTextField();
        bIniciar = new javax.swing.JButton();
        lClientes = new javax.swing.JLabel();
        lNumClientes = new javax.swing.JLabel();
        lP = new javax.swing.JLabel();
        tP = new javax.swing.JTextField();
        lQ = new javax.swing.JLabel();
        tQ = new javax.swing.JTextField();
        BSgteDia = new javax.swing.JButton();
        lDia = new javax.swing.JLabel();
        lDiaActual = new javax.swing.JLabel();
        lStock = new javax.swing.JLabel();
        lInventario = new javax.swing.JLabel();
        sOrdenes = new javax.swing.JScrollPane();
        tOrdenes = new javax.swing.JTable();
        bAuto = new javax.swing.JButton();
        sMinoristas = new javax.swing.JScrollPane();
        tMinoristas = new javax.swing.JTable();
        cTengoPedido = new javax.swing.JCheckBox();
        lPedido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MAYORISTA");
        setResizable(false);

        lPuerto.setText("Puerto:");

        bIniciar.setText("Iniciar");
        bIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIniciarActionPerformed(evt);
            }
        });

        lClientes.setText("Número de  Minoristas:");

        lNumClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lNumClientes.setText("###");

        lP.setText("p:");

        lQ.setText("q:");

        BSgteDia.setText("Siguiente Dia");
        BSgteDia.setEnabled(false);
        BSgteDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSgteDiaActionPerformed(evt);
            }
        });

        lDia.setText("Dia actual: ");

        lDiaActual.setText("###");

        lStock.setText("Stock:");

        lInventario.setText("####");

        tOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Minorista", "Cantidad", "Dias de espera", "Aceptado", "Entregado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tOrdenes.setEnabled(false);
        sOrdenes.setViewportView(tOrdenes);

        bAuto.setText("Auto");
        bAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAutoActionPerformed(evt);
            }
        });

        tMinoristas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Minorista"
            }
        ));
        tMinoristas.setEnabled(false);
        sMinoristas.setViewportView(tMinoristas);

        cTengoPedido.setText("Tengo un pedido");
        cTengoPedido.setEnabled(false);
        cTengoPedido.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cTengoPedidoStateChanged(evt);
            }
        });

        lPedido.setText("<html>\nCantidad: \n<br>\nDias de espera: \n</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lDia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lDiaActual))
                            .addComponent(BSgteDia)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cTengoPedido)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lStock)
                                    .addComponent(lP))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tP, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lClientes)
                                .addGap(31, 31, 31)
                                .addComponent(lNumClientes))
                            .addComponent(sMinoristas, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(sOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lQ)
                        .addGap(18, 18, 18)
                        .addComponent(tQ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lPuerto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAuto)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPuerto)
                    .addComponent(tPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bIniciar)
                    .addComponent(lP)
                    .addComponent(tP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lQ)
                    .addComponent(tQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAuto))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lStock)
                            .addComponent(lInventario))
                        .addGap(32, 32, 32)
                        .addComponent(cTengoPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sMinoristas, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lClientes)
                            .addComponent(lNumClientes))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDia)
                    .addComponent(lDiaActual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BSgteDia)
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIniciarActionPerformed
        CMayorista.iniciarServidor(Double.parseDouble(this.tP.getText()), Double.parseDouble(this.tQ.getText()), Integer.parseInt(this.tPuerto.getText()));
        this.tP.setEditable(Boolean.FALSE);
        this.tP.setEnabled(Boolean.FALSE);
        this.tQ.setEditable(Boolean.FALSE);
        this.tQ.setEnabled(Boolean.FALSE);
        this.tPuerto.setEditable(Boolean.FALSE);
        this.tPuerto.setEnabled(Boolean.FALSE);
        this.BSgteDia.setEnabled(true);
    }//GEN-LAST:event_bIniciarActionPerformed

    private void BSgteDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSgteDiaActionPerformed
        CMayorista.siguienteDia();
    }//GEN-LAST:event_BSgteDiaActionPerformed

    private void bAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAutoActionPerformed
        this.tP.setText(""+100);
        this.tQ.setText(""+500);
        this.tPuerto.setText(""+5555);
        bIniciarActionPerformed(evt);
    }//GEN-LAST:event_bAutoActionPerformed

    private void cTengoPedidoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cTengoPedidoStateChanged
        if(!this.cTengoPedido.isSelected())
            this.lPedido.setText("");
    }//GEN-LAST:event_cTengoPedidoStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VMayorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VMayorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VMayorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VMayorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VMayorista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BSgteDia;
    private javax.swing.JButton bAuto;
    private javax.swing.JButton bIniciar;
    private javax.swing.JCheckBox cTengoPedido;
    private javax.swing.JLabel lClientes;
    private javax.swing.JLabel lDia;
    private javax.swing.JLabel lDiaActual;
    private javax.swing.JLabel lInventario;
    private javax.swing.JLabel lNumClientes;
    private javax.swing.JLabel lP;
    private javax.swing.JLabel lPedido;
    private javax.swing.JLabel lPuerto;
    private javax.swing.JLabel lQ;
    private javax.swing.JLabel lStock;
    private javax.swing.JScrollPane sMinoristas;
    private javax.swing.JScrollPane sOrdenes;
    private javax.swing.JTable tMinoristas;
    private javax.swing.JTable tOrdenes;
    private javax.swing.JTextField tP;
    private javax.swing.JTextField tPuerto;
    private javax.swing.JTextField tQ;
    // End of variables declaration//GEN-END:variables
}
