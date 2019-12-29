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
package View;

import Controller.Controller;
import Model.Album;
import Model.Artist;
import Model.PlayList;
import Model.Song;
import Other.Auxiliar;
import com.coti.tools.Esdia;
import static java.lang.String.format;
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class View {

    Controller c = new Controller();

    /**
     *
     * @param string El prompt a imprimirse con el menu.
     * @param opciones Las opciones que se pueden meter por teclado.
     */
    public void runMenuPrincipal(String string, String[] opciones) {
        boolean finish = false;
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch (opcion) {
                case "1":
                    this.pedirGeneracionAleatoria();
                    break;
                case "2":
                    this.runMenuArchivos();
                    break;
                case "3":
                    this.runMenuAlbum();
                    break;
                case "4":
                    this.runMenuArtista();
                    break;
                case "5":
                    this.runMenuPlaylist();
                    break;
                case "6":
                    this.imprimirCanciones();
                    break;
                case "q":
                    finish = Esdia.yesOrNo("¿Desea finalizar la ejecucion?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!finish);
    }

    private void pedirGeneracionAleatoria() {
        boolean ret;
        int numArtistas, numAlbumes, numCanciones, numPlaylists;
        c.vaciarColecciones();
        numArtistas = Esdia.readInt("Introduzca el número de artistas a generar: ");
        numAlbumes = Esdia.readInt("Introduzca el número de albumes a generar: ");
        numCanciones = Esdia.readInt("Introduzca el número de canciones a generar: ");
        numPlaylists = Esdia.readInt("Introduzca el número de playlists a generar: ");
        System.out.println("\nGENERANDO ALEATORIAMENTE\n");
        ret = c.generarAleatoriamente(numArtistas, numAlbumes, numCanciones, numPlaylists);
        if (ret) {
            System.out.println("\nGENERACIÓN ALEATORIA EXITOSA\n");
        } else {
            System.out.println("Fallo en la generación aleatoria.");
        }
    }

    private void runMenuArchivos() {
        boolean finish = false;
        String string = "1. Exportar artistas en formato encolumnado"
                + "\n2. Exportar álbumes a tabla HTML"
                + "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch (opcion) {
                case "1":
                    this.pedirExportacionArtistas();
                    break;
                case "2":
                    this.pedirExportacionAlbumes();
                    break;
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!finish);
    }

    private void runMenuAlbum() {
        boolean finish = false;
        String string = "1. Añadir un álbum"
                + "\n2. Borrar un álbum"
                + "\n3. Modificar un álbum"
                + "\n4. Consultar un álbum"
                + "\n5. Imprimir nombres de álbumes"
                + "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "3", "4", "5", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch (opcion) {
                case "1":
                    this.anadirAlbum();
                    break;
                case "2":
                    this.borrarAlbum();
                    break;
                case "3":
                    this.modificarAlbum();
                    break;
                case "4":
                    this.consultarAlbum();
                    break;
                case "5":
                    this.imprimirAlbumes();
                    break;
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!finish);
    }

    private void runMenuArtista() {
        boolean finish = false;
        String string = "1. Añadir un artista"
                + "\n2. Borrar un artista"
                + "\n3. Modificar un artista"
                + "\n4. Listado de albumes de un artista"
                + "\n5. Imprimir nombres de artistas"
                + "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "3", "4", "5", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch (opcion) {
                case "1":
                    this.anadirArtista();
                    break;
                case "2":
                    this.borrarArtista();
                    break;
                case "3":
                    this.modificarArtista();
                    break;
                case "4":
                    this.consultarAlbumesArtista();
                    break;
                case "5":
                    this.imprimirArtistas();
                    break;
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!finish);
    }

    private void runMenuPlaylist() {
        boolean finish = false;
        String string = "1. Añadir una playlist"
                + "\n2. Borrar una canción de una playlist"
                + "\n3. Añadir una canción a una playlist"
                + "\n4. Imprimir nombres de playlists"
                + "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "3", "4", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch (opcion) {
                case "1":
                    this.anadirPlaylist();
                    break;
                case "2":
                    this.borrarCancionDePlaylist();
                    break;
                case "3":
                    this.anadirCancionAPlaylist();
                    break;
                case "4":
                    this.imprimirPlaylists();
                    break;
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!finish);
    }

    private void imprimirCanciones() {
        ArrayList<Song> cancionesTemp = new ArrayList<Song>();
        String header = format("%-45s | %-10s | %-10s", "Canción", "Duración", "Año");
        c.sortCanciones();
        cancionesTemp = c.getCanciones();
        System.out.printf("%s\n", header);
        for (Song cancion : cancionesTemp) {
            System.out.printf("%s\n", cancion.toString());
        }

    }

    private void pedirExportacionArtistas() {
        boolean ret;
        System.out.println("\nEXPORTANDO ARTISTAS EN ARTISTAS.COL\n");
        ret = c.pedirExportacionArtistas();
        if (ret) {
            System.out.println("\nEXPORTACIÓN FINALIZADA\n");
        } else {
            System.out.println("\nEXPORTACIÓN FALLIDA\n");
        }
    }

    private void pedirExportacionAlbumes() {
        boolean ret;
        System.out.println("\nEXPORTANDO ALBUMES EN ALBUMES.HTML\n");
        ret = c.pedirExportacionAlbumes();
        if (ret) {
            System.out.println("\nEXPORTACIÓN FINALIZADA\n");
        } else {
            System.out.println("\nEXPORTACIÓN FALLIDA\n");
        }
    }

    private void anadirAlbum() {
        String nombreAlbum, nombreArtista;
        Artist interprete;
        Album a;
        ArrayList<String> cancionesStr = new ArrayList<String>();
        ArrayList<Song> canciones = new ArrayList<Song>();
        System.out.println("\nADICIÓN DE UN ALBUM A LA LISTA DE ALBUMES\n");
        nombreArtista = Esdia.readString("Introduzca el nombre del artista: ");
        interprete = c.obtenerArtistaPorNombre(nombreArtista);
        if (interprete == null) {
            interprete = new Artist(nombreArtista);
            c.anadirArtistaAModelo(interprete);
        }
        nombreAlbum = Esdia.readString("Introduzca el nombre del album: ");
        cancionesStr = Auxiliar.leerStringHastaVacio("\nIntroduzca el nombre de una cancion, o enter para salir: ");
        for (String s : cancionesStr) {
            Song so = new Song(nombreArtista, s);
            canciones.add(so);
            c.anadirCancionAModelo(so);
        }
        a = new Album(nombreArtista, nombreAlbum, canciones);
        c.anadirAlbumAModelo(a);
    }

    private void borrarAlbum() {
        String nombreAlbum;
        Album a;
        Artist ar;
        System.out.println("\nBORRADO DE UN ALBUM DE LA LISTA DE ALBUMES\n");
        nombreAlbum = Esdia.readString("Introduzca el nombre del album a borrar: ");
        a = c.obtenerAlbumPorNombre(nombreAlbum);
        if (a != null) {
            ar = c.obtenerArtistaPorNombre(a.getTitulo());
            if (ar != null) {
                c.borrarAlbumDeArtista(a, ar);
            }
            for (Song s : a.getCanciones()) {
                c.borrarCancionDeModelo(s);
            }
            c.borrarAlbumDeModelo(a);
        } else {
            System.out.printf("Álbum \"%s\" no encontrado.\n", nombreAlbum);
        }
    }

    private void modificarAlbum() {
        String nombre, opcion, nuevoValor;
        String[] opciones = {"1", "2", "q"};
        Album a;
        boolean ret = true;
        nombre = Esdia.readString("Introduzca el nombre del album que quiere modificar: ");
        a = c.obtenerAlbumPorNombre(nombre);
        if (a != null) {
            System.out.println("Seleccione el campo que quiere modificar.\n");
            opcion = Esdia.readString("1. Título"
                    + "\n2. Año"
                    + "\nq. Volver atrás", opciones);
            switch (opcion) {
                case "1":
                    System.out.printf("Título actual: %s\n", a.getTitulo());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoAlbum(a, nuevoValor, opcion);
                    break;
                case "2":
                    System.out.printf("Año actual: %d\n", a.getAnno());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoAlbum(a, nuevoValor, opcion);
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Opción incorrecta, no se han realizado cambios.");
            }
        } else {
            System.out.printf("Álbum \"%s\" no encontrado.\n", nombre);
        }
        if (!ret) {
            System.out.println("No se han realizado cambios por un error interno."); //Nunca deberíamos de llegar aquí, pero por si acaso.
        }
    }

    private void consultarAlbum() {
        String opcion;
        Album res;
        System.out.println("\nCONSULTA DE ALBUMES\n");
        opcion = Esdia.readString("Introduzca el nombre del álbum a buscar: ");
        res = c.obtenerAlbumPorNombre(opcion);
        if (res != null) {
            System.out.println(res.toString());
        } else {
            System.out.printf("Álbum \"%s\" no encontrado.\n", opcion);
        }
    }

    private void anadirArtista() {
        String nombre;
        Artist a;
        ArrayList<String> albumes = new ArrayList<String>();
        System.out.println("\nADICIÓN DE UN ARTISTA A LA LISTA DE ARTISTAS\n");
        nombre = Esdia.readString("Introduzca el nombre del artista: ");
        albumes = Auxiliar.leerStringHastaVacio("\nIntroduzca el nombre de un album, o enter para salir: ");
        a = new Artist(nombre, albumes);
        c.anadirArtistaAModelo(a);
    }

    private void borrarArtista() {
        String nombre;
        ArrayList<String> albumes;
        Artist a;
        System.out.println("\nBORRADO DE UN ARTISTA DE LA LISTA DE ARTISTAS\n");
        nombre = Esdia.readString("Introduzca el nombre del artista: ");
        a = c.obtenerArtistaPorNombre(nombre);
        if (a != null) {
            if (c.borrarArtista(a)) {
                System.out.printf("Artista \"%s\" borrado correctamente.\n", nombre);
            } else {
                albumes = a.getAlbumes();
                for (String s : albumes) {
                    System.out.println(s);
                }
                System.out.printf("No se ha podido borrar el artista, ya que los álbumes indicados siguen dados de alta en la lista de álbumes.\n");
            }
        } else {
            System.out.printf("Artista %s no encontrado.\n", nombre);
        }
    }

    private void modificarArtista() {
        String nombre, opcion, nuevoValor;
        String[] opciones = {"1", "2", "3", "4", "5", "q"};
        Artist a;
        boolean ret = true;
        nombre = Esdia.readString("Introduzca el nombre del artista: ");
        a = c.obtenerArtistaPorNombre(nombre);
        if (a != null) {
            System.out.println("Seleccione el campo que quiere modificar.\n");
            opcion = Esdia.readString("1. Biografía"
                    + "\n2. Instagram"
                    + "\n3. Twitter"
                    + "\n4. Facebook"
                    + "\n5. Wikipedia"
                    + "\nq. Volver atrás", opciones);
            switch (opcion) {
                //TODO: Quizas habria que hacer esto desde el controlador? idk.
                case "1":
                    System.out.printf("Biografia actual: %s\n", a.getBiografia());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoArtista(a, nuevoValor, opcion);
                    break;
                case "2":
                    System.out.printf("Instagram actual: %s\n", a.getInstagram());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoArtista(a, nuevoValor, opcion);
                    break;
                case "3":
                    System.out.printf("Twitter actual: %s\n", a.getTwitter());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoArtista(a, nuevoValor, opcion);
                    break;
                case "4":
                    System.out.printf("Facebook actual: %s\n", a.getFacebook());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoArtista(a, nuevoValor, opcion);
                    break;
                case "5":
                    System.out.printf("Wikipedia actual: %s\n", a.getWikipedia());
                    nuevoValor = Esdia.readString("Introduzca un nuevo valor para el campo: ");
                    ret = c.cambiarAtributoArtista(a, nuevoValor, opcion);
                    break;
                case "q":
                    return;
                default:
                    System.out.println("Opción incorrecta, no se han realizado cambios.");
            }
        } else {
            System.out.printf("Artista \"%s\" no encontrado.\n", nombre);
        }
        if (!ret) {
            System.out.println("No se han realizado cambios por un error interno."); //Nunca deberíamos de llegar aquí, pero por si acaso.
        }
    }

    private void consultarAlbumesArtista() {
        String opcion;
        Artist res;
        System.out.println("\nCONSULTA DE ALBUMES POR NOMBRE DEL ARTISTA\n");
        opcion = Esdia.readString("Introduzca el nombre del artista: ");
        res = c.obtenerArtistaPorNombre(opcion);
        if (res != null) {
            System.out.printf("%s\n--------------------\n", opcion.toUpperCase());
            for (String s : res.getAlbumes()) {
                System.out.printf("%s\n", s);
            }
        } else {
            System.out.printf("Artista \"%s\" no encontrado.\n", opcion);
        }
    }

    private void anadirPlaylist() {
        String titulo;
        int numCanciones;
        PlayList plTemp;
        System.out.println("\nCREACIÓN DE PLAYLIST\n");
        titulo = Esdia.readString("Introduzca el nombre de la playlist: ");
        numCanciones = Esdia.readInt("Introduzca el número de canciones: ");
        plTemp = c.generarPlaylistAleatoria(titulo, numCanciones);
        if (plTemp != null) {
            System.out.printf("%s\n--------------------\n%s", "PLAYLIST CREADA: ", plTemp.toString());
        } else {
            System.out.println("Error al crear playlist.\n");
        }
    }

    private void borrarCancionDePlaylist() {
        String titulo, nombreCancion;
        PlayList plTemp;
        System.out.println("\nBORRAR CANCIÓN DE PLAYLIST\n");
        titulo = Esdia.readString("Introduzca el nombre de la playlist: ");
        plTemp = c.obtenerPlaylistPorNombre(titulo);
        if (plTemp != null) {
            System.out.println(plTemp.toString());
            nombreCancion = Esdia.readString("Nombre de la canción a borrar: ");
            if (c.borrarCancionPlaylist(plTemp, nombreCancion)) {
                System.out.println("\nCanción borrada.\n");
            } else {
                System.out.println("\nCanción no encontrada.\n");
            }
        } else {
            System.out.printf("Playlist \"%s\" no encontrada.\n", titulo);
        }
    }

    private void anadirCancionAPlaylist() {
        String titulo, nombreCancion;
        PlayList plTemp;
        Song songTmp;
        System.out.println("\nAÑADIR CANCIÓN A PLAYLIST\n");
        titulo = Esdia.readString("Introduzca el nombre de la playlist: ");
        //TODO: Sustituir esta obtencion de playlist por otro metodo que solo compruebe que existe, ya que solo necesitamos saber si existe.
        plTemp = c.obtenerPlaylistPorNombre(titulo);
        if (plTemp != null) {
            nombreCancion = Esdia.readString("Nombre de la canción a añadir: ");
            songTmp = c.obtenerCancionPorNombre(nombreCancion);
            if (songTmp != null) {
                c.anadirCancionAPlaylist(plTemp, songTmp);
            } else {
                System.out.printf("Canción \"%s\" no encontrada.\n", titulo);
            }
        } else {
            System.out.printf("Playlist \"%s\" no encontrada.\n", titulo);
        }
    }

    public void pedirImportacion() {
        c.pedirImportacion();
    }

    public void runMenuFinal() {
        boolean opcion;
        opcion = Esdia.yesOrNo("Desea serializar Musicfy antes de salir?");
        if (opcion) {
            c.pedirSerializacion();
        }
    }

    private void imprimirAlbumes() {
        System.out.println("\nARTISTAS Y NOMBRES DE TODOS LOS ALBUMES\n");
        for (Album a : c.obtenerAlbumes()) {
            System.out.printf("%s - %s\n", a.getInterpretes().toString(), a.getTitulo());
        }
    }

    private void imprimirArtistas() {
        System.out.println("\nNOMBRES DE TODOS LOS ARTISTAS\n");
        for (Artist a : c.obtenerArtistas()) {
            System.out.println(a.getNombre());
        }
    }

    private void imprimirPlaylists() {
        System.out.println("\nNOMBRES DE TODAS LAS PLAYLIST\n");
        for (PlayList p : c.obtenerPlaylists()) {
            System.out.println(p.getNombre());
        }
    }

}
