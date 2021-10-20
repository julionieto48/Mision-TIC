package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cartelera;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cinema.class)
public class Cinema_ { 

    public static volatile SingularAttribute<Cinema, Integer> idCinema;
    public static volatile SingularAttribute<Cinema, String> ciudad;
    public static volatile SingularAttribute<Cinema, String> direccion;
    public static volatile SingularAttribute<Cinema, Integer> numeroSalas;
    public static volatile CollectionAttribute<Cinema, Cartelera> carteleraCollection;

}