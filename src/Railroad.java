/**
 * A class encapsulating a representation of a railroad square.
 */
public class Railroad extends Property {

    /**
     * Constructs a railroad object with the input name.
     *
     * @param name the name of this railroad.
     */
    public Railroad(String name) {
        super(name, 200, 0, 25, -1);
    }

    /**
     * Computes the landing cost of an owned railroad in a unique way.
     *
     * @return the landing cost of landing on this railroad.
     */
    @Override
    public int getLandingCost() {
        return this.getRent() * (int) Math.pow(2, this.getOwner().getNumRRs());
    }

}
