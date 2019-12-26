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
package Controller;

import Model.Album;
import Model.Artist;
import Model.Model;
import Model.PlayList;
import Model.Song;
import Other.ComparadorSong;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Controller {
    Model m = new Model();

    public void sortCanciones() {
        m.sortCanciones();
    }

    public ArrayList<Song> getCanciones() {
        return m.getMu().getCanciones();
    }

    public void pedirImportacion() {
        m.importFromDisk();
    }

    public Album obtenerAlbumPorNombre(String opcion) {
        for (Album a: m.getMu().getAlbumes()) {
            if (a.getTitulo().toLowerCase().equals(opcion.toLowerCase())) {
                return a;
            } 
        }
        return null;
    }

    public Artist obtenerArtistaPorNombre(String opcion) {
        for (Artist a: m.getMu().getArtistas()) {
            if (a.getNombre().toLowerCase().equals(opcion.toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    public PlayList generarPlaylistAleatoria(String titulo, int numCanciones) {
        PlayList plTemp = new PlayList();
        ArrayList<Song> songTmp = this.m.getMu().getCanciones();
        Random r = new Random();
        plTemp.setNombre(titulo);
        for (int i = 0; i < numCanciones; i++) {
            plTemp.anadirCancion(songTmp.get(r.nextInt(songTmp.size())));
        }
        this.m.anadirPlaylist(plTemp);
        return plTemp;
    }

    public PlayList obtenerPlaylistPorNombre(String titulo) {
        for (PlayList p: this.m.getMu().getPlaylists()) {
            if (p.getNombre().toLowerCase().equals(titulo.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    public int borrarCancionPlaylist(PlayList plTemp, String nombreCancion) {
        for (Song s: plTemp.getCanciones()) {
            if (s.getTitulo().toLowerCase().equals(nombreCancion.toLowerCase())) {
                plTemp.borrarCancion(s);
                return 1;
            }
        }
        return 0;
    }

    public Song obtenerCancionPorNombre(String nombreCancion) {
        for (Song s: this.m.getMu().getCanciones()) {
            if (s.getTitulo().toLowerCase().equals(nombreCancion.toLowerCase())) {
                return s;
            }
        }
        return null;
    }

    public void anadirCancionAPlaylist(PlayList plTemp, Song songTmp) {
        //TODO: Posiblemente rehacer esto.
        for (PlayList p: this.m.getMu().getPlaylists()) {
            if (p.equals(plTemp)) {
                p.anadirCancion(songTmp);
            }
        }
    }

    public void pedirExportacionArtistas() {
        m.exportarArtistas();
    }


}
