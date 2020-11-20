/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import version5.Proceso;
import version5.Recurso;
import version5.SistemaOperativo;

/**
 *
 * @author Usuario
 */
public class CrearInstruccion extends javax.swing.JFrame {

    /**
     * Creates new form CrearInstruccion
     */
    public CrearInstruccion(SistemaOperativo sist) {
        initComponents();
        this.so = sist;
        cargarLista();
    }

    public void cargarLista() {
        DefaultListModel listModel1 = new DefaultListModel<String>();
        Iterator<Recurso> it = this.so.getRecursos().iterator();
        while (it.hasNext()) {
            listModel1.addElement(it.next()); // toString automatico?
        }
        listaRecursos.setModel(listModel1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaRecursos = new javax.swing.JList<>();
        btnVolver = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCreartRecurso = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        cbSincronica = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingrese Descripción: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 86, 135, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Crear Instrucción");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 10, 197, 34));

        listaRecursos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listaRecursos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 104, 131, 156));

        btnVolver.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnVolver.setText("<-- volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("En caso de ser sinrónica:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 73, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 293, 16, 13));

        btnCreartRecurso.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnCreartRecurso.setText("Crear Recurso");
        btnCreartRecurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreartRecursoActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreartRecurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 159, -1, 37));

        jLabel5.setText("También puede crear ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 117, -1, -1));

        btnCrear.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnCrear.setText("Guardar Instrucción");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 214, -1, 46));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Elija el recurso que utiliza");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 86, -1, -1));

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 105, 135, 155));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("un nuevo recurso...");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(385, 136, 102, -1));

        cbSincronica.setText("Instrucción Sincrónica");
        cbSincronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSincronicaActionPerformed(evt);
            }
        });
        jPanel1.add(cbSincronica, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 266, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        String desc = txtDesc.getText();
        boolean esSinc = cbSincronica.isSelected();

        if (desc.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese una descripción para la instrucción", "Datos inválidos", JOptionPane.ERROR_MESSAGE);
        } else {
            if (esSinc && listaRecursos.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Si desea ingresar una instrución sincrónica debe seleccionar un recurso", "Falta información", JOptionPane.ERROR_MESSAGE);
            } else {
                if (esSinc) {
                    int indRec = listaRecursos.getSelectedIndex();
                    Recurso recurso = so.getRecursos().get(indRec);
                    so.crearInstruccionSincronica(desc, recurso);
                } else {
                    so.crearInstruccionAsincronica(desc);
                }
                listaRecursos.clearSelection();
                txtDesc.setText("");
                cbSincronica.setSelected(false);
                JOptionPane.showMessageDialog(null, "Instrucción creada");
            }
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCreartRecursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreartRecursoActionPerformed
        CrearRecurso cr = new CrearRecurso(this.so);
        cr.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnCreartRecursoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        CrearPrograma cp = new CrearPrograma(this.so);
        cp.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cbSincronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSincronicaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSincronicaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnCreartRecurso;
    private javax.swing.JButton btnVolver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cbSincronica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> listaRecursos;
    private javax.swing.JTextArea txtDesc;
    // End of variables declaration//GEN-END:variables

    SistemaOperativo so;

}
