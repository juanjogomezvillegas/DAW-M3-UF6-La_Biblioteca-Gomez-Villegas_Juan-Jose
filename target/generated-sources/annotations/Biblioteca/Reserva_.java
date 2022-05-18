package Biblioteca;

import Biblioteca.Llibre;
import Biblioteca.Usuari;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-05-10T15:41:02")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, String> DataRetorn;
    public static volatile SingularAttribute<Reserva, String> data;
    public static volatile ListAttribute<Reserva, Llibre> llibres;
    public static volatile SingularAttribute<Reserva, Integer> LlibreId;
    public static volatile ListAttribute<Reserva, Usuari> usuaris;
    public static volatile SingularAttribute<Reserva, Integer> UsuariDni;
    public static volatile SingularAttribute<Reserva, Integer> id;
    public static volatile SingularAttribute<Reserva, String> Estat;

}