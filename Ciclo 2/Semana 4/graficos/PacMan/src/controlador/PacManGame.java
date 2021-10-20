/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import modelo.*;
/**
 *
 * @author USUARIO
 */
public class PacManGame {
    
    private Tablero t;
    private PacMan pacman;

    public PacManGame() {
        pacman = retornarPacman();
        t = new Tablero(pacman, 0);
        t.mostrarTablero();
    }
    
    public void iniciarJuego(){
        do{
            
        }while(pacman.sigueVivo());
    }
    
    public void ponerGalletas(){
        
    }
    
    public void ponerFantasmas(){
        
    }
    //patron singleton permite que pacman se instancie una sola vez
    public PacMan retornarPacman(){
        if(pacman==null) return new PacMan("Rojo",0,3);
        else return pacman;
    }
    
      /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PacManGame();
    }
    

}
