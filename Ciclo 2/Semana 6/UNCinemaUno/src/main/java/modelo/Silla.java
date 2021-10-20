/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "silla")
@NamedQueries({
    @NamedQuery(name = "Silla.findAll", query = "SELECT s FROM Silla s"),
    @NamedQuery(name = "Silla.findByIdSilla", query = "SELECT s FROM Silla s WHERE s.idSilla = :idSilla"),
    @NamedQuery(name = "Silla.findByFila", query = "SELECT s FROM Silla s WHERE s.fila = :fila"),
    @NamedQuery(name = "Silla.findByNumero", query = "SELECT s FROM Silla s WHERE s.numero = :numero"),
    @NamedQuery(name = "Silla.findByUbicacion", query = "SELECT s FROM Silla s WHERE s.ubicacion = :ubicacion")})
public class Silla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idSilla")
    private Integer idSilla;
    @Column(name = "fila")
    private String fila;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "ubicacion")
    private String ubicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "silla")
    private Collection<Tiquete> tiqueteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "silla")
    private Collection<Espectador> espectadorCollection;

    public Silla() {
    }

    public Silla(Integer idSilla) {
        this.idSilla = idSilla;
    }

    public Integer getIdSilla() {
        return idSilla;
    }

    public void setIdSilla(Integer idSilla) {
        this.idSilla = idSilla;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Collection<Tiquete> getTiqueteCollection() {
        return tiqueteCollection;
    }

    public void setTiqueteCollection(Collection<Tiquete> tiqueteCollection) {
        this.tiqueteCollection = tiqueteCollection;
    }

    public Collection<Espectador> getEspectadorCollection() {
        return espectadorCollection;
    }

    public void setEspectadorCollection(Collection<Espectador> espectadorCollection) {
        this.espectadorCollection = espectadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSilla != null ? idSilla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Silla)) {
            return false;
        }
        Silla other = (Silla) object;
        if ((this.idSilla == null && other.idSilla != null) || (this.idSilla != null && !this.idSilla.equals(other.idSilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Silla[ idSilla=" + idSilla + " ]";
    }
    
}
