
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

// Importa les llibreries següents:
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Usuari: Entitat de Persistencia que fara referencia a la taula usuari de la Base de Dades
 * 
 * @version 1
 * **/
@Entity
@Table(name="usuari")
public class Usuari {
    
    // Defineix el Camp DNI de la taula usuari (clau primaria)
    @Id
    @Column(name="DNI")
    private String dni;
    
    // Defineix el Camp Nom de la taula usuari
    @Column(name="Nom")
    private String Nom;
    
    // Defineix el Camp Cognoms de la taula usuari
    @Column(name="Cognoms")
    private String Cognoms;
    
    // Defineix el Camp Poblacio de la taula usuari
    @Column(name="Poblacio")
    private String Poblacio;
    
    // Defineix una relació molts a molts entre usuari i llibre, amb la taula reserves com a taula intermitja
    @ManyToMany
    @JoinTable(
        name="reserves", 
        joinColumns=@JoinColumn(name="Usuari_DNI")
        ,inverseJoinColumns=@JoinColumn(name="Llibre_ID"))
    private List<Llibre> llibres;

    /**
     * Usuari: Metode Constructor buit de la Classe Usuari
     * **/
    public Usuari() {
    }

    /**
     * Usuari: Metode Constructor de la Classe Usuari
     * 
     * @param dni DNI de l'Usuari
     * @param Nom Nom de l'Usuari
     * @param Cognoms Cognoms de l'Usuari
     * @param Poblacio Població de l'Usuari
     * **/
    public Usuari(String dni, String Nom, String Cognoms, String Poblacio) {
        this.dni = dni;
        this.Nom = Nom;
        this.Cognoms = Cognoms;
        this.Poblacio = Poblacio;
    }

    /**
     * getDni: Metode que retorna el dni de l'usuari
     * 
     * @return dni de l'usuari
     * **/
    public String getDni() {
        return dni;
    }

    /**
     * setDni: Metode que estableix el dni de l'usuari
     * 
     * @param dni dni de l'usuari
     * **/
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * getNom: Metode que retorna el Nom de l'usuari
     * 
     * @return Nom de l'usuari
     * **/
    public String getNom() {
        return Nom;
    }

    /**
     * setNom: Metode que estableix el Nom de l'usuari
     * 
     * @param Nom Nom de l'usuari
     * **/
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * getCognoms: Metode que retorna els Cognoms de l'usuari
     * 
     * @return Cognoms de l'usuari
     * **/
    public String getCognoms() {
        return Cognoms;
    }

    /**
     * setCognoms: Metode que estableix els Cognoms de l'usuari
     * 
     * @param Cognoms Cognoms de l'usuari
     * **/
    public void setCognoms(String Cognoms) {
        this.Cognoms = Cognoms;
    }

    /**
     * getPoblacio: Metode que retorna la Població de l'usuari
     * 
     * @return Població de l'usuari
     * **/
    public String getPoblacio() {
        return Poblacio;
    }

    /**
     * setPoblacio: Metode que estableix la Població de l'usuari
     * 
     * @param Poblacio Població de l'usuari
     * **/
    public void setPoblacio(String Poblacio) {
        this.Poblacio = Poblacio;
    }

    /**
     * getLlibres: Metode que retorna la llista de llibres que ha reservat l'usuari
     * 
     * @return llista de llibres que ha reservat l'usuari
     * **/
    public List<Llibre> getLlibres() {
        return llibres;
    }

    /**
     * getLlibres: Metode que estableix una llista de llibres que ha reservat l'usuari
     * 
     * @param llibres llista de llibres que ha reservat l'usuari
     * **/
    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }
    
    /**
     * toString: Metode que retornara un String mostrant tots els camps de la taula usuari
     * 
     * @return String builder que mostra tots els camps de la taula usuari
     * **/
    @Override
    public String toString() {
        // Instancia un Objecte StringBuilder
        StringBuilder builder = new StringBuilder();
        
        // Prepara l'objecte StringBuilder
        builder.append("Usuari: [DNI=");
        builder.append(dni);
        builder.append(", Nom=");
        builder.append(Nom);
        builder.append(", Cognoms=");
        builder.append(Cognoms);
        builder.append(", Poblacio=");
        builder.append(Poblacio);
        builder.append("]");
        
        // Retorna l'objecte StringBuilder
        return builder.toString();
    }
    
}
