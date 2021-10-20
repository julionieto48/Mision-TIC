package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Horario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, String> tipo;
    public static volatile SingularAttribute<Sala, String> tipoPantalla;
    public static volatile CollectionAttribute<Sala, Horario> horarioCollection;
    public static volatile SingularAttribute<Sala, Integer> idSala;
    public static volatile SingularAttribute<Sala, Integer> capacidad;

}