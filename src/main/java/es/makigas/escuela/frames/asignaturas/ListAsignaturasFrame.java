/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.makigas.escuela.frames.asignaturas;

import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.DAOManager;
import es.makigas.escuela.dao.mysql.MySQLDaoManager;
import es.makigas.escuela.frames.profesores.ListProfesoresFrame;
import es.makigas.escuela.modelo.Asignatura;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author danirod
 */
public class ListAsignaturasFrame extends javax.swing.JFrame {

    private DAOManager manager;
    
    private AsignaturasTableModel modelo;
    
    public ListAsignaturasFrame(DAOManager manager) throws DAOException {
        initComponents();
        this.manager = manager;
        this.modelo = new AsignaturasTableModel(manager.getAsignaturaDAO());
        this.tabla.setModel(modelo);
        updateTable();
        this.detalle.setEditable(false);
        this.detalle.setAsignatura(null);
        this.detalle.setModel(new ProfesoresComboModel(manager.getProfesorDAO()));
        activarBotonesCRUD(false);
        activarBotonesGuardar(false);
        
        tabla.getSelectionModel().addListSelectionListener(e -> {
            activarBotonesCRUD(tabla.getSelectedRow() != -1);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        add = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        editar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        detalle = new es.makigas.escuela.frames.asignaturas.DetalleAsignaturaPanel();

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        add.setText("Nuevo");
        add.setBorderPainted(false);
        add.setContentAreaFilled(false);
        add.setFocusable(false);
        add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        toolbar.add(add);
        toolbar.add(jSeparator1);

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        editar.setText("Editar");
        editar.setBorderPainted(false);
        editar.setContentAreaFilled(false);
        editar.setEnabled(false);
        editar.setFocusable(false);
        editar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        toolbar.add(editar);

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        borrar.setText("Borrar");
        borrar.setBorderPainted(false);
        borrar.setContentAreaFilled(false);
        borrar.setEnabled(false);
        borrar.setFocusable(false);
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        toolbar.add(borrar);
        toolbar.add(jSeparator2);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/continue.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setBorderPainted(false);
        guardar.setContentAreaFilled(false);
        guardar.setEnabled(false);
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        toolbar.add(guardar);

        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setBorderPainted(false);
        cancelar.setContentAreaFilled(false);
        cancelar.setEnabled(false);
        cancelar.setFocusable(false);
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        toolbar.add(cancelar);

        getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel1.add(detalle, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        activarBotonesGuardar(true);
        detalle.setEditable(true);
        detalle.setAsignatura(null);
        detalle.loadData();
    }//GEN-LAST:event_addActionPerformed

    Asignatura obtenerAsignaturaSeleccionada() throws DAOException {
        long identificador = (long) tabla.getValueAt(tabla.getSelectedRow(), 0);
        return manager.getAsignaturaDAO().obtener(identificador);
    }
    
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        try {
            activarBotonesGuardar(true);
            detalle.setEditable(true);
            detalle.setAsignatura(obtenerAsignaturaSeleccionada());
            detalle.loadData();
        } catch (DAOException ex) {
            Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "¿Seguro que quieres borrar este asignatura?",
                "Borrar asignatura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Asignatura asignatura = obtenerAsignaturaSeleccionada();
                manager.getAsignaturaDAO().eliminar(asignatura);
                updateTable();
                activarBotonesCRUD(false);
            } catch (DAOException ex) {
                Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_borrarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try {
            detalle.saveData();
            Asignatura asignatura = detalle.getAsignatura();
            if (asignatura.getId() != null) {
                manager.getAsignaturaDAO().modificar(asignatura);
            } else {
                manager.getAsignaturaDAO().insertar(asignatura);
            }
            detalle.setAsignatura(null);
            detalle.setEditable(false);
            detalle.loadData();
            updateTable();
            activarBotonesCRUD(false);
            activarBotonesGuardar(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        detalle.setAsignatura(null);
        detalle.setEditable(false);
        detalle.loadData();
        activarBotonesCRUD(false);
        activarBotonesGuardar(false);
        tabla.clearSelection();
    }//GEN-LAST:event_cancelarActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MySQLDaoManager manager = new MySQLDaoManager("localhost", "ejemplo", "ejemplo", "escuela");
                    new ListAsignaturasFrame(manager).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ListProfesoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DAOException ex) {
                    Logger.getLogger(ListAsignaturasFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    void activarBotonesCRUD(boolean activo) {
        this.borrar.setEnabled(activo);
        this.editar.setEnabled(activo);
    }
    
    void activarBotonesGuardar(boolean activo) {
        this.guardar.setEnabled(activo);
        this.cancelar.setEnabled(activo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton borrar;
    private javax.swing.JButton cancelar;
    private es.makigas.escuela.frames.asignaturas.DetalleAsignaturaPanel detalle;
    private javax.swing.JButton editar;
    private javax.swing.JButton guardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JTable tabla;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

    private void updateTable() throws DAOException {
        this.modelo.update();
        this.modelo.fireTableDataChanged();
    }
}
