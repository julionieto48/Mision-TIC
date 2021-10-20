public class Poderoso extends Vehiculo{

    private String poderVehiculo;

        
    public Poderoso(String tipo, int velocidad, int cantidad, int poder, String poderVehiculo) {
        super(tipo, velocidad, cantidad, poder);
        this.poderVehiculo = poderVehiculo;
    }



    @Override
    public String toString() {
        return super.toString() + "\t poder Vehiculo" + poderVehiculo;
    }

    
    
}
