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

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jorge Cruz García (jorgecruz@usal.es)
 */
public class Auxiliar {
    
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
}
