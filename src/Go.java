/**
 * A class encapsulating a Go square.
 */
public class Go extends Square {

    /**
     * Constructs a Go square object.
     */
    public Go() {
        super("the CCD");
    }

    /**
     * Returns true because this space is Go.
     *
     * @return true because this space is Go.
     */
    @Override
    public boolean isGo() {
        return true;
    }
}
