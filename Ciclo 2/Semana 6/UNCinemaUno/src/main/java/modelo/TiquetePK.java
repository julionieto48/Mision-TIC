/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author user
 */
@Embeddable
public class TiquetePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Silla_idSilla")
    private int sillaidSilla;
    @Basic(optional = false)
    @Column(name = "Horario_Pelicula_idPelicula")
    private int horarioPeliculaidPelicula;
    @Basic(optional = false)
    @Column(name = "Horario_Sala_idSala")
    private int horarioSalaidSala;

    public TiquetePK() {
    }

    public TiquetePK(int sillaidSilla, int horarioPeliculaidPelicula, int horarioSalaidSala) {
        this.sillaidSilla = sillaidSilla;
        this.horarioPeliculaidPelicula = horarioPeliculaidPelicula;
        this.horarioSalaidSala = horarioSalaidSala;
    }

    public int getSillaidSilla() {
        return sillaidSilla;
    }

    public void setSillaidSilla(int sillaidSilla) {
        this.sillaidSilla = sillaidSilla;
    }

    public int getHorarioPeliculaidPelicula() {
        return horarioPeliculaidPelicula;
    }

    public void setHorarioPeliculaidPelicula(int horarioPeliculaidPelicula) {
        this.horarioPeliculaidPelicula = horarioPeliculaidPelicula;
    }

    public int getHorarioSalaidSala() {
        return horarioSalaidSala;
    }

    public void setHorarioSalaidSala(int horarioSalaidSala) {
        this.horarioSalaidSala = horarioSalaidSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sillaidSilla;
        hash += (int) horarioPeliculaidPelicula;
        hash += (int) horarioSalaidSala;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiquetePK)) {
            return false;
        }
        TiquetePK other = (TiquetePK) object;
        if (this.sillaidSilla != other.sillaidSilla) {
            return false;
        }
        if (this.horarioPeliculaidPelicula != other.horarioPeliculaidPelicula) {
            return false;
        }
        if (this.horarioSalaidSala != other.horarioSalaidSala) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TiquetePK[ sillaidSilla=" + sillaidSilla + ", horarioPeliculaidPelicula=" + horarioPeliculaidPelicula + ", horarioSalaidSala=" + horarioSalaidSala + " ]";
    }
    
}
