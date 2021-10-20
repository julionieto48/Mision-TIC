package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Ruta;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Autobus.class)
public class Autobus_ { 

    public static volatile SingularAttribute<Autobus, String> marca;
    public static volatile SingularAttribute<Autobus, String> compañiabus;
    public static volatile SingularAttribute<Autobus, Integer> capacidadbus;
    public static volatile CollectionAttribute<Autobus, Ruta> rutaCollection;
    public static volatile SingularAttribute<Autobus, String> modelobus;
    public static volatile SingularAttribute<Autobus, Integer> idAutobus;

}