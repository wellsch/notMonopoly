public class GoToJail extends Square {

    /**
     * Constructs a go to jail square object.
     */
    public GoToJail() {
        super("Go-To Jail");
    }

    /**
     * Returns true because this space is a go-to jail space.
     *
     * @return true because this space is a go-to jail space.
     */
    @Override
    public boolean isGoToJail() {
        return true;
    }
}
