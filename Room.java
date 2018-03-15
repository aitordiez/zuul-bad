import java.util.HashMap;
import java.util.Set;
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    private HashMap<String, Room>salidas;
    private String itemDescription;
    private int itemWeight;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String itemDescription, int itemWeight) 
    {
        this.description = description;
        salidas=new HashMap<>();
        this.itemDescription=itemDescription;
        this.itemWeight=itemWeight;
    }

    /**
     * Define una salida para la habitaci�n.
     * @param direccion. La direccion de la salida.
     * @param localizacion. La localizacion de la habitacion dada
     */
    public void setExit(String direccion, Room localizacion) 
    {
        salidas.put(direccion,localizacion);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Metodo que tome como par�metro una cadena que represente una direcci�n y 
     * devuelva el objeto de la clase Room asociado a esa salida o null si no 
     * existe esa salida en dicha ubicaci�n
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
        return " Tu estas en " + getDescription() +  " tiene un objeto " + itemDescription +  " que pesa " + itemWeight + ".\n" + getExitString();
    }
}

