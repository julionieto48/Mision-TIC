
import java.util.ArrayList;

public class Arraylist {

    void ArrayList(){

        ArrayList<String> carro = new ArrayList<String>();
        carro.add("volvo");
        carro.add("BMW");
        System.out.println(carro);
    
    
        carro.get(0);
        carro.set(0, "Opel");
        System.out.println(carro);
    
        for (int i = 0; i < carro.size(); i++) {
            System.out.println(carro.get(i));
          }
    }

   
    

}
