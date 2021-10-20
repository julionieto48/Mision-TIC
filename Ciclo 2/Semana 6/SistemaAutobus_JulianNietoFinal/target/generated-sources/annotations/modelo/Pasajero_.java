package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Equipaje;
import modelo.PasajeroPK;
import modelo.Ticket;
import modelo.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Pasajero.class)
public class Pasajero_ { 

    public static volatile SingularAttribute<Pasajero, String> edadpas;
    public static volatile SingularAttribute<Pasajero, String> direccionpas;
    public static volatile SingularAttribute<Pasajero, String> vIPpas;
    public static volatile SingularAttribute<Pasajero, Equipaje> equipajeidEquipaje;
    public static volatile CollectionAttribute<Pasajero, Ticket> ticketCollection;
    public static volatile SingularAttribute<Pasajero, Integer> telefonopas;
    public static volatile SingularAttribute<Pasajero, Usuario> usuario;
    public static volatile SingularAttribute<Pasajero, PasajeroPK> pasajeroPK;
    public static volatile SingularAttribute<Pasajero, String> nombrepas;
    public static volatile SingularAttribute<Pasajero, String> sexopas;

}