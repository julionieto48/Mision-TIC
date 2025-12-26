public class Escritorio extends Ordenador {

    private double peso;

    public Escritorio(String tipo, String modelo, int memoriaRam, double velocidadProcesador,double peso) {
        super(tipo, modelo, memoriaRam, velocidadProcesador);
        this.peso = peso;
    }

    @Override
    public String toString() {
        return super.toString() + "\t Peso: " + peso + "kg\n";
    }
    
}
