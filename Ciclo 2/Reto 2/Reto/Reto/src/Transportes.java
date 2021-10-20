import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transportes {



    public static List <Vehiculo> Ordenadores = new ArrayList<>();

    public static void  agregarVehiculo(String comando) {
        String[] comandoListado = comando.split("&");

        String tipo = comandoListado[1];  // tipo de vehiculo

        int cantidad = Integer.parseInt(comandoListado[2]);
        int velocidad = Integer.parseInt(comandoListado[3]); // velocidad
        int poder = Integer.parseInt(comandoListado[4]); // unidades de poder

        if (tipo.equals("Poderos")){
            String poderVehiculo = comandoListado[5] ;
            Poderoso nuevoPortatil = new Poderoso(tipo,velocidad, cantidad, poder, poderVehiculo); // instancio
            Ordenadores.add(nuevoPortatil); // anado a la lista d eobjetos

        } else if(tipo.equals("Magico")) {
            String poderMagico = comandoListado[5]  ;
            Magico nuevoEscritorio = new Magico(tipo,velocidad, cantidad, poder, poderMagico);
            Ordenadores.add(nuevoEscritorio); // anado a la lista de objetos

        }
    }


    public static void listarInventario() {
        System.out.println("***Listado de Vehículos***");
        Ordenadores.forEach((item)->{
            System.out.print(item);
        }
        
        );
        
    }
    //_____________________________________________________::::::::_____________________________________________________________
    public static void procesarComandos() {
        Scanner entrada = new Scanner(System.in);
        String comando = entrada.nextLine();
        char opcion = comando.charAt(0); 

        // no se cuantos comando se seleccionan

        do{

            switch(opcion){
                case '1':
                    agregarVehiculo(comando);
                    comando = entrada.nextLine(); // volver a pedir
                    opcion = comando.charAt(0);
                    break;
                case '2':
                    listarInventario();
                    comando = entrada.nextLine(); // volver a pedir
                    opcion = comando.charAt(0);
                    break;

            }

        }while(opcion == '1' || opcion == '2'  );
    }

    //_____________________________________________________::::::::_____________________________________________________________

    public static void main(String[] args) throws Exception {
        procesarComandos();
        // 1 agrega  2 muestra  otros: acaba 
    }
}


/*
ejemplos
1&Magico&8&160&200&invisibilidad
1&Poderoso&3&150&500&SuperVelocidad

*/