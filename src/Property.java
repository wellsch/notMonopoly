/**
 * A class encapsulating a property square.
 */
public class Property extends Square {

    /*
    String representing the cost of purchasing the property.
     */
    private final int cost;

    /*
    Integer representing the cost of purchasing a house on the property.
     */
    private final int houseCost;

    /*
    Integer representing the initial rent of the property.
     */
    private final int rent;

    /*
    Integer representing the group of properties this belongs to.
     */

    /*
    Integer representing the number of houses on the property.
     */
    private int numHouses;

    /*
    Boolean representing whether this property is in a monopoly.
     */
    private boolean inMonopoly;

    /*
    Player object representing who owns the property.
     */
    private Player owner;

    /**
     * Constructs a new property with the input parameters.
     *
     * @param name the name of the property.
     * @param cost the purchase cost of the property.
     * @param houseCost the cost of a house on the property.
     * @param rent the base rent of the property.
     */
    public Property(String name, int cost, int houseCost, int rent) {
        super(name);
        this.cost = cost;
        this.houseCost = houseCost;
        this.rent = rent;
        this.numHouses = 0;
        this.inMonopoly = false;
        this.owner = null;
    }

    /**
     * Sets the inMonopoly boolean.
     *
     * @param val the value to set inMonopoly to.
     */
    public void setInMonopoly(boolean val){
        this.inMonopoly = val;
    }

    /**
     * Set the owner of the property.
     *
     * @param owner the owner of the property.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Increment this properties number of houses.
     *
     * @return the new number of houses.
     */
    public int incrementHouses() {
        return ++this.numHouses;
    }

    /**
     * Return the inMonopoly boolean.
     *
     * @return the inMonopoly boolean.
     */
    public boolean isInMonopoly() {
        return this.inMonopoly;
    }

    /**
     * Returns the owned boolean.
     *
     * @return the owned boolean.
     */
    public boolean isOwned() {
        return this.owner != null;
    }

    /**
     * Returns the initial cost of the property.
     *
     * @return the initial cost of the property.
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Returns the base rent of the property.
     *
     * @return the base rent of the property.
     */
    public int getRent() {
        return rent;
    }

    /**
     * Returns the cost of buying a house on the property.
     *
     * @return the cost of buying a house on the property.
     */
    public int getHouseCost() {
        return houseCost;
    }

    /**
     * Returns the number of houses on the property.
     *
     * @return the number of houses on the property.
     */
    public int getNumHouses() {
        return numHouses;
    }

    /**
     * Returns the cost of the non-owner landing on this square.
     *
     * @return the cost of the non-owner landing on this square.
     */
    public int getLandingCost() {
        return this.rent * (int) Math.pow(2, this.numHouses);
    }

    /**
     * Returns the owner of this property.
     *
     * @return the owner of this property.
     */
    public Player getOwner() {
        return owner;
    }
}
