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
package es.makigas.escuela.modelo;

import java.util.Objects;

public class Matricula {
    
    public class IdMatricula {
        
        private long alumno;
        
        private long asignatura;
        
        private int year;

        public IdMatricula(long alumno, long asignatura, int year) {
            this.alumno = alumno;
            this.asignatura = asignatura;
            this.year = year;
        }

        public long getAlumno() {
            return alumno;
        }

        public void setAlumno(long alumno) {
            this.alumno = alumno;
        }

        public long getAsignatura() {
            return asignatura;
        }

        public void setAsignatura(long asignatura) {
            this.asignatura = asignatura;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "IdMatricula{" + "alumno=" + alumno + ", asignatura=" + asignatura + ", year=" + year + '}';
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 53 * hash + (int) (this.alumno ^ (this.alumno >>> 32));
            hash = 53 * hash + (int) (this.asignatura ^ (this.asignatura >>> 32));
            hash = 53 * hash + this.year;
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
            final IdMatricula other = (IdMatricula) obj;
            if (this.alumno != other.alumno) {
                return false;
            }
            if (this.asignatura != other.asignatura) {
                return false;
            }
            if (this.year != other.year) {
                return false;
            }
            return true;
        }
        
    }
    
    private IdMatricula id;
    
    private Integer nota = null;

    public Matricula(IdMatricula id) {
        this.id = id;
    }
    
    public Matricula(long alumno, long asignatura, int year) {
        this.id = new IdMatricula(alumno, asignatura, year);
    }

    public IdMatricula getID() {
        return id;
    }
    
    public void setIdMatricula(IdMatricula id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nota);
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
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", nota=" + nota + '}';
    }
}
