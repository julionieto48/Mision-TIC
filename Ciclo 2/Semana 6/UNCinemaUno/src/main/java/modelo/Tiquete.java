/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "tiquete")
@NamedQueries({
    @NamedQuery(name = "Tiquete.findAll", query = "SELECT t FROM Tiquete t"),
    @NamedQuery(name = "Tiquete.findBySillaidSilla", query = "SELECT t FROM Tiquete t WHERE t.tiquetePK.sillaidSilla = :sillaidSilla"),
    @NamedQuery(name = "Tiquete.findByHorarioPeliculaidPelicula", query = "SELECT t FROM Tiquete t WHERE t.tiquetePK.horarioPeliculaidPelicula = :horarioPeliculaidPelicula"),
    @NamedQuery(name = "Tiquete.findByHorarioSalaidSala", query = "SELECT t FROM Tiquete t WHERE t.tiquetePK.horarioSalaidSala = :horarioSalaidSala"),
    @NamedQuery(name = "Tiquete.findByEstado", query = "SELECT t FROM Tiquete t WHERE t.estado = :estado")})
public class Tiquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TiquetePK tiquetePK;
    @Column(name = "estado")
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "Horario_Pelicula_idPelicula", referencedColumnName = "Pelicula_idPelicula", insertable = false, updatable = false),
        @JoinColumn(name = "Horario_Sala_idSala", referencedColumnName = "Sala_idSala", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Horario horario;
    @JoinColumn(name = "Silla_idSilla", referencedColumnName = "idSilla", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Silla silla;

    public Tiquete() {
    }

    public Tiquete(TiquetePK tiquetePK) {
        this.tiquetePK = tiquetePK;
    }

    public Tiquete(int sillaidSilla, int horarioPeliculaidPelicula, int horarioSalaidSala) {
        this.tiquetePK = new TiquetePK(sillaidSilla, horarioPeliculaidPelicula, horarioSalaidSala);
    }

    public TiquetePK getTiquetePK() {
        return tiquetePK;
    }

    public void setTiquetePK(TiquetePK tiquetePK) {
        this.tiquetePK = tiquetePK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
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
        hash += (tiquetePK != null ? tiquetePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiquete)) {
            return false;
        }
        Tiquete other = (Tiquete) object;
        if ((this.tiquetePK == null && other.tiquetePK != null) || (this.tiquetePK != null && !this.tiquetePK.equals(other.tiquetePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tiquete[ tiquetePK=" + tiquetePK + " ]";
    }
    
}
