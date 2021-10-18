package monopoly;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class GameSavingAndLoading {

    private GameCreator game;
    Assets[] playerAsset;
    private final MonopolyDBManager monopolyDB;

    public GameSavingAndLoading() {
        monopolyDB = new MonopolyDBManager();
    }
    
    public void setGame(GameCreator game){
        this.game = game;
    }

    public void savePlayer() {
        this.dropTables("PLAYER");
        this.dropTables("ASSETS");
        this.createTables();
        for (int i = 0; i < game.getPlayers().length; i++) {
            String insertPlayer = "INSERT INTO PLAYER VALUES ('" + this.game.getPlayers()[i].getName() + "', " + this.game.getPlayers()[i].getMoney() + ", " + this.game.getPlayers()[i].getCurrentLocation().getLocationID() + ", " + this.game.getPlayers()[i].getJailCounter() + ", " + this.game.getPlayers()[i].isJailState() + ", " + this.game.getPlayers()[i].isInGame() + ", " + this.game.getPlayers()[i].isPaidThisRound() + ")";
            monopolyDB.updateDB(insertPlayer);
        }
    }
    
    public void saveAssets() {
        for (int i = 0; i < game.getAssets().length; i++){
            if (game.getAssets()[i] != null){
                String insertAssetOfPlayer = "INSERT INTO ASSETS VALUES ('', " + game.getAssets()[i].getBoardPosition().getLocationID() + ", " + game.getAssets()[i].getLevel() + ")";
                monopolyDB.updateDB(insertAssetOfPlayer);
            }
        }
    }

    public void updatePlayer() {
        for (int i = 0; i < game.getPlayers().length; i++) {
            String updatePlayer = "UPDATE PLAYER SET MONEY = " + game.getPlayers()[i].getMoney() + " ,LOCATIONID = " + game.getPlayers()[i].getCurrentLocation().getLocationID() + ", JAILCOUNTER = " + game.getPlayers()[i].getJailCounter() + ", JAILSTATE = " + game.getPlayers()[i].isJailState() + ", INGAME = " + game.getPlayers()[i].isInGame() + ", PAIDTHISROUND = " + game.getPlayers()[i].isPaidThisRound() + " WHERE NAME = '" + game.getPlayers()[i].getName() + "'";
            monopolyDB.updateDB(updatePlayer);
        }
    }
    
    public void updateAssets(){
        for (int i = 0; i < game.getAssets().length; i++){
            if (game.getAssets()[i] != null){
                if (game.getAssets()[i].getOwner() != null){
                    String updateAsset = "UPDATE ASSETS SET PLAYERNAME = '" + game.getAssets()[i].getOwner() + "', LOCATIONID = " + game.getAssets()[i].getBoardPosition().getLocationID() + ", LEVEL = " + game.getAssets()[i].getLevel() + " WHERE LOCATIONID = " + game.getAssets()[i].getBoardPosition().getLocationID();
                    monopolyDB.updateDB(updateAsset);
                }                
            }
        }
    }


    public void createTables() {
        String playerTable = "PLAYER";
        String assetsTable = "ASSETS";

        String createSQLPlayerTable = "CREATE TABLE " + playerTable + " (NAME VARCHAR(50), MONEY INT, LOCATIONID INT, JAILCOUNTER INT, JAILSTATE BOOLEAN, INGAME BOOLEAN, PAIDTHISROUND BOOLEAN)";

        String createSQLAssetsTable = "CREATE TABLE " + assetsTable + " (PLAYERNAME VARCHAR(50), LOCATIONID INT, LEVEL INT)";

        monopolyDB.updateDB(createSQLPlayerTable);
        monopolyDB.updateDB(createSQLAssetsTable);
    }

    public void dropTables(String table) {
        String dropTable = "DROP TABLE " + table;

        monopolyDB.updateDB(dropTable);
    }

    public void loadAsset(String playerName) {
        ResultSet rsAssets = null;
        String queryAssets = "SELECT * FROM ASSETS";
        ArrayList<Assets> assetArray = new ArrayList<>();

        rsAssets = monopolyDB.queryDB(queryAssets);
        try {
            while (rsAssets.next()) {
                String owner = rsAssets.getString(playerName);
                int locationID = rsAssets.getInt("LOCATIONID");
                int level = rsAssets.getInt("LEVEL");

                assetArray.add(new Assets(game.getLocations()[locationID], level, owner));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int count = 0;
        
        this.playerAsset = new Assets[assetArray.size()];
        for (Assets asset : assetArray){
            this.playerAsset[count] = asset;
            count++;
        }
        try {
            rsAssets.close();
        } catch (SQLException ex) {
            Logger.getLogger(GameSavingAndLoading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Player[] loadPlayer(){
        ResultSet rsPlayer = null;
        String queryPlayer = "SELECT * FROM PLAYER";
        ArrayList<Player> playerArray = new ArrayList<>();
        
        rsPlayer = monopolyDB.queryDB(queryPlayer);
        try {
            while (rsPlayer.next()) {
                String name = rsPlayer.getString("NAME");
                int money = rsPlayer.getInt("MONEY");
                int locationID = rsPlayer.getInt("LOCATIONID");
                int jailCounter = rsPlayer.getInt("JAILCOUNTER");
                boolean jailState = rsPlayer.getBoolean("JAILSTATE");
                boolean inGame = rsPlayer.getBoolean("INGAME");
                boolean paidThisRound = rsPlayer.getBoolean("PAIDTHISROUND");
                
                this.loadAsset(name);
                playerArray.add(new Player(name, money ,game.getLocations()[locationID], jailCounter, jailState, this.playerAsset, inGame, paidThisRound));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        Player[] players = new Player[playerArray.size()];
        int count = 0;
        for (Player player : playerArray){
            players[count] = player;
            count++;
        }
        try {
            rsPlayer.close();
        } catch (SQLException ex) {
            Logger.getLogger(GameSavingAndLoading.class.getName()).log(Level.SEVERE, null, ex);
        }
        dropTables("PLAYER");
        dropTables("ASSETS");
        createTables();
        return players;
    }
}
