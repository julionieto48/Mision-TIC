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
public class HorarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Pelicula_idPelicula")
    private int peliculaidPelicula;
    @Basic(optional = false)
    @Column(name = "Sala_idSala")
    private int salaidSala;

    public HorarioPK() {
    }

    public HorarioPK(int peliculaidPelicula, int salaidSala) {
        this.peliculaidPelicula = peliculaidPelicula;
        this.salaidSala = salaidSala;
    }

    public int getPeliculaidPelicula() {
        return peliculaidPelicula;
    }

    public void setPeliculaidPelicula(int peliculaidPelicula) {
        this.peliculaidPelicula = peliculaidPelicula;
    }

    public int getSalaidSala() {
        return salaidSala;
    }

    public void setSalaidSala(int salaidSala) {
        this.salaidSala = salaidSala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) peliculaidPelicula;
        hash += (int) salaidSala;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioPK)) {
            return false;
        }
        HorarioPK other = (HorarioPK) object;
        if (this.peliculaidPelicula != other.peliculaidPelicula) {
            return false;
        }
        if (this.salaidSala != other.salaidSala) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.HorarioPK[ peliculaidPelicula=" + peliculaidPelicula + ", salaidSala=" + salaidSala + " ]";
    }
    
}
