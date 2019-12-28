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

package Other;

import java.nio.file.Path;
import com.coti.tools.Rutas;
/**
 *
 * @author Jorge Cruz García (jorgecruz@usal.es)
 */
public class Constants {
    //Carpetas

    /**
     *
     */
    public final static Path RUTA_MUSICFY = Rutas.pathToFolderOnDesktop("musicfy");

    /**
     *
     */
    public final static Path RUTA_BINARIOS = RUTA_MUSICFY.resolve("binarios");

    /**
     *
     */
    public final static Path RUTA_DATOS = RUTA_MUSICFY.resolve("datos");

    /**
     *
     */
    public final static Path RUTA_SALIDA = RUTA_MUSICFY.resolve("salida");

    /**
     *
     */
    public final static Path RUTA_ALEATORIOS = RUTA_MUSICFY.resolve("aleatorios");
    //Archivos

    /**
     *
     */
    public final static Path RUTA_MUSICFY_BIN = RUTA_BINARIOS.resolve("musicfy.bin");

    /**
     *
     */
    public final static Path RUTA_ALBUMES = RUTA_DATOS.resolve("albumes.txt");

    /**
     *
     */
    public final static Path RUTA_ARTISTAS = RUTA_DATOS.resolve("artistas.txt");

    /**
     *
     */
    public final static Path RUTA_ARTISTAS_COL = RUTA_SALIDA.resolve("artistas.col");

    /**
     *
     */
    public final static Path RUTA_ALBUMES_HTML = RUTA_SALIDA.resolve("albumes.html");

    /**
     *
     */
    public final static Path RUTA_NOMBRES_ALBUMES = RUTA_ALEATORIOS.resolve("nombresAlbumes.txt");

    /**
     *
     */
    public final static Path RUTA_NOMBRES_ARTISTAS = RUTA_ALEATORIOS.resolve("nombresArtistas.txt");

    /**
     *
     */
    public final static Path RUTA_NOMBRES_PLAYLISTS = RUTA_ALEATORIOS.resolve("nombresPlaylists.txt");

    /**
     *
     */
    public final static Path RUTA_NOMBRES_CANCIONES = RUTA_ALEATORIOS.resolve("titulosCanciones.txt");
    //Otras constantes

    /**
     *
     */
    public final static int MINUTOS_MAX = 5; // Minutos maximos que dura una canción con longitud aleatoria.

    /**
     *
     */
    public final static int MINUTOS_MIN = 1; // Minutos minimos que dura una cancion con longitud aleatoria.

    /**
     *
     */
    public final static int ANO_MIN = 1980; // Año minimo para una cancion generada aleatoriamente.

    /**
     *
     */
    public final static int CANCIONES_MAX = 12; // Canciones maximas en un albún generado aleatoriamente.

    /**
     *
     */
    public final static int LONG_PLAYLIST_MAX = 25; // Número máximo de canciones por playlist generado aleatoriamente.

    /**
     *
     */
    public final static int ALBUMES_MAX = 5; // Álbumes maximos para un artista generado aleatoriamente.
            
}
