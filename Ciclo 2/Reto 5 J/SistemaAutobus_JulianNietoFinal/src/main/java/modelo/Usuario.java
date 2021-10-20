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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByNombreuser", query = "SELECT u FROM Usuario u WHERE u.nombreuser = :nombreuser"),
    @NamedQuery(name = "Usuario.findByContrasenauser", query = "SELECT u FROM Usuario u WHERE u.contrasenauser = :contrasenauser")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nombre_user")
    private String nombreuser;
    @Basic(optional = false)
    @Column(name = "Contrasena_user")
    private String contrasenauser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Pasajero> pasajeroCollection;

    public Usuario() {
    }

    public Usuario(String nombreuser) {
        this.nombreuser = nombreuser;
    }

    public Usuario(String nombreuser, String contrasenauser) {
        this.nombreuser = nombreuser;
        this.contrasenauser = contrasenauser;
    }

    public String getNombreuser() {
        return nombreuser;
    }

    public void setNombreuser(String nombreuser) {
        this.nombreuser = nombreuser;
    }

    public String getContrasenauser() {
        return contrasenauser;
    }

    public void setContrasenauser(String contrasenauser) {
        this.contrasenauser = contrasenauser;
    }

    public Collection<Pasajero> getPasajeroCollection() {
        return pasajeroCollection;
    }

    public void setPasajeroCollection(Collection<Pasajero> pasajeroCollection) {
        this.pasajeroCollection = pasajeroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreuser != null ? nombreuser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nombreuser == null && other.nombreuser != null) || (this.nombreuser != null && !this.nombreuser.equals(other.nombreuser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuario[ nombreuser=" + nombreuser + " ]";
    }
    
}
