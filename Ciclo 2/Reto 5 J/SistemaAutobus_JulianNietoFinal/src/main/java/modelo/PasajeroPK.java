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
public class PasajeroPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idPasajero")
    private int idPasajero;
    @Basic(optional = false)
    @Column(name = "Cedula_pas")
    private int cedulapas;
    @Basic(optional = false)
    @Column(name = "Usuario_Nombre_user")
    private String usuarioNombreuser;

    public PasajeroPK() {
    }

    public PasajeroPK(int idPasajero, int cedulapas, String usuarioNombreuser) {
        this.idPasajero = idPasajero;
        this.cedulapas = cedulapas;
        this.usuarioNombreuser = usuarioNombreuser;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getCedulapas() {
        return cedulapas;
    }

    public void setCedulapas(int cedulapas) {
        this.cedulapas = cedulapas;
    }

    public String getUsuarioNombreuser() {
        return usuarioNombreuser;
    }

    public void setUsuarioNombreuser(String usuarioNombreuser) {
        this.usuarioNombreuser = usuarioNombreuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPasajero;
        hash += (int) cedulapas;
        hash += (usuarioNombreuser != null ? usuarioNombreuser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasajeroPK)) {
            return false;
        }
        PasajeroPK other = (PasajeroPK) object;
        if (this.idPasajero != other.idPasajero) {
            return false;
        }
        if (this.cedulapas != other.cedulapas) {
            return false;
        }
        if ((this.usuarioNombreuser == null && other.usuarioNombreuser != null) || (this.usuarioNombreuser != null && !this.usuarioNombreuser.equals(other.usuarioNombreuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PasajeroPK[ idPasajero=" + idPasajero + ", cedulapas=" + cedulapas + ", usuarioNombreuser=" + usuarioNombreuser + " ]";
    }
    
}
