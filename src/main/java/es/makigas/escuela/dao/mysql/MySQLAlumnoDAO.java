package es.makigas.escuela.dao.mysql;

import es.makigas.escuela.dao.AlumnoDAO;
import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.modelo.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLAlumnoDAO implements AlumnoDAO {

    final String INSERT = "INSERT INTO alumnos(nombre, apellidos, fecha_nac) VALUES(?, ?, ?)";
    final String UPDATE = "UPDATE alumnos SET nombre = ?, apellidos = ?, fecha_nac = ? WHERE id_alumno = ?";
    final String DELETE = "DELETE FROM alumnos WHERE id_alumno = ?";
    final String GETALL = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos";
    final String GETONE = "SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos WHERE id_alumno = ?";
    
    private Connection conn;
    
    public MySQLAlumnoDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insertar(Alumno a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getApellidos());
            stat.setDate(3, new Date(a.getFechaNacimiento().getTime()));
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado.");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getLong(1));
            } else {
                throw new DAOException("No puedo asignar ID a este alumno.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
    }

    @Override
    public void modificar(Alumno a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getApellidos());
            stat.setDate(3, new Date(a.getFechaNacimiento().getTime()));
            stat.setLong(4, a.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya modificado el modelo.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }

    @Override
    public void eliminar(Alumno a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que el alumno no se haya borrado.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }
    
    private Alumno convertir(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        Date fechaNac = rs.getDate("fecha_nac");
        Alumno alumno = new Alumno(nombre, apellidos, fechaNac);
        alumno.setId(rs.getLong("id_alumno"));
        return alumno;
    }
    
    @Override
    public List<Alumno> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Alumno> alumnos = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                alumnos.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return alumnos;
    }

    @Override
    public Alumno obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Alumno a = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                a = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado ese registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return a;
    }
}
