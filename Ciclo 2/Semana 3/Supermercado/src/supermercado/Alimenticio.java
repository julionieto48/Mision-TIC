/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class Alimenticio extends Producto{
    
    private Date fechaVencimiento;
    

    public Alimenticio() {
        super();
        fechaVencimiento=new Date();
    }

    
    
    public Alimenticio(Date fechaVencimiento) {
        super();
        this.fechaVencimiento = fechaVencimiento;
    }

    public Alimenticio(Date fechaVencimiento, int idProducto, String nombreProducto, int cantidad, int precio, String tipo) {
        super(idProducto, nombreProducto, cantidad, precio, tipo);
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString()+"\tFecha de Vencimiento: "+fechaVencimiento.toString()+"\n"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
