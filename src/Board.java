/**
 * A class encapsulating a representation of a monopoly board.
 */
public class Board {

    /*
    An array of squares that is the internal representation of the board.
     */
    private final Square[] spaces;

    public Board() {
        // Initialize the board.
        this.spaces = new Square[32];

        // First stretch.
        this.spaces[0] = new Go();
        this.spaces[1] = new Property("Old Sid", 50, 50, 2, 0);
        this.spaces[2] = new Property("Baker Kitchen", 50, 50, 2, 0);
        this.spaces[3] = new Property("Rice Stadium", 60, 50, 4, 0);
        this.spaces[4] = new Railroad("Rice Shuttle");
        this.spaces[5] = new Property("Old Hanszen", 75, 50, 6, 1);
        this.spaces[6] = new Property("Herzstein", 75, 50, 6, 1);
        this.spaces[7] = new Property("Martel", 100, 50, 8, 1);

        // Second stretch.
        this.spaces[8] = new Jail();
        this.spaces[9] = new Property("Baker", 125, 100, 10, 2);
        this.spaces[10] = new Property("South Servery", 125, 100, 10, 2);
        this.spaces[11] = new Property("Siebel Servery", 150, 100, 12, 2);
        this.spaces[12] = new Railroad("BCycles");
        this.spaces[13] = new Property("Herman Brown Hall", 180, 100, 14, 3);
        this.spaces[14] = new Property("Humanities", 180, 100, 14, 3);
        this.spaces[15] = new Property("Rayzor", 200, 100, 17, 3);

        // Third stretch.
        this.spaces[16] = new FreeParking();
        this.spaces[17] = new Property("Jones", 220, 150, 20, 4);
        this.spaces[18] = new Property("Brown", 220, 150, 20, 4);
        this.spaces[19] = new Property("Baker Institute", 240, 150, 22, 4);
        this.spaces[20] = new Railroad("RUPD Shuttle");
        this.spaces[21] = new Property("McMurtry", 260, 150, 26, 5);
        this.spaces[22] = new Property("Duncan", 260, 150, 26, 5);
        this.spaces[23] = new Property("McNair", 280, 150, 28, 5);

        // Fourth stretch.
        this.spaces[24] = new GoToJail();
        this.spaces[25] = new Property("New Sid", 300, 200, 30, 6);
        this.spaces[26] = new Property("Duncan Hall", 300, 200, 30, 6);
        this.spaces[27] = new Property("West Servery", 320, 200, 32, 6);
        this.spaces[28] = new Railroad("Houston Metro");
        this.spaces[29] = new Property("Sallyport", 350, 200, 35, 7);
        this.spaces[30] = new Property("North Servery", 350, 200, 35, 7);
        this.spaces[31] = new Property("Fondren Library", 400, 200, 50, 7);
    }

    /**
     * Returns the square object representing the
     * input space.
     *
     * @param space an integer between 0-31
     * @return The square object on the space input.
     */
    public Square getSquare(int space) {
        return this.spaces[space];
    }

    /**
     * Returns the size of the board.
     *
     * @return the size of the board.
     */
    public int getSize() {
        return this.spaces.length;
    }

    /**
     * Returns the location of the jail square.
     *
     * @return the location of the jail square.
     */
    public int jailSpace() {
        return 8;
    }
}
