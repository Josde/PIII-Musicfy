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
import Other.Constants;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jorgecruz@usal.es (Jorge Cruz García, DNI: 21740040A)
 */
public class Controller {

    Model m = new Model();
    /**
     * Ordena las canciones del modelo, por el orden establecido en el
     * comparador de canciones.
     */
    public void sortCanciones() {
        m.sortCanciones();
    }

    /**
     * Devuelve todas las canciones del modelo.
     * @return devuelve todas las canciones del modelo
     */
    public ArrayList<Song> getCanciones() {
        return m.getMu().getCanciones();
    }

    /**
     * Pide al model importar los datos del disco duro. El modelo se encarga de
     * todo.
     */
    public int pedirImportacion() {
        return m.importFromDisk();
    }

    /**
     * Devuelve un album de la lista por su nombre.
     * @param opcion El nombre del album que queremos buscar
     * @return El album si se encuentra, y null si no se encuentra
     */
    public Album obtenerAlbumPorNombre(String opcion) {
        for (Album a : m.getMu().getAlbumes()) {
            if (a.getTitulo().toLowerCase().equals(opcion.toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    /**
     * Busca un Artist por su nombre y lo devuelve
     * @param opcion El nombre del artista que queremos buscar
     * @return El artista si se encuentra, o si no se encuentra null
     */
    public Artist obtenerArtistaPorNombre(String opcion) {
        for (Artist a : m.getMu().getArtistas()) {
            if (a.getNombre().toLowerCase().equals(opcion.toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    /**
     * Genera una PlayList aleatoria, con el número de canciones indicado.
     * @param titulo el titulo de la playlist a generar
     * @param numCanciones el numero de canciones que tendrá
     * @return La playlist aleatoria
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
     * Devuelve una PlayList tras buscarla por su nombre.
     * @param titulo El titulo de la playlist que queremos buscar
     * @return La playlist si se encuentra, null si no se encuentra.
     */
    public PlayList obtenerPlaylistPorNombre(String titulo) {
        for (PlayList p : this.m.getMu().getPlaylists()) {
            if (p.getNombre().toLowerCase().equals(titulo.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    /**
     * Borra una Song (por su nombre) de una PlayList
     * @param plTemp la playlist de la cual queremos borrar una canción
     * @param nombreCancion el nombre de la canción a borrar
     * @return true si lo conseguimos, false si no
     */
    public boolean borrarCancionPlaylist(PlayList plTemp, String nombreCancion) {
        for (Song s : plTemp.getCanciones()) {
            if (s.getTitulo().toLowerCase().equals(nombreCancion.toLowerCase())) {
                plTemp.borrarCancion(s);
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve una Song por su nombre.
     * @param nombreCancion el nombre de la canción que queremos buscar
     * @return la canción si se encuentra, null si no se encuentra
     */
    public Song obtenerCancionPorNombre(String nombreCancion) {
        for (Song s : this.m.getMu().getCanciones()) {
            if (s.getTitulo().toLowerCase().equals(nombreCancion.toLowerCase())) {
                return s;
            }
        }
        return null;
    }

    /**
     * Añade una Song a una PlayList.
     * @param plTemp la playlist a la cual queremos añadir una cancion
     * @param songTmp la canción a añadir
     * @return true si lo consigue, false si no
     */
    public boolean anadirCancionAPlaylist(PlayList plTemp, Song songTmp) {
        if (plTemp != null && songTmp != null) {
            for (PlayList p : this.m.getMu().getPlaylists()) {
                if (p.equals(plTemp)) {
                    p.anadirCancion(songTmp);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Hace que el modelo exporte los artistas en formato COL.
     * @return Pide al modelo que guarde los artistas en formato COL, devuelve
     * true si lo consigue y false si no
     */
    public boolean pedirExportacionArtistas() {
        return m.exportarArtistas();
    }

    /**
     * Hace comprobaciones y luego pide al modelo que guarde el musicfy. No
     * retorna nada, pero puede lanzar una excepción si falla.
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
     * Añade un Artist al modelo.
     * @param a El artista a añadir al modelo
     * @return true si se ha añadido, false si no
     */
    public boolean anadirArtistaAModelo(Artist a) {
        if (a != null) {
            m.anadirArtista(a);
            return true;
        }
        return false;
    }

    /**
     * Borra un Artist del modelo.
     * @param a El artista a borrar
     * @return true si se ha borrado, false si no
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
     * Cambia un atributo de un Artist, dependiendo de la elección previa del usuario.
     * @param a El artista del cual queremos cambiar un campo
     * @param nuevoValor El nuevo valor del campo
     * @param opcion Un string que define que campo se cambia (1 = biografia, 2
     * = instagram, 3 = twitter, 4 = facebook, 5 = wikipedia)
     * @return true si se ha cambiado, false si no
     */
    public boolean cambiarAtributoArtista(Artist a, String nuevoValor, String opcion) {
        if (a != null) {
            switch (opcion) {
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
     * Añade un Album al modelo.
     * @param a El album a añadir al modelo
     * @return true si se añade, false si no
     */
    public boolean anadirAlbumAModelo(Album a) {
        if (a != null) {
            m.anadirAlbum(a);
            return true;
        }
        return false;
    }

    /**
     * Añade un Song al modelo.
     * @param so la cancion a añadir al modelo
     * @return true si se añade, false si no
     */
    public boolean anadirCancionAModelo(Song so) {
        if (so != null) {
            m.anadirCancion(so);
            return true;
        }
        return false;
    }

    /**
     * Borra un Album de la lista de albumes de un Artist.
     * @param a El album que borrar del perfil de un artista
     * @param ar El artista del cual queremos quitar el album
     * @return true si se ha borrado, false si no
     */
    public boolean borrarAlbumDeArtista(Album a, Artist ar) {
        if (a != null && ar != null) {
            ar.getAlbumes().remove(a.getTitulo());
            return true;
        }
        return false;
    }

    /**
     * Borra una Song del modelo.
     * @param s la canción a borrar
     * @return true si la canción era válida y se ha borrado, false si no
     */
    public boolean borrarCancionDeModelo(Song s) {
        if (s != null) {
            m.borrarCancion(s);
            return true;
        }
        return false;
    }

    /**
     * Borra un Album del modelo.
     * @param a El album a borrar
     * @return true si el album era válido y se ha borrado, false si no
     */
    public boolean borrarAlbumDeModelo(Album a) {
        if (a != null) {
            m.borrarAlbum(a);
            return true;
        }
        return false;
    }

    /**
     * Cambia un atributo de un Album, según la elección previa del usuario.
     * @param a el album cuyo atributo queremos cambiar
     * @param nuevoValor el nuevo valor a darle
     * @param opcion un número que nos dirá que atributo se cambia (1 = titulo,
     * 2 = año)
     * @return
     */
    public boolean cambiarAtributoAlbum(Album a, String nuevoValor, String opcion) {
        if (a != null) {
            switch (opcion) {
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
     * Vacía todo el modelo, usado para luego cargarlo aleatoriamente.
     */
    public void vaciarColecciones() {
        m.vaciarColecciones();
    }

    /**
     * Genera aleatoriamente los datos, según los datos en los .txt de aleatorio.
     * @param numArtistas el número de artistas a generar aleatoriamente
     * @param numAlbumes el número de albumes a generar aleatoriamente
     * @param numCanciones el número de canciones a generar aleatoriamente
     * @param numPlaylists el número de playlists a generar aleatoriamente
     * @return true si se ha completado la operación, false si ha habido algún
     * fallo
     */
    public boolean generarAleatoriamente(int numArtistas, int numAlbumes, int numCanciones, int numPlaylists) {
        ArrayList<Album> albumesTmp = new ArrayList<Album>();
        ArrayList<Artist> artistasTmp = new ArrayList<Artist>();
        ArrayList<Song> cancionesTmp = new ArrayList<Song>();
        ArrayList<PlayList> playlistsTmp = new ArrayList<PlayList>();
        Random r = new Random();
        if (Constants.RUTA_NOMBRES_ALBUMES.toFile().exists()
                && Constants.RUTA_NOMBRES_ARTISTAS.toFile().exists()
                && Constants.RUTA_NOMBRES_CANCIONES.toFile().exists()
                && Constants.RUTA_NOMBRES_PLAYLISTS.toFile().exists()) {
            try {
                String nombreTmp;
                String[] nombresAlbumes = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_ALBUMES);
                String[] nombresArtistas = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_ARTISTAS);
                String[] nombresCanciones = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_CANCIONES);
                String[] nombresPlaylists = Auxiliar.leerLineasEnArray(Constants.RUTA_NOMBRES_PLAYLISTS);
                if (nombresAlbumes == null || nombresArtistas == null
                        || nombresCanciones == null || nombresPlaylists == null) {
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
     * Devuelve los Album del modelo.
     * @return retorna los albumes del modelo
     */
    public Iterable<Album> obtenerAlbumes() {
        return m.getMu().getAlbumes();
    }

    /**
     * Devuelve los Artist del modelo.
     * @return retorna los artistas del modelo
     */
    public Iterable<Artist> obtenerArtistas() {
        return m.getMu().getArtistas();
    }

    /**
     * Devuelve las PlayList del modelo.
     * @return retorna las playlists del modelo
     */
    public Iterable<PlayList> obtenerPlaylists() {
        return m.getMu().getPlaylists();
    }

    /**
     * Pide la exportación de los albumes a formato de tabla HTML.
     * @return true si se ha exportado, false si no.
     */
    public boolean pedirExportacionAlbumes() {
        return m.exportarAlbumes();
    }

}
