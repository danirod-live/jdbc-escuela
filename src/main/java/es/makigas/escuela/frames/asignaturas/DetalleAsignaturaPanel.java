
package es.makigas.escuela.frames.asignaturas;

import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.ProfesorDAO;
import es.makigas.escuela.modelo.Asignatura;
import es.makigas.escuela.modelo.Profesor;

public class DetalleAsignaturaPanel extends javax.swing.JPanel {

    private boolean editable;
    
    private Asignatura asignatura;
    
    private ProfesoresComboModel model;
    
    public DetalleAsignaturaPanel() {
        initComponents();
        model = new ProfesoresComboModel(null);
    }
    
    public DetalleAsignaturaPanel(ProfesorDAO dao) throws DAOException {
        initComponents();
        model = new ProfesoresComboModel(dao);
        model.update();
        profesor.setModel(model);
    }

    public ProfesoresComboModel getModel() {
        return model;
    }

    public void setModel(ProfesoresComboModel model) throws DAOException {
        this.model = model;
        profesor.setModel(model);
        model.update();
    }
    
    public void setAsignatura(Asignatura a) {
        this.asignatura = a;
    }
    
    public Asignatura getAsignatura() {
        return asignatura;
    }
    
    public void setEditable(boolean editable) {
        this.editable = editable;
        this.nombre.setEditable(editable);
        this.profesor.setEnabled(editable);
    }
    
    public boolean isEditable() {
        return editable;
    }
    
    public void loadData() {
        if (asignatura == null) {
            asignatura = new Asignatura("", null);
        }
        nombre.setText(asignatura.getNombre());
        nombre.requestFocus();
    }
    
    public void saveData() {
        if (asignatura == null) {
            asignatura = new Asignatura("", null);
        }
        asignatura.setNombre(nombre.getText());
        ProfesorComboView pcv = (ProfesorComboView) profesor.getSelectedItem();
        Profesor profesor = pcv.getProfesor();
        asignatura.setIdProfesor(profesor.getId());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        profesor = new javax.swing.JComboBox<>();

        jLabel1.setText("Asignatura:");

        jLabel2.setText("Profesor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre)
                    .addComponent(profesor, 0, 298, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(profesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JComboBox<ProfesorComboView> profesor;
    // End of variables declaration//GEN-END:variables
}
