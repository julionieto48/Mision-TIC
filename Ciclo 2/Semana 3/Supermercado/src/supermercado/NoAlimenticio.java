/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.Date;

/**
 *
 * @author USUARIO
 */
public class NoAlimenticio extends Producto{
    private Date fechaVenceGarantia;
    

    public NoAlimenticio() {
        super();
        fechaVenceGarantia=new Date();
    }

    
    
    public NoAlimenticio(Date fechaVencimiento) {
        super();
        this.fechaVenceGarantia = fechaVencimiento;
    }

    public NoAlimenticio(Date fechaVencimiento, int idProducto, String nombreProducto, int cantidad, int precio, String tipo) {
        super(idProducto, nombreProducto, cantidad, precio, tipo);
        this.fechaVenceGarantia = fechaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString()+"\tFecha de Vencimiento de Garantía: "+fechaVenceGarantia.toString()+"\n"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
