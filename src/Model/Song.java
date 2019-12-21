/* 
 * Copyright (C) 2019 jorgecruz@usal.es
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Model;

import static java.lang.String.format;
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Song {
    private String titulo;
    private int anno;
    private int duracion;
    private ArrayList<Artist> interpretes;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public ArrayList<Artist> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(ArrayList<Artist> interpretes) {
        this.interpretes = interpretes;
    }

    @Override
    public String toString() {
        String ret;
        StringBuilder sb = new StringBuilder();
        for (Artist a: this.interpretes) {
            sb.append(a.getNombre());
            if (this.interpretes.indexOf(a) != this.interpretes.size() - 1) {
                sb.append(", "); //Ponemos una coma, si no es el último cantante.
            }
        }
        ret = format("%s - %s | %s | %s", sb, this.titulo, this.duracion, this.titulo);
        return ret;
    }
    
    
}
