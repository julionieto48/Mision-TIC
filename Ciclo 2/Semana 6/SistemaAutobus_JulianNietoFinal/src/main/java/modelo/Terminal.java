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
@Table(name = "terminal")
@NamedQueries({
    @NamedQuery(name = "Terminal.findAll", query = "SELECT t FROM Terminal t"),
    @NamedQuery(name = "Terminal.findByIdTerminalterm", query = "SELECT t FROM Terminal t WHERE t.idTerminalterm = :idTerminalterm"),
    @NamedQuery(name = "Terminal.findByCiudadterm", query = "SELECT t FROM Terminal t WHERE t.ciudadterm = :ciudadterm"),
    @NamedQuery(name = "Terminal.findByAforoterm", query = "SELECT t FROM Terminal t WHERE t.aforoterm = :aforoterm"),
    @NamedQuery(name = "Terminal.findByNumeroempleadosterm", query = "SELECT t FROM Terminal t WHERE t.numeroempleadosterm = :numeroempleadosterm")})
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTerminal_term")
    private Integer idTerminalterm;
    @Column(name = "Ciudad_term")
    private String ciudadterm;
    @Column(name = "Aforo_term")
    private Integer aforoterm;
    @Column(name = "Numero empleados_term")
    private Integer numeroempleadosterm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terminal")
    private Collection<Empleado> empleadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terminal")
    private Collection<Ruta> rutaCollection;

    public Terminal() {
    }

    public Terminal(Integer idTerminalterm) {
        this.idTerminalterm = idTerminalterm;
    }

    public Integer getIdTerminalterm() {
        return idTerminalterm;
    }

    public void setIdTerminalterm(Integer idTerminalterm) {
        this.idTerminalterm = idTerminalterm;
    }

    public String getCiudadterm() {
        return ciudadterm;
    }

    public void setCiudadterm(String ciudadterm) {
        this.ciudadterm = ciudadterm;
    }

    public Integer getAforoterm() {
        return aforoterm;
    }

    public void setAforoterm(Integer aforoterm) {
        this.aforoterm = aforoterm;
    }

    public Integer getNumeroempleadosterm() {
        return numeroempleadosterm;
    }

    public void setNumeroempleadosterm(Integer numeroempleadosterm) {
        this.numeroempleadosterm = numeroempleadosterm;
    }

    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
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
        hash += (idTerminalterm != null ? idTerminalterm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminal)) {
            return false;
        }
        Terminal other = (Terminal) object;
        if ((this.idTerminalterm == null && other.idTerminalterm != null) || (this.idTerminalterm != null && !this.idTerminalterm.equals(other.idTerminalterm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Terminal[ idTerminalterm=" + idTerminalterm + " ]";
    }
    
}
