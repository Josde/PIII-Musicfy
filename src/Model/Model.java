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

import Other.ComparadorAlbum;
import Other.ComparadorSong;
import Other.Constants;
import com.coti.tools.OpMat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Model {
    Musicfy mu = new Musicfy();
    //TODO: Añadir exportToDisk.
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

    public Musicfy getMu() {
        return mu;
    }

    public void setMu(Musicfy mu) {
        this.mu = mu;
    }

    public void sortCanciones() {
        Comparator<Song> c = new ComparadorSong();
        Collections.sort(this.mu.getCanciones(), c);
    }
    
    public void sortAlbumes() {
        Comparator<Album> a = new ComparadorAlbum();
        Collections.sort(this.mu.getAlbumes(), a);
    }

    public void anadirPlaylist(PlayList plTemp) {
        this.getMu().getPlaylists().add(plTemp);
    }

    public void exportarArtistas() {
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
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void anadirArtista(Artist a) {
        this.getMu().getArtistas().add(a);
    }

    public void borrarArtista(Artist a) {
        this.getMu().getArtistas().remove(a);
    }

    public void anadirCancion(Song so) {
        this.getMu().getCanciones().add(so);
    }

    public void anadirAlbum(Album a) {
        this.getMu().getAlbumes().add(a);
    }
    
    public void borrarCancion(Song so) {
        this.getMu().getCanciones().remove(so);
    }

    public void borrarAlbum(Album a) {
        this.getMu().getAlbumes().remove(a);
    }
    
}
