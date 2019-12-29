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
import static java.lang.String.format;
import java.util.Calendar;
import java.util.Random;
/**
 *
 * @author jorgecruz@usal.es (Jorge Cruz García, DNI: 21740040A)
 */

public class Album implements Serializable {
    private String titulo;
    private ArrayList<String> interpretes;
    private int anno;
    private String duracion;
    private int numCanciones;
    private ArrayList<Song> canciones;
    private Tipo tipo;

    /**
     * Constructor con 3 datos, y que rellena el resto de campos con valores pseudo aleatorios.
     * @param nombreArtista
     * @param nombreAlbum
     * @param canciones
     */
    public Album(String nombreArtista, String nombreAlbum, ArrayList<Song> canciones) {
        Random r = new Random();
        this.interpretes = new ArrayList<String>();
        this.interpretes.add(nombreArtista);
        this.titulo = nombreAlbum;
        this.canciones = canciones;
        this.anno = r.nextInt(Calendar.getInstance().get(Calendar.YEAR) - Constants.ANO_MIN) + Constants.ANO_MIN;
        this.numCanciones = this.canciones.size();
        this.duracion = (r.nextInt(Constants.MINUTOS_MAX) * this.numCanciones)
                        + " mins " + r.nextInt(60) + " segs";
        if (this.canciones.size() > 1) {
            this.tipo = Tipo.ALBUM;
        } else {
            this.tipo = Tipo.SENCILLO;
        }
    }

    private Album() {
        this.interpretes = new ArrayList<String>();
        this.canciones = new ArrayList<Song>();
    }

    /**
     * Constructor de solo nombre, y el resto de cosas vacías y aleatorias.
     * @param nombreAlbum
     */
    public Album(String nombreAlbum) {
        Random r = new Random();
        this.titulo = nombreAlbum;
        this.numCanciones = r.nextInt(Constants.CANCIONES_MAX - 1) + 1;
        if (this.numCanciones > 1) {
            this.tipo = Tipo.ALBUM;
        } else {
            this.tipo = Tipo.SENCILLO;
        }
        this.anno = r.nextInt(Calendar.getInstance().get(Calendar.YEAR) - Constants.ANO_MIN) + Constants.ANO_MIN;
        this.duracion = (r.nextInt(Constants.MINUTOS_MAX) * this.numCanciones)
                        + " mins " + r.nextInt(60) + " segs";
        this.interpretes = new ArrayList<String>();
        this.canciones = new ArrayList<Song>();
    }

    /**
     *
     * @return
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getInterpretes() {
        return interpretes;
    }

    /**
     *
     * @param interpretes
     */
    public void setInterpretes(ArrayList<String> interpretes) {
        this.interpretes = interpretes;
    }

    /**
     *
     * @return
     */
    public int getAnno() {
        return anno;
    }

    /**
     *
     * @param anno
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }

    /**
     *
     * @return
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     *
     * @param duracion
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     *
     * @return
     */
    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    /**
     *
     * @param canciones
     */
    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    /**
     *
     * @return
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public int getNumCanciones() {
        return numCanciones;
    }

    /**
     *
     * @param numCanciones
     */
    public void setNumCanciones(int numCanciones) {
        this.numCanciones = numCanciones;
    }

    /**
     * Factory de album, el formato del string[] es:
     * titulo, artistas, año, duracion, nº canciones, tipo, cancion1;cancion2
     * @param s
     * @return
     */
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

    /**
     * Devuelve un String[] con los datos del Album en el mismo orden que el factory.
     * @return String[] con los campos en el mismo orden que el factory.
     */
    public String[] toStringArray() {
        StringBuilder sbArtistas = new StringBuilder();
        StringBuilder sbCanciones = new StringBuilder();
        String tipoTmp;
        String[] ret = new String[7];
        for (String s: this.interpretes) {
            sbArtistas.append(s);
            if (this.interpretes.indexOf(s) != this.interpretes.size() - 1) {
                sbArtistas.append(", ");
            }
        }
        for (Song so: this.canciones) {
            sbCanciones.append(so.getTitulo());
            if (this.canciones.indexOf(so) != this.canciones.size() - 1) {
                sbCanciones.append(";");
            }
        }
        if (this.tipo == Tipo.ALBUM) {
            tipoTmp = "Albúm";
        } else {
            tipoTmp = "Sencillo";
        }
        ret[0] = sbArtistas.toString();
        ret[1] = this.titulo;
        ret[2] = sbCanciones.toString();
        ret[3] = Integer.toString(this.anno);
        ret[4] = Integer.toString(this.numCanciones);
        ret[5] = this.duracion;
        ret[6] = tipoTmp;
        return ret;
    }


}
