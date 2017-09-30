package es.makigas.escuela.dao.mysql;

import es.makigas.escuela.dao.AsignaturaDAO;
import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.modelo.Asignatura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLAsignaturaDAO implements AsignaturaDAO {

    private Connection conn;
    
    final String INSERT = "INSERT INTO asignaturas(nombre, profesor) VALUES(?, ?)";
    final String UPDATE = "UPDATE asignaturas SET nombre = ?, profesor = ? WHERE id_asignatura = ?";
    final String DELETE = "DELETE FROM asignaturas WHERE id_asignatura = ?";
    final String GETALL = "SELECT id_asignatura, nombre, profesor FROM asignaturas";
    final String GETONE = GETALL + " WHERE id_asignatura = ?";
    
    MySQLAsignaturaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Asignatura a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getNombre());
            stat.setLong(2, a.getIdProfesor());
            
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya insertado ningún registro.");
            }
            
            /* Obtener las claves. */
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getLong(1));
            } else {
                throw new DAOException("Error al generar el ID de la asignatura.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
    }

    @Override
    public void modificar(Asignatura a) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getNombre());
            stat.setLong(2, a.getIdProfesor());
            stat.setLong(3, a.getId());
            
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
    public void eliminar(Asignatura a) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getId());
            
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya eliminado ningún registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }
    
    Asignatura convertir(ResultSet rs) throws SQLException {
        long id = rs.getLong("id_asignatura");
        String nombre = rs.getString("nombre");
        long idProfesor = rs.getLong("profesor");
        
        Asignatura a = new Asignatura(nombre, idProfesor);
        a.setId(id);
        return a;
    }

    @Override
    public List<Asignatura> obtenerTodos() throws DAOException {
        List<Asignatura> asignaturas = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                asignaturas.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        
        return asignaturas;
    }

    @Override
    public Asignatura obtener(Long id) throws DAOException {
        Asignatura asignatura = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                asignatura = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado este registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return asignatura;
    }
    
}
