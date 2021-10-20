/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

/**
 *
 * @author USUARIO
 */
public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private int precio;
    private String tipo;

    public Producto() {
        idProducto=0;
        nombreProducto="Por definir";
        precio=0;
        cantidad=0;
        tipo="Alimenticio";
    }

    public Producto(int idProducto, String nombreProducto, int cantidad, int precio, String tipo) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipo = tipo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String s="";
        s+="\tCodigo: "+idProducto+"\n";
        s+="\tNombre: "+nombreProducto+"\n";
        s+="\tCantidad: "+cantidad+"\n";
        s+="\tPrecio: "+precio+"\n";
        s+="\tProducto de tipo: "+tipo+"\n";
                
        return s;
    }
    
    
    
    
        
}
