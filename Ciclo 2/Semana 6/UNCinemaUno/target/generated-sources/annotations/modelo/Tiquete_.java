package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Horario;
import modelo.Silla;
import modelo.TiquetePK;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-06T14:01:50", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Tiquete.class)
public class Tiquete_ { 

    public static volatile SingularAttribute<Tiquete, TiquetePK> tiquetePK;
    public static volatile SingularAttribute<Tiquete, String> estado;
    public static volatile SingularAttribute<Tiquete, Horario> horario;
    public static volatile SingularAttribute<Tiquete, Silla> silla;

}