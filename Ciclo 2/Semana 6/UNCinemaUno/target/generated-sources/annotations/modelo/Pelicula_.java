package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cartelera;
import modelo.Horario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Pelicula.class)
public class Pelicula_ { 

    public static volatile SingularAttribute<Pelicula, String> descripcion;
    public static volatile CollectionAttribute<Pelicula, Horario> horarioCollection;
    public static volatile SingularAttribute<Pelicula, String> categoria;
    public static volatile SingularAttribute<Pelicula, String> formato;
    public static volatile SingularAttribute<Pelicula, Integer> duracion;
    public static volatile SingularAttribute<Pelicula, Integer> idPelicula;
    public static volatile SingularAttribute<Pelicula, Integer> edadMinima;
    public static volatile SingularAttribute<Pelicula, String> nombre;
    public static volatile CollectionAttribute<Pelicula, Cartelera> carteleraCollection;

}