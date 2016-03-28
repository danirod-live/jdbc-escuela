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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Utilidades comunes para trabajar con MySQL.
 *
 * @author danirod
 */
class MySQLUtils {

    /**
     * Cierra los recursos empleados durante una conexión con la base de datos.
     *
     * @param stat consulta iniciada ante la base de datos.
     * @throws DAOException en caso de problemas durante el cierre.
     */
    public static void cerrar(PreparedStatement stat) throws DAOException {
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar el recurso SQL", ex);
            }
        }
    }

    /**
     * Cierra los recursos empleados durante una conexión con la base de datos.
     *
     * @param stat consulta iniciada ante la base de datos.
     * @param rs cursor para acceder a los datos de la consulta.
     * @throws DAOException en caso de problemas durante el cierre.
     */
    public static void cerrar(PreparedStatement stat, ResultSet rs) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar el recurso SQL", ex);
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar el recurso SQL", ex);
            }
        }
    }

}
