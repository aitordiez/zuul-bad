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
    private static final int PESO_MAXIMO = 60;
    private int pesoActualDelObjeto;
    /**
     * Constructor de los objetos de la clase Player.
     */
    public Player(Room habitacion)
    {
        // initialise instance variables
        atras = new Stack<>();
        listaDeObjetos=new ArrayList<>();
        currentRoom=habitacion;
        this.pesoActualDelObjeto = pesoActualDelObjeto; 
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
     * Imprime la lista de objetos y la habitacion en la que estas actualmente.  
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Indica que el usuario ha comido y que no tiene mas hambre.
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
            if(objetoDeLaSala.getPuedeSerCogido()){
                if(objetoDeLaSala.getItemWeight() + pesoActualDelObjeto < PESO_MAXIMO){
                    listaDeObjetos.add(objetoDeLaSala);
                    currentRoom.getEliminarObjeto(objetoDeLaSala);
                    pesoActualDelObjeto += objetoDeLaSala.getItemWeight();
                    System.out.println("El objeto cogido de la sala fue: "  + objetoDeLaSala.getDescription());
                }else{
                    System.out.println("El objeto pesa mucho");
                }
            }else{
                System.out.println("El objeto no puede ser cogido");
            }   
        }else{
            System.out.println("El id introducido no es el correcto");
        }

    }

    /**
     * Metodo para que el jugador pueda soltar los objetos dentro de una sala
     * @param idObjetoSoltado. El id del objeto que el jugador va a soltar
     */
    public void drop(String idObjetoSoltado){
        Item objetosDeLaSala= null;
        for (Item idObjetoSoltadoDeLaSala : listaDeObjetos){
            if(idObjetoSoltadoDeLaSala.getId().contains(idObjetoSoltado)){
                objetosDeLaSala = idObjetoSoltadoDeLaSala;
            }
        }

        if(objetosDeLaSala != null){
            currentRoom.addItem(objetosDeLaSala);
            listaDeObjetos.remove(objetosDeLaSala);
            pesoActualDelObjeto -= objetosDeLaSala.getItemWeight();
            System.out.println("El objeto que ha sido soltado es: " + objetosDeLaSala.getDescription());
        }else{
            System.out.println("El objeto no ha sido soltado");
        }
    }

    /**
     * Metodo para imprimir la informacion de los objetos que lleva consigo el jugador.
     * 
     */
    public void items(){
        for(Item objetosDeLaLista : listaDeObjetos){
            System.out.println(objetosDeLaLista.getInformacionDeLosObjetos()); 
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
