import java.util.ArrayList;
import java.util.List;

public class ObjetosRecursivos{
    // ejemplo d euna lsita d earchivos ..cada directorio tiene un sibderoctior 
    public String nombre;
    List<ObjetosRecursivos> subdir;  // la clase se declaran 2 coleccione socmo listas
    List<String> archivos; // llamado entre llaves se le dice a la lista lo que va a contener
    ObjetosRecursivos(String nombre){
        this.nombre = nombre;
        this.subdir = new ArrayList<ObjetosRecursivos>(); // convierte la lista a array list
    }


}