package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.HorarioPK;
import modelo.Pelicula;
import modelo.Sala;
import modelo.Tiquete;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Horario.class)
public class Horario_ { 

    public static volatile SingularAttribute<Horario, Date> fecha;
    public static volatile SingularAttribute<Horario, String> estado;
    public static volatile SingularAttribute<Horario, Pelicula> pelicula;
    public static volatile SingularAttribute<Horario, Integer> disponibiliad;
    public static volatile SingularAttribute<Horario, Sala> sala;
    public static volatile CollectionAttribute<Horario, Tiquete> tiqueteCollection;
    public static volatile SingularAttribute<Horario, HorarioPK> horarioPK;

}