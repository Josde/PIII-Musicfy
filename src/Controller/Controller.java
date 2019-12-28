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
import Other.Auxiliar;
import Other.ComparadorSong;
import Other.Constants;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Controller {
    Model m = new Model();

    /**
     *
     */
    public void sortCanciones() {
        m.sortCanciones();
    }

    /**
     *
     * @return
     */
    public ArrayList<Song> getCanciones() {
        return m.getMu().getCanciones();
    }

    /**
     *
     */
    public void pedirImportacion() {
        m.importFromDisk();
    }

    /**
     *
     * @param opcion
     * @return
     */
    public Album obtenerAlbumPorNombre(String opcion) {
        for (Album a: m.getMu().getAlbumes()) {
            if (a.getTitulo().toLowerCase().equals(opcion.toLowerCase())) {
                return a;
            } 
        }
        return null;
    }

    /**
     *
     * @param opcion
     * @return
     */
    public Artist obtenerArtistaPorNombre(String opcion) {
        for (Artist a: m.getMu().getArtistas()) {
            if (a.getNombre().toLowerCase().equals(opcion.toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    /**
     *
     * @param titulo
     * @param numCanciones
     * @return
     */
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

    /**
     *
     * @param titulo
     * @return
     */
    public PlayList obtenerPlaylistPorNombre(String titulo) {
        for (PlayList p: this.m.getMu().getPlaylists()) {
            if (p.getNombre().toLowerCase().equals(titulo.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param plTemp
     * @param nombreCancion
     * @return
     */
    public int borrarCancionPlaylist(PlayList plTemp, String nombreCancion) {
        for (Song s: plTemp.getCanciones()) {
            if (s.getTitulo().toLowerCase().equals(nombreCancion.toLowerCase())) {
                plTemp.borrarCancion(s);
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param nombreCancion
     * @return
     */
    public Song obtenerCancionPorNombre(String nombreCancion) {
        for (Song s: this.m.getMu().getCanciones()) {
            if (s.getTitulo().toLowerCase().equals(nombreCancion.toLowerCase())) {
                return s;
            }
        }
        return null;
    }

    /**
     *
     * @param plTemp
     * @param songTmp
     * @return
     */
    public boolean anadirCancionAPlaylist(PlayList plTemp, Song songTmp) {
        //TODO: Posiblemente rehacer esto.
        if (plTemp != null && songTmp != null) {
            for (PlayList p: this.m.getMu().getPlaylists()) {
                if (p.equals(plTemp)) {
                    p.anadirCancion(songTmp);
                }
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean pedirExportacionArtistas() {
        return m.exportarArtistas();
    }

    /**
     *
     */
    public void pedirSerializacion() {
        if (Constants.RUTA_MUSICFY_BIN.toFile().exists()) {
            m.serializarMusicfy(Constants.RUTA_MUSICFY_BIN.toFile());
        } else {
            try {
                Files.createDirectories(Constants.RUTA_MUSICFY_BIN.getParent());
                Files.createFile(Constants.RUTA_MUSICFY_BIN);
                m.serializarMusicfy(Constants.RUTA_MUSICFY_BIN.toFile());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param a
     * @return
     */
    public boolean anadirArtistaAModelo(Artist a) {
        if (a != null) {
            m.anadirArtista(a);
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param a
     * @return
     */
    public boolean borrarArtista(Artist a) {
        if (!a.getAlbumes().isEmpty()) {
            return false;
        } else {
            m.borrarArtista(a);
            return true;
        }
    }

    /**
     *
     * @param a
     * @param nuevoValor
     * @param opcion
     * @return
     */
    public boolean cambiarAtributoArtista(Artist a, String nuevoValor, String opcion) {
        if (a != null) {
            switch(opcion) {
                case "1":
                    a.setBiografia(nuevoValor);
                    return true;
                case "2":
                    a.setInstagram(nuevoValor);
                    return true;
                case "3":
                    a.setTwitter(nuevoValor);
                    return true;
                case "4":
                    a.setFacebook(nuevoValor);
                    return true;
                case "5":
                    a.setWikipedia(nuevoValor);
                    return true;
                default: //Opción inválida.
                    return false;
            }
        }
        return false;
    }

    /**
     *
     * @param a
     * @return
     */
    public boolean anadirAlbumAModelo(Album a) {
        if (a != null) {
            m.anadirAlbum(a);
            return true;
        }
        return false;
    }

    /**
     *
     * @param so
     * @return
     */
    public boolean anadirCancionAModelo(Song so) {
        if (so != null) {
            m.anadirCancion(so);
            return true;
        }
        return false;
    }

    /**
     *
     * @param a
     * @param ar
     * @return
     */
    public boolean borrarAlbumDeArtista(Album a, Artist ar) {
        if (a != null && ar != null) {
            ar.getAlbumes().remove(a.getTitulo());
            return true;
        }
        return false;
    }

    /**
     *
     * @param s
     * @return
     */
    public boolean borrarCancionDeModelo(Song s) {
        if (s != null) {
            m.borrarCancion(s);
            return true;
        }
        return false;
    }

    /**
     *
     * @param a
     * @return
     */
    public boolean borrarAlbumDeModelo(Album a) {
        if (a != null) {
            m.borrarAlbum(a);
            return true;
        }
        return false;
    }

    /**
     *
     * @param a
     * @param nuevoValor
     * @param opcion
     * @return
     */
    public boolean cambiarAtributoAlbum(Album a, String nuevoValor, String opcion) {
        if (a != null) {
            switch(opcion) {
                case "1":
                    a.setTitulo(nuevoValor);
                    return true;
                case "2":
                    a.setAnno(Integer.valueOf(nuevoValor));
                    return true;
                default:
                    return false;
            } 
        } 
        return false;
    }

    /**
     *
     */
    public void vaciarColecciones() {
        m.vaciarColecciones();
    }

    /**
     *
     * @param numArtistas
     * @param numAlbumes
     * @param numCanciones
     * @param numPlaylists
     * @return
     */
    public boolean generarAleatoriamente(int numArtistas, int numAlbumes, int numCanciones, int numPlaylists) {
        ArrayList<Album> albumesTmp = new ArrayList<Album>();
        ArrayList<Artist> artistasTmp = new ArrayList<Artist>();
        ArrayList<Song> cancionesTmp = new ArrayList<Song>();
        ArrayList<PlayList> playlistsTmp = new ArrayList<PlayList>();
        Random r = new Random();
        if (Constants.RUTA_NOMBRES_ALBUMES.toFile().exists() &&
            Constants.RUTA_NOMBRES_ARTISTAS.toFile().exists() &&
            Constants.RUTA_NOMBRES_CANCIONES.toFile().exists() &&
            Constants.RUTA_NOMBRES_PLAYLISTS.toFile().exists()) {
            try { 
                String nombreTmp;
                String[] nombresAlbumes = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_ALBUMES);
                String[] nombresArtistas = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_ARTISTAS);
                String[] nombresCanciones = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_CANCIONES);
                String[] nombresPlaylists = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_PLAYLISTS);
                if (nombresAlbumes == null || nombresArtistas == null ||
                    nombresCanciones == null || nombresPlaylists == null) {
                    return false;
                }
                for (int i = 0; i < numArtistas; i++) {
                    do {
                        nombreTmp = nombresArtistas[r.nextInt(nombresArtistas.length)];
                    } while (nombreTmp.isBlank());
                    artistasTmp.add(new Artist(nombreTmp));
                }
                for (int i = 0; i < numCanciones; i++) {
                    do {
                        nombreTmp = nombresCanciones[r.nextInt(nombresCanciones.length)];
                    } while (nombreTmp.isBlank());
                    cancionesTmp.add(new Song(nombreTmp));
                }
                for (int i = 0; i < numAlbumes; i++) {
                    do {
                        nombreTmp = nombresAlbumes[r.nextInt(nombresAlbumes.length)];
                    } while (nombreTmp.isBlank());
                    albumesTmp.add(new Album(nombreTmp));
                }
                for (int i = 0; i < numPlaylists; i++) {
                    do {
                        nombreTmp = nombresPlaylists[r.nextInt(nombresPlaylists.length)];
                    } while (nombreTmp.isBlank());
                    playlistsTmp.add(new PlayList(nombreTmp));
                }
                m.correlacionarDatosYActualizarModelo(albumesTmp, cancionesTmp, artistasTmp, playlistsTmp);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        return false;
    }

    /**
     *
     * @return
     */
    public Iterable<Album> obtenerAlbumes() {
        return m.getMu().getAlbumes();
    }

    /**
     *
     * @return
     */
    public Iterable<Artist> obtenerArtistas() {
        return m.getMu().getArtistas();
    }

    /**
     *
     * @return
     */
    public Iterable<PlayList> obtenerPlaylists() {
        return m.getMu().getPlaylists();
    }

    /**
     *
     * @return
     */
    public boolean pedirExportacionAlbumes() {
        return m.exportarAlbumes();
    }


}
