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
@Table(name = "horario")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByPeliculaidPelicula", query = "SELECT h FROM Horario h WHERE h.horarioPK.peliculaidPelicula = :peliculaidPelicula"),
    @NamedQuery(name = "Horario.findBySalaidSala", query = "SELECT h FROM Horario h WHERE h.horarioPK.salaidSala = :salaidSala"),
    @NamedQuery(name = "Horario.findByFecha", query = "SELECT h FROM Horario h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Horario.findByEstado", query = "SELECT h FROM Horario h WHERE h.estado = :estado"),
    @NamedQuery(name = "Horario.findByDisponibiliad", query = "SELECT h FROM Horario h WHERE h.disponibiliad = :disponibiliad")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorarioPK horarioPK;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "estado")
    private String estado;
    @Column(name = "disponibiliad")
    private Integer disponibiliad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horario")
    private Collection<Tiquete> tiqueteCollection;
    @JoinColumn(name = "Pelicula_idPelicula", referencedColumnName = "idPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pelicula pelicula;
    @JoinColumn(name = "Sala_idSala", referencedColumnName = "idSala", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sala sala;

    public Horario() {
    }

    public Horario(HorarioPK horarioPK) {
        this.horarioPK = horarioPK;
    }

    public Horario(int peliculaidPelicula, int salaidSala) {
        this.horarioPK = new HorarioPK(peliculaidPelicula, salaidSala);
    }

    public HorarioPK getHorarioPK() {
        return horarioPK;
    }

    public void setHorarioPK(HorarioPK horarioPK) {
        this.horarioPK = horarioPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getDisponibiliad() {
        return disponibiliad;
    }

    public void setDisponibiliad(Integer disponibiliad) {
        this.disponibiliad = disponibiliad;
    }

    public Collection<Tiquete> getTiqueteCollection() {
        return tiqueteCollection;
    }

    public void setTiqueteCollection(Collection<Tiquete> tiqueteCollection) {
        this.tiqueteCollection = tiqueteCollection;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horarioPK != null ? horarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horarioPK == null && other.horarioPK != null) || (this.horarioPK != null && !this.horarioPK.equals(other.horarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Horario[ horarioPK=" + horarioPK + " ]";
    }
    
}
