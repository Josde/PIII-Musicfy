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

import java.util.ArrayList;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Artist {
    private String nombre;
    private String biografia;
    private String instagram;
    private String twitter;
    private String facebook;
    private String wikipedia;
    private ArrayList<String> albumes;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public ArrayList<String> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<String> albumes) {
        this.albumes = albumes;
    }

    
    
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
}
