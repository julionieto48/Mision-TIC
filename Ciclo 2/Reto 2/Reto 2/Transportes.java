import java.util.Scanner;


class Transportes {

  Poderoso poderoso;  // los objetos
  Magico magico;
   

  private static void procesarComandos() {
    Scanner sc= new Scanner(System.in); 
    // opcion 1 agregar vehiculo :  añadir clase hija
    // opcion 2  listar items del inventario
    // opcion 3 salir
    System.out.println("***Listado de Vehículos***");  
   
    int opcion = 0;
    do{    
      opcion = sc.nextInt();
      if (opcion == 1) agregarVehiculo();
      else if(opcion == 2) listarItem();
        
      }while (opcion != 3);  
    System.out.println("***finalizado***"); 
  }


  
  private static void listarItem() {
    Scanner sc= new Scanner(System.in); 
    String objeto = sc.nextLine();
    if (objeto == "Magico") {}
    else if(objeto == "Magico"){}
  }



  private static void agregarVehiculo() {
    


  }



  public static void main(String[] args) {
    procesarComandos();
      
    
    
  }
}