package Biblioteca;

import Biblioteca.Llibre;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-10T15:41:02")
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile ListAttribute<Autor, Llibre> llibres;
    public static volatile SingularAttribute<Autor, String> Cognoms;
    public static volatile SingularAttribute<Autor, Integer> id;
    public static volatile SingularAttribute<Autor, String> Nom;
    public static volatile SingularAttribute<Autor, String> Pais;

}