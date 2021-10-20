/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "ruta")
@NamedQueries({
    @NamedQuery(name = "Ruta.findAll", query = "SELECT r FROM Ruta r"),
    @NamedQuery(name = "Ruta.findByTerminalidTerminal", query = "SELECT r FROM Ruta r WHERE r.rutaPK.terminalidTerminal = :terminalidTerminal"),
    @NamedQuery(name = "Ruta.findByAutobusidAutobus", query = "SELECT r FROM Ruta r WHERE r.rutaPK.autobusidAutobus = :autobusidAutobus"),
    @NamedQuery(name = "Ruta.findBySillasdisponiblesrut", query = "SELECT r FROM Ruta r WHERE r.sillasdisponiblesrut = :sillasdisponiblesrut"),
    @NamedQuery(name = "Ruta.findByFechadesalidarut", query = "SELECT r FROM Ruta r WHERE r.fechadesalidarut = :fechadesalidarut"),
    @NamedQuery(name = "Ruta.findByPreciorut", query = "SELECT r FROM Ruta r WHERE r.preciorut = :preciorut"),
    @NamedQuery(name = "Ruta.findByIdRutarut", query = "SELECT r FROM Ruta r WHERE r.rutaPK.idRutarut = :idRutarut"),
    @NamedQuery(name = "Ruta.findByDestinoRuta", query = "SELECT r FROM Ruta r WHERE r.destinoRuta = :destinoRuta")})
public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RutaPK rutaPK;
    @Column(name = "Sillas_disponibles_rut")
    private Integer sillasdisponiblesrut;
    @Column(name = "Fecha_de_salida_rut")
    @Temporal(TemporalType.DATE)
    private Date fechadesalidarut;
    @Column(name = "Precio_rut")
    private Integer preciorut;
    @Column(name = "Destino_Ruta")
    private String destinoRuta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruta")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "Autobus_idAutobus", referencedColumnName = "idAutobus", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autobus autobus;
    @JoinColumn(name = "Terminal_idTerminal", referencedColumnName = "idTerminal_term", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Terminal terminal;

    public Ruta() {
    }

    public Ruta(RutaPK rutaPK) {
        this.rutaPK = rutaPK;
    }

    public Ruta(int terminalidTerminal, int autobusidAutobus, int idRutarut) {
        this.rutaPK = new RutaPK(terminalidTerminal, autobusidAutobus, idRutarut);
    }

    public RutaPK getRutaPK() {
        return rutaPK;
    }

    public void setRutaPK(RutaPK rutaPK) {
        this.rutaPK = rutaPK;
    }

    public Integer getSillasdisponiblesrut() {
        return sillasdisponiblesrut;
    }

    public void setSillasdisponiblesrut(Integer sillasdisponiblesrut) {
        this.sillasdisponiblesrut = sillasdisponiblesrut;
    }

    public Date getFechadesalidarut() {
        return fechadesalidarut;
    }

    public void setFechadesalidarut(Date fechadesalidarut) {
        this.fechadesalidarut = fechadesalidarut;
    }

    public Integer getPreciorut() {
        return preciorut;
    }

    public void setPreciorut(Integer preciorut) {
        this.preciorut = preciorut;
    }

    public String getDestinoRuta() {
        return destinoRuta;
    }

    public void setDestinoRuta(String destinoRuta) {
        this.destinoRuta = destinoRuta;
    }

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
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
        hash += (rutaPK != null ? rutaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;
        if ((this.rutaPK == null && other.rutaPK != null) || (this.rutaPK != null && !this.rutaPK.equals(other.rutaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ruta[ rutaPK=" + rutaPK + " ]";
    }
    
}
