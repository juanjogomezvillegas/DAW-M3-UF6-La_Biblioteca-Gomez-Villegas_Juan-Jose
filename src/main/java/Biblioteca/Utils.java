
/*
MIT License

Copyright (c) 2022 Juan José Gómez Villegas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

package Biblioteca;

/**
 * Utils: Classe abstracte que contindrà les variables del programa
 * 
 * @version 1
 * **/
public abstract class Utils {
    
    // Crea les variables del programa
    private static final String NameBiblioteca = "Biblioteca ABP"; // nom de la biblioteca
    private static final String Unit = "C"; // unitat del terminal
    
    /**
     * getNameBiblioteca: Metode que retornara el nom de la biblioteca
     * 
     * @return nom de la biblioteca
     * **/
    public static String getNameBiblioteca() {
        return NameBiblioteca;
    }
    
    /**
     * getUnitTerminal: Metode que retornara l'unitat del terminal
     * 
     * @return unitat del terminal
     * **/
    public static String getUnitTerminal() {
        return Unit;
    }
    
    /**
     * isNumber: Metode que comprova si un string es un numero o no
     * 
     * @param numero numero a comprovar
     * @return si el numero entrat per parametre es integer o no
     */
    public static boolean isNumber(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * setPrint: Metode que mostrara un missatge a la consola
     * 
     * @param message missatge que s'imprimira a la pantalla
     * **/
    public static void setPrint(String message) {
        System.out.print(message);
    }
    
    /**
     * setPrintln: Metode que mostrara un missatge a la consola amb un salt de linia
     * 
     * @param message missatge que s'imprimira a la pantalla
     * **/
    public static void setPrintln(String message) {
        System.out.println(message);
    }
    
    /**
     * setPrintError: Metode que mostrara un missatge d'error a la consola
     * 
     * @param message missatge que s'imprimira a la pantalla
     * **/
    public static void setPrintError(String message) {
        System.err.print("Error: "+message+" !!!");
    }
    
    /**
     * setPrintlnError: Metode que mostrara un missatge d'error a la consola amb un salt de linia
     * 
     * @param message missatge que s'imprimira a la pantalla
     * **/
    public static void setPrintlnError(String message) {
        System.err.println("Error: "+message+" !!!");
    }
    
}
