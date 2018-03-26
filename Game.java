import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player= new Player(createRooms());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room entradas,gradaOeste,restaurante,gradaEste,
        banhos,gradaNorte,chiringuito, salida, palcoFamiliaJugadores, salidaNoroeste;

        // create the rooms
        entradas = new Room("Taquilla para entrar al campo","entradas", 30);
        gradaOeste = new Room("Bienvenido a la grada oeste","reparto de bufandas", 45);
        restaurante = new Room("Restaurante del campo","comida",12);
        gradaEste = new Room("Bienvenido a la grada este","periodistas",50);
        banhos= new Room("Seccion de los baños del partido","servicios",23);
        gradaNorte = new Room("Bienvenido a la grada norte","puesto de bufandas", 34);
        chiringuito = new Room("Cafeteria del campo","bar",500);
        salida = new Room("Salida del campo", "portero de taquilla", 38);
        salidaNoroeste = new Room("Salida Noroeste del campo","policia", 56);
        palcoFamiliaJugadores = new Room("El palco familiar de los jugadores","sillones", 60);
        // initialise room exits
        //Direcciones de la taquilla de las entradas
        entradas.setExit("east", gradaOeste);
        //Direcciones de las gradas Oeste
        gradaOeste.setExit("east", gradaNorte);
        gradaOeste.setExit("south", restaurante);
        gradaOeste.setExit("west", entradas);
        gradaOeste.setExit("southWest", palcoFamiliaJugadores);
        //Direcciones del restaurante
        restaurante.setExit("north", gradaOeste);
        restaurante.setExit("southEast",salida);
        //Direcciones de las gradas Este
        gradaEste.setExit("east", salidaNoroeste);
        gradaEste.setExit("south", banhos);
        gradaEste.setExit("west", gradaNorte);
        //Direcciones de los baños.
        banhos.setExit("north", gradaEste);
        // Direcciones de la grada norte
        gradaNorte.setExit("north", chiringuito);
        gradaNorte.setExit("east", gradaEste);
        gradaNorte.setExit("west", gradaOeste);
        //Direcciones del chiringuito
        chiringuito.setExit("south", gradaNorte);
        //Direcciones del palco familiar de los jugadores.
        palcoFamiliaJugadores.setExit("east", gradaOeste);
        //Añadir mas elementos en la entrada.
        entradas.addItem(new Item("puesto de bufandas", 48, "Bufandas", true));
        entradas.addItem(new Item("detector de metales", 39, "Detector", true));
        //Añadir mas elementos en la grada oeste
        gradaOeste.addItem(new Item("periodistas",35, "Periodistas", false));
        gradaOeste.addItem(new Item("panel de marcador electronico", 78, "Panel", true));
        gradaOeste.addItem(new Item("palco de autoridades",90, "Palco", false));
        //Añadir mas elementos en el restaurante.
        restaurante.addItem(new Item("mesas", 70, "Mesas", true));
        restaurante.addItem(new Item("cocineros", 40, "Cocineros", true));
        //Añadir mas elementos a la grada este.
        gradaEste.addItem(new Item("asientos reservado visitante", 67 , "Asientos", false));
        gradaEste.addItem(new Item("policias", 20, "Policias", true));
        gradaEste.addItem(new Item("aseos", 40, "Aseos", false));
        //Añadido el elemento especial para coger cualquier objeto.
        gradaEste.addItem(new Item("gafas", 15, "Gafas", true));
        //Añadir mas elementos a los baños.
        banhos.addItem(new Item("Lavabos", 46, "Lavabos", true));
        banhos.addItem(new Item("secador de manos", 45, "Secador", true));
        //Añadir mas elementos a la grada norte.
        gradaNorte.addItem(new Item("Periodistas de radio", 50, "Periodistas", false));
        gradaNorte.addItem(new Item("Aseos", 60, "Aseos", false));
        gradaNorte.addItem(new Item("Asientos reservados para los ultras locales", 80, "Asientos", true));
        //Añadir mas elementos al chiringuito.
        chiringuito.addItem(new Item("refrescos", 10, "Refrescos", true));
        chiringuito.addItem(new Item("bocadillos", 30, "Bocadillos", true));
        //Añadir mas elementos para el jugador.
        palcoFamiliaJugadores.addItem(new Item("peso de los refrescos", 30, "Peso", true));
        palcoFamiliaJugadores.addItem(new Item("peso de la comida", 55, "Pesos", true));
        return entradas;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        player.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            player.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            player.look();
        }
        else if (commandWord.equals("eat")){
            player.eat();
        }
        else if (commandWord.equals("back")){
            player.back();
        }
        else if (commandWord.equals("take")){
            player.take(command.getSecondWord());
        }
        else if (commandWord.equals("items")){
            player.items();  
        }
        else if (commandWord.equals("drop")){
            player.drop(command.getSecondWord());
        }
        else if (commandWord.equals("gafas")){
            player.gafas(command.getSecondWord());
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

  

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

}
