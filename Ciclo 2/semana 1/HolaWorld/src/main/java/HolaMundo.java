/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author user
 * 
 * instanciar es crear el objeto en la memoria ..."metodo objeto" o clase como tipo de dato
 * objeto.metodo() unobjeto es cuando existe en la memoria ram 
 * 
 * static se usa cuando no se necesita instanciar un objeto para acceder a una funcion o variable
 * 
 * abstract.. esta clas eno tiene atributos
 */
public class HolaMundo {
   
    public static void main(String[] args){
        System.out.println("Hola mundo");
        
        Scanner sc  = new Scanner(System.in); // objeto tipo scaner ... llamo al constructor con parametros System.in-> es estatico, por eso no 
        // uso new
        
        System.out.println("ingresa num"); // lee toda una cadena de texto
        int n = sc.nextInt();
        int m = 2*n;
        System.out.println("Resultado: " + m);
        
    }
          
}
