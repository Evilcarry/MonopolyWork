package monopoly;

import java.util.Observable;

/**
 *
 * @author benjh
 */
public class MonopolyModel extends Observable {

    private GameCreator newGame;
    private MenuForGame menu;
    private PlayerForGameActions playerForGameActions;
    private DiceRoll roll;
    private GameSavingAndLoading saveLoad;
    public DataReference data;

    public void gameStart(int amountOfPlayers, String[] playerNames) {
        this.newGame = new GameCreator(amountOfPlayers);                             //Initializing newGame
        this.newGame.createLocations();                                              //Creating all the locations.
        this.newGame.createAssets();                                                 //Creating all the assets
        this.newGame.createPlayers(amountOfPlayers, playerNames);                    //Creating all the players with names.
    }

    /**
     * Must be called when the game starts, Gives a data reference to help
     * control the game.
     */
    public void dataReference() {
        this.data = new DataReference(this.newGame);
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * This will change the data to the next player. calls resetCounters.
     */
    public void nextPlayer() {
        if (this.data.currentPlayer == this.newGame.getPlayers().length) {
            this.data.currentPlayer = 0;
        } else {
            this.data.currentPlayer++;
        }
        resetCounters();
        this.setChanged();
        this.notifyObservers(data);
    }
    
    public void rollToMove(){
        roll = new DiceRoll();
        
        int dieRoll = roll.diceRoll();
        
        this.data.die = dieRoll;
        this.data.dieRoll = true;
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * resetCounters must be called when the game goes to the next player.
     */
    public void resetCounters() {
        this.data.upgradeAsset = false;
        this.data.dieRoll = false;
        this.data.die = 0;
        this.data.sellAsset = false;
        this.data.upgradeAssetCounter = 0;
        this.data.sellAssetCounter = 0;
    }

    /**
     * This will notify the observer that the player wants to upgrade an asset.
     */
    public void upgradeAssetMenu() {
        this.data.upgradeAsset = true;
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * This is for when a player buys an asset, it sets the asset to the player
     * object, It also gives the asset object an owner.
     */
    public void buyAsset() {
        String playerName = this.data.game.getPlayers()[this.data.currentPlayer].getName(); // get player name
        int locationID = this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getLocationID(); // get current location ID

        this.data.game.getPlayers()[this.data.currentPlayer].setAsset(this.data.game.getAssets()[locationID]); //set asset to player
        this.data.game.getAssets()[locationID].setOwner(playerName); //set owner of the asset.
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * This will upgrade the asset 1 level up.
     *
     * @param assetName
     * @return true if it upgraded successfully else returns false.
     */
    public boolean upgradeAssetConfirm(String assetName) {
        boolean successUpgrade = false;
        this.data.upgradeAssetCounter = 1; //this will make it so a player can only upgrade once
        for (int i = 0; i < this.data.game.getPlayers()[this.data.currentPlayer].getAsset().length; i++) {
            if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].getBoardPosition().getName().contains(assetName)) { // checking for a match.
                if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].getLevel() == 4) { //if the asset is already at level 4 it will return false
                    successUpgrade = false;
                } else {
                    this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].setLevel(); // upgrading the level
                    successUpgrade = true; //returning true after a successful upgrade.
                }
            }
        }
        this.setChanged();
        this.notifyObservers(data);

        return successUpgrade;
    }

    /**
     * To charge a player x money.
     *
     * @param amount
     */
    public void chargePlayer(int amount) {
        this.data.game.getPlayers()[this.data.currentPlayer].setMoney(this.data.game.getPlayers()[this.data.currentPlayer].getMoney() - amount);
    }

    /**
     * To pay a player x money.
     *
     * @param amount
     */
    public void payPlayer(int amount) {
        this.data.game.getPlayers()[this.data.currentPlayer].setMoney(this.data.game.getPlayers()[this.data.currentPlayer].getMoney() + amount);
    }

    public boolean purchaseble() {
        boolean trueIfPurchaseble = false;

        if (this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getClass().toString().contains("class monopoly.HousingLocation") ){
            trueIfPurchaseble = true;
        }
        return trueIfPurchaseble;
    }

    /**
     * Message to be displayed when a player wants to buy an asset.
     *
     * @return
     */
    public String buyMessage() {
        String player = this.data.game.getPlayers()[this.data.currentPlayer].getName();
        String money = Integer.toString(this.data.game.getPlayers()[this.data.currentPlayer].getMoney());
        String price = Integer.toString(this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().cloneObject().getPrice());
        String location = this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getName();

        String stats = player + "'s current location is " + location + "\n";
        stats += "The price of " + location + " is :$" + price + "\n";
        stats += player + " currently has $" + money;

        return stats;
    }

    /**
     * This is for the player to know the instructions of the game.
     *
     * @return A String of instructions
     */
    public String instructions() {
        String instructions = "----------------------------------------------------------------------INSTRUCTIONS--------------------------------------------------------------------------------------------\n";
        instructions += "The rules are simple, the player that gets to round 10 first wins, or if you all agree to end the game earlie, no one wins, or until everyone runs out of money\n";
        instructions += "Each player has to move across the 24 different locations\n";
        instructions += "Each player gets given $200000 at the start of the game, a player can get more money by landing on a chance card, by owning a property and other players paying rent \n";
        instructions += "Also whenever they complete a round and go through the starting location 'GO'\n";
        instructions += "A player can also sell an asset, only allowed to sell one asset per turn\n";
        instructions += "Each player can purchase the location they landed on but they must have the necessary funds to purchase it, be careful! if you run out of money you instantly lose the game\n";
        instructions += "If a player goes to jail they have to either roll a 6, if they fail to do so in 3 turns, the player will be freed from prison\n";
        instructions += "Chance cards can be good and dangerous so be careful.\n";
        instructions += "Game will automatically save after each dice roll.\n";
        instructions += "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        return instructions;
    }
}
