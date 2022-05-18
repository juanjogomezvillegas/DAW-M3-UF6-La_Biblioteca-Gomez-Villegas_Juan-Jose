
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
 * Reserva: Entitat de Persistencia que fara referencia a la taula reserves de la Base de Dades
 * La Taula reserves es la taula intermitja de la relació entre la taula usuari i llibre
 * 
 * @version 1
 * **/
@Entity
@Table(name="reserves")
public class Reserva {
    
    // Defineix el Camp ID de la taula reserves (clau primaria)
    @Id
    @Column(name="ID")
    private int id;
    
    // Defineix una relació molts a un entre reserves i usuari
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="Usuari_DNI")
    private Usuari UsuariDni;
    
    // Defineix una relació molts a un entre reserves i llibre
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="Llibre_ID")
    private Llibre LlibreId;
    
    // Defineix el Camp Data de la taula reserves
    @Column(name="Data")
    private String data;
    
    // Defineix el Camp DataRetorn de la taula reserves
    @Column(name="DataRetorn")
    private String DataRetorn;
    
    // Defineix el Camp Estat de la taula reserves
    @Column(name="Estat")
    private String Estat;

    /**
     * Reserva: Metode Constructor buit de la Classe Reserva
     * **/
    public Reserva() {
    }

    /**
     * Reserva: Metode Constructor de la Classe Reserva
     * 
     * @param id ID de la reserva
     * @param UsuariDni Objecte Usuari
     * @param LlibreId Objecte Llibre
     * @param data Data de la reserva
     * @param DataRetorn Data de Retorn de la reserva
     * @param Estat Estat de la reserva
     * **/
    public Reserva(int id, Usuari UsuariDni, Llibre LlibreId, String data, String DataRetorn, String Estat) {
        this.id = id;
        this.UsuariDni = UsuariDni;
        this.LlibreId = LlibreId;
        this.data = data;
        this.DataRetorn = DataRetorn;
        this.Estat = Estat;
    }

    /**
     * getId: Metode que retorna l'Id de la reserva
     * 
     * @return Id de la reserva
     * **/
    public int getId() {
        return id;
    }

    /**
     * setId: Metode que estableix l'Id de la reserva
     * 
     * @param id Id de la reserva
     * **/
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getUsuariDni: Metode que retorna l'usuari que ha fet la reserva
     * 
     * @return usuari que ha fet la reserva (Objecte Usuari)
     * **/
    public Usuari getUsuariDni() {
        return UsuariDni;
    }

    /**
     * setUsuariDni: Metode que estableix l'usuari que ha fet la reserva
     * 
     * @param UsuariDni usuari que ha fet la reserva (Objecte Usuari)
     * **/
    public void setUsuariDni(Usuari UsuariDni) {
        this.UsuariDni = UsuariDni;
    }

    /**
     * getLlibreId: Metode que retorna el llibre del que s'ha fet la reserva
     * 
     * @return llibre del que s'ha fet la reserva (Objecte Llibre)
     * **/
    public Llibre getLlibreId() {
        return LlibreId;
    }

    /**
     * setLlibreId: Metode que estableix el llibre del que s'ha fet la reserva
     * 
     * @param LlibreId llibre del que s'ha fet la reserva (Objecte Llibre)
     * **/
    public void setLlibreId(Llibre LlibreId) {
        this.LlibreId = LlibreId;
    }

    /**
     * getData: Metode que retorna la data de la reserva
     * 
     * @return data de la reserva
     * **/
    public String getData() {
        return data;
    }

    /**
     * setData: Metode que estableix la data de la reserva
     * 
     * @param data data de la reserva
     * **/
    public void setData(String data) {
        this.data = data;
    }

    /**
     * getDataRetorn: Metode que retorna la data de Retorn de la reserva
     * 
     * @return data de Retorn de la reserva
     * **/
    public String getDataRetorn() {
        return DataRetorn;
    }

    /**
     * setDataRetorn: Metode que estableix la data de Retorn de la reserva
     * 
     * @param DataRetorn data de Retorn de la reserva
     * **/
    public void setDataRetorn(String DataRetorn) {
        this.DataRetorn = DataRetorn;
    }

    /**
     * getEstat: Metode que retorna l'Estat de la reserva
     * 
     * @return Estat de la reserva
     * **/
    public String getEstat() {
        return Estat;
    }
    
    /**
     * setEstat: Metode que estableix l'Estat de la reserva
     * 
     * @param Estat Estat de la reserva
     * **/
    public void setEstat(String Estat) {
        this.Estat = Estat;
    }
    
    /**
     * toString: Metode que retornara un String mostrant tots els camps de la taula reserves
     * 
     * @return String builder que mostra tots els camps de la taula reserves
     * **/
    @Override
    public String toString() {
        // Instancia un Objecte StringBuilder
        StringBuilder builder = new StringBuilder();
        
        // Prepara l'objecte StringBuilder
        builder.append("Reserva: [id=");
        builder.append(id);
        builder.append(", Usuari=");
        builder.append(UsuariDni);
        builder.append(", Llibre=");
        builder.append(LlibreId);
        builder.append(", data=");
        builder.append(data);
        builder.append(", DataRetorn=");
        builder.append(DataRetorn);
        builder.append(", Estat=");
        builder.append(Estat);
        builder.append("]");
        
        // Retorna l'objecte StringBuilder
        return builder.toString();
    }
    
}
