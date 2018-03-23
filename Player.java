import java.util.Stack;
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
    /**
     * Constructor de los objetos de la clase Player.
     */
    public Player(Room habitacion)
    {
        // initialise instance variables
        atras = new Stack<>();
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
     *"Help" 
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
     * Metodo para imprimir la informacion de la localizacion
     */
    private void printLocationInfo(){
        System.out.print(currentRoom.getLongDescription());
        System.out.println();
    }
}
