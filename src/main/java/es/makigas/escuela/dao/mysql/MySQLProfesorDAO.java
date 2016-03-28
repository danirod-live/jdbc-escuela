/*
 *  aplicacion-jdbc: Ejemplo de uso de JDBC y Java
 *  Copyright (C) 2016  Dani Rodríguez <danirod@outlook.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.makigas.escuela.dao.mysql;

import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.dao.ProfesorDAO;
import es.makigas.escuela.modelo.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pensado para interactuar con la tabla de Profesores en MySQL.
 * 
 * @author danirod
 */
public class MySQLProfesorDAO implements ProfesorDAO {

    final static String INSERT = "INSERT INTO profesores(nombre, apellidos) VALUES(?, ?)";
    final static String UPDATE = "UPDATE profesores SET nombre = ?, apellidos = ? WHERE id_profesor = ?";
    final static String DELETE = "DELETE FROM profesores WHERE id_profesor = ?";
    final static String GETALL = "SELECT id_profesor, nombre, apellidos FROM profesores";
    final static String GETONE = GETALL + " WHERE id_profesor = ?";
    
    private Connection conn;
    
    public MySQLProfesorDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void insertar(Profesor p) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, p.getNombre());
            stat.setString(2, p.getApellidos());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya insertado ningún registro.");
            }
            
            /* Insertamos las claves. */
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getLong(1));
            } else {
                throw new DAOException("No se puede obtener la clave del profesor.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
    }

    @Override
    public void modificar(Profesor p) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, p.getNombre());
            stat.setString(2, p.getApellidos());
            stat.setLong(3, p.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya modificado ningún registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }

    @Override
    public void eliminar(Profesor p) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, p.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya eliminado ningún registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat);
        }
    }
    
    Profesor convertir(ResultSet rs) throws SQLException {
        // Extraemos los datos.
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        long id = rs.getLong("id_profesor");
        // Generamos el profesor.
        Profesor p = new Profesor(nombre, apellidos);
        p.setId(id);
        return p;
    }

    @Override
    public List<Profesor> obtenerTodos() throws DAOException {
        List<Profesor> profesores = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                profesores.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return profesores;
    }

    @Override
    public Profesor obtener(Long id) throws DAOException {
        Profesor profesor = null;
        ResultSet rs = null;
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                profesor = convertir(rs);
            } else {
                throw new DAOException("No hay ningún registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL", ex);
        } finally {
            MySQLUtils.cerrar(stat, rs);
        }
        return profesor;
    }
    
}
