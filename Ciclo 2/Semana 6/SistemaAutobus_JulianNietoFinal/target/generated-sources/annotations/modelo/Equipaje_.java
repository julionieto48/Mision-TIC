package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Pasajero;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Equipaje.class)
public class Equipaje_ { 

    public static volatile SingularAttribute<Equipaje, String> idEquipajeeq;
    public static volatile SingularAttribute<Equipaje, String> sobredimensionadoeq;
    public static volatile CollectionAttribute<Equipaje, Pasajero> pasajeroCollection;
    public static volatile SingularAttribute<Equipaje, Float> valoreq;
    public static volatile SingularAttribute<Equipaje, Integer> pesoeq;

}