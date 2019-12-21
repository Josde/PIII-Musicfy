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
import Model.Song;
import com.coti.tools.Esdia;
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class View {
    private Controller c;

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
        String string = "1. Exportar artistas" + 
                        "\n2. Exportar álbumes" + 
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
        ArrayList<Song> cancionesTemp;
        c.sortCanciones();
        cancionesTemp = c.getCanciones();
        for (Song cancion: cancionesTemp) {
            System.out.printf("%s\n", cancion.toString());
        }
        
    }

    private void pedirExportacionArtistas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void anadirPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrarCancionDePlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void anadirCancionAPlaylist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
