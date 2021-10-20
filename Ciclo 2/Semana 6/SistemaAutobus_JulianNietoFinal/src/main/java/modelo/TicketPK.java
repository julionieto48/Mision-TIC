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
public class TicketPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Numero_Ticket")
    private String numeroTicket;
    @Basic(optional = false)
    @Column(name = "Pasajero_idPasajero")
    private int pasajeroidPasajero;
    @Basic(optional = false)
    @Column(name = "Ruta_Terminal_idTerminal")
    private int rutaTerminalidTerminal;
    @Basic(optional = false)
    @Column(name = "Ruta_Autobus_idAutobus")
    private int rutaAutobusidAutobus;
    @Basic(optional = false)
    @Column(name = "Ruta_idRuta")
    private int rutaidRuta;

    public TicketPK() {
    }

    public TicketPK(String numeroTicket, int pasajeroidPasajero, int rutaTerminalidTerminal, int rutaAutobusidAutobus, int rutaidRuta) {
        this.numeroTicket = numeroTicket;
        this.pasajeroidPasajero = pasajeroidPasajero;
        this.rutaTerminalidTerminal = rutaTerminalidTerminal;
        this.rutaAutobusidAutobus = rutaAutobusidAutobus;
        this.rutaidRuta = rutaidRuta;
    }

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public int getPasajeroidPasajero() {
        return pasajeroidPasajero;
    }

    public void setPasajeroidPasajero(int pasajeroidPasajero) {
        this.pasajeroidPasajero = pasajeroidPasajero;
    }

    public int getRutaTerminalidTerminal() {
        return rutaTerminalidTerminal;
    }

    public void setRutaTerminalidTerminal(int rutaTerminalidTerminal) {
        this.rutaTerminalidTerminal = rutaTerminalidTerminal;
    }

    public int getRutaAutobusidAutobus() {
        return rutaAutobusidAutobus;
    }

    public void setRutaAutobusidAutobus(int rutaAutobusidAutobus) {
        this.rutaAutobusidAutobus = rutaAutobusidAutobus;
    }

    public int getRutaidRuta() {
        return rutaidRuta;
    }

    public void setRutaidRuta(int rutaidRuta) {
        this.rutaidRuta = rutaidRuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroTicket != null ? numeroTicket.hashCode() : 0);
        hash += (int) pasajeroidPasajero;
        hash += (int) rutaTerminalidTerminal;
        hash += (int) rutaAutobusidAutobus;
        hash += (int) rutaidRuta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketPK)) {
            return false;
        }
        TicketPK other = (TicketPK) object;
        if ((this.numeroTicket == null && other.numeroTicket != null) || (this.numeroTicket != null && !this.numeroTicket.equals(other.numeroTicket))) {
            return false;
        }
        if (this.pasajeroidPasajero != other.pasajeroidPasajero) {
            return false;
        }
        if (this.rutaTerminalidTerminal != other.rutaTerminalidTerminal) {
            return false;
        }
        if (this.rutaAutobusidAutobus != other.rutaAutobusidAutobus) {
            return false;
        }
        if (this.rutaidRuta != other.rutaidRuta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TicketPK[ numeroTicket=" + numeroTicket + ", pasajeroidPasajero=" + pasajeroidPasajero + ", rutaTerminalidTerminal=" + rutaTerminalidTerminal + ", rutaAutobusidAutobus=" + rutaAutobusidAutobus + ", rutaidRuta=" + rutaidRuta + " ]";
    }
    
}
