package es.makigas.escuela.util;

import es.makigas.escuela.modelo.Profesor;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.profesor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProfesorComboView other = (ProfesorComboView) obj;
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        return true;
    }

    
    
}
