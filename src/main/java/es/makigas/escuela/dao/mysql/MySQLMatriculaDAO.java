package es.makigas.escuela.dao.mysql;

import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.MatriculaDAO;
import es.makigas.escuela.modelo.Matricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MySQLMatriculaDAO implements MatriculaDAO {

    static final String INSERT = "INSERT INTO matriculas(alumno, asignatura, fecha, nota) VALUES(?, ?, ?, ?)";
    static final String UPDATE = "UPDATE matriculas SET nota = ? WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    static final String DELETE = "DELETE FROM matriculas WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    static final String GETALL = "SELECT alumno, asignatura, fecha, nota FROM matriculas";
    static final String GETONE = GETALL + " WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    static final String GETALU = GETALL + " WHERE alumno = ?";
    static final String GETCUR = GETALL + " WHERE fecha = ?";
    static final String GETASI = GETALL + " WHERE asignatura = ?";
    
    private Connection conn;
    
    public MySQLMatriculaDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insertar(Matricula a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setLong(1, a.getID().getAlumno());
            stat.setLong(2, a.getID().getAsignatura());
            stat.setInt(3, a.getID().getYear());
            if (a.getNota() != null) {
                stat.setInt(4, a.getNota());
            } else {
                stat.setNull(4, Types.INTEGER);
            }
            
            /* Hacemos la inserción. */
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se hayan insertado registros.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }

    @Override
    public void modificar(Matricula a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            if (a.getNota() != null) {
                stat.setInt(1, a.getNota());
            } else {
                stat.setNull(1, Types.INTEGER);
            }
            stat.setLong(2, a.getID().getAlumno());
            stat.setLong(3, a.getID().getAsignatura());
            stat.setInt(4, a.getID().getYear());
            
            /* Hacemos el update. */
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya actualizado ningún registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }

    @Override
    public void eliminar(Matricula a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getID().getAlumno());
            stat.setLong(2, a.getID().getAsignatura());
            stat.setInt(3, a.getID().getYear());
            
            /* Hacemos el delete. */
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya borrado ningún registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }
    
    Matricula convertir(ResultSet rs) throws SQLException {
        /* Extracción. */
        long alumno = rs.getLong("alumno");
        long asignatura = rs.getLong("asignatura");
        int year = rs.getInt("fecha");
        
        /* Nota es distinto porque puede ser null. */
        Integer nota = rs.getInt("nota");
        if (rs.wasNull()) nota = null;
        
        /* Matrícula. */
        Matricula mat = new Matricula(alumno, asignatura, year);
        mat.setNota(nota);
        return mat;
    }

    @Override
    public List<Matricula> obtenerTodos() throws DAOException {
        List<Matricula> matriculas = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                matriculas.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return matriculas;
    }

    @Override
    public Matricula obtener(Matricula.IdMatricula id) throws DAOException {
        Matricula matricula = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id.getAlumno());
            stat.setLong(2, id.getAsignatura());
            stat.setInt(3, id.getYear());
            rs = stat.executeQuery();
            if (rs.next()) {
                matricula = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado ese registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return matricula;
    }

    @Override
    public List<Matricula> obtenerPorAlumno(long alumno) throws DAOException {
        List<Matricula> matriculas = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(GETALU);
            stat.setLong(1, alumno);
            rs = stat.executeQuery();
            while (rs.next()) {
                matriculas.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return matriculas;
    }

    @Override
    public List<Matricula> obtenerPorAsignatura(long asignatura) throws DAOException {
        List<Matricula> matriculas = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(GETASI);
            stat.setLong(1, asignatura);
            rs = stat.executeQuery();
            while (rs.next()) {
                matriculas.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return matriculas;
    }

    @Override
    public List<Matricula> obtenerPorCurso(int curso) throws DAOException {
        List<Matricula> matriculas = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(GETCUR);
            stat.setInt(1, curso);
            rs = stat.executeQuery();
            while (rs.next()) {
                matriculas.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return matriculas;
    }

    
    
}