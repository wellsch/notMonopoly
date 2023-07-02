/**
 * A class encapsulating a jail square.
 */
public class Jail extends Square {

    /**
     * Constructs a jail square object.
     */
    public Jail() {
        super("Weiss");
    }

    /**
     * Returns true because this space is Jail.
     *
     * @return true because this space is Jail.
     */
    @Override
    public boolean isJail() {
        return true;
    }
}
