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
public class PacMan {

    private String color;
    private int puntuacion;
    private int vidasRestantes;

    public PacMan() {
        color="Amarillo";
        puntuacion=0;
        vidasRestantes=3;
    }

    public PacMan(String color, int puntuacion, int vidasRestantes) {
        this.color = color;
        this.puntuacion = puntuacion;
        this.vidasRestantes = vidasRestantes;
    }
    
    

    
    
    
    
    
     
    public void sumarPuntuacion(){
        puntuacion++;//esto es lo mismo que puntuacion+=1
    }
    
    public int obtenerPuntacion(){
        return puntuacion;
    }
    
    public void restarVida(){
        vidasRestantes--;//esto es lo mismo que vidasRestates-=1
    }
    
    public boolean sigueVivo(){
        if(vidasRestantes>0) return true;
        else return false;
    }
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    
}
