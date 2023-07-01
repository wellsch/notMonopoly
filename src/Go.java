public class Go extends Square {

    /**
     * Constructs a Go square object.
     */
    public Go() {
        super("Go");
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
