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
public class RutaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Terminal_idTerminal")
    private int terminalidTerminal;
    @Basic(optional = false)
    @Column(name = "Autobus_idAutobus")
    private int autobusidAutobus;
    @Basic(optional = false)
    @Column(name = "idRuta_rut")
    private int idRutarut;

    public RutaPK() {
    }

    public RutaPK(int terminalidTerminal, int autobusidAutobus, int idRutarut) {
        this.terminalidTerminal = terminalidTerminal;
        this.autobusidAutobus = autobusidAutobus;
        this.idRutarut = idRutarut;
    }

    public int getTerminalidTerminal() {
        return terminalidTerminal;
    }

    public void setTerminalidTerminal(int terminalidTerminal) {
        this.terminalidTerminal = terminalidTerminal;
    }

    public int getAutobusidAutobus() {
        return autobusidAutobus;
    }

    public void setAutobusidAutobus(int autobusidAutobus) {
        this.autobusidAutobus = autobusidAutobus;
    }

    public int getIdRutarut() {
        return idRutarut;
    }

    public void setIdRutarut(int idRutarut) {
        this.idRutarut = idRutarut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) terminalidTerminal;
        hash += (int) autobusidAutobus;
        hash += (int) idRutarut;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaPK)) {
            return false;
        }
        RutaPK other = (RutaPK) object;
        if (this.terminalidTerminal != other.terminalidTerminal) {
            return false;
        }
        if (this.autobusidAutobus != other.autobusidAutobus) {
            return false;
        }
        if (this.idRutarut != other.idRutarut) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RutaPK[ terminalidTerminal=" + terminalidTerminal + ", autobusidAutobus=" + autobusidAutobus + ", idRutarut=" + idRutarut + " ]";
    }
    
}
