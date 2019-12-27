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

import Other.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.String.format;
/**
 *
 * @author jorgecruz@usal.es
 */

public class Album implements Serializable {
    private String titulo;
    private ArrayList<String> interpretes;
    private int anno;
    private String duracion;
    private int numCanciones;
    private ArrayList<Song> canciones;
    private Tipo tipo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(ArrayList<String> interpretes) {
        this.interpretes = interpretes;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getNumCanciones() {
        return numCanciones;
    }

    public void setNumCanciones(int numCanciones) {
        this.numCanciones = numCanciones;
    }
    
    //TODO: Implementar los factories de album, artista y song.
    public static Album factory(String[] s) {
        Album a = new Album();
        ArrayList<Song> canciones = new ArrayList<Song>();
        ArrayList<String> strArtistas = new ArrayList<String>();
        Song songTmp;
        String[] strTmp;    
        a.setTitulo(s[0]);
        strTmp = s[1].split(";");
        for (String str: strTmp) {
            strArtistas.add(str);
        }
        a.setInterpretes(strArtistas);
        a.setAnno(Integer.valueOf(s[2]));
        //TODO: Arreglar duración.
        a.setDuracion(s[3]);
        a.setNumCanciones(Integer.valueOf(s[4]));
        if (s[5].equals("álbum")) {
            a.setTipo(Tipo.ALBUM);
            strTmp = s[6].split(";");
            for (String str: strTmp) {
                songTmp = new Song(str, Integer.valueOf(s[2]), s[3], strArtistas);
                songTmp.setDuracionAleatoria(Constants.MINUTOS_MAX, Constants.MINUTOS_MIN);
                canciones.add(songTmp);
            }
        } else {
            a.setTipo(Tipo.SENCILLO);
            songTmp = new Song(s[0], Integer.valueOf(s[2]), s[3], strArtistas);
            songTmp.setDuracionAleatoria(Constants.MINUTOS_MAX, Constants.MINUTOS_MAX);
            canciones.add(songTmp);
        }
        a.setCanciones(canciones);
        return a;
    }

    @Override
    public String toString() {
        String ret, tipoTmp;
        StringBuilder sbArtistas = new StringBuilder();
        StringBuilder sbCanciones = new StringBuilder();
        for (String s: this.interpretes) {
            sbArtistas.append(s);
            if (this.interpretes.indexOf(s) != this.interpretes.size() - 1) {
                sbArtistas.append(", ");
            }
        }
        for (Song so: this.canciones) {
            sbCanciones.append(so.getTitulo());
            sbCanciones.append("\n");
        }
        if (this.tipo == Tipo.ALBUM) {
            tipoTmp = "Albúm";
        } else {
            tipoTmp = "Sencillo";
        }
        ret = format("%s - %s | %s | %s | %d canciones | %d\nCanciones:\n %s", sbArtistas, 
                this.titulo, this.duracion, tipoTmp, this.numCanciones, this.anno,  sbCanciones);
        return ret;
    }
    
    
}
