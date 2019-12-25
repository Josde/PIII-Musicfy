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

import java.io.FileOutputStream;
import java.util.ArrayList;
import Other.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Musicfy implements java.io.Serializable {
    private ArrayList<Song> canciones = new ArrayList<Song>();
    private ArrayList<Album> albumes = new ArrayList<Album>();
    private ArrayList<Artist> artistas = new ArrayList<Artist>();
    private ArrayList<PlayList> playlists = new ArrayList<PlayList>();

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    public ArrayList<Artist> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artist> artistas) {
        this.artistas = artistas;
    }

    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlayList> playlists) {
        this.playlists = playlists;
    }

    void anadirCanciones(ArrayList<Song> canciones) {
        for (Song s: canciones) {
            this.canciones.add(s);
        }
    }
    
}
