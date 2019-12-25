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
import com.coti.tools.OpMat;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            this.deserializarMusicfy(musicfyBin);
        } else {
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

    void deserializarMusicfy(File musicfyBin) {
        try {
            FileInputStream archivoEntrada = new FileInputStream(musicfyBin);
            BufferedInputStream bosEntrada = new BufferedInputStream(archivoEntrada);
            ObjectInputStream entrada = new ObjectInputStream(bosEntrada);
            this.mu = (Musicfy) entrada.readObject();
            entrada.close();
            archivoEntrada.close();
        } catch (IOException i) {
            System.err.println("Archivo musicfy.bin no encontrado.");
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.err.println("Clase no encontrada en el archivo.");
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
    
}
