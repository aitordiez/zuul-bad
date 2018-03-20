import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    //La descripcion de la habitacion.
    public String description;
    //La coleccion de las salidas.
    private HashMap<String, Room>salidas;
    //La descripcion del objeto.
    private String itemDescription;
    //El peso de los objetos.
    private int itemWeight;
    //Coleccion de la lista de los objetos.
    private ArrayList<items> listaItem;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param itemDescription. La descripcion del objeto.
     * @param itemWeight. El peso del objeto.
     */
    public Room(String description, String itemDescription, int itemWeight) 
    {
        //inicializacion de la descripcion de la habitacion.
        this.description = description;
        //inicializacion de la coleccion de las salidas.
        salidas=new HashMap<>();
        //inicializacion de la descripcion de los objetos.
        this.itemDescription=itemDescription;
        //inicializacion del peso del objeto.
        this.itemWeight=itemWeight;
        //inicializacion de la lista de los objetos.
        listaItem=new ArrayList<>();
    }

    /**
     * Define una salida para la habitación.
     * @param direccion. La direccion de la salida.
     * @param localizacion. La localizacion de la habitacion dada.
     */
    public void setExit(String direccion, Room localizacion) 
    {
        salidas.put(direccion,localizacion);
    }

    /**
     * Devuelve la descripcion de la habitacion. 
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Metodo que tome como parámetro una cadena que represente una dirección y 
     * devuelva el objeto de la clase Room asociado a esa salida o null si no 
     * existe esa salida en dicha ubicación.
     * @return devuelve las salidas con las distintas direcciones.
     */
    public Room getExit(String direccion){
        return salidas.get(direccion);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        Set<String>listaDeLasDirecciones=salidas.keySet();
        String salidass = "Exits: ";  
        for(String direccionesDeLasHabitaciones : listaDeLasDirecciones){
            salidass += direccionesDeLasHabitaciones + " ";
        }
        return salidass;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        return " Tu estas en " + getDescription() +  " tiene un objeto " + itemDescription +  " que pesa " + itemWeight + 
               ".\n" + " lista de los objetos " + informacionDeLosObjetosDeLaHabitacion() + ".\n" + getExitString();
    }
    
    /**
     * Añadir a la lista los objetos de la clase items.
     * @param itemDescription. La descripcion del objeto.
     * @param itemWeight. El peso del objeto.
     */
    public void addItem(String itemDescription, int itemWeight){
        items it= new items(itemDescription, itemWeight);
        listaItem.add(it);
    }
    
    /**
     * Muestra la informacion de los objetos de cada habitacion.
     * @return informacionDeLosObjetos.La informacion de los objetos de cada habitacion.
     */
    public String informacionDeLosObjetosDeLaHabitacion(){
        String informacionDeLosObjetos="";
        if(listaItem.size() <= 0){
            informacionDeLosObjetos="No hay ningun objeto en esta habitacion";
        }else{
            for(items objetoDeLaLista : listaItem){
                informacionDeLosObjetos += objetoDeLaLista.getInformacionDeLosObjetos();
            }
        }
        return informacionDeLosObjetos;
    }
    
    /**
     * Informacion de la lista de los objetos que hay en cada sala.
     * @return listaItem. La lista de los objetos que hay en la sala.  
     */
    public ArrayList<items> getObjetos(){
        return listaItem;
    }
}

