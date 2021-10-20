/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "espectador")
@NamedQueries({
    @NamedQuery(name = "Espectador.findAll", query = "SELECT e FROM Espectador e"),
    @NamedQuery(name = "Espectador.findByIdEspectador", query = "SELECT e FROM Espectador e WHERE e.espectadorPK.idEspectador = :idEspectador"),
    @NamedQuery(name = "Espectador.findByNombre", query = "SELECT e FROM Espectador e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Espectador.findByTipo", query = "SELECT e FROM Espectador e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Espectador.findByPuntos", query = "SELECT e FROM Espectador e WHERE e.puntos = :puntos"),
    @NamedQuery(name = "Espectador.findByCelular", query = "SELECT e FROM Espectador e WHERE e.celular = :celular"),
    @NamedQuery(name = "Espectador.findBySillaidSilla", query = "SELECT e FROM Espectador e WHERE e.espectadorPK.sillaidSilla = :sillaidSilla")})
public class Espectador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EspectadorPK espectadorPK;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "puntos")
    private Integer puntos;
    @Column(name = "celular")
    private BigInteger celular;
    @JoinColumn(name = "Silla_idSilla", referencedColumnName = "idSilla", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Silla silla;

    public Espectador() {
    }

    public Espectador(EspectadorPK espectadorPK) {
        this.espectadorPK = espectadorPK;
    }

    public Espectador(int idEspectador, int sillaidSilla) {
        this.espectadorPK = new EspectadorPK(idEspectador, sillaidSilla);
    }

    public EspectadorPK getEspectadorPK() {
        return espectadorPK;
    }

    public void setEspectadorPK(EspectadorPK espectadorPK) {
        this.espectadorPK = espectadorPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public BigInteger getCelular() {
        return celular;
    }

    public void setCelular(BigInteger celular) {
        this.celular = celular;
    }

    public Silla getSilla() {
        return silla;
    }

    public void setSilla(Silla silla) {
        this.silla = silla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (espectadorPK != null ? espectadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espectador)) {
            return false;
        }
        Espectador other = (Espectador) object;
        if ((this.espectadorPK == null && other.espectadorPK != null) || (this.espectadorPK != null && !this.espectadorPK.equals(other.espectadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Espectador[ espectadorPK=" + espectadorPK + " ]";
    }
    
}
