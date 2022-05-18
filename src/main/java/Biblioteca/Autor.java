
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Autor: Entitat de Persistencia que fara referencia a la taula autor de la Base de Dades
 * 
 * @version 1
 * **/
@Entity
@Table(name="autor")
public class Autor {
    
    // Defineix el Camp ID de la taula autor (clau primaria)
    @Id
    @Column(name="ID")
    private int id;
    
    // Defineix el Camp Nom de la taula autor
    @Column(name="Nom")
    private String Nom;
    
    // Defineix el Camp Cognoms de la taula autor
    @Column(name="Cognoms")
    private String Cognoms;
    
    // Defineix el Camp Pais de la taula autor
    @Column(name="Pais")
    private String Pais;
    
    // Defineix una relació un a molts entre autor i llibre
    @OneToMany(mappedBy="Autor", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Llibre> llibres;

    /**
     * Autor: Metode Constructor buit de la Classe Autor
     * **/
    public Autor() {
    }

    /**
     * Autor: Metode Constructor de la Classe Autor
     * 
     * @param id ID de l'autor
     * @param Nom Nom de l'autor
     * @param Cognoms Cognoms de l'autor
     * @param Pais Pais de l'autor
     * **/
    public Autor(int id, String Nom, String Cognoms, String Pais) {
        this.id = id;
        this.Nom = Nom;
        this.Cognoms = Cognoms;
        this.Pais = Pais;
    }

    /**
     * getId: Metode que retorna l'Id de l'autor
     * 
     * @return Id de l'autor
     * **/
    public int getId() {
        return id;
    }

    /**
     * setId: Metode que estableix l'Id de l'autor
     * 
     * @param id Id de l'autor
     * **/
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getNom: Metode que retorna el Nom de l'autor
     * 
     * @return Nom de l'autor
     * **/
    public String getNom() {
        return Nom;
    }

    /**
     * setNom: Metode que estableix el Nom de l'autor
     * 
     * @param Nom Nom de l'autor
     * **/
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * getCognoms: Metode que retorna els Cognoms de l'autor
     * 
     * @return Cognoms de l'autor
     * **/
    public String getCognoms() {
        return Cognoms;
    }

    /**
     * getCognoms: Metode que estableix els Cognoms de l'autor
     * 
     * @param Cognoms Cognoms de l'autor
     * **/
    public void setCognoms(String Cognoms) {
        this.Cognoms = Cognoms;
    }

    /**
     * getPais: Metode que retorna el Pais de l'autor
     * 
     * @return Pais de l'autor
     * **/
    public String getPais() {
        return Pais;
    }

    /**
     * setPais: Metode que estableix el Pais de l'autor
     * 
     * @param Pais Pais de l'autor
     * **/
    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    /**
     * getLlibres: Metode que retorna la llista de llibres que ha escrit l'autor
     * 
     * @return llista de llibres que ha escrit l'autor
     * **/
    public List<Llibre> getLlibres() {
        return llibres;
    }

    /**
     * setLlibres: Metode que estableix la llista de llibres que ha escrit l'autor
     * 
     * @param llibres llista de llibres que ha escrit l'autor
     * **/
    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }
    
    /**
     * toString: Metode que retornara un String mostrant tots els camps de la taula autor
     * 
     * @return String builder que mostra tots els camps de la taula autor
     * **/
    @Override
    public String toString() {
        // Instancia un Objecte StringBuilder
        StringBuilder builder = new StringBuilder();
        
        // Prepara l'objecte StringBuilder
        builder.append("Autor: [ID=");
        builder.append(id);
        builder.append(", Nom=");
        builder.append(Nom);
        builder.append(", Cognoms=");
        builder.append(Cognoms);
        builder.append(", Pais=");
        builder.append(Pais);
        builder.append("]");
        
        // Retorna l'objecte StringBuilder
        return builder.toString();
    }
    
}
