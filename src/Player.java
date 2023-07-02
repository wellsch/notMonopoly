import java.util.ArrayList;

/**
 * A class encapsulating a player of monopoly.
 */
public class Player {

    /*
    String representing the name of the player.
     */
    private final String name;

    /*
    Integer representing the square of the player.
     */
    private int square;

    /*
    Integer representing the amount of money the player has.
     */
    private int money;

    /*
    HashSet that contains Property objects owned by the player.
     */
    private ArrayList<Property> properties;

    /*
    Boolean representing whether the player is in jail.
     */
    private boolean inJail;

    /*
    A boolean representing whether the player is still playing.
     */
    private boolean playing;

    /**
     * Constructs a new player object with the given name.
     *
     * @param name the name of this player.
     */
    public Player(String name) {
        this.name = name;
        this.square = 0;
        this.money = 1500;
        this.properties = new ArrayList<>();
        this.inJail = false;
        this.playing = true;
    }

    public void goToJail(int jailSquare) {
        System.out.println(this.name + " has been sent to jail!");
        this.square = jailSquare;
        this.inJail = true;
    }

    /**
     * Returns whether the player is currently in jail.
     *
     * @return whether the player is currently in jail.
     */
    public boolean notInJail() {
        return !inJail;
    }

    /**
     * Has the player escape jail by paying.
     */
    public void escapeJailPay() {
        if (this.money < 50) System.out.println(this.name + " cannot escape jail as they do not have enough money.");
        else {
            this.inJail = false;
            this.money -= 50;
            System.out.println(this.name + " successfully escaped jail. They now have $" + this.money + ".");
        }
    }

    /**
     * Has the player escape jail after a roll of doubles.
     */
    public void escapeJailRoll() {
        this.inJail = false;
        System.out.println(this.name + " successfully escaped jail.");
    }

    /**
     * Returns whether the player is still in the game.
     *
     * @return whether the player is still in the game.
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Attempts to have the player buy the input
     * property
     *
     * @param property the property to buy.
     * @return a boolean representing success.
     */
    public boolean buyProperty(Property property) {
        // Check cases where this player cannot buy the property.
        if (property.isOwned())
            System.out.println(this.name + " cannot buy " + property.getName() + " as it is already owned.");
        else if (this.money < property.getCost())
            System.out.println(this.name + " cannot buy " + property.getName() + " as you do not have enough money.");
        // Otherwise allow them to purchase property and update trackers.
        else {
            this.money -= property.getCost();
            this.properties.add(property);
            property.setOwner(this);
            System.out.println(this.name + " successfully purchased " + property.getName() + " for $" + property.getCost() + ".");
            return true;
        }
        return false;
    }

    /**
     * Attempts to have the player buy a house on
     * the input property.
     *
     * @param property the property to buy a house on.
     */
    public void buyHouse(Property property) {
        // Check cases where this player cannot buy a house.
        if (!properties.contains(property))
            System.out.println(this.name + " can not buy a house on " + property.getName() + " as you do not own it.");
        else if (!property.isInMonopoly())
            System.out.println(this.name + " can not buy a house on " + property.getName() + " as it is not in a monopoly.");
        else if (this.money < property.getHouseCost())
            System.out.println(this.name + " can not buy a house on " + property.getName() + " as you do not have enough money.");
        else if (property.getNumHouses() == 5)
            System.out.println(this.name + " can not buy a house on " + property.getName() + " as it already has 5 houses.");
        // Otherwise allow them to purchase a house and update trackers.
        else {
            this.money -= property.getHouseCost();
            System.out.println(this.name + " built a house on " + property.getName() + ". It has " + property.incrementHouses() + " houses now.");
        }
    }

    /**
     * Rolls dice for the player.
     *
     * @return A DiceRoll object representing the roll.
     */
    public DiceRoll rollDice() {
        // Roll dice
        int dice1 = (int) Math.ceil(Math.random() * 6.0);
        int dice2 = (int) Math.ceil(Math.random() * 6.0);

        System.out.println(this.name + " rolled a " + dice1 + " and a " + dice2 + ".");

        // Construct a dice roll object
        return new DiceRoll(dice1 + dice2, dice1 == dice2);
    }

    /**
     * Moves the player numSpaces spaces and returns the
     * resulting space landed on.
     *
     * @param numSpaces the number of spaces to move.
     * @return the updated space value.
     */
    public int moveSpaces(int numSpaces) {
        this.square += numSpaces;
        return this.square;
    }

    /**
     * Passes go on a board with totalSpaces spaces.
     *
     * @param totalSpaces the total number of spaces
     *                    in the board.
     */
    public void passGo(int totalSpaces) {
        // Error handling
        if (this.square < totalSpaces) System.out.println("You did not pass Go!");
        // Update values
        else {
            this.square %= totalSpaces;
            this.money += 200;
            System.out.println(this.name + " passed Go. They now have $" + this.money + ".");
        }
    }

    /**
     * Computes the number of railroads owned by the player.
     *
     * @return the number of railroads that the player owns.
     */
    public int getNumRRs() {
        int retval = 0;
        for (Property prop : this.properties) {
            if (prop instanceof Railroad)
                retval++;
        }
        return retval;
    }

    /**
     * Returns the players name.
     *
     * @return the players name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the square that the player is on.
     *
     * @return the square that the player is on.
     */
    public int getSquare() {
        return square;
    }

    /**
     * Returns the player's money.
     *
     * @return the player's money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Returns the list of the players properties.
     *
     * @return the list of the players properties.
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }
}
