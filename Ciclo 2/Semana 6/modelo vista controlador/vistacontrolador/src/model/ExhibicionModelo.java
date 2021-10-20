package model;

public class ExhibicionModelo {
    private int    id;
    private String name;
    
    public ExhibicionModelo(int id, String name){
        this.id    = id;
        this.name = name;                
    }

    
    /**
     * @return the id
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
    
    
    public String toString(){
        return this.name;
    }

}
