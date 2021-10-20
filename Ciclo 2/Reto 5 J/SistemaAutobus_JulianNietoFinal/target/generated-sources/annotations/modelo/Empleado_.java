package modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.EmpleadoPK;
import modelo.Terminal;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-10T21:06:37", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, EmpleadoPK> empleadoPK;
    public static volatile SingularAttribute<Empleado, String> tipoemp;
    public static volatile SingularAttribute<Empleado, Terminal> terminal;
    public static volatile SingularAttribute<Empleado, Date> turnoemp;

}