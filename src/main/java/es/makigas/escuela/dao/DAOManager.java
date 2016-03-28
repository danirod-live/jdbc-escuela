package es.makigas.escuela.dao;

public interface DAOManager {
    
    AlumnoDAO getAlumnoDAO();
    
    AsignaturaDAO getAsignaturaDAO();
    
    MatriculaDAO getMatriculaDAO();
    
    ProfesorDAO getProfesorDAO();
    
}
