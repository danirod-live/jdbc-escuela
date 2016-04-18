package es.makigas.escuela.frames.asignaturas;

import es.makigas.escuela.modelo.Profesor;

public class ProfesorComboView {
    
    private Profesor profesor;

    public ProfesorComboView(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return profesor.getApellidos() + " " + profesor.getNombre();
    }
    
}
