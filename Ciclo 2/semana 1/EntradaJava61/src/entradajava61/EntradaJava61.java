/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entradajava61;

import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class EntradaJava61 {

    private static int calcularDoble(int n){
        if(n < 0 && n % 2 != 0){
            System.out.println("El numero es negativo y es impar ");
        }else if(n < 0 || n % 2 == 0){
            System.out.println("El numero es negativo o es par ");
        }else{
            System.out.println("El numero es positivo y es impar ");
        }
        return n*2;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un numero");
        int n = sc.nextInt();
        int m=calcularDoble(n);
        System.out.println("Resultado es: "+m);
        
    }
    
}
