/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class Inventario {

    private ArrayList<Producto> productos;

    public Inventario() {
        productos = new ArrayList();
    }

    public Inventario(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    public void agregarProductos(Producto p){
        productos.add(p);
    }
    
    public void listarProductos(){
        System.out.println("----Inventario-----");
        for (Producto p : productos) {//este es el for de python for element in lista
            p.toString();
            
        }
        
        //Este seria el for normalito si el anterior es muy confuso
//        for (int i = 0; i < productos.size(); i++) {
//            productos.get(i).toString();
//        }
        
    }
    
    public void procesarComandos(){
        Scanner s= new Scanner(System.in);
        Producto p= new Producto();
        String comando[]=s.nextLine().split("&");
        if(comando[0]=="1"){
            p.setIdProducto(Integer.parseInt(comando[1]));
            p.setNombreProducto(comando[2]);
            p.setCantidad(Integer.parseInt(comando[3]));
            p.setPrecio(Integer.parseInt(comando[4]));
            p.setTipo(comando[5]);
            agregarProductos(p);
        }
        if(comando[0]=="2"){
            listarProductos();
        }
       if(comando[3]=="3"){
           System.out.println("Gracias por su compra");
       }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Inventario().procesarComandos();
    }
    
}
