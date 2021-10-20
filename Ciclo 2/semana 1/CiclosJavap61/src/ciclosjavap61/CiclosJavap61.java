/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciclosjavap61;

import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class CiclosJavap61 {

    public static void menu(){
        System.out.println("\t\tBienvenido al Cajero P61\n");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Consignar");
        System.out.println("3. Retirar");
        System.out.println("4. Salir\n");
        System.out.println("Por favor seleccione la opcion que desea realizar");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        long saldo=15000000;
        long valor=0;
        int opcion=0;
        //boolean bandera=false;
        String c="s";
        do{
            menu();
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Su saldo es: "+saldo);
                    break;
                case 2:
                    System.out.println("Por favor ingrese el valor que desea consignar");
                    valor=sc.nextLong();
                    saldo+=valor;//saldo=saldo+valor
                    System.out.println("Su nuevo saldo es: "+ saldo);
                    break;
                case 3:
                    System.out.println("Por favor ingrese el valor que desea retirar");
                    valor=sc.nextLong();
                    saldo-=valor;//saldo=saldo+valor
                    System.out.println("Su nuevo saldo es: "+ saldo);
                    //break;
                case 4:
                    System.out.println("Gracias por utilizar nuestro servicio");
                    break;
                default:
                    System.out.println("Usted ha ingresado una opcion invalida");

            }
            System.out.println("Desea continuar (s/n)");
            c=sc.next();
        }while(!c.equals("n"));        
    }
    
}
