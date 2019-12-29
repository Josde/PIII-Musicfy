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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jorge Cruz García (jorgecruz@usal.es)
 */
public class Auxiliar {

    /**
     * Lee strings hasta que se presiona enter sin escribir nada.
     *
     * @param prompt el texto con el que se indica al usuario lo que hay que
     * introducir
     * @return un ArrayList con todos los Strings introducidos menos el último,
     * que es uno vacío y no se devuelve
     */
    public static ArrayList<String> leerStringHastaVacio(String prompt) {
        ArrayList<String> strTemp = new ArrayList<String>();
        String tmpLine;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(prompt);
            tmpLine = sc.nextLine();
            if (!tmpLine.isBlank()) {
                strTemp.add(tmpLine.trim());
            }
        } while (!tmpLine.isBlank());
        return strTemp;
    }

    /**
     * Modificación de readAllLines para que devuelva String[] en vez de una
     * List
     *
     * @param rutaALeer La ruta del archivo a leer
     * @return String[] tal que cada elemento es una linea del archivo
     */
    public static String[] leerLineasEnArray(Path rutaALeer) {
        List<String> strTmp = new ArrayList<String>();
        if (!rutaALeer.toFile().exists()) {
            return null;
        }
        try {
            strTmp = Files.readAllLines(rutaALeer);
            for (String ln : strTmp) {
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
     * Función auxiliar para generar la tabla de HTML
     *
     * @param sb el StringBuilder donde tenemos nuestro contenido
     * @param tag el tag HTML que añadir
     * @param elemento el contenido de dentro del tag
     */
    public static void anadirTag(StringBuilder sb, String tag, String elemento) {
        sb.append("<").append(tag).append(">");
        sb.append(elemento);
        sb.append("</").append(tag).append(">");
        sb.append("\n");
    }

    /**
     * modificación de anadirTag para usar th, el header de tabla de HTML
     *
     * @param sb
     * @param elemento
     */
    public static void anadirHeader(StringBuilder sb, String elemento) {
        anadirTag(sb, "th", elemento);
    }

    /**
     * modificación de anadirTag para usar td, el header de tabla de HTML
     *
     * @param sb
     * @param elemento
     */
    public static void anadirDato(StringBuilder sb, String elemento) {
        anadirTag(sb, "td", elemento);
    }
}
