/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ticket")
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByNumeroTicket", query = "SELECT t FROM Ticket t WHERE t.ticketPK.numeroTicket = :numeroTicket"),
    @NamedQuery(name = "Ticket.findByPasajeroidPasajero", query = "SELECT t FROM Ticket t WHERE t.ticketPK.pasajeroidPasajero = :pasajeroidPasajero"),
    @NamedQuery(name = "Ticket.findByRutaTerminalidTerminal", query = "SELECT t FROM Ticket t WHERE t.ticketPK.rutaTerminalidTerminal = :rutaTerminalidTerminal"),
    @NamedQuery(name = "Ticket.findByRutaAutobusidAutobus", query = "SELECT t FROM Ticket t WHERE t.ticketPK.rutaAutobusidAutobus = :rutaAutobusidAutobus"),
    @NamedQuery(name = "Ticket.findByRutaidRuta", query = "SELECT t FROM Ticket t WHERE t.ticketPK.rutaidRuta = :rutaidRuta")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TicketPK ticketPK;
    @JoinColumn(name = "Pasajero_idPasajero", referencedColumnName = "idPasajero", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pasajero pasajero;
    @JoinColumns({
        @JoinColumn(name = "Ruta_Terminal_idTerminal", referencedColumnName = "Terminal_idTerminal", insertable = false, updatable = false),
        @JoinColumn(name = "Ruta_Autobus_idAutobus", referencedColumnName = "Autobus_idAutobus", insertable = false, updatable = false),
        @JoinColumn(name = "Ruta_idRuta", referencedColumnName = "idRuta_rut", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Ruta ruta;

    public Ticket() {
    }

    public Ticket(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public Ticket(String numeroTicket, int pasajeroidPasajero, int rutaTerminalidTerminal, int rutaAutobusidAutobus, int rutaidRuta) {
        this.ticketPK = new TicketPK(numeroTicket, pasajeroidPasajero, rutaTerminalidTerminal, rutaAutobusidAutobus, rutaidRuta);
    }

    public TicketPK getTicketPK() {
        return ticketPK;
    }

    public void setTicketPK(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketPK != null ? ticketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.ticketPK == null && other.ticketPK != null) || (this.ticketPK != null && !this.ticketPK.equals(other.ticketPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ticket[ ticketPK=" + ticketPK + " ]";
    }
    
}
