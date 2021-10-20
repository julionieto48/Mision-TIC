package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Autobus;
import modelo.RutaPK;
import modelo.Terminal;
import modelo.Ticket;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Ruta.class)
public class Ruta_ { 

    public static volatile SingularAttribute<Ruta, Date> fechadesalidarut;
    public static volatile SingularAttribute<Ruta, Integer> sillasdisponiblesrut;
    public static volatile SingularAttribute<Ruta, RutaPK> rutaPK;
    public static volatile SingularAttribute<Ruta, String> destinoRuta;
    public static volatile CollectionAttribute<Ruta, Ticket> ticketCollection;
    public static volatile SingularAttribute<Ruta, Autobus> autobus;
    public static volatile SingularAttribute<Ruta, Terminal> terminal;
    public static volatile SingularAttribute<Ruta, Integer> preciorut;

}