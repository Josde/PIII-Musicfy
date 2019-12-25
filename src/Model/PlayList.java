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

import java.util.ArrayList;
import static java.lang.String.format;
/**
 *
 * @author jorgecruz@usal.es
 */
public class PlayList {
    private String nombre;
    private ArrayList<Song> canciones = new ArrayList<Song>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        String ret;
        StringBuilder sb = new StringBuilder();
        for (Song s: this.canciones) {
            sb.append(s.toString());
            sb.append("\n");
        }
        ret = format("%s\n---------------------\n%s\n", this.nombre, sb);
        return ret;
    }

    public void anadirCancion(Song s) {
        if (s != null) {
            this.canciones.add(s);
        }
    }

    public void borrarCancion(Song s) {
        this.canciones.remove(s);
    }
    
    
}