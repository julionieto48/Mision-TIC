public class Vehiculo {
    
    private int velocidad,cantidad,poder;
    private String tipo;
    
    public Vehiculo(String tipo, int velocidad, int cantidad, int poder) {
        this.velocidad = velocidad;
        this.cantidad = cantidad;
        this.poder = poder;
        this.tipo = tipo ;
    }


    @Override
    public String toString() {
        return  "\t" +  tipo+ "- Cantidad: " + cantidad  + "\n"
                     +  "\t velocidad: "     + velocidad + " Km/h \n"
                     +  "\t poder: "         + poder     + " unidades \n"
        
        ;
         
    }
    
    
}
