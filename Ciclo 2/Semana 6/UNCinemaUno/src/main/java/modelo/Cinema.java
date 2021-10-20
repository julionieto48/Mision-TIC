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
@Table(name = "cinema")
@NamedQueries({
    @NamedQuery(name = "Cinema.findAll", query = "SELECT c FROM Cinema c"),
    @NamedQuery(name = "Cinema.findByIdCinema", query = "SELECT c FROM Cinema c WHERE c.idCinema = :idCinema"),
    @NamedQuery(name = "Cinema.findByDireccion", query = "SELECT c FROM Cinema c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cinema.findByNumeroSalas", query = "SELECT c FROM Cinema c WHERE c.numeroSalas = :numeroSalas"),
    @NamedQuery(name = "Cinema.findByCiudad", query = "SELECT c FROM Cinema c WHERE c.ciudad = :ciudad")})
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCinema")
    private Integer idCinema;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "numeroSalas")
    private Integer numeroSalas;
    @Column(name = "ciudad")
    private String ciudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
    private Collection<Cartelera> carteleraCollection;

    public Cinema() {
    }

    public Cinema(Integer idCinema) {
        this.idCinema = idCinema;
    }

    public Integer getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(Integer idCinema) {
        this.idCinema = idCinema;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumeroSalas() {
        return numeroSalas;
    }

    public void setNumeroSalas(Integer numeroSalas) {
        this.numeroSalas = numeroSalas;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Collection<Cartelera> getCarteleraCollection() {
        return carteleraCollection;
    }

    public void setCarteleraCollection(Collection<Cartelera> carteleraCollection) {
        this.carteleraCollection = carteleraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCinema != null ? idCinema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cinema)) {
            return false;
        }
        Cinema other = (Cinema) object;
        if ((this.idCinema == null && other.idCinema != null) || (this.idCinema != null && !this.idCinema.equals(other.idCinema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cinema[ idCinema=" + idCinema + " ]";
    }
    
}
