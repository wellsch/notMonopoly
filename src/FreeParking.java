/**
 * A class encapsulating a free parking square.
 */
public class FreeParking extends Square {

    /**
     * Constructs a Free Parking square object.
     */
    public FreeParking() {
        super("West Lot");
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
