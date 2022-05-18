package Biblioteca;

import Biblioteca.Autor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-10T15:41:02")
@StaticMetamodel(Llibre.class)
public class Llibre_ { 

    public static volatile SingularAttribute<Llibre, Integer> id;
    public static volatile SingularAttribute<Llibre, String> Estat;
    public static volatile SingularAttribute<Llibre, Autor> AutorId;
    public static volatile SingularAttribute<Llibre, String> Titol;
    public static volatile SingularAttribute<Llibre, Integer> Edicio;

}