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
package main;

import Model.Model;
import View.View;

/**
 *
 * @author jorgecruz@usal.es
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        View v = new View();
        v.pedirImportacion();
        String[] opciones = {"1", "2", "3", "4", "5", "6", "q"};
        v.runMenuPrincipal("1. Generación aleatoria" +
                "\n2. Archivos" +
                "\n3. Álbum" + 
                "\n4. Artista" + 
                "\n5. PlayList" +
                "\n6. Canciones" +
                "\nq. Salir", opciones);
    }
    
}
