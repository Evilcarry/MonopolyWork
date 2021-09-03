package monopoly;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class GameCreator implements java.io.Serializable {

    private GlobalLocation[] locations;
    private Player[] players;
    private Assets[] assets;

    public GameCreator(int numberOfPlayers) {
        this.locations = new GlobalLocation[24];
        this.players = new Player[numberOfPlayers];
        this.assets = new Assets[24];
    }

    public void createAssets() {
        for (int i = 0; i < 24; i++) {
            this.assets[i] = new Assets(this.locations[i], 0);
        }
    }

    /**
     * This method creates the players.
     *
     * @param numberOfPlayers which dictates the number of players.
     * @param playerName[] which has the names of the players.
     */
    public void createPlayers(int numberOfPlayers, String playerName[]) {
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = new Player(playerName[i], locations[0]);
        }
    }

    /**
     * This method loads players back up from a save
     *
     * @param numberOfPlayers
     * @param player[]
     */
    public void createPlayers(int numberOfPlayers, Player[] player) {
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = player[i];
        }
    }

    public void createLocations() {
        this.locations[0] = new GoLocation("Go", 0);
        this.locations[1] = new HousingLocation("Bay of Islands", 1, 10000, 0, 5000, 5000);
        this.locations[2] = new HousingLocation("Milford Sound", 2, 10000, 0, 5000, 5000);
        this.locations[3] = new ChanceLocation("Chance", 3, 3, "this is a chance message");
        this.locations[4] = new HousingLocation("Queenstown", 4, 10000, 0, 5000, 5000);
        this.locations[5] = new HousingLocation("Lake Taupo", 5, 10000, 0, 5000, 5000);
        this.locations[6] = new GotoJail("Go to jail", 6);
        this.locations[7] = new HousingLocation("Rotorua", 7, 10000, 0, 5000, 5000);
        this.locations[8] = new HousingLocation("Fox Glaciers", 8, 10000, 0, 5000, 5000);
        this.locations[9] = new ChanceLocation("Chance", 9, 9, "this is a chance message");
        this.locations[10] = new HousingLocation("Mount Cook", 10, 10000, 0, 5000, 5000);
        this.locations[11] = new HousingLocation("Hawke's Bay", 11, 10000, 0, 5000, 5000);
        this.locations[12] = new GotoJail("Go to jail", 12);
        this.locations[13] = new HousingLocation("Auckland", 13, 10000, 0, 5000, 5000);
        this.locations[14] = new HousingLocation("Coromandel Peninsula", 14, 10000, 0, 5000, 5000);
        this.locations[15] = new ChanceLocation("Chance", 15, 15, "this is a chance message");
        this.locations[16] = new HousingLocation("Kaikoura", 16, 10000, 0, 5000, 5000);
        this.locations[17] = new HousingLocation("Fiordland", 17, 10000, 0, 5000, 5000);
        this.locations[18] = new ChanceLocation("Chance", 18, 18, "this is a chance message");
        this.locations[19] = new HousingLocation("Chatham Island", 19, 10000, 0, 5000, 5000);
        this.locations[20] = new HousingLocation("Wellington", 20, 10000, 0, 5000, 5000);
        this.locations[21] = new ChanceLocation("Chance", 21, 21, "this is a chance message");
        this.locations[22] = new HousingLocation("Christchurch", 22, 10000, 0, 5000, 5000);
        this.locations[23] = new HousingLocation("Stewwart Island", 23, 10000, 0, 5000, 5000);
    }

    public GlobalLocation[] getLocations() {
        return locations;
    }

    public void setLocations(GlobalLocation[] locations) {
        this.locations = locations;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Assets[] getAssets() {
        return assets;
    }

    public void setAssets(Assets[] assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "List of locations: " + locations + ". List of players: " + players + ". List of assets: " + assets;
    }

}
