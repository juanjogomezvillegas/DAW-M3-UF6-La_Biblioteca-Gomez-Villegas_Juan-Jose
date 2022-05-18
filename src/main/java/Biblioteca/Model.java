
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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Model: Classe que fara servir les Entitats de Persistencia per connectar-se a la Base de Dades
 * 
 * @version 1
 * **/
public class Model {
    
    // Crea l'objecte Scanner
    private final Scanner lector;

    /**
     * Model: Metode Constructor de la Classe Model
     * 
     * @param lector Objecte Scanner
     * **/
    public Model(Scanner lector) {
        // Rep l'objecte Scanner
        this.lector = lector;
    }
    
    /**
     * setAction: Metode que executa l'acció escollida per l'usuari
     * 
     * @param action accio ha realitzar
     * **/
    public void setAction(String action) {
        // Obre una connexió amb la base de dades fent servir l'Unitat de Persistencia definida en el fitxer persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LaBiblioteca");
        EntityManager em = emf.createEntityManager();
        
        // I selecciona l'acció ha realitzar
        switch (action) {
            // Cas AddLlibres, Afegir Llibres
            case "AddLlibres":
                Model.addLlibres(em, lector);
                break;
            // Cas AddReserves, Afegir Reserves
            case "AddReserves":
                Model.addReserves(em, lector);
                break;
            // Cas ActivarLLibres, Activar Llibres
            case "ActivarLLibres":
                Model.setInactivarLLibres("L", em, lector);
                break;
            // Cas InactivarLLibres, Inactivar Llibres
            case "InactivarLLibres":
                Model.setInactivarLLibres("I", em, lector);
                break;
            // Cas RetornarLlibre, Retorna un Llibres
            case "RetornarLlibre":
                Model.setRetornarLlibre(em, lector);
                break;
            // Cas GetLlibresLliures, Mostrar els Llibres Lliures
            case "GetLlibresLliures":
                Model.showLlibresLliures(em);
                break;
            // Cas GetLlibresPendents, Mostra els Llibres Pendents de Retornar
            case "GetLlibresPendents":
                Model.showLlibresPendents(em);
                break;
            // Cas per defecte
            default:
                Utils.setPrintlnError("Error: L'Acció no està disponible !!!");
        }
        
        // Tanca la connexió amb la base de dades
        em.close();
    }
    
    /**
     * getAutoIncrement: Metode que incrementara el valor de la clau primaria d'una taula indicada per parametre
     * 
     * @param em Objecte Entity Manager
     * @param table tabla indicada
     * @param primaryKey clau primaria a incrementar
     * @return següent valor de la clau primaria
     * **/
    private static int getAutoIncrement(EntityManager em, String table, String primaryKey) {
        return Integer.parseInt(em.createQuery("SELECT MAX(i."+primaryKey+") FROM "+table+" i").getSingleResult().toString())+1;
    }
    
