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

package Other;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.String.format;
/**
 *
 * @author Jorge Cruz García (jorgecruz@usal.es)
 */
public class Auxiliar {
    
    /**
     *
     * @param prompt
     * @return
     */
    public static ArrayList<String> leerStringHastaVacio(String prompt) {
        ArrayList<String> strTemp = new ArrayList<String>();
        String tmpLine;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(prompt);
            tmpLine = sc.nextLine();
            if (!tmpLine.equals("")) {
                strTemp.add(tmpLine.trim());
            }
        } while (!tmpLine.equals(""));
        return strTemp;
    }

    /**
     *
     * @param rutaALeer
     * @return
     */
    public static String[] leerLineasEnArray(Path rutaALeer) {
        List<String> strTmp = new ArrayList<String>();
        if (!rutaALeer.toFile().exists()) {
            return null;
        }
        try {
            strTmp = Files.readAllLines(rutaALeer);
            for (String ln: strTmp) {
                if (ln.isBlank()) {
                    strTmp.remove(ln);
                }
            }
            return strTmp.toArray(new String[strTmp.size()]);
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        }
    }
    
    /**
     *
     * @param sb
     * @param tag
     * @param elemento
     */
    public static void anadirTag(StringBuilder sb, String tag, String elemento) {
        sb.append("<").append(tag).append(">");
        sb.append(elemento);
        sb.append("</").append(tag).append(">");
        sb.append("\n");
    }
    
    /**
     *
     * @param sb
     * @param elemento
     */
    public static void anadirHeader(StringBuilder sb, String elemento) {
        anadirTag(sb, "th", elemento);
    }
    
    /**
     *
     * @param sb
     * @param elemento
     */
    public static void anadirDato(StringBuilder sb, String elemento) {
        anadirTag(sb, "td", elemento);
    }
}
