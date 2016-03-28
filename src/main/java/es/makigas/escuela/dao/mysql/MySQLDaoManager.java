package es.makigas.escuela.dao.mysql;

import es.makigas.escuela.dao.AlumnoDAO;
import es.makigas.escuela.dao.AsignaturaDAO;
import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.DAOManager;
import es.makigas.escuela.dao.MatriculaDAO;
import es.makigas.escuela.dao.ProfesorDAO;
import es.makigas.escuela.modelo.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySQLDaoManager implements DAOManager {
    
    private Connection conn;
    
    private AlumnoDAO alumnos = null;
    private ProfesorDAO profesores = null;
    private MatriculaDAO matriculas = null;
    private AsignaturaDAO asignaturas = null;
    
    public MySQLDaoManager(String host, String username, String password, String database) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
    }

    @Override
    public AlumnoDAO getAlumnoDAO() {
        if (alumnos == null) {
            alumnos = new MySQLAlumnoDAO(conn);
        }
        return alumnos;
    }

    @Override
    public AsignaturaDAO getAsignaturaDAO() {
        if (asignaturas == null) {
            asignaturas = new MySQLAsignaturaDAO(conn);
        }
        return asignaturas;
    }

    @Override
    public MatriculaDAO getMatriculaDAO() {
        if (matriculas == null) {
            matriculas = new MySQLMatriculaDAO(conn);
        }
        return matriculas;
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        if (profesores == null) {
            profesores = new MySQLProfesorDAO(conn);
        }
        return profesores;
    }
}
