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
import com.coti.tools.Esdia;
import static java.lang.String.format;
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class View {
    Controller c = new Controller();

    public void runMenuPrincipal(String string, String[] opciones) {
        boolean finish = false;
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch(opcion) {
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
        } while(!finish);
    }

    private void pedirGeneracionAleatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void runMenuArchivos() {
        boolean finish = false;
        String string = "1. Exportar artistas en formato encolumnado" + 
                        "\n2. Exportar álbumes a tabla HTML" + 
                        "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch(opcion) {
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
        } while(!finish);
    }

    private void runMenuAlbum() {
        boolean finish = false;
        String string = "1. Añadir un álbum" + 
                        "\n2. Borrar un álbum" + 
                        "\n3. Modificar un álbum" + 
                        "\n4. Consultar un álbum" + 
                        "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "3", "4", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch(opcion) {
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
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while(!finish);
    }

    private void runMenuArtista() {
        boolean finish = false;
        String string = "1. Añadir un artista" + 
                        "\n2. Borrar un artista" + 
                        "\n3. Modificar un artista" + 
                        "\n4. Listado de albumes de un artista" + 
                        "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "3", "4", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch(opcion) {
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
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while(!finish);
    }

    private void runMenuPlaylist() {
        boolean finish = false;
        String string = "1. Añadir una playlist" + 
                        "\n2. Borrar una canción de una playlist" + 
                        "\n3. Añadir una canción a una playlist" + 
                        "\nq. Salir al menú principal";
        String[] opciones = {"1", "2", "3", "q"};
        String opcion;
        do {
            opcion = Esdia.readString(string, opciones).toLowerCase();
            switch(opcion) {
                case "1":
                    this.anadirPlaylist();
                    break;
                case "2":
                    this.borrarCancionDePlaylist();
                    break;
                case "3": 
                    this.anadirCancionAPlaylist();
                    break;
                case "q":
                    finish = Esdia.yesOrNo("¿Desea volver al menú principal?");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while(!finish);
    }

    private void imprimirCanciones() {
        ArrayList<Song> cancionesTemp = new ArrayList<Song>();
        String header = format("%-45s | %-10s | %-10s", "Canción", "Duración", "Año");
        c.sortCanciones();
        cancionesTemp = c.getCanciones();
        System.out.printf("%s\n", header);
        for (Song cancion: cancionesTemp) {
            System.out.printf("%s\n", cancion.toString());
        }
        
    }

    private void pedirExportacionArtistas() {
        System.out.println("\nEXPORTANDO ARTISTAS EN ARTISTAS.COL\n");
        c.pedirExportacionArtistas();
        System.out.println("\nEXPORTACIÓN FINALIZADA\n");
    }

    private void pedirExportacionAlbumes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void anadirAlbum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrarAlbum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void modificarAlbum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrarArtista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void modificarArtista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void consultarAlbumesArtista() {
        String opcion;
        Artist res;
        System.out.println("\nCONSULTA DE ALBUMES POR NOMBRE DEL ARTISTA\n");
        opcion = Esdia.readString("Introduzca el nombre del artista: ");
        res = c.obtenerArtistaPorNombre(opcion);
        if (res != null) {
            System.out.printf("%s\n--------------------\n", opcion.toUpperCase());
            for (String s: res.getAlbumes()) {
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
        if (plTemp != null){
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
            if (c.borrarCancionPlaylist(plTemp, nombreCancion) == 1) {
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

}
