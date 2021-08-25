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
public class MainApplication {

    private GlobalLocation[] locations = new GlobalLocation[24];
    private Player[] players = new Player[4];
    private Assets[] assets = new Assets[24];

    public void createAssets() {
        for (int i = 0; i < 24; i++) {
            this.assets[i] = new Assets(this.locations[i], 0);
        }
    }

    public void createPlayers() {
        for (int i = 0; i < 4; i++) {
            this.players[i] = new Player("player " + i, locations[0]);
        }
    }

    public void createLocations() {
        for (int i = 0; i < 24; i++) {

            if (i == 6 || i == 12) {
                this.locations[i] = new GotoJail("Go to jail", i);
            }
            if (i % 3 == 0) {
                locations[i] = new ChanceLocation("Chance", i, i, "this is a chance card");
            }
            if (i == 0) {
                this.locations[i] = new GoLocation("Go", i);
            } else {
                this.locations[i] = new HousingLocation("house: " + i, i, 20, 0, 10, 15);
            }
        }
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
        return "MainApplication{" + "locations=" + locations + ", players=" + players + ", assets=" + assets + '}';
    }

}
