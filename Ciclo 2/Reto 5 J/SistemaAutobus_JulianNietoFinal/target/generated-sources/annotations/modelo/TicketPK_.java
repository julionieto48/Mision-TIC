package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(TicketPK.class)
public class TicketPK_ { 

    public static volatile SingularAttribute<TicketPK, Integer> rutaAutobusidAutobus;
    public static volatile SingularAttribute<TicketPK, Integer> pasajeroidPasajero;
    public static volatile SingularAttribute<TicketPK, Integer> rutaTerminalidTerminal;
    public static volatile SingularAttribute<TicketPK, String> numeroTicket;
    public static volatile SingularAttribute<TicketPK, Integer> rutaidRuta;

}