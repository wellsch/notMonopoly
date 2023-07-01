import java.util.HashSet;

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
    private HashSet<Property> properties;

    /*
    Boolean representing whether the player is in jail.
     */
    private boolean inJail;

    /*
    A boolean representing whether the player is still playing.
     */
    private boolean playing;

    public Player(String name) {
        this.name = name;
        this.square = 0;
        this.money = 1500;
        this.properties = new HashSet<>();
        this.inJail = false;
        this.playing = true;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean isPlaying() {
        return playing;
    }

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
     * @return a boolean representing whether a house
     * was successfully purchased on a property.
     */
    public boolean buyHouse(Property property) {
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
            return true;
        }
        return false;
    }

    /**
     * Rolls dice for the player.
     *
     * @return A DiceRoll object representing the roll.
     */
    public DiceRoll rollDice() {
        int dice1 = (int) Math.ceil(Math.random() * 6.0);
        int dice2 = (int) Math.ceil(Math.random() * 6.0);
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
}
