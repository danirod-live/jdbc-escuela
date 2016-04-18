package es.makigas.escuela.frames.asignaturas;

import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.ProfesorDAO;
import es.makigas.escuela.modelo.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ProfesoresComboModel extends DefaultComboBoxModel<ProfesorComboView> {
    
    private ProfesorDAO profesores;
    
    private List<Profesor> lista;
    
    public ProfesoresComboModel(ProfesorDAO profesores) {
        this.profesores = profesores;
        this.lista = new ArrayList<>();
    }
    
    public void update() throws DAOException {
        if (profesores != null) {
            lista = profesores.obtenerTodos();
            for (Profesor p : lista) {
                addElement(new ProfesorComboView(p));
            }
        }
    }
    
    
}
