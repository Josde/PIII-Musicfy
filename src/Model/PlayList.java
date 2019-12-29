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

import java.io.Serializable;
import static java.lang.String.format;
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class PlayList implements Serializable {

    private String nombre;
    private ArrayList<Song> canciones = new ArrayList<Song>();

    /**
     *
     * @param nombrePlaylist
     */
    public PlayList(String nombrePlaylist) {
        this.nombre = nombrePlaylist;
        this.canciones = new ArrayList<Song>();
    }

    /**
     *
     */
    public PlayList() {
        this.canciones = new ArrayList<Song>();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    /**
     *
     * @param canciones
     */
    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        String ret;
        StringBuilder sb = new StringBuilder();
        for (Song s : this.canciones) {
            sb.append(s.toString());
            sb.append("\n");
        }
        ret = format("%s\n---------------------\n%s\n", this.nombre, sb);
        return ret;
    }

    /**
     *
     * @param s
     */
    public void anadirCancion(Song s) {
        if (s != null) {
            this.canciones.add(s);
        }
    }

    /**
     *
     * @param s
     */
    public void borrarCancion(Song s) {
        this.canciones.remove(s);
    }

}
