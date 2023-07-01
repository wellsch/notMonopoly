public abstract class Square {

    /*
    The name of the square.
     */
    private final String name;

    public Square(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the square.
     *
     * @return the name of the square.
     */
    public String getName() {
        return name;
    }

    /**
     * Determines if the square is a property.
     *
     * @return a boolean representing whether the square is a property.
     */
    public boolean isProperty() {
        return false;
    }

    /**
     * Determines if the square is Go.
     *
     * @return a boolean representing whether the square is Go.
     */
    public boolean isGo() {
        return false;
    }

    /**
     * Determines if the square is Jail.
     *
     * @return a boolean representing whether the square is Jail.
     */
    public boolean isJail() {
        return false;
    }

    /**
     * Determines if the square is Free Parking.
     *
     * @return a boolean representing whether the square is Free Parking.
     */
    public boolean isFreeParking() {
        return false;
    }

    /**
     * Determines if the square is a Go-To Jail.
     *
     * @return a boolean representing whether the square is a Go-To Jail.
     */
    public boolean isGoToJail() {
        return false;
    }
}
