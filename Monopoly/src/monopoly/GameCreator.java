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
        this.locations = new GlobalLocation[16];
        this.players = new Player[numberOfPlayers];
        this.assets = new Assets[16];
    }

    /**
     * This method is in charge of creating a list of assets.
     */
    public void createAssets() {
        for (int i = 0; i < 15; i++) {
            if (this.locations[i].cloneObject() != null) {
                this.assets[i] = new Assets(this.locations[i], 0);
            }
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
     * This method loads players back up from a save TODO: May need to change
     * the logic of this, instead of just giving the player object it could just
     * create a new one.
     *
     * @param numberOfPlayers
     * @param player[]
     */
    public void createPlayers(int numberOfPlayers, Player[] player) {
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = player[i];
        }
    }

    /**
     * This method is to initialize hard coded locations.
     */
    public void createLocations() {
        this.locations[0] = new GoLocation("Go", 0);
        this.locations[1] = new HousingLocation("Auckland", 1, 10000, 0, 5000, 5000);
        this.locations[2] = new ChanceLocation("A chance location", 2, 1, "this is a chance message");
        this.locations[3] = new HousingLocation("Coromandel", 3, 10000, 0, 5000, 5000);
        this.locations[4] = new GoToJail("Go to jail", 4);
        this.locations[5] = new HousingLocation("Lake Taupo", 5, 10000, 0, 5000, 5000);
        this.locations[6] = new ChanceLocation("A chance location", 6, 2, "this is a chance message");
        this.locations[7] = new HousingLocation("Rotorua", 7, 10000, 0, 5000, 5000);
        this.locations[8] = new GoToJail("Go to jail", 8);
        this.locations[9] = new HousingLocation("Mount Cook", 9, 10000, 0, 5000, 5000);
        this.locations[10] = new ChanceLocation("A chance location", 10, 3, "this is a chance message");
        this.locations[11] = new HousingLocation("Wellington", 11, 10000, 0, 5000, 5000);
        this.locations[12] = new HousingLocation("Christchurch", 12, 10000, 0, 5000, 5000);
        this.locations[13] = new HousingLocation("Queenstown", 13, 10000, 0, 5000, 5000);
        this.locations[14] = new ChanceLocation("A chance location", 14, 4, "this is a chance message");
        this.locations[15] = new HousingLocation("Fiorlands", 15, 10000, 0, 5000, 5000);
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
