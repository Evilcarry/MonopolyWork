/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author benjh
 */
public class GameCreator {

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

    public void createLocations() {
        for (int i = 0; i < 24; i++) {

            if (i == 0) {
                this.locations[i] = new GoLocation("Go", i);
            } else if (i == 6 || i == 12) {
                this.locations[i] = new GotoJail("Go to jail", i);
            } else if (i % 3 == 0) {
                    locations[i] = new ChanceLocation("Chance", i, i, "this is a chance card");
            } else {
                this.locations[i] = new HousingLocation("house: " + i, i, 10000, 0, 5000, 5000);
            }
        }
        
        this.locations[0] = new GoLocation("Go", 0);
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
