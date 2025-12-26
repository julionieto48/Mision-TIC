public class Portatil extends Ordenador{

    private String color;

    public Portatil(String tipo, String modelo, int memoriaRam, double velocidadProcesador, String color) {
        super(tipo, modelo, memoriaRam, velocidadProcesador);
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + "\t Color: " + color ;
    }

    
    
}
