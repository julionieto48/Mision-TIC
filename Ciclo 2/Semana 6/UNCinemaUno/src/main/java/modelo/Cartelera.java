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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "cartelera")
@NamedQueries({
    @NamedQuery(name = "Cartelera.findAll", query = "SELECT c FROM Cartelera c"),
    @NamedQuery(name = "Cartelera.findByPeliculaidPelicula", query = "SELECT c FROM Cartelera c WHERE c.carteleraPK.peliculaidPelicula = :peliculaidPelicula"),
    @NamedQuery(name = "Cartelera.findByCinemaidCinema", query = "SELECT c FROM Cartelera c WHERE c.carteleraPK.cinemaidCinema = :cinemaidCinema"),
    @NamedQuery(name = "Cartelera.findByFunciones", query = "SELECT c FROM Cartelera c WHERE c.funciones = :funciones"),
    @NamedQuery(name = "Cartelera.findByEstado", query = "SELECT c FROM Cartelera c WHERE c.estado = :estado")})
public class Cartelera implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarteleraPK carteleraPK;
    @Column(name = "funciones")
    private Integer funciones;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "Cinema_idCinema", referencedColumnName = "idCinema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cinema cinema;
    @JoinColumn(name = "Pelicula_idPelicula", referencedColumnName = "idPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pelicula pelicula;

    public Cartelera() {
    }

    public Cartelera(CarteleraPK carteleraPK) {
        this.carteleraPK = carteleraPK;
    }

    public Cartelera(int peliculaidPelicula, int cinemaidCinema) {
        this.carteleraPK = new CarteleraPK(peliculaidPelicula, cinemaidCinema);
    }

    public CarteleraPK getCarteleraPK() {
        return carteleraPK;
    }

    public void setCarteleraPK(CarteleraPK carteleraPK) {
        this.carteleraPK = carteleraPK;
    }

    public Integer getFunciones() {
        return funciones;
    }

    public void setFunciones(Integer funciones) {
        this.funciones = funciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carteleraPK != null ? carteleraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartelera)) {
            return false;
        }
        Cartelera other = (Cartelera) object;
        if ((this.carteleraPK == null && other.carteleraPK != null) || (this.carteleraPK != null && !this.carteleraPK.equals(other.carteleraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cartelera[ carteleraPK=" + carteleraPK + " ]";
    }
    
}
