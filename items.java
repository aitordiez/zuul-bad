/**
 * Write a description of class items here.
 * 
 * @author (Aitor Diez) 
 * @version (15/3/2018)
 */
public class items
{
    //variable de la descripcion del objeto.
    private String itemDescription;
    //variable del peso del objeto.
    private int itemWeight;

    /**
     * Constructor for objects of class items
     * @param itemDescription. La descripcion del objeto.
     * @param itemWeight. Informacion del peso del objeto.
     */
    public items(String itemDescription, int itemWeight)
    {
        //Inicialización de la descripcion del objeto.
        this.itemDescription=itemDescription;
        //Inicializacion del peso del objeto.
        this.itemWeight=itemWeight;
    }

    /**
     * Informacion de la descripcion y el peso del objeto.
     * @return getInformacionDeLosObjetos. La informacion del objeto y su peso.
     */
    public String getInformacionDeLosObjetos()
    {
        return itemDescription + " " + itemWeight + " .\n "; 
    }
    
    /**
     * Informacion de la descripcion del objeto.
     * @return itemDescription. La descripcion del objeto.
     */
    public String getDescription(){
        return itemDescription;
    }
    
    /**
     * Informacion del peso del objeto.
     * @return getWeight. El peso del objeto.
     */
    public int getWeight(){
        return itemWeight;
    }
}
