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

import Other.Auxiliar;
import Other.ComparadorAlbum;
import Other.ComparadorSong;
import Other.Constants;
import com.coti.tools.OpMat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Model {
    Musicfy mu = new Musicfy();
    //TODO: Añadir exportToDisk.

    /**
     *
     */
    public void importFromDisk() {
        File musicfyBin = Other.Constants.RUTA_MUSICFY_BIN.toFile();
        if (musicfyBin.exists()) {
            try {
                this.deserializarMusicfy(musicfyBin);
                System.out.println("Musicfy deserializado");
            } catch (IOException e) { //Si el archivo es invalido o cualquier otra cosa, importamos desde los txt.
                this.importFromDiskTxt();
            }
        } else { //Si el archivo binario directamente no existe, importamos desde los txt
            this.importFromDiskTxt();
        }
    }
    
    /**
     *
     */
    public void importFromDiskTxt() {
        File albumesTxt = Other.Constants.RUTA_ALBUMES.toFile();
        File artistasTxt = Other.Constants.RUTA_ARTISTAS.toFile();
        try {
            String[][] albumes = OpMat.importFromDisk(albumesTxt, "\t");
            String[][] artistas = OpMat.importFromDisk(artistasTxt, "#");
            ArrayList<Album> listaAlbum = new ArrayList<Album>();
            ArrayList<Artist> listaArtista = new ArrayList<Artist>();
            Album a;
            for (String[] s: albumes) {
                a = Album.factory(s);
                listaAlbum.add(a);
                //Tambien añadimos cada cancion a la lista de canciones de Musicfy.
                this.mu.anadirCanciones(a.getCanciones());
            }
            for (String[] s: artistas) {
                listaArtista.add(Artist.factory(s));
            }
            this.mu.setAlbumes(listaAlbum);
            this.mu.setArtistas(listaArtista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     * @param musicfyBin
     */
    public void serializarMusicfy(File musicfyBin) {
        try {
            FileOutputStream archivoSalida = new FileOutputStream(musicfyBin);
            BufferedOutputStream bosSalida = new BufferedOutputStream(archivoSalida);
            ObjectOutputStream salida = new ObjectOutputStream(bosSalida);
            salida.writeObject(this.mu);
            salida.close();
            archivoSalida.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    void deserializarMusicfy(File musicfyBin) throws IOException {
        try {
            FileInputStream archivoEntrada = new FileInputStream(musicfyBin);
            BufferedInputStream bosEntrada = new BufferedInputStream(archivoEntrada);
            ObjectInputStream entrada = new ObjectInputStream(bosEntrada);
            this.mu = (Musicfy) entrada.readObject();
            entrada.close();
            archivoEntrada.close();
        } catch (IOException i) {
            System.err.println("Archivo musicfy.bin no encontrado o inválido.");
            throw i;
        } catch (ClassNotFoundException c) {
            System.err.println("Clase no encontrada en el archivo binario.");
            c.printStackTrace();
        } 
    }

    /**
     *
     * @return
     */
    public Musicfy getMu() {
        return mu;
    }

    /**
     *
     * @param mu
     */
    public void setMu(Musicfy mu) {
        this.mu = mu;
    }

    /**
     *
     */
    public void sortCanciones() {
        Comparator<Song> c = new ComparadorSong();
        Collections.sort(this.mu.getCanciones(), c);
    }
    
    /**
     *
     */
    public void sortAlbumes() {
        Comparator<Album> a = new ComparadorAlbum();
        Collections.sort(this.mu.getAlbumes(), a);
    }

    /**
     *
     * @param plTemp
     */
    public void anadirPlaylist(PlayList plTemp) {
        this.getMu().getPlaylists().add(plTemp);
    }

    /**
     *
     * @return
     */
    public boolean exportarArtistas() {
        ArrayList<String[]> artistas = new ArrayList<String[]>(); //Usamos arraylist porque el num de artistas es variable.
        for (Artist a : this.getMu().getArtistas()) {
            artistas.add(a.toStringArray());
        }
        String[][] tabla = new String[artistas.size()][];
        for (int i = 0; i < artistas.size(); i++) {
            String[] fila = artistas.get(i);
            tabla[i] = fila;
        }
        try {
            if (!Constants.RUTA_ARTISTAS_COL.toFile().exists()) {
                Files.createDirectories(Constants.RUTA_ARTISTAS_COL.getParent());
                Files.createFile(Constants.RUTA_ARTISTAS_COL);
            }
            OpMat.exportToDisk(tabla, Constants.RUTA_ARTISTAS_COL.toFile(), "\t");
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     *
     * @return
     */
    public boolean exportarAlbumes() {
        ArrayList<String[]> albumes = new ArrayList<String[]>(); //Usamos arraylist porque el num de artistas es variable.
        for (Album a : this.getMu().getAlbumes()) {
            albumes.add(a.toStringArray());
        }
        try {
            if (!Constants.RUTA_ALBUMES_HTML.toFile().exists()) {
                Files.createDirectories(Constants.RUTA_ALBUMES_HTML.getParent());
                Files.createFile(Constants.RUTA_ALBUMES_HTML);
            }
            File f = Constants.RUTA_ALBUMES_HTML.toFile();
            if (!f.exists()) return false;
            FileWriter fw = new FileWriter(f, false); // Creamos un fileWriter sin append para vaciar el archivo.
            BufferedWriter bw = new BufferedWriter(fw);
            String[] header = {"Intérpretes", "Título", "Canciones", "Año", "Nº Canciones", "Duración", "Tipo"};
            StringBuilder sb = new StringBuilder();
            sb.append("<style>\n table, td, th { border: 1px solid #000000 }\n</style>");
            sb.append("<table>");
            sb.append("<tr>");
            for (String h : header) {
                Auxiliar.anadirHeader(sb, h);
            }
            sb.append("</tr>");
            for (String[] s: albumes) {
                sb.append("<tr>");
                for (String ss: s) {
                    Auxiliar.anadirDato(sb, ss);
                }
                sb.append("</tr>");
            }
            sb.append("</table>");
            bw.write(sb.toString());
            bw.close();
            fw.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param a
     */
    public void anadirArtista(Artist a) {
        this.getMu().getArtistas().add(a);
    }

    /**
     *
     * @param a
     */
    public void borrarArtista(Artist a) {
        this.getMu().getArtistas().remove(a);
    }

    /**
     *
     * @param so
     */
    public void anadirCancion(Song so) {
        this.getMu().getCanciones().add(so);
    }

    /**
     *
     * @param a
     */
    public void anadirAlbum(Album a) {
        this.getMu().getAlbumes().add(a);
    }
    
    /**
     *
     * @param so
     */
    public void borrarCancion(Song so) {
        this.getMu().getCanciones().remove(so);
    }

    /**
     *
     * @param a
     */
    public void borrarAlbum(Album a) {
        this.getMu().getAlbumes().remove(a);
    }

    /**
     *
     */
    public void vaciarColecciones() {
        this.getMu().getArtistas().clear();
        this.getMu().getAlbumes().clear();
        this.getMu().getCanciones().clear();
        this.getMu().getPlaylists().clear();
    }

    /**
     *
     * @param albumesTmp
     * @param cancionesTmp
     * @param artistasTmp
     * @param playlistsTmp
     */
    public void correlacionarDatosYActualizarModelo(ArrayList<Album> albumesTmp, ArrayList<Song> cancionesTmp, ArrayList<Artist> artistasTmp, ArrayList<PlayList> playlistsTmp) {
        Random r = new Random();
        for (Artist a: artistasTmp) {
            ArrayList<Album> albumes = new ArrayList<Album>();
            Album alb;
            int numAlbumes = r.nextInt(Constants.ALBUMES_MAX - 1) + 1;
            for (int i = 0; i < numAlbumes; i++) {
                ArrayList<String> interprete = new ArrayList<String>();
                alb = albumesTmp.get(r.nextInt(albumesTmp.size()));
                interprete.addAll(Arrays.asList(a.getNombre()));
                alb.setInterpretes(interprete);
                albumes.add(alb);
            }
        }
        for (Album a: albumesTmp) {
            if (a.getInterpretes().isEmpty()) { // Si el álbum no tiene interprete, le asignamos uno.
                Artist aTmp = artistasTmp.get(r.nextInt(artistasTmp.size()));
                a.getInterpretes().add(aTmp.getNombre());
                aTmp.getAlbumes().add(a.getTitulo());
            }
            ArrayList<Song> songTmp = new ArrayList<Song>();
            Song cancion;
            for (int i = 0; i < a.getNumCanciones(); i++) {
                cancion = cancionesTmp.get(r.nextInt(cancionesTmp.size()));
                cancion.setInterpretes(a.getInterpretes());
                songTmp.add(cancion);
            }
            a.setCanciones(songTmp);
        }
        for (PlayList p: playlistsTmp) {
            ArrayList<Song> listaCanciones = new ArrayList<Song>();
            Song cancion;
            int canciones = r.nextInt(Constants.LONG_PLAYLIST_MAX - 1) + 1;
            for (int i = 0; i < canciones; i++) {
                cancion = cancionesTmp.get(r.nextInt(cancionesTmp.size()));
                listaCanciones.add(cancion);
            }
            p.setCanciones(listaCanciones);
        }
        for (Song s: cancionesTmp) { //Ultimo bucle, se asegura de que no queden canciones sin interprete
            if (s.getInterpretes().isEmpty()) {
                s.getInterpretes().add(artistasTmp.get(r.nextInt(artistasTmp.size())).getNombre());
            }
        }
        this.getMu().setPlaylists(playlistsTmp);
        this.getMu().setArtistas(artistasTmp);
        this.getMu().setAlbumes(albumesTmp);
        this.getMu().setCanciones(cancionesTmp);
        
    }

    
    
}
