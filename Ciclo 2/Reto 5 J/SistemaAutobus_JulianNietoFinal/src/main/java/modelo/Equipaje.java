/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "equipaje")
@NamedQueries({
    @NamedQuery(name = "Equipaje.findAll", query = "SELECT e FROM Equipaje e"),
    @NamedQuery(name = "Equipaje.findByIdEquipajeeq", query = "SELECT e FROM Equipaje e WHERE e.idEquipajeeq = :idEquipajeeq"),
    @NamedQuery(name = "Equipaje.findByPesoeq", query = "SELECT e FROM Equipaje e WHERE e.pesoeq = :pesoeq"),
    @NamedQuery(name = "Equipaje.findBySobredimensionadoeq", query = "SELECT e FROM Equipaje e WHERE e.sobredimensionadoeq = :sobredimensionadoeq"),
    @NamedQuery(name = "Equipaje.findByValoreq", query = "SELECT e FROM Equipaje e WHERE e.valoreq = :valoreq")})
public class Equipaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEquipaje_eq")
    private String idEquipajeeq;
    @Column(name = "Peso_eq")
    private Integer pesoeq;
    @Column(name = "Sobredimensionado_eq")
    private String sobredimensionadoeq;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Valor_eq")
    private Float valoreq;
    @OneToMany(mappedBy = "equipajeidEquipaje")
    private Collection<Pasajero> pasajeroCollection;

    public Equipaje() {
    }

    public Equipaje(String idEquipajeeq) {
        this.idEquipajeeq = idEquipajeeq;
    }

    public String getIdEquipajeeq() {
        return idEquipajeeq;
    }

    public void setIdEquipajeeq(String idEquipajeeq) {
        this.idEquipajeeq = idEquipajeeq;
    }

    public Integer getPesoeq() {
        return pesoeq;
    }

    public void setPesoeq(Integer pesoeq) {
        this.pesoeq = pesoeq;
    }

    public String getSobredimensionadoeq() {
        return sobredimensionadoeq;
    }

    public void setSobredimensionadoeq(String sobredimensionadoeq) {
        this.sobredimensionadoeq = sobredimensionadoeq;
    }

    public Float getValoreq() {
        return valoreq;
    }

    public void setValoreq(Float valoreq) {
        this.valoreq = valoreq;
    }

    public Collection<Pasajero> getPasajeroCollection() {
        return pasajeroCollection;
    }

    public void setPasajeroCollection(Collection<Pasajero> pasajeroCollection) {
        this.pasajeroCollection = pasajeroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipajeeq != null ? idEquipajeeq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipaje)) {
            return false;
        }
        Equipaje other = (Equipaje) object;
        if ((this.idEquipajeeq == null && other.idEquipajeeq != null) || (this.idEquipajeeq != null && !this.idEquipajeeq.equals(other.idEquipajeeq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Equipaje[ idEquipajeeq=" + idEquipajeeq + " ]";
    }
    
}
