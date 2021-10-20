/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Tablero {
    
    private PacMan p;
    private int nivel;
    private int ancho;
    private int alto;

    public Tablero() {
        p = new PacMan();
        nivel=0;
        ancho=60;
        alto=30;
    }

    public Tablero(PacMan p, int nivel) {
        this.p = p;
        this.nivel = nivel;
        ancho=60;
        alto=30;
    }
    
    

   
    
    
    public void comprobarNivelActual(){
        nivel= p.obtenerPuntacion()/25;
    }
    
    public void mostrarTablero(){
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if(i==0 || i==alto-1 ||j==0 ||j==ancho-1)System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println("");
        }
    }
    
}
