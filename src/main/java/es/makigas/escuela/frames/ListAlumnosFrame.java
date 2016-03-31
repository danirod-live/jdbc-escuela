package es.makigas.escuela.frames;

import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.DAOManager;
import es.makigas.escuela.dao.mysql.MySQLDaoManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListAlumnosFrame extends javax.swing.JFrame {

    private DAOManager manager;
    
    private AlumnosTableModel model;
    
    public ListAlumnosFrame(DAOManager manager) throws DAOException {
        initComponents();
        this.manager = manager;
        this.model = new AlumnosTableModel(manager.getAlumnoDAO());
        this.model.updateModel();
        this.tabla.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolbar.setRollover(true);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(jButton1);

        getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

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

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws SQLException {
        DAOManager manager = new MySQLDaoManager("localhost", "ejemplo", "ejemplo", "escuela");
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new ListAlumnosFrame(manager).setVisible(true);
            } catch (DAOException ex) {
                Logger.getLogger(ListAlumnosFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables
}
