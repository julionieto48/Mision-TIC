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
public class CarteleraPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Pelicula_idPelicula")
    private int peliculaidPelicula;
    @Basic(optional = false)
    @Column(name = "Cinema_idCinema")
    private int cinemaidCinema;

    public CarteleraPK() {
    }

    public CarteleraPK(int peliculaidPelicula, int cinemaidCinema) {
        this.peliculaidPelicula = peliculaidPelicula;
        this.cinemaidCinema = cinemaidCinema;
    }

    public int getPeliculaidPelicula() {
        return peliculaidPelicula;
    }

    public void setPeliculaidPelicula(int peliculaidPelicula) {
        this.peliculaidPelicula = peliculaidPelicula;
    }

    public int getCinemaidCinema() {
        return cinemaidCinema;
    }

    public void setCinemaidCinema(int cinemaidCinema) {
        this.cinemaidCinema = cinemaidCinema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) peliculaidPelicula;
        hash += (int) cinemaidCinema;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarteleraPK)) {
            return false;
        }
        CarteleraPK other = (CarteleraPK) object;
        if (this.peliculaidPelicula != other.peliculaidPelicula) {
            return false;
        }
        if (this.cinemaidCinema != other.cinemaidCinema) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CarteleraPK[ peliculaidPelicula=" + peliculaidPelicula + ", cinemaidCinema=" + cinemaidCinema + " ]";
    }
    
}
