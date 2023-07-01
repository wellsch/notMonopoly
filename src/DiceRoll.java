public class DiceRoll {

    /*
    An integer representing the number of spaces to move
     */
    private final int spaces;

    /*
    A boolean representing whether the roll was doubles.
     */
    private final boolean doubles;

    /**
     * Constructs a new DiceRoll object.
     *
     * @param spaces the number of spaces rolled.
     * @param doubles whether the roll was doubles
     */
    public DiceRoll(int spaces, boolean doubles) {
        this.spaces = spaces;
        this.doubles = doubles;
    }

    /**
     * Returns the number of spaces in the dice roll.
     *
     * @return the number of spaces in the dice roll.
     */
    public int getSpaces() {
        return spaces;
    }

    /**
     * Returns whether the roll was doubles.
     *
     * @return whether the roll was doubles.
     */
    public boolean isDoubles() {
        return doubles;
    }
}
