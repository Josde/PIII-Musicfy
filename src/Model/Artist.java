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

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Artist implements Serializable {
    private String nombre;
    private String biografia;
    private String instagram;
    private String twitter;
    private String facebook;
    private String wikipedia;
    private ArrayList<String> albumes;

    /**
     *
     * @param nombre
     * @param albumes
     */
    public Artist(String nombre, ArrayList<String> albumes) {
        this.nombre = nombre;
        this.biografia = "Biografía no disponible";
        this.facebook = "@" + nombre + "_fb";
        this.instagram = "@" + nombre + "_ig";
        this.twitter = "@" + nombre + "_tw";
        this.albumes = albumes;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     *
     * @param biografia
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     *
     * @return
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     *
     * @param instagram
     */
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    /**
     *
     * @return
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     *
     * @param twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     *
     * @return
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     *
     * @param facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     *
     * @return
     */
    public String getWikipedia() {
        return wikipedia;
    }

    /**
     *
     * @param wikipedia
     */
    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getAlbumes() {
        return albumes;
    }

    /**
     *
     * @param albumes
     */
    public void setAlbumes(ArrayList<String> albumes) {
        this.albumes = albumes;
    }

    /**
     *
     * @return
     */
    public String[] toStringArray() {
        StringBuilder sb = new StringBuilder();
        String[] ret = new String[7];
        ret[0] = this.nombre;
        ret[1] = this.biografia;
        ret[2] = this.instagram;
        ret[3] = this.twitter;
        ret[4] = this.facebook;
        ret[5] = this.wikipedia;
        for (String s: this.albumes) {
            sb.append(s);
            sb.append(";");
        }
        ret[6] = sb.toString();
        return ret;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static Artist factory(String[] s) {
        Artist a = new Artist();
        String[] strTmp; 
        ArrayList<String> albumes = new ArrayList<String>();
        a.nombre = s[0];
        a.biografia = s[1];
        a.instagram = s[2];
        a.twitter = s[3];
        a.facebook = s[4];
        a.wikipedia = s[5];
        strTmp = s[6].split(";");
        for (String ss: strTmp) {
            albumes.add(ss);
        }
        a.albumes = albumes;
        return a;
    }

    /**
     *
     * @param nombre
     */
    public Artist(String nombre) {
        this.nombre = nombre;
        this.biografia = "Biografía no disponible";
        this.facebook = "@" + nombre + "_fb";
        this.instagram = "@" + nombre + "_ig";
        this.twitter = "@" + nombre + "_tw";
        this.albumes = new ArrayList<String>();
        
    }

    /**
     *
     */
    public Artist() { //Constructor vacío; solo aloca espacio para la lista.
        this.albumes = new ArrayList<String>();
    }
    
    
    
}
