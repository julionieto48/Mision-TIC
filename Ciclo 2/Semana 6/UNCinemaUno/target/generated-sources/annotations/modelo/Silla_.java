package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Espectador;
import modelo.Tiquete;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Silla.class)
public class Silla_ { 

    public static volatile SingularAttribute<Silla, String> ubicacion;
    public static volatile SingularAttribute<Silla, Integer> numero;
    public static volatile SingularAttribute<Silla, Integer> idSilla;
    public static volatile CollectionAttribute<Silla, Espectador> espectadorCollection;
    public static volatile SingularAttribute<Silla, String> fila;
    public static volatile CollectionAttribute<Silla, Tiquete> tiqueteCollection;

}