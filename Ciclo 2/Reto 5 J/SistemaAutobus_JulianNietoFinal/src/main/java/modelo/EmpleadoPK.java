/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author user
 */
@Embeddable
public class EmpleadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEmpleado_emp")
    private int idEmpleadoemp;
    @Basic(optional = false)
    @Column(name = "Terminal_idTerminal")
    private int terminalidTerminal;

    public EmpleadoPK() {
    }

    public EmpleadoPK(int idEmpleadoemp, int terminalidTerminal) {
        this.idEmpleadoemp = idEmpleadoemp;
        this.terminalidTerminal = terminalidTerminal;
    }

    public int getIdEmpleadoemp() {
        return idEmpleadoemp;
    }

    public void setIdEmpleadoemp(int idEmpleadoemp) {
        this.idEmpleadoemp = idEmpleadoemp;
    }

    public int getTerminalidTerminal() {
        return terminalidTerminal;
    }

    public void setTerminalidTerminal(int terminalidTerminal) {
        this.terminalidTerminal = terminalidTerminal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpleadoemp;
        hash += (int) terminalidTerminal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadoPK)) {
            return false;
        }
        EmpleadoPK other = (EmpleadoPK) object;
        if (this.idEmpleadoemp != other.idEmpleadoemp) {
            return false;
        }
        if (this.terminalidTerminal != other.terminalidTerminal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EmpleadoPK[ idEmpleadoemp=" + idEmpleadoemp + ", terminalidTerminal=" + terminalidTerminal + " ]";
    }
    
}
