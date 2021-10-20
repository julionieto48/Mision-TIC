package model;

public class MuseoModelo {
    private int    id;
    private String name;
    
    public MuseoModelo(int id, String name){
        this.id     = id;
        this.name = name;
    }

     /**
     * @return the mu_id
     */
    public int getId() {
        return id;
    }

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
    public String toString() {
        return this.name;  // lo que yo quiero que se imprima en este caso el nombre 
    }


    
}
