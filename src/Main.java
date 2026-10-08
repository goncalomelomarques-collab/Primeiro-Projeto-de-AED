import dataStructures.TwoWayIterator;
import java.util.Scanner;

    /**
     * Work carried out by: Gonçalo Melo Marques 74277
     * 2026/10/09
     * sou lindo
     */

public class Main {

    //Messages:
    //Print messages:
    public static final String helpMessage =
            "addStation: Adds a new station to the end of the railway line.\n" +
            "removeStation: Removes a station from the railway line.\n" +
            "consultStation: Checks whether a station belongs to the railway line.\n" +
            "listStations: Lists all stations currently stored in the railway line.\n" +
            "help: Displays the list of available commands.\n" +
            "quit: Terminates the application.";
    public static final String BYE =
            "Stations saved.\n" +
            "Goodbye.";
    public static final String HAVE_STATION = "true";
    public static final String DONT_HAVE_STATION = "false";
    public static final String EMPTY = "No stations available.";
    public static final String ADDED_STATION = "Station successfully added.";
    public static final String REMOVED_STATION = "Station successfully removed.";

    //Exception Messages:
    public static final String NO_STATION_FOUND = "Station not found.";

    //command messages:
    public static final String HELP = "help";
    public static final String EXIT = "quit";
    public static final String ADD_STATION = "addstation";
    public static final String REMOVE_STATION = "removestation";
    public static final String CONSULT_STATION = "consultstation";
    public static final String LIST_STATIONS = "liststations";

    //Commands of Main:

    /**
     * Reads commands, one per line, and executes them until "quit" is read.
     * Unknown commands are ignored. Closes the scanner at the end.
     *
     * @param in scanner over the standard input
     * @param rl railway line on which the commands are executed
     * @pre in != null && rl != null
     * @pre the input contains a "quit" command (otherwise nextLine() throws
     *      java.util.NoSuchElementException when the input ends)
     */
    public static void executeCommands(Scanner in, RailwayLine rl){
        String cmd;
        do {
            cmd = in.nextLine().toLowerCase();
            switch (cmd){
                case EXIT -> System.out.println(BYE);
                case HELP -> System.out.println(helpMessage);
                case ADD_STATION -> addStation(in, rl);
                case REMOVE_STATION -> removeStation(in, rl);
                case CONSULT_STATION -> isInRailway(in, rl);
                case LIST_STATIONS -> listStations(in, rl);
            }
        }while (!cmd.equals(EXIT));
        in.close();}

    /**
     * Reads a station name and adds it to the end of the railway line.
     *
     * @param in scanner over the standard input
     * @param rl railway line where the station is added
     * @pre in != null && rl != null
     * @pre the next line of the input is the station name
     */
    public static void addStation(Scanner in, RailwayLine rl){
        String stationName = in.nextLine().toLowerCase();
        rl.addStation(stationName);
        System.out.println(ADDED_STATION);
    }

    /**
     * Reads a station name and removes it from the railway line.
     * Prints NO_STATION_FOUND if the station does not belong to the line.
     *
     * @param in scanner over the standard input
     * @param rl railway line from where the station is removed
     * @pre in != null && rl != null
     * @pre the next line of the input is the station name
     */
    public static void removeStation(Scanner in, RailwayLine rl){
        try {
            String stationName = in.nextLine().toLowerCase();
            rl.removeStation(stationName);
            System.out.println(REMOVED_STATION);
        }catch (Exception e){ System.out.println(NO_STATION_FOUND);}
    }

    /**
     * Reads a station name and prints whether it belongs to the railway line
     * ("true" or "false").
     *
     * @param in scanner over the standard input
     * @param rl railway line to be consulted
     * @pre in != null && rl != null
     * @pre the next line of the input is the station name
     */
    public static void isInRailway(Scanner in, RailwayLine rl){
        String stationName = in.nextLine().toLowerCase();
        if(rl.hasStation(stationName)){System.out.println(HAVE_STATION);}
        else{ System.out.println(DONT_HAVE_STATION);}
    }

    /**
     * Reads the direction and lists the stations of the railway line.
     * "&gt;" lists from the first to the last station, "&lt;" from the last to
     * the first. Any other direction prints nothing.
     * Prints EMPTY if the railway line has no stations.
     *
     * @param in scanner over the standard input
     * @param rl railway line whose stations are listed
     * @pre in != null && rl != null
     * @pre the next line of the input is the direction ("&lt;" or "&gt;")
     */
    public static void listStations(Scanner in, RailwayLine rl){
        String dir = in.nextLine().trim();

        if (rl.isEmpty()) {
            System.out.println(EMPTY);
        } else {
            TwoWayIterator<String> it = rl.railwayLineIterator();
            if (dir.equals("<")) listByDescendentOrder(it);
            else if (dir.equals(">")) listByAscendingOrder(it);
        }
    }

    /**
     * Prints the stations from the last to the first.
     *
     * @param it two-way iterator over the stations of the railway line
     * @pre it != null
     */
    private static void listByDescendentOrder( TwoWayIterator<String> it){
        it.fullForward();
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }

    /**
     * Prints the stations from the first to the last.
     *
     * @param it two-way iterator over the stations of the railway line
     * @pre it != null
     */
    private static void listByAscendingOrder(TwoWayIterator<String> it){
        while(it.hasNext()){
            String currentStation = it.next();
            System.out.println(currentStation);
        }
    }

    /**
     * Starts the application with an empty railway line.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args){
        RailwayLine rl = new RailwayLineClass();
        Scanner in = new Scanner(System.in);
        executeCommands(in, rl);
    }
}