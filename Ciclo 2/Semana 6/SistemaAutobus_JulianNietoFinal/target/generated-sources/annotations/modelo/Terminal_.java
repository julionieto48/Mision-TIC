package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Empleado;
import modelo.Ruta;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Terminal.class)
public class Terminal_ { 

    public static volatile CollectionAttribute<Terminal, Empleado> empleadoCollection;
    public static volatile SingularAttribute<Terminal, Integer> aforoterm;
    public static volatile SingularAttribute<Terminal, Integer> idTerminalterm;
    public static volatile SingularAttribute<Terminal, String> ciudadterm;
    public static volatile CollectionAttribute<Terminal, Ruta> rutaCollection;
    public static volatile SingularAttribute<Terminal, Integer> numeroempleadosterm;

}