public class FreeParking extends Square {

    /**
     * Constructs a Free Parking square object.
     */
    public FreeParking() {
        super("Free Parking");
    }

    /**
     * Returns true because this space is Free Parking.
     *
     * @return true because this space is Free Parking.
     */
    @Override
    public boolean isFreeParking() {
        return true;
    }
}
