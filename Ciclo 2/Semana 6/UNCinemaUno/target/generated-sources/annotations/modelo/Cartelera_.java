package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CarteleraPK;
import modelo.Cinema;
import modelo.Pelicula;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cartelera.class)
public class Cartelera_ { 

    public static volatile SingularAttribute<Cartelera, String> estado;
    public static volatile SingularAttribute<Cartelera, Pelicula> pelicula;
    public static volatile SingularAttribute<Cartelera, CarteleraPK> carteleraPK;
    public static volatile SingularAttribute<Cartelera, Integer> funciones;
    public static volatile SingularAttribute<Cartelera, Cinema> cinema;

}