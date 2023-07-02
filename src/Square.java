/**
 * An abstract class encapsulating a square on a monopoly board.
 */
public abstract class Square {

    /*
    The name of the square.
     */
    private final String name;

    /**
     * Constructs a new square object with the input name.
     *
     * @param name the name of this square.
     */
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
}
