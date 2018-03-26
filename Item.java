
/**
 * Write a description of class items here.
 * 
 * @author (Aitor Diez) 
 * @version (15/3/2018)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemWeight;
    private String id;
    private boolean pickUpItem;
    /**
     * Constructor for objects of class items
     * @param itemDescription, itemWeight, id
     */
    public Item(String itemDescription, int itemWeight, String id, boolean pickUpItem)
    {
        // initialise instance variables
        this.itemDescription=itemDescription;
        this.itemWeight=itemWeight;
        this.id=id;
        this.pickUpItem=pickUpItem;
    }

    /**
     * Informacion de la descripcion y el peso del objeto
     * @return la descripcion del objeto y el peso.
     * 
     */
    public String getInformacionDeLosObjetos()
    {
        return itemDescription + " " + itemWeight + " .\n "; 
    }
    
    /**
     * Metodo para la descripcion del objeto.
     * @return itemDescription. Devuelve la descripcion del objeto.
     */
    public String getDescription(){
        return itemDescription;
    }
    
    /**
     * Metodo para el peso del objeto.
     * @return itemWeight. Devuelve el peso del objeto
     */
    public int getItemWeight(){
        return itemWeight;
    }
    
    /**
     * Metodo para el id del objeto.
     * @return id. El id del objeto.
     */
    public String getId(){
        return id;
    }
    
    /**
     * Metodo getter para que objeto puede ser cogido o no.
     * @return pickUpItem. El objeto puede ser cogido o no.
     */
    public boolean getPuedeSerCogido(){
        return pickUpItem;
    }
}
