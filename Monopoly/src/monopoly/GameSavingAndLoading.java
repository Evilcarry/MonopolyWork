package monopoly;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class GameSavingAndLoading {

    private GameCreator game;
    private int amountOfPlayers;
    private final MonopolyDBManager monopolyDB;

    public GameSavingAndLoading(GameCreator game, int amountOfPlayers) {
        this.game = game;
        this.amountOfPlayers = amountOfPlayers;
        monopolyDB = new MonopolyDBManager();
        tableChecker();
    }

    public void savePlayer(GameCreator game, int amountOfPlayers) {
        for (int i = 0; i < amountOfPlayers; i++) {
            String insertPlayer = "INSERT INTO PLAYER VALUES ('" + game.getPlayers()[i].getName() + "', " + game.getPlayers()[i].getMoney() + ", " + game.getPlayers()[i].getCurrentLocation().getLocationID() + ", " + game.getPlayers()[i].getJailCounter() + ", " + game.getPlayers()[i].isJailState() + ", " + game.getPlayers()[i].getRounds() + ", " + game.getPlayers()[i].isInGame() + ", " + game.getPlayers()[i].isPaidThisRound() + ")";
            monopolyDB.updateDB(insertPlayer);
        }
    }

    public void updatePlayer(GameCreator game, int amountOfPlayers) {
        for (int i = 0; i < amountOfPlayers; i++) {
            String updatePlayer = "UPDATE PLAYER SET MONEY = " + game.getPlayers()[i].getMoney() + " ,LOCATIONID = " + game.getPlayers()[i].getCurrentLocation().getLocationID() + ", JAILCOUNTER = " + game.getPlayers()[i].getJailCounter() + ", JAILSTATE = " + game.getPlayers()[i].isJailState() + ", ROUNDS = " + game.getPlayers()[i].getRounds() + ", INGAME = " + game.getPlayers()[i].isInGame() + ", PAIDTHISROUND = " + game.getPlayers()[i].isPaidThisRound() + " WHERE NAME = " + game.getPlayers()[i].getName();
            monopolyDB.updateDB(updatePlayer);
        }
    }

    public void updateAssets(int locationID, String action) {
        if (action.contains("buy")) {
            String insertAssetOfPlayer = "INSERT INTO ASSETS VALUES ('" + game.getAssets()[locationID].getOwner() + "', " + game.getAssets()[locationID].getBoardPosition() + ", " + game.getAssets()[locationID].getLevel() + ")";
            monopolyDB.updateDB(insertAssetOfPlayer);
        } else {
            String deleteAssetOfPlayer = "DELETE FROM ASSETS WHERE LOCATIONID = " + game.getAssets()[locationID];
            monopolyDB.updateDB(deleteAssetOfPlayer);
        }
    }

    public void createTables() {
        String playerTable = "PLAYER";
        String assetsTable = "ASSETS";

        String createSQLPlayerTable = "CREATE TABLE " + playerTable + " (NAME VARCHAR(50), MONEY INT, LOCATIONID INT, JAILCOUNTER INT, JAILSTATE BOOLEAN, ROUNDS INT, INGAME BOOLEAN, PAIDTHISROUND BOOLEAN)";

        String createSQLAssetsTable = "CREATE TABLE " + assetsTable + " (PLAYERNAME VARCHAR(50), LOCATIONID INT, LEVEL INT)";

        monopolyDB.updateDB(createSQLAssetsTable);
        monopolyDB.updateDB(createSQLPlayerTable);
    }

    public void dropTables(String table) {
        String dropTable = "DROP TABLE " + table;

        monopolyDB.updateDB(dropTable);
    }

    public void tableChecker() {
        //CHECKING THE METADATA TO SEE IF THE TABLE EXISTS.
        try {
            DatabaseMetaData dBMD = monopolyDB.getConn().getMetaData();
            ResultSet rs = dBMD.getTables(null, null, "PLAYER", null);

            if (rs.next()) {
                dropTables("PLAYER");
                rs = dBMD.getTables(null, null, "ASSETS", null);
                if (rs.next()) {
                    dropTables("ASSETS");
                    createTables();
                } else {
                    createTables();
                }
            } else {
                rs = dBMD.getTables(null, null, "ASSETS", null);
                if (rs.next()) {
                    dropTables("ASSETS");
                    createTables();
                } else {
                    createTables();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Player[] loadPlayer(int amountOfPlayers) {
        Player[] players = new Player[amountOfPlayers];
        ArrayList<Assets> assetArray = new ArrayList<>();

        ArrayList<Assets> playerOneAsset = new ArrayList<>();
        ArrayList<Assets> playerTwoAsset = new ArrayList<>();
        ArrayList<Assets> playerThreeAsset = new ArrayList<>();
        ArrayList<Assets> playerFourAsset = new ArrayList<>();

        ResultSet rsPlayer = null;
        ResultSet rsAssets = null;

        String queryPlayer = "SELECT * FROM PLAYER";
        String queryAssets = "SELECT * FROM ASSETS";

        rsAssets = monopolyDB.queryDB(queryAssets);
        try {
            while (rsAssets.next()) {
                String owner = rsAssets.getString("NAME");
                int locationID = rsAssets.getInt("LOCATIONID");
                int level = rsAssets.getInt("LEVEL");

                assetArray.add(new Assets(game.getLocations()[locationID], level, owner));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //GIVING THE ASSETS THEIR OWNERS.
        int count = 0;
        for (Assets asset : assetArray) {
            for (int i = 0; i < game.getAssets().length; i++) {
                if (game.getAssets()[i].getBoardPosition().getLocationID() == asset.getBoardPosition().getLocationID()) {
                    game.getAssets()[i].setOwner(asset.getOwner());
                }
            }
            if (count == 0) {
                playerOneAsset.add(asset);
            } else {
                if (playerOneAsset.get(0).getOwner() != asset.getOwner()) {
                    playerTwoAsset.add(asset);
                    if (playerTwoAsset.get(0).getOwner() != asset.getOwner()) {
                        playerThreeAsset.add(asset);
                        if (playerThreeAsset.get(0).getOwner() != asset.getOwner()) {
                            playerFourAsset.add(asset);
                        }
                    }
                }
            }
            count++;
        }

        rsPlayer = monopolyDB.queryDB(queryPlayer);
        try {
            count = 0;
            while (rsPlayer.next()) {

                String name = rsPlayer.getString("NAME");
                int money = rsPlayer.getInt("MONEY");
                int locationID = rsPlayer.getInt("LOCATIONID");
                int jailCounter = rsPlayer.getInt("JAILCOUNTER");
                boolean jailState = rsPlayer.getBoolean("JAILSTATE");
                int rounds = rsPlayer.getInt("ROUNDS");
                boolean inGame = rsPlayer.getBoolean("INGAME");
                boolean paidThisRound = rsPlayer.getBoolean("PAIDTHISROUND");

                if (playerOneAsset.get(0).getOwner().contains(name)) {
                    //player one
                    players[count] = new Player(name, money, game.getLocations()[locationID], jailCounter, jailState, toAssetsArray(playerOneAsset), rounds, inGame, paidThisRound);
                } else if (playerTwoAsset.get(0).getOwner().contains(name)) {
                    players[count] = new Player(name, money, game.getLocations()[locationID], jailCounter, jailState, toAssetsArray(playerTwoAsset), rounds, inGame, paidThisRound);
                    //player two
                } else if (playerThreeAsset.get(0).getOwner().contains(name)) {
                    players[count] = new Player(name, money, game.getLocations()[locationID], jailCounter, jailState, toAssetsArray(playerThreeAsset), rounds, inGame, paidThisRound);
                    //player three
                } else if (playerFourAsset.get(0).getOwner().contains(name)) {
                    //player four
                    players[count] = new Player(name, money, game.getLocations()[locationID], jailCounter, jailState, toAssetsArray(playerFourAsset), rounds, inGame, paidThisRound);
                } else {
                    System.out.println("error at creating player");
                }
                count++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return players;
    }

    public Assets[] toAssetsArray(ArrayList<Assets> playerAsset) {
        Assets[] playerArray = new Assets[playerAsset.size()];
        int count = 0;
        for (Assets asset : playerAsset) {
            playerArray[count] = asset;
            count++;
        }
        dropTables("PLAYER");
        dropTables("ASSETS");
        createTables();

        return playerArray;
    }
}
