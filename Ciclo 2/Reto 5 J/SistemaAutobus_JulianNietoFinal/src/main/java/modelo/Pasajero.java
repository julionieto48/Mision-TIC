/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author user
 */
@Entity
@Table(name = "pasajero")
@NamedQueries({
    @NamedQuery(name = "Pasajero.findAll", query = "SELECT p FROM Pasajero p"),
    @NamedQuery(name = "Pasajero.findByIdPasajero", query = "SELECT p FROM Pasajero p WHERE p.pasajeroPK.idPasajero = :idPasajero"),
    @NamedQuery(name = "Pasajero.findByCedulapas", query = "SELECT p FROM Pasajero p WHERE p.pasajeroPK.cedulapas = :cedulapas"),
    @NamedQuery(name = "Pasajero.findByNombrepas", query = "SELECT p FROM Pasajero p WHERE p.nombrepas = :nombrepas"),
    @NamedQuery(name = "Pasajero.findBySexopas", query = "SELECT p FROM Pasajero p WHERE p.sexopas = :sexopas"),
    @NamedQuery(name = "Pasajero.findByDireccionpas", query = "SELECT p FROM Pasajero p WHERE p.direccionpas = :direccionpas"),
    @NamedQuery(name = "Pasajero.findByTelefonopas", query = "SELECT p FROM Pasajero p WHERE p.telefonopas = :telefonopas"),
    @NamedQuery(name = "Pasajero.findByVIPpas", query = "SELECT p FROM Pasajero p WHERE p.vIPpas = :vIPpas"),
    @NamedQuery(name = "Pasajero.findByEdadpas", query = "SELECT p FROM Pasajero p WHERE p.edadpas = :edadpas"),
    @NamedQuery(name = "Pasajero.findByUsuarioNombreuser", query = "SELECT p FROM Pasajero p WHERE p.pasajeroPK.usuarioNombreuser = :usuarioNombreuser")})
public class Pasajero implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PasajeroPK pasajeroPK;
    @Column(name = "Nombre_pas")
    private String nombrepas;
    @Column(name = "Sexo_pas")
    private String sexopas;
    @Column(name = "Direccion_pas")
    private String direccionpas;
    @Column(name = "Telefono_pas")
    private Integer telefonopas;
    @Column(name = "VIP_pas")
    private String vIPpas;
    @Column(name = "Edad_pas")
    private String edadpas;
    @JoinColumn(name = "Equipaje_idEquipaje", referencedColumnName = "idEquipaje_eq")
    @ManyToOne
    private Equipaje equipajeidEquipaje;
    @JoinColumn(name = "Usuario_Nombre_user", referencedColumnName = "Nombre_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pasajero")
    private Collection<Ticket> ticketCollection;

    public Pasajero() {
    }

    public Pasajero(PasajeroPK pasajeroPK) {
        this.pasajeroPK = pasajeroPK;
    }

    public Pasajero(int idPasajero, int cedulapas, String usuarioNombreuser) {
        this.pasajeroPK = new PasajeroPK(idPasajero, cedulapas, usuarioNombreuser);
    }

    public PasajeroPK getPasajeroPK() {
        return pasajeroPK;
    }

    public void setPasajeroPK(PasajeroPK pasajeroPK) {
        this.pasajeroPK = pasajeroPK;
    }

    public String getNombrepas() {
        return nombrepas;
    }

    public void setNombrepas(String nombrepas) {
        this.nombrepas = nombrepas;
    }

    public String getSexopas() {
        return sexopas;
    }

    public void setSexopas(String sexopas) {
        this.sexopas = sexopas;
    }

    public String getDireccionpas() {
        return direccionpas;
    }

    public void setDireccionpas(String direccionpas) {
        this.direccionpas = direccionpas;
    }

    public Integer getTelefonopas() {
        return telefonopas;
    }

    public void setTelefonopas(Integer telefonopas) {
        this.telefonopas = telefonopas;
    }

    public String getVIPpas() {
        return vIPpas;
    }

    public void setVIPpas(String vIPpas) {
        this.vIPpas = vIPpas;
    }

    public String getEdadpas() {
        return edadpas;
    }

    public void setEdadpas(String edadpas) {
        this.edadpas = edadpas;
    }

    public Equipaje getEquipajeidEquipaje() {
        return equipajeidEquipaje;
    }

    public void setEquipajeidEquipaje(Equipaje equipajeidEquipaje) {
        this.equipajeidEquipaje = equipajeidEquipaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasajeroPK != null ? pasajeroPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasajero)) {
            return false;
        }
        Pasajero other = (Pasajero) object;
        if ((this.pasajeroPK == null && other.pasajeroPK != null) || (this.pasajeroPK != null && !this.pasajeroPK.equals(other.pasajeroPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pasajero[ pasajeroPK=" + pasajeroPK + " ]";
    }
    
}
