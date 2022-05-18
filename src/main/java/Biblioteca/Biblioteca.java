
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

// Importa les següents llibreries:
import java.util.Scanner;

/**
 * Biblioteca: Classe que instanciara la classe Model i la classe Scanner
 * 
 * @version 1
 * **/
public class Biblioteca {
    
    // Crea els objectes Scanner i Model
    private final Scanner lector;
    private final Model model;
    
    /**
     * Biblioteca: Metode Constructor de la Classe Biblioteca
     * **/
    public Biblioteca() {
        // Instancia l'objecte Scanner
        lector = new Scanner(System.in);
        // Estableix el patró delimitant de l'objecte Scanner en un salt de linia
        lector.useDelimiter("\n");
        
        // Instancia l'objecte Model
        model = new Model(lector);
        
        // Executa el metode set
        Biblioteca.set(lector, model);
    }
    
    /**
     * set: Metode que executara la Classe Model
     * 
     * @param lector Objecte Scanner
     * @param model Objecte Model
     * **/
    public static void set(Scanner lector, Model model) {
        String opcio;
        
        // Mostra un missatge de benvinguda a l'usuari i el copyright del gestor de reserves
        Utils.setPrintln(" Benvinguts a la Biblioteca ABP, Versió 1.0 ");
        Utils.setPrintln(" Copyright (c) 2022 Juan José Gómez Villegas ");
        
        do {
            // Demana a l'usuari una de les següents opcions:
            Utils.setPrintln("");
            Utils.setPrintln("----------------------------------------------------------");
            Utils.setPrintln(" "+Utils.getNameBiblioteca()+" ");
            Utils.setPrintln("----------------------------------------------------------");
            Utils.setPrintln(" [-1 ] Sortir. ");
            Utils.setPrintln(" [ 0 ] Ajuda. ");
            Utils.setPrintln(" [ 1 ] Afegir Llibres. ");
            Utils.setPrintln(" [ 2 ] Afegir Reserves. ");
            Utils.setPrintln(" [ 3 ] Activar Llibres. ");
            Utils.setPrintln(" [ 4 ] Inactivar Llibres. ");
            Utils.setPrintln(" [ 5 ] Retorna un llibre. ");
            Utils.setPrintln(" [ 6 ] Llistar els llibres lliures mostrant l'autor. ");
            Utils.setPrintln(" [ 7 ] Mostrar els llibres pendents de retornar. ");
            Utils.setPrintln("----------------------------------------------------------");
            Utils.setPrintln(Utils.getUnitTerminal()+":\\ ");
            // Guarda l'opció escollida per l'usuari
            opcio = lector.next();
            
            // I selecciona la opció seleccionada per l'usuari
            switch (opcio) {
                // Cas -1, Sortir del Programa
                case "-1":
                    break;
                // Cas 0, Mostra una Ajuda a l'Usuari
                case "0":
                    Biblioteca.setHelp();
                    break;
                // Cas 1, Afegir llibres
                case "1":
                    model.setAction("AddLlibres");
                    break;
                // Cas 2, Afegir Reserves
                case "2":
                    model.setAction("AddReserves");
                    break;
                // Cas 3, Activar Llibres
                case "3":
                    model.setAction("ActivarLLibres");
                    break;
                // Cas 4, Inactivar Llibres
                case "4":
                    model.setAction("InactivarLLibres");
                    break;
                // Cas 5, Retorna un Llibre
                case "5":
                    model.setAction("RetornarLlibre");
                    break;
                // Cas 6, Mostrar els Llibres Lliures
                case "6":
                    model.setAction("GetLlibresLliures");
                    break;
                // Cas 7, Mostrar els Llibres Pendents de Retornar
                case "7":
                    model.setAction("GetLlibresPendents");
                    break;
                // Cas per defecte
                default:
                    Utils.setPrintlnError("La Opció "+opcio+" no està disponible");
            }
        } while (!opcio.equals("-1"));
    }
    
    /**
     * setHelp: Metode que mostrara una Ajuda a l'Usuari
     * **/
    private static void setHelp() {
        Utils.setPrintln(" -1 => Sortir. ");
        Utils.setPrintln("  0 => Ajuda. ");
        Utils.setPrintln("  1 => Afegir Llibres. ");
        Utils.setPrintln("  2 => Afegir Reserves. ");
        Utils.setPrintln("  3 => Activar Llibres. ");
        Utils.setPrintln("  4 => Inactivar Llibres. ");
        Utils.setPrintln("  5 => Retorna un llibre. ");
        Utils.setPrintln("  6 => Llistar els llibres lliures mostrant l'autor. ");
        Utils.setPrintln("  7 => Mostrar els llibres pendents de retornar. ");
    }
    
}
