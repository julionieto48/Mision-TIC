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
public class EspectadorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEspectador")
    private int idEspectador;
    @Basic(optional = false)
    @Column(name = "Silla_idSilla")
    private int sillaidSilla;

    public EspectadorPK() {
    }

    public EspectadorPK(int idEspectador, int sillaidSilla) {
        this.idEspectador = idEspectador;
        this.sillaidSilla = sillaidSilla;
    }

    public int getIdEspectador() {
        return idEspectador;
    }

    public void setIdEspectador(int idEspectador) {
        this.idEspectador = idEspectador;
    }

    public int getSillaidSilla() {
        return sillaidSilla;
    }

    public void setSillaidSilla(int sillaidSilla) {
        this.sillaidSilla = sillaidSilla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEspectador;
        hash += (int) sillaidSilla;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspectadorPK)) {
            return false;
        }
        EspectadorPK other = (EspectadorPK) object;
        if (this.idEspectador != other.idEspectador) {
            return false;
        }
        if (this.sillaidSilla != other.sillaidSilla) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EspectadorPK[ idEspectador=" + idEspectador + ", sillaidSilla=" + sillaidSilla + " ]";
    }
    
}
