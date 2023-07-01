public class Board {

    private final int numSpaces;

    private final Square[] spaces;

    private final int numPlayers;

    private final Player[] players;

    public Board(String[] playerNames) {
        // Initialize the board.
        this.numSpaces = 32;
        this.spaces = new Square[this.numSpaces];

        // First stretch.
        this.spaces[0] = new Go();
        this.spaces[1] = new Property("", 50, 50, 2);
        this.spaces[2] = new Property("", 60, 50, 4);
        this.spaces[3] = new FreeParking();
        this.spaces[4] = new Railroad("");
        this.spaces[5] = new Property("", 75, 50, 6);
        this.spaces[6] = new Property("", 75, 50, 6);
        this.spaces[7] = new Property("", 100, 50, 8);

        // Second stretch.
        this.spaces[8] = new Jail();
        this.spaces[9] = new Property("", 125, 100, 10);
        this.spaces[10] = new Property("", 125, 100, 10);
        this.spaces[11] = new Property("", 150, 100, 12);
        this.spaces[12] = new Railroad("");
        this.spaces[13] = new Property("", 180, 100, 14);
        this.spaces[14] = new Property("", 180, 100, 14);
        this.spaces[15] = new Property("", 200, 100, 17);

        // Third stretch.
        this.spaces[16] = new FreeParking();
        this.spaces[17] = new Property("", 220, 150, 20);
        this.spaces[18] = new Property("", 220, 150, 20);
        this.spaces[19] = new Property("", 240, 150, 22);
        this.spaces[20] = new Railroad("");
        this.spaces[21] = new Property("", 260, 150, 26);
        this.spaces[22] = new Property("", 260, 150, 26);
        this.spaces[23] = new Property("", 280, 150, 28);

        // Fourth stretch.
        this.spaces[24] = new GoToJail();
        this.spaces[25] = new Property("", 300, 200, 30);
        this.spaces[26] = new Property("", 300, 200, 30);
        this.spaces[27] = new Property("", 320, 200, 32);
        this.spaces[28] = new Railroad("");
        this.spaces[29] = new FreeParking();
        this.spaces[30] = new Property("", 350, 200, 35);
        this.spaces[31] = new Property("", 400, 200, 50);


        // Initialize the list of players.
        this.numPlayers = playerNames.length;
        this.players = new Player[this.numPlayers];

        for (int i = 0; i < this.numPlayers; i++) {
            this.players[i] = new Player(playerNames[i]);
        }
    }
}
