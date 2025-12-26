import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {



    public static List <Ordenador> Ordenadores = new ArrayList<>();

    public static void agregarOrdenador(String comando) {
        String[] comandoListado = comando.split("&");
        String tipo = comandoListado[1];
        String modelo = comandoListado[4];
        int memoriaRam = Integer.parseInt(comandoListado[2]);
        double velocidadProcesador = Double.parseDouble(comandoListado[3]);

        if (tipo.equals("Portatil")){
            String color = comandoListado[5];
            Portatil nuevoPortatil = new Portatil(tipo, modelo, memoriaRam, velocidadProcesador, color); // instancio
            Ordenadores.add(nuevoPortatil); // anado a la lista d eobjetos
        } else if(tipo.equals("Escritorio")) {
            Double peso = Double.parseDouble(comandoListado[5])  ;
            Escritorio nuevoEscritorio = new Escritorio( tipo, modelo, memoriaRam, velocidadProcesador,peso);
            Ordenadores.add(nuevoEscritorio); // anado a la lista de objetos

        }
    }


    public static void listarOrdenadores() {
        System.out.println("*** Inventario de Ordenadores***");
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
                    agregarOrdenador(comando);
                    comando = entrada.nextLine(); // volver a pedir
                    opcion = comando.charAt(0);
                    break;
                case '2':
                    listarOrdenadores();
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
"1&Portatil&4&2.5&ASD 456 & Morado"
1&Portatil&4&2.5&Ryzen 456 & Black

1&Escritorio&12&3.5&Ryzen 56 & 78.0

*/