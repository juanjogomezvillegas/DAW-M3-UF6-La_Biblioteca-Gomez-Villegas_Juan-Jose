
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
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Llibre: Entitat de Persistencia que fara referencia a la taula llibre de la Base de Dades
 * 
 * @version 1
 * **/
@Entity
@Table(name="llibre")
public class Llibre {
    
    // Defineix el Camp ID de la taula llibre (clau primaria)
    @Id
    @Column(name="ID")
    private int id;
    
    // Defineix el Camp Titol de la taula llibre
    @Column(name="Titol")
    private String Titol;
    
    // Defineix el Camp Edicio de la taula llibre
    @Column(name="Edicio")
    private int Edicio;
    
    // Defineix el Camp Estat de la taula llibre
    @Column(name="Estat")
    private String Estat;
    
    // Defineix una relació molts a un entre llibre i autor
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="autorID")
    private Autor Autor;
    
    // Defineix una relació molts a molts entre llibre i usuari, amb la taula reserves com a taula intermitja
    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
        name="reserves", 
        joinColumns= @JoinColumn(name="Llibre_ID")
        ,inverseJoinColumns=@JoinColumn(name="Usuari_DNI"))
    private List<Usuari> usuaris;

    /**
     * Llibre: Metode Constructor buit de la Classe Llibre
     * **/
    public Llibre() {
    }

    /**
     * Llibre: Metode Constructor de la Classe Llibre
     * 
     * @param id ID del Llibre
     * @param Titol Titol del Llibre
     * @param Edicio Edició del Llibre
     * @param Estat Estat del Llibre
     * @param Autor Objecte Autor
     * **/
    public Llibre(int id, String Titol, int Edicio, String Estat, Autor Autor) {
        this.id = id;
        this.Titol = Titol;
        this.Edicio = Edicio;
        this.Estat = Estat;
        this.Autor = Autor;
    }

    /**
     * getId: Metode que retorna l'Id del llibre
     * 
     * @return Id del llibre
     * **/
    public int getId() {
        return id;
    }

    /**
     * setId: Metode que estableix l'Id del llibre
     * 
     * @param id Id del llibre
     * **/
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getTitol: Metode que retorna el Titol del llibre
     * 
     * @return Titol del llibre
     * **/
    public String getTitol() {
        return Titol;
    }

    /**
     * setTitol: Metode que estableix el Titol del llibre
     * 
     * @param Titol Titol del llibre
     * **/
    public void setTitol(String Titol) {
        this.Titol = Titol;
    }

    /**
     * getEdicio: Metode que retorna l'Edició del llibre
     * 
     * @return Edició del llibre
     * **/
    public int getEdicio() {
        return Edicio;
    }

    /**
     * setEdicio: Metode que estableix l'Edició del llibre
     * 
     * @param Edicio Edició del llibre
     * **/
    public void setEdicio(int Edicio) {
        this.Edicio = Edicio;
    }

    /**
     * getEstat: Metode que retorna l'Estat del llibre
     * 
     * @return Estat del llibre
     * **/
    public String getEstat() {
        return Estat;
    }

    /**
     * setEstat: Metode que estableix l'Estat del llibre
     * 
     * @param Estat Estat del llibre
     * **/
    public void setEstat(String Estat) {
        this.Estat = Estat;
    }

    /**
     * getAutor: Metode que retorna l'Autor del llibre
     * 
     * @return Autor del llibre (Objecte Autor)
     * **/
    public Autor getAutor() {
        return Autor;
    }

    /**
     * setAutor: Metode que estableix l'Autor del llibre
     * 
     * @param Autor Autor del llibre (Objecte Autor)
     * **/
    public void setAutor(Autor Autor) {
        this.Autor = Autor;
    }

    /**
     * getUsuaris: Metode que retorna la llista d'usuaris que han reservat el llibre
     * 
     * @return llista d'usuaris que han reservat el llibre
     * **/
    public List<Usuari> getUsuaris() {
        return usuaris;
    }

    /**
     * setUsuaris: Metode que estableix una llista d'usuaris que han reservat el llibre
     * 
     * @param usuaris llista d'usuaris que han reservat el llibre
     * **/
    public void setUsuaris(List<Usuari> usuaris) {
        this.usuaris = usuaris;
    }
    
    /**
     * toString: Metode que retornara un String mostrant tots els camps de la taula llibre
     * 
     * @return String builder que mostra tots els camps de la taula llibre
     * **/
    @Override
    public String toString() {
        // Instancia un Objecte StringBuilder
        StringBuilder builder = new StringBuilder();
        
        // Prepara l'objecte StringBuilder
        builder.append("Llibre: [ID=");
        builder.append(id);
        builder.append(", Titol=");
        builder.append(Titol);
        builder.append(", Edicio=");
        builder.append(Edicio);
        builder.append(", Estat=");
        builder.append(Estat);
        builder.append(", Autor=");
        builder.append(Autor);
        builder.append("]");
        
        // Retorna l'objecte StringBuilder
        return builder.toString();
    }
    
}
