package modelo;

import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.EspectadorPK;
import modelo.Silla;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Espectador.class)
public class Espectador_ { 

    public static volatile SingularAttribute<Espectador, String> tipo;
    public static volatile SingularAttribute<Espectador, EspectadorPK> espectadorPK;
    public static volatile SingularAttribute<Espectador, BigInteger> celular;
    public static volatile SingularAttribute<Espectador, Integer> puntos;
    public static volatile SingularAttribute<Espectador, String> nombre;
    public static volatile SingularAttribute<Espectador, Silla> silla;

}