public class Magico extends Vehiculo {

    private String poderMagico;
         

  
    public Magico(String tipo, int velocidad, int cantidad, int poder, String poderMagico) {
        super(tipo, velocidad, cantidad, poder);
        this.poderMagico = poderMagico;
    }




    @Override
    public String toString() {
        return super.toString() + "\t poder Magico: " + poderMagico ;
    }
    
}
