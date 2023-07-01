public class Property {

    /*
    String representing the name of the property.
     */
    private final String name;

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
    private final int propGroup;

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


    public Property(String name, int cost, int houseCost, int rent, int propGroup) {
        this.name = name;
        this.cost = cost;
        this.houseCost = houseCost;
        this.rent = rent;
        this.propGroup = propGroup;
        this.numHouses = 0;
        this.inMonopoly = false;
        this.owner = null;
    }

    public void setInMonopoly(boolean val){
        this.inMonopoly = val;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int incrementHouses() {
        return ++this.numHouses;
    }

    public boolean isInMonopoly() {
        return this.inMonopoly;
    }

    public boolean isOwned() {
        return this.owner != null;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public int getLandingCost() {
        return this.rent * (int) Math.pow(2, this.numHouses);
    }
}
