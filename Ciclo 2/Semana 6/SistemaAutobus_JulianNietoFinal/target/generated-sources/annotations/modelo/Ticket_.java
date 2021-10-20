package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Pasajero;
import modelo.Ruta;
import modelo.TicketPK;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Pasajero> pasajero;
    public static volatile SingularAttribute<Ticket, Ruta> ruta;
    public static volatile SingularAttribute<Ticket, TicketPK> ticketPK;

}