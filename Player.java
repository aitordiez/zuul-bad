import java.util.Stack;
import java.util.ArrayList;
/**
 * Clase Player
 * 
 * @author (Aitor Diez) 
 * @version (23/03/2018)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> atras;
    private Room anteriorSala;
    private ArrayList<Item> listaDeObjetos;
    /**
     * Constructor de los objetos de la clase Player.
     */
    public Player(Room habitacion)
    {
        // initialise instance variables
        atras = new Stack<>();
        listaDeObjetos=new ArrayList<>();
        currentRoom=habitacion;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        } 

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;

        nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            atras.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     *"Look" 
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * "Eat"
     */
    public void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * Método para volver atras en una habitacion
     */
    public void back(){
        currentRoom=anteriorSala;
        if(atras.empty())
        {
            atras.push(currentRoom);
        }else{
            currentRoom = atras.pop();
        }
        printLocationInfo();
    }

    /**
     * Metodo para que el jugador pueda coger los objetos de una sala.
     * @param idObjeto. El id del objeto a coger. 
     */
    public void take(String idObjeto){
        Item objetoDeLaSala = currentRoom.getListaObjeto(idObjeto); 
        if(objetoDeLaSala != null){
            listaDeObjetos.add(objetoDeLaSala);
            currentRoom.getEliminarObjeto(objetoDeLaSala);
            System.out.println("El objeto cogido de la sala ha sido: "  + objetoDeLaSala.getDescription());
        }else{
            System.out.println("El objeto es muy pesado");

        }
    }

    /**
     * Método que devuelve la sala en la que estamos.
     * @return currentRoom. Devuelve la sala en la que estamos.
     */
    public Room currentRoom(){
        return currentRoom;
    }

    /**
     * Metodo para imprimir la informacion de la localizacion
     */
    private void printLocationInfo(){
        System.out.print(currentRoom.getLongDescription());
        System.out.println();
    }

}
