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
@Table(name = "autobus")
@NamedQueries({
    @NamedQuery(name = "Autobus.findAll", query = "SELECT a FROM Autobus a"),
    @NamedQuery(name = "Autobus.findByIdAutobus", query = "SELECT a FROM Autobus a WHERE a.idAutobus = :idAutobus"),
    @NamedQuery(name = "Autobus.findByCapacidadbus", query = "SELECT a FROM Autobus a WHERE a.capacidadbus = :capacidadbus"),
    @NamedQuery(name = "Autobus.findByMarca", query = "SELECT a FROM Autobus a WHERE a.marca = :marca"),
    @NamedQuery(name = "Autobus.findByModelobus", query = "SELECT a FROM Autobus a WHERE a.modelobus = :modelobus"),
    @NamedQuery(name = "Autobus.findByCompa\u00f1iabus", query = "SELECT a FROM Autobus a WHERE a.compa\u00f1iabus = :compa\u00f1iabus")})
public class Autobus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAutobus")
    private Integer idAutobus;
    @Column(name = "Capacidad_bus")
    private Integer capacidadbus;
    @Column(name = "Marca")
    private String marca;
    @Column(name = "Modelo_bus")
    private String modelobus;
    @Column(name = "Compa\u00f1ia_bus")
    private String compañiabus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autobus")
    private Collection<Ruta> rutaCollection;

    public Autobus() {
    }

    public Autobus(Integer idAutobus) {
        this.idAutobus = idAutobus;
    }

    public Integer getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(Integer idAutobus) {
        this.idAutobus = idAutobus;
    }

    public Integer getCapacidadbus() {
        return capacidadbus;
    }

    public void setCapacidadbus(Integer capacidadbus) {
        this.capacidadbus = capacidadbus;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelobus() {
        return modelobus;
    }

    public void setModelobus(String modelobus) {
        this.modelobus = modelobus;
    }

    public String getCompañiabus() {
        return compañiabus;
    }

    public void setCompañiabus(String compañiabus) {
        this.compañiabus = compañiabus;
    }

    public Collection<Ruta> getRutaCollection() {
        return rutaCollection;
    }

    public void setRutaCollection(Collection<Ruta> rutaCollection) {
        this.rutaCollection = rutaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutobus != null ? idAutobus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autobus)) {
            return false;
        }
        Autobus other = (Autobus) object;
        if ((this.idAutobus == null && other.idAutobus != null) || (this.idAutobus != null && !this.idAutobus.equals(other.idAutobus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autobus[ idAutobus=" + idAutobus + " ]";
    }
    
}
