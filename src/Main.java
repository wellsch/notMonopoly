import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

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

    /*
    Array to store player objects.
     */
    static Player[] players;

    /*
    Game board.
     */
    static Board board;

    /**
     * Asks the user for how many players they would like and
     * what their names are.
     *
     * @return an array of player objects representing the players.
     * @throws IOException in the case of buffered reader issues.
     */
    private static void setupGame() throws IOException {
        // Init board.
        board = new Board();

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
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i+1) + " enter your name:");
            players[i] = new Player(input.readLine());
        }
    }

    /**
     * Prints the input players properties and allows them to choose
     * one to buy a house on.
     *
     * @param player the player whose turn it is.
     * @throws IOException in the case of input interruptions.
     * @throws InterruptedException in the case of a sleep being interrupted.
     */
    private static void houseHandling(Player player) throws IOException, InterruptedException {
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
                            " has " + player.getProperties().get(i).getNumHouses() + " houses - rent is: $" +
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
                // Have the player buy a house on the property.
                if (chosen) player.buyHouse(player.getProperties().get(house - 1));
                System.out.println("Type \"done\" if you do not want to buy anymore houses.");
                inString = input.readLine();
                if (inString.equalsIgnoreCase("done")) done = false;
            }
        }
        TimeUnit.MILLISECONDS.sleep(500);
    }

    /**
     * Handles auctioning off an unbought property to the players.
     *
     * @param property the property that is being auctioned
     * @throws IOException in the case of input interruption
     */
    private static void auctionHandler(Property property) throws IOException {
        Player winner = null;
        int maxBid = 0;
        boolean won = false;
        while (!won) {
            for (Player bidder : players) {
                if (winner != null && winner.equals(bidder)) {
                    won = true;
                    bidder.buyPropertyVal(property, maxBid);
                    break;
                }
                // Get number of players w/ error handling.
                boolean gotBid = false;
                int bid;
                while (!gotBid) {
                    System.out.println(bidder.getName() + " enter a bid higher than " + maxBid + " or 0 to not bid.");
                    inString = input.readLine();
                    try {
                        bid = Integer.parseInt(inString);
                    } catch (NumberFormatException e) {
                        System.out.println("Please input an integer!");
                        continue;
                    }

                    // Range correction
                    if (bid <= maxBid && bid != 0) {
                        System.out.println("Please input a bid in the displayed range.");
                        continue;
                    }
                    // Player wants to buy.
                    if (bid != 0) {
                        maxBid = bid;
                        winner = bidder;
                    }
                    gotBid = true;
                }
            }
            if (maxBid == 0) {
                System.out.println("No one has bought the property!");
                won = true;
            }
        }
    }

    /**
     * Handles the input player landing on the input property.
     *
     * @param property the property landed on.
     * @param player the player whose turn it is.
     * @throws IOException if the input is interrupted.
     */
    private static void propertyHandler(Property property, Player player) throws IOException {
        if (!property.isOwned()) {
            System.out.println(player.getName() + " landed on " + property.getName() +
                    " which is unowned. It costs $" + property.getCost() +
                    ". To buy, type \"buy.\" To pass, type anything else.");
            inString = input.readLine();

            // Player buys property.
            if (inString.equalsIgnoreCase("buy")) player.buyProperty(property);

            // Player opted not to buy property or failed to.
            if (!property.isOwned()) {
                System.out.println(player.getName() + " did not buy " + property.getName() +
                        ". It is now up for auction.");
                // Auction handling.
                auctionHandler(property);
            }
        } else {
            // Player lands on unowned property
            System.out.println(player.getName() + " landed on " + property.getOwner().getName() +
                    "'s " + property.getName() + " it costs $" + property.getLandingCost() + ".");

            // Player loses
            if (player.getMoney() < property.getLandingCost()) {
                System.out.println(player.getName() + " cannot afford to land here, and have lost the game.");
                player.lose();
            }
            // Player can pay.
            else player.payPlayer(property.getOwner(), property.getLandingCost());
        }
    }

    /**
     * Runs one turn for a player who is still in the game.
     *
     * @param player the player whose turn it is.
     * @throws IOException if input is interrupted.
     * @throws InterruptedException if the sleep is interrupted.
     */
    private static void livePlayerHandler(Player player) throws IOException, InterruptedException {
        if (player.notInJail()) {
            // Print status and options.
            System.out.println("It is " + player.getName() + "'s turn. They are at "
                    + board.getSquare(player.getSquare()).getName() + " and have $" + player.getMoney() + ".");
            System.out.println("Type \"houses\" to view your properties and buy houses, and simply press enter to roll.");
            inString = input.readLine();

            // Houses handling
            if (inString.equalsIgnoreCase("houses")) houseHandling(player);

            // Rolling handling
            // Boolean to allow for doubles.
            boolean stillRolling = true;
            while (stillRolling) {
                DiceRoll roll = player.rollDice();
                stillRolling = player.notInJail() && roll.isDoubles();
                if (player.notInJail() || roll.isDoubles()) {
                    if (!player.notInJail()) player.escapeJailRoll();
                    // Move the player
                    player.moveSpaces(roll.getSpaces());

                    // Pass go
                    if (player.getSquare() >= board.getSize()) player.passGo(board.getSize());

                    // Handle landing on a property.
                    if (board.getSquare(player.getSquare()) instanceof Property property) {
                        propertyHandler(property, player);
                    }
                    // Handle landing on special squares.
                    else if (board.getSquare(player.getSquare()) instanceof GoToJail)
                        player.goToJail(board.jailSpace());
                    else if (board.getSquare(player.getSquare()) instanceof Jail)
                        System.out.println(player.getName() + " is just visiting " + board.getSquare(player.getSquare()).getName() + ".");
                    else
                        System.out.println(player.getName() + " is at " + board.getSquare(player.getSquare()).getName() + ".");
                } else
                    System.out.println("You fail to escape jail!");
                if (stillRolling) {
                    System.out.println("You rolled doubles! Press enter to roll again.");
                    input.readLine();
                }
            }
        } else {
            // Print status and options.
            System.out.println("It is " + player.getName() + "'s turn. They are at "
                    + board.getSquare(player.getSquare()).getName() + " and have $" + player.getMoney() + ".");
            System.out.println("Type \"houses\" to view your properties and buy houses, \"pay\" to escape jail and roll" +
                    " or simply press enter to try for doubles.");
            inString = input.readLine();
            // Houses handling
            if (inString.equalsIgnoreCase("houses"))
                houseHandling(player);
            else if (inString.equalsIgnoreCase("pay"))
                player.escapeJailPay();
        }
    }

    /**
     * Runs the main game loop of the monopoly game.
     *
     * @param args not used
     * @throws IOException in the case of input interruptions.
     * @throws InterruptedException in the case of the sleep being interrupted.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // Setup players and create board
        setupGame();

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
                    livePlayerHandler(player);
                }
            }
            numPlayers = livePlayers;
        }
    }
}
