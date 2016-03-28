package es.makigas.escuela.dao;

import es.makigas.escuela.modelo.Matricula;
import java.util.List;

public interface MatriculaDAO extends DAO<Matricula, Matricula.IdMatricula> {
    
    List<Matricula> obtenerPorAlumno(long alumno) throws DAOException;
    
    List<Matricula> obtenerPorAsignatura(long asignatura) throws DAOException;
    
    List<Matricula> obtenerPorCurso(int curso) throws DAOException;
    
}
