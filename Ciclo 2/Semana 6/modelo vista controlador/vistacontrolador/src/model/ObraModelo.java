package model;

/**
 *
 * @author casierrav
 */
public class ObraModelo {

    private int    id; 
    private String name; 
    private String type; 
    private double price; 
    private String museum; 
    private int    idExhibitionFK;
    private String exhibition; 
    private String presentation;

    
    public ObraModelo(int id, String name, String type, double price, int idExhibition){
        this.id             = id;
        this.name           = name;
        this.type           = type;
        this.price          = price;
        this.idExhibitionFK = idExhibition;
    }
    
    // sobre cargo los constructores ... muestro to lo de la tabla del query
    public ObraModelo(int id, String name, String type, double price, 
                    String museum, String exhibition, String presentation){
        this.id           = id;
        this.name         = name;
        this.type         = type;
        this.price        = price;
        this.museum       = museum;
        this.exhibition   = exhibition;
        this.presentation = presentation;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the museum
     */
    public String getMuseum() {
        return museum;
    }

    /**
     * @param idExhibitionFK the idExhibitionFK to set
     */
    public void setIdExhibitionFK(int idExhibitionFK) {
        this.idExhibitionFK = idExhibitionFK;
    }

    /**
     * @return the exhibition
     */
    public String getExhibition() {
        return exhibition;
    }

    /**
     * @return the presentation
     */
    public String getPresentation() {
        return presentation;
    }
    
    
    /**
     * @return the idExhibitionFK
     */
    public int getIdExhibitionFK() {
        return idExhibitionFK;
    }
    
    public Object[] toArray(){
        Object[] data = {id, name, type, price, museum, exhibition, presentation}; // arreglo tipo objeto
        return data;
    }
}