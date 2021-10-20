/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "empleado")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdEmpleadoemp", query = "SELECT e FROM Empleado e WHERE e.empleadoPK.idEmpleadoemp = :idEmpleadoemp"),
    @NamedQuery(name = "Empleado.findByTipoemp", query = "SELECT e FROM Empleado e WHERE e.tipoemp = :tipoemp"),
    @NamedQuery(name = "Empleado.findByTurnoemp", query = "SELECT e FROM Empleado e WHERE e.turnoemp = :turnoemp"),
    @NamedQuery(name = "Empleado.findByTerminalidTerminal", query = "SELECT e FROM Empleado e WHERE e.empleadoPK.terminalidTerminal = :terminalidTerminal")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpleadoPK empleadoPK;
    @Column(name = "Tipo_emp")
    private String tipoemp;
    @Column(name = "Turno_emp")
    @Temporal(TemporalType.DATE)
    private Date turnoemp;
    @JoinColumn(name = "Terminal_idTerminal", referencedColumnName = "idTerminal_term", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Terminal terminal;

    public Empleado() {
    }

    public Empleado(EmpleadoPK empleadoPK) {
        this.empleadoPK = empleadoPK;
    }

    public Empleado(int idEmpleadoemp, int terminalidTerminal) {
        this.empleadoPK = new EmpleadoPK(idEmpleadoemp, terminalidTerminal);
    }

    public EmpleadoPK getEmpleadoPK() {
        return empleadoPK;
    }

    public void setEmpleadoPK(EmpleadoPK empleadoPK) {
        this.empleadoPK = empleadoPK;
    }

    public String getTipoemp() {
        return tipoemp;
    }

    public void setTipoemp(String tipoemp) {
        this.tipoemp = tipoemp;
    }

    public Date getTurnoemp() {
        return turnoemp;
    }

    public void setTurnoemp(Date turnoemp) {
        this.turnoemp = turnoemp;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empleadoPK != null ? empleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empleadoPK == null && other.empleadoPK != null) || (this.empleadoPK != null && !this.empleadoPK.equals(other.empleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Empleado[ empleadoPK=" + empleadoPK + " ]";
    }
    
}
