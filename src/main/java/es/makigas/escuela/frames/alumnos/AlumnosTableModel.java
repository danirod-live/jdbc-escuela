package es.makigas.escuela.frames.alumnos;

import es.makigas.escuela.dao.AlumnoDAO;
import es.makigas.escuela.dao.DAOException;
import es.makigas.escuela.modelo.Alumno;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

class AlumnosTableModel extends AbstractTableModel {
    
    private AlumnoDAO alumnos;
    
    private List<Alumno> datos = new ArrayList<>();
    
    public AlumnosTableModel(AlumnoDAO alumnos) {
        this.alumnos = alumnos;
    }
    
    public void updateModel() throws DAOException {
        datos = alumnos.obtenerTodos();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID";
            case 1: return "Apellidos";
            case 2: return "Nombre";
            case 3: return "Fecha de nacimiento";
            default: return "[no]";
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alumno preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0: return preguntado.getId();
            case 1: return preguntado.getApellidos();
            case 2: return preguntado.getNombre();
            case 3:
                DateFormat df = DateFormat.getDateInstance();
                return df.format(preguntado.getFechaNacimiento());
            default:
                return "";
        }
    }
    
}
