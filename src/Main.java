import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A main class for running the monopoly program.
 */
public class Main {

    /*
    Instantiate scanner for user inputs
     */
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    /*
    String to store user inputs.
     */
    static String inString;

    /**
     * Asks the user for how many players they would like and
     * what their names are.
     *
     * @return an array of player objects representing the players.
     * @throws IOException in the case of buffered reader issues.
     */
    private static Player[] setupGame() throws IOException {
        // Get number of players w/ error handling.
        boolean gotPlayers = false;
        int numPlayers = 0;
        while (!gotPlayers) {
            System.out.println("Please input the number of players: ");
            inString = input.readLine();
            try {
                numPlayers = Integer.parseInt(inString);
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer!");
                continue;
            }
            gotPlayers = true;
        }

        // Create player objects.
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i+1) + " enter your name:");
            players[i] = new Player(input.readLine());
        }

        return players;
    }

    /**
     * Prints the input players properties and allows them to choose
     * one to buy a house on.
     *
     * @param player the player whose turn it is.
     * @throws IOException in the case of input interruptions.
     */
    private static void houseHandling(Player player) throws IOException {
        // Loop until the player is done.
        boolean done = false;
        while (!done) {
            if (player.getProperties().size() == 0) {
                System.out.println("You have no properties.");
                done = true;
            } else {
                // Print properties and houses
                for (int i = 0; i < player.getProperties().size(); i++) {
                    System.out.println((i+1) + ": " + player.getProperties().get(i).getName() +
                            " has " + player.getProperties().get(i).getName() + " houses - costs: " +
                            player.getProperties().get(i).getLandingCost());
                }
                // Have the player choose what property they'd like to buy a house on.
                boolean chosen = false;
                int house = 0;
                while (!chosen) {
                    System.out.println("Please input the number of the property you'd like to choose, 0 for none: ");
                    inString = input.readLine();
                    // Error handling
                    try {
                        house = Integer.parseInt(inString);
                    } catch (NumberFormatException e) {
                        System.out.println("Please input an integer!");
                        continue;
                    }
                    // Range correction
                    if (house > player.getProperties().size() || house < 0) {
                        System.out.println("Please input an integer in the displayed range.");
                        continue;
                    }
                    // Player doesn't want to buy.
                    if (house == 0) {
                        done = true;
                        break;
                    }
                    chosen = true;
                }
                // Have the player buy the chosen property.
                if (chosen) player.buyHouse(player.getProperties().get(house - 1));
                System.out.println("Type \"done\" if you do not want to buy anymore houses.");
                inString = input.readLine();
                if (inString.equalsIgnoreCase("done")) done = false;
            }
        }
    }

    /**
     * Runs the main game loop of the monopoly game.
     *
     * @param args not used
     * @throws IOException in the case of input interruptions.
     */
    public static void main(String[] args) throws IOException {
        // Setup game and create board
        Player[] players = setupGame();
        Board board = new Board();

        int numPlayers = players.length;
        // Main game loop
        while (numPlayers > 1) {
            // Check number of players still in the game each loop.
            int livePlayers = 0;
            // Give each player a turn.
            for (Player player : players) {
                // Only let players still in the game play.
                if (player.isPlaying()) {
                    livePlayers++;
                    if (!player.isInJail()) {
                        // Print status and options.
                        System.out.println("It is " + player.getName() + "'s turn. They are at "
                                + board.getSquare(player.getSquare()).getName() + " and have $" + player.getMoney() + ".");
                        System.out.println("Type \"houses\" to view your properties and buy houses, and simply press enter to roll.");
                        inString = input.readLine();
                        // Houses handling
                        if (inString.equalsIgnoreCase("houses")) {
                            houseHandling(player);
                        }
                    } else {
                        // Print status and options.
                        System.out.println("It is " + player.getName() + "'s turn. They are at "
                                + board.getSquare(player.getSquare()).getName() + " and have $" + player.getMoney() + ".");
                        System.out.println("Type \"houses\" to view your properties and buy houses, \"pay\" to escape jail and roll" +
                                " or simply press enter to try for doubles.");
                        inString = input.readLine();
                        // Houses handling
                        if (inString.equalsIgnoreCase("houses")) {
                            houseHandling(player);
                        }
                    }
                }
            }
            numPlayers = livePlayers;
        }
    }
}
