
/**
 * Write a description of class items here.
 * 
 * @author (Aitor Diez) 
 * @version (15/3/2018)
 */
public class items
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemWeight;

    /**
     * Constructor for objects of class items
     */
    public items(String itemDescription, int itemWeight)
    {
        // initialise instance variables
        this.itemDescription=itemDescription;
        this.itemWeight=itemWeight;
    }

    /**
     * Informacion de la descripcion y el peso del objeto
     * @return la descripcion del objeto
     * 
     */
    public String getInformacionDeLosObjetos()
    {
        return itemDescription + " " + itemWeight + " .\n "; 
    }
    
}
