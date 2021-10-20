/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablerojavap61;

/**
 *
 * @author USUARIO
 */
public class TableroJavap61 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        final int N=30;
        final int M=50;
        
        for (int i =0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if((i+j)%2==0){
                   System.out.print("#"); 
                }else{
                    System.out.print(" ");
                }    
            }
            System.out.println("");
        }
    }
    
}