    /**
     * addLlibres: Metode que Afegira Llibres nous a la base de dades
     * 
     * @param em Objecte Entity Manager
     * @param lector Objecte Scanner
     * **/
    private static void addLlibres(EntityManager em, Scanner lector) {
        // Demana el Titol del llibre a l'usuari
        boolean correcte = false;
        String titol;
        do {
            Utils.setPrintln("Entra el Titol del Llibre: ");
            titol = lector.next();
            // Valida que el Titol no sigui una cadena buida
            if (!titol.equals("") || !titol.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana l'Edició del llibre a l'usuari
        correcte = false;
        int edicio = 0;
        String edicio2;
        do {
            Utils.setPrintln("Entra l'Edició del Llibre: ");
            edicio2 = lector.next();
            // Valida que l'Edició sigui un numero
            if (Utils.isNumber(edicio2)) {
                edicio = Integer.parseInt(edicio2);
                correcte = true;
            } else {
                Utils.setPrintlnError("L'Edició d'un llibre no pot ser una cadena de text");
            }
        } while (!correcte);
        
        // Demana l'Estat del llibre a l'usuari
        correcte = false;
        String estat;
        do {
            Utils.setPrintln("Entra l'Estat del Llibre (L = lliure, I = inactiu): ");
            estat = lector.next();
            // Valida que l'Estat sigui L = lliure o I = inactiu
            if (estat.equals("L") || estat.equals("I")) {
                correcte = true;
            } else {
                Utils.setPrintlnError("L'Estat d'un llibre només pot ser L = lliure o I = inactiu");
            }
        } while (!correcte);
        
        // Mostra una llista de tots els autors
        List<Autor> autors = em.createQuery("SELECT a FROM Autor a").getResultList();
        for (Autor actual : autors) {
            Utils.setPrintln(actual.toString());
        }
        // Demana l'Id de l'autor del llibre a l'usuari
        correcte = false;
        int autorId = 0;
        do {
            Utils.setPrintln("Entra l'ID del Autor del Llibre: ");
            String autorId2 = lector.next();
            // Valida que l'Id de l'autor sigui un numero
            if (Utils.isNumber(autorId2)) {
                autorId = Integer.parseInt(autorId2);
                correcte = true;
            }
        } while (!correcte);
        // Busca l'autor fent servir l'Id entrat per l'usuari
        Autor a = em.find(Autor.class, autorId);
        // Si l'autor no existeix executa el metode addAutors, per crear un autor nou
        if (a == null) {
            a = Model.addAutors(em, lector);
        }
        
        // Obre una Transacció
        em.getTransaction().begin();
        
        // Instancia un objecte Llibre nou
        Llibre l = new Llibre();
        // Estableix les dades del llibre nou
        l.setId(Model.getAutoIncrement(em,"Llibre","id"));
        l.setTitol(titol);
        l.setEdicio(edicio);
        l.setEstat(estat);
        l.setAutor(a);
        
        // Persisteix els canvis a la cache
        em.persist(l);
        
        // Mostra un missatge
        Utils.setPrintln("El Llibre "+l.getTitol()+" ha estat creat correctament.");
        
        // Fa un commit de la Transacció
        em.getTransaction().commit();
    }
    
    /**
     * addAutors: Metode que Afegira Autors nous a la base de dades
     * 
     * @param em Objecte Entity Manager
     * @param lector Objecte Scanner
     * @return Objecte Autor
     * **/
    private static Autor addAutors(EntityManager em, Scanner lector) {
        // Demana el Nom de l'autor a l'usuari
        boolean correcte = false;
        String nom;
        do {
            Utils.setPrintln("Entra el Nom de l'Autor: ");
            nom = lector.next();
            // Valida que el Nom de l'autor no sigui una cadena buida
            if (!nom.equals("") || !nom.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana els Cognoms de l'autor a l'usuari
        correcte = false;
        String cognom;
        do {
            Utils.setPrintln("Entra el Cognom de l'Autor: ");
            cognom = lector.next();
            // Valida que els Cognoms de l'autor no siguin una cadena buida
            if (!cognom.equals("") || !cognom.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana el Pais de l'autor a l'usuari
        correcte = false;
        String pais;
        do {
            Utils.setPrintln("Entra el País de l'Autor: ");
            pais = lector.next();
            // Valida que el Pais de l'autor no sigui una cadena buida
            if (!pais.equals("") || !pais.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Obre una Transacció
        em.getTransaction().begin();
        
        // Instancia un objecte Autor nou
        Autor a = new Autor();
        // Estableix les dades de l'autor nou
        a.setId(Model.getAutoIncrement(em,"Autor","id"));
        a.setNom(nom);
        a.setCognoms(cognom);
        a.setPais(pais);
        
        // Persisteix els canvis a la cache
        em.persist(a);
        
        // Mostra un missatge
        Utils.setPrintln("L'Autor "+a.getNom()+" "+a.getCognoms()+" ha estat creat correctament.");
        
        // Fa un commit de la Transacció
        em.getTransaction().commit();
        
        // Retorna l'objecte autor
        return a;
    }
    
    /**
     * addReserves: Metode que Afegira Reserves noves a la base de dades
     * 
     * @param em Objecte Entity Manager
     * @param lector Objecte Scanner
     * **/
    private static void addReserves(EntityManager em, Scanner lector) {
        // Mostra tots els usuaris
        List<Usuari> usuaris = em.createQuery("SELECT u FROM Usuari u").getResultList();
        for (Usuari actual : usuaris) {
            Utils.setPrintln(actual.toString());
        }
        // Demana el DNI de l'usuari a l'usuari
        boolean correcte = false;
        String usuariDni = "";
        do {
            Utils.setPrintln("Entra el DNI de l'Usuari: ");
            usuariDni = lector.next();
            // Valida que el DNI no sigui una cadena buida
            if (!usuariDni.equals("") || !usuariDni.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        // Busca l'usuari fent servir el DNI entrat per l'usuari
        Usuari u = em.find(Usuari.class, usuariDni);
        // Si l'usuari no existeix executa el metode addUsuaris, per crear un usuari nou
        if (u == null) {
            u = Model.addUsuaris(em, lector);
        }
        
        // Mostra tots els llibres lliures
        List<Llibre> llibres = em.createQuery("SELECT l FROM Llibre l WHERE l.Estat = 'L'").getResultList();
        for (Llibre actual : llibres) {
            Utils.setPrintln(actual.toString());
        }
        // Demana l'Id del llibre a l'usuari
        correcte = false;
        Llibre l = null;
        int llibreId;
        do {
            Utils.setPrintln("Entra l'Id del Llibre: ");
            String llibreId2 = lector.next();
            // Valida que l'Id del llibre sigui un numero
            if (Utils.isNumber(llibreId2)) {
                // Si es un numero el transforma de String a Integer
                llibreId = Integer.parseInt(llibreId2);
                // Busca el llibre fent servir l'Id entrat per l'usuari
                l = em.find(Llibre.class, llibreId);
                // Si el llibre existeix surt del bucle
                if (l != null) {
                    correcte = true;
                }
            }
        } while (!correcte);
        
        // Demana la Data a l'usuari
        correcte = false;
        String data;
        do {
            Utils.setPrintln("Entra la Data de la Reserva (Format: YYYY-MM-DD): ");
            data = lector.next();
            // Valida que la Data no sigui una cadena buida
            if (!data.equals("") || !data.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana la Data de Retorn a l'usuari
        correcte = false;
        String dataRetorn;
        do {
            Utils.setPrintln("Entra la Data de Retorn (Format: YYYY-MM-DD): ");
            dataRetorn = lector.next();
            // Valida que la Data de Retorn no sigui una cadena buida
            if (!dataRetorn.equals("") || !dataRetorn.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Obre una Transacció
        em.getTransaction().begin();
        
        // Instancia un objecte Reserva nou
        Reserva r = new Reserva();
        // Estableix les dades de la reserva nova
        r.setId(Model.getAutoIncrement(em,"Reserva","id"));
        r.setUsuariDni(u);
        r.setLlibreId(l);
        r.setData(data);
        r.setDataRetorn(dataRetorn);
        r.setEstat("P");
        
        // I actualitza l'estat del llibre
        l.setEstat("R");
        
        // Persisteix els canvis a la cache
        em.persist(r);
        em.persist(l);
        
        // Mostra un missatge
        Utils.setPrintln("La Reserva de l'Usuari "+u.getNom()+" "+u.getCognoms()+" del llibre "+l.getTitol()+" ha estat creada correctament.");
        
        // Fa un commit de la Transacció
        em.getTransaction().commit();
    }
    
    /**
     * addReserves: Metode que Afegira Usuaris nous a la base de dades
     * 
     * @param em Objecte Entity Manager
     * @param lector Objecte Scanner
     * @return Objecte Usuari
     * **/
    private static Usuari addUsuaris(EntityManager em, Scanner lector) {
        // Demana el DNI a l'usuari
        boolean correcte = false;
        String dni;
        do {
            Utils.setPrintln("DNI de l'Usuari: ");
            dni = lector.next();
            // Valida que el DNI no sigui una cadena buida
            if (!dni.equals("") || !dni.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana el Nom a l'usuari
        correcte = false;
        String nom;
        do {
            Utils.setPrintln("Nom de l'Usuari: ");
            nom = lector.next();
            // Valida que el Nom no sigui una cadena buida
            if (!nom.equals("") || !nom.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana el Cognom a l'usuari
        correcte = false;
        String cognom;
        do {
            Utils.setPrintln("Cognom de l'Usuari: ");
            cognom = lector.next();
            // Valida que el Cognom no sigui una cadena buida
            if (!cognom.equals("") || !cognom.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Demana la Població a l'usuari
        correcte = false;
        String poblacio;
        do {
            Utils.setPrintln("Població de l'Usuari: ");
            poblacio = lector.next();
            // Valida que la Població no sigui una cadena buida
            if (!poblacio.equals("") || !poblacio.equals(" ")) {
                correcte = true;
            }
        } while (!correcte);
        
        // Obre una Transacció
        em.getTransaction().begin();
        
        // Instancia un objecte Usuari nou
        Usuari u = new Usuari();
        // Estableix les dades del Usuari nou
        u.setDni(dni);
        u.setNom(nom);
        u.setCognoms(cognom);
        u.setPoblacio(poblacio);
        
        // Persisteix els canvis a la cache
        em.persist(u);
        
        // Mostra un missatge
        Utils.setPrintln("L'Usuari "+u.getNom()+" "+u.getCognoms()+" ha estat creat correctament.");
        
        // Fa un commit de la Transacció
        em.getTransaction().commit();
        
        // Retorna l'objecte usuari
        return u;
    }
    
    /**
     * setInactivarLLibres: Metode que Inactivara o Activara llibres de la Base de Dades
     * 
     * @param em Objecte Entity Manager
     * @param lector Objecte Scanner
     * **/
    private static void setInactivarLLibres(String action, EntityManager em, Scanner lector) {
        List<Llibre> llibres = null;
        // Si l'acció es I
        if (action.equals("I")) {
            // Mostra els llibres lliures
            llibres = Model.getLlibresLliures(em);
            
        // Si l'acció es L
        } else if (action.equals("L")) {
            // Mostra els llibres inactivats
            llibres = Model.getLlibresInactivats(em);
        }
        for (Llibre actual : llibres) {
            Utils.setPrintln(actual.toString());
        }
        
        // Demana l'Id del llibre a l'usuari
        boolean correcte = false;
        int llibreId = 0;
        do {
            if (action.equals("I")) {
                Utils.setPrintln("Entra l'ID del Llibre a Inactivar: ");
            } else if (action.equals("L")) {
                Utils.setPrintln("Entra l'ID del Llibre a Activar: ");
            }
            String llibreId2 = lector.next();
            // Valida que l'Id del llibre sigui un numero
            if (Utils.isNumber(llibreId2)) {
                llibreId = Integer.parseInt(llibreId2);
                correcte = true;
            }
        } while (!correcte);
        
        // Busca el llibre fent servir l'Id entrat per l'usuari
        Llibre l = em.find(Llibre.class, llibreId);
        
        // Si el llibre existeix
        if (l != null) {
            // Obre una Transacció
            em.getTransaction().begin();
            
            //Modifica l'estat del llibre
            if (action.equals("I")) {
                l.setEstat("I");
            } else if (action.equals("L")) {
                l.setEstat("L");
            }
            
            // Persisteix els canvis a la cache
            em.persist(l);
            
            // Mostra un missatge
            if (action.equals("I")) {
                Utils.setPrintln("El Llibre "+l.getTitol()+" ha estat desactivat correctament.");
            } else if (action.equals("L")) {
                Utils.setPrintln("El Llibre "+l.getTitol()+" ha estat activat correctament.");
            }
            
            // Fa un commit de la Transacció
            em.getTransaction().commit();
        } else {
            // Si el llibre no existeix, mostra un error a l'usuari
            Utils.setPrintlnError("El Llibre no existeix");
        }
    }
    
    /**
     * setRetornarLlibre: Metode que Canviara l'estat d'una Reserva i un llibre indicats a Retornat i Lliure
     * 
     * @param em Objecte Entity Manager
     * @param lector Objecte Scanner
     * **/
    private static void setRetornarLlibre(EntityManager em, Scanner lector) {
        // Mostra els llibres pendents de retornar
        List<Reserva> reserves = Model.getLlibresPendents(em);
        for (Reserva actual : reserves) {
            Utils.setPrintln(actual.toString());
        }
        // Demana l'Id de la reserva a l'usuari
        boolean correcte = false;
        Reserva r = null;
        int ReservaId;
        do {
            Utils.setPrintln("Entra l'Id de la Reserva: ");
            String ReservaId2 = lector.next();
            // Valida que l'Id de la reserva sigui un numero
            if (Utils.isNumber(ReservaId2)) {
                // Si es un numero el transforma de String a Integer
                ReservaId = Integer.parseInt(ReservaId2);
                // Busca la reserva fent servir l'Id entrat per l'usuari
                r = em.find(Reserva.class, ReservaId);
                // Si el llibre existeix surt del bucle
                if (r != null) {
                    correcte = true;
                }
            }
        } while (!correcte);
        
        // Obre una Transacció
        em.getTransaction().begin();
        
        // Modifica l'estat de la reserva
        r.setEstat("R");
        
        // Modifica l'estat del llibre
        Llibre l = r.getLlibreId();
        l.setEstat("L");
        
        // Persisteix els canvis a la cache
        em.persist(r);
        em.persist(l);
        
        // Mostra un missatge
        Utils.setPrintln("El Llibre "+l.getTitol()+" ha estat retornat correctament.");
        
        // Fa un commit de la Transacció
        em.getTransaction().commit();
    }
    
    /**
     * getLlibresInactivats: Metode que Retornara els llibres Inactivats
     * 
     * @param em Objecte Entity Manager
     * @return llista de llibres Inactivats
     * **/
    private static List<Llibre> getLlibresInactivats(EntityManager em) {
        return em.createQuery("SELECT l FROM Llibre l WHERE l.Estat = 'I'").getResultList();
    }
    
    /**
     * getLlibresLliures: Metode que Retornara els llibres Lliures
     * 
     * @param em Objecte Entity Manager
     * @return llista de llibres Lliures
     * **/
    private static List<Llibre> getLlibresLliures(EntityManager em) {
        return em.createQuery("SELECT l FROM Llibre l WHERE l.Estat = 'L'").getResultList();
    }
    
    /**
     * showLlibresLliures: Metode que Mostrara els llibres Lliures
     * 
     * @param em Objecte Entity Manager
     * **/
    private static void showLlibresLliures(EntityManager em) {
        // Obté els llibres lliures
        List<Llibre> llibres = Model.getLlibresLliures(em);
        
        // els mostra a la pantalla
        for (Llibre actual : llibres) {
            Utils.setPrintln(actual.toString());
        }
    }
    
    /**
     * getLlibresPendents: Metode que Retornara els llibres Pendents de Retornar
     * 
     * @param em Objecte Entity Manager
     * @return llista de llibres Pendents de Retornar
     * **/
    private static List<Reserva> getLlibresPendents(EntityManager em) {
        return em.createQuery("SELECT r FROM Reserva r WHERE r.Estat = 'P' and r.LlibreId.Estat = 'R'").getResultList();
    }
    
    /**
     * showLlibresPendents: Metode que Mostrara els llibres Pendents de Retornar
     * 
     * @param em Objecte Entity Manager
     * **/    
    private static void showLlibresPendents(EntityManager em) {
        // Obté els llibres pendents de retorna
        List<Reserva> reserves = Model.getLlibresPendents(em);
        
        // els mostra a la pantalla
        for (Reserva actual : reserves) {
            Utils.setPrintln(actual.toString());
        }
    }
    
}
