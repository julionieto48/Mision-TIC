public class Ordenador {
    private String tipo , modelo;
    private int memoriaRam;
    private double velocidadProcesador;

    public Ordenador(String tipo, String modelo, int memoriaRam, double velocidadProcesador) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.memoriaRam = memoriaRam;
        this.velocidadProcesador = velocidadProcesador;
    }

    @Override
    public String toString() {
        return "\t Ordenador" + tipo + "- Modelo: "
               + modelo + "\n"
               + "\t velocidad del procesador: " + velocidadProcesador
               + "GHz \n" + "\t memoria RAM " + memoriaRam + " GB \n";
        
        
    }
    
    
}
