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
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es (Jorge Cruz García, DNI: 21740040A)
 */
public class Musicfy implements Serializable {

    private ArrayList<Song> canciones = new ArrayList<Song>();
    private ArrayList<Album> albumes = new ArrayList<Album>();
    private ArrayList<Artist> artistas = new ArrayList<Artist>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();

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

    /**
     *
     * @return
     */
    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    /**
     *
     * @param albumes
     */
    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    /**
     *
     * @return
     */
    public ArrayList<Artist> getArtistas() {
        return artistas;
    }

    /**
     *
     * @param artistas
     */
    public void setArtistas(ArrayList<Artist> artistas) {
        this.artistas = artistas;
    }

    /**
     *
     * @return
     */
    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    /**
     *
     * @param playlists
     */
    public void setPlaylists(ArrayList<PlayList> playlists) {
        this.playlists = playlists;
    }

    void anadirCanciones(ArrayList<Song> canciones) {
        for (Song s : canciones) {
            this.canciones.add(s);
        }
    }

}
