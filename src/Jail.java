public class Jail extends Square {

    /**
     * Constructs a jail square object.
     */
    public Jail() {
        super("Jail");
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
