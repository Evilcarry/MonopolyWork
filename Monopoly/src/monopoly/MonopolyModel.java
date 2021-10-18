package monopoly;

import java.util.Observable;

/**
 *
 * @author benjh
 */
public class MonopolyModel extends Observable {

    private GameCreator newGame;
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
        int players = this.data.game.getPlayers().length;
        
        if (this.data.currentPlayer == (players - 1)) {
            this.data.currentPlayer = 0;
        } else {
            this.data.currentPlayer++;
        }
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * This is for deciding how many times a player should move, with a random
     * number generator from 1 to 6.
     */
    public void rollToMove() {
        roll = new DiceRoll();

        int dieRoll = roll.diceRoll();

        this.data.die = dieRoll;
        this.data.dieRoll = true;
        this.data.hasRolled = true;

        for (int i = 0; i < dieRoll; i++) {
            this.movePlayer();
        }

        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * This method moves the player up one position, if the player is at
     * position 15 It will move the player to the starting position.
     */
    public void movePlayer() {
        int currentLocation = this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getLocationID();
        int nextLocation;

        if (currentLocation == 15) {
            nextLocation = this.data.game.getLocations()[0].getLocationID();
            this.data.game.getPlayers()[this.data.currentPlayer].setCurrentLocation(this.data.game.getLocations()[nextLocation]);
        } else {
            nextLocation = this.data.game.getLocations()[currentLocation + 1].getLocationID();
            this.data.game.getPlayers()[this.data.currentPlayer].setCurrentLocation(this.data.game.getLocations()[nextLocation]);
        }
    }

    /**
     * resetCounters must be called when the game goes to the next player.
     */
    public void resetCounters() {
        this.data.upgradeAsset = false;
        this.data.dieRoll = false;
        this.data.die = 0;
        this.data.hasRolled = false;
        this.data.sellAsset = false;
        this.data.successPurchase = false;
        this.data.playerChance = false;
        this.data.paytogetout = false;
        this.data.chanceRoll = 0;
    }

    /**
     * This will notify the observer that the player wants to upgrade an asset.
     */
    public void upgradeAssetMenu() {
        this.data.upgradeAsset = true;
        this.setChanged();
        this.notifyObservers(data);
    }

    public void sellAssetMenu() {
        this.data.sellAsset = true;
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
        this.chargePlayer(this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().cloneObject().getPrice());
        this.data.successPurchase = true;
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
        for (int i = 0; i < this.data.game.getPlayers()[this.data.currentPlayer].getAsset().length; i++) {
            if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i] != null) {
                if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].getBoardPosition().getName().contains(assetName)) { // checking for a match.
                    if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].getLevel() == 3) { //if the asset is already at level 4 it will return false
                        successUpgrade = false;
                    } else {
                        this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].setLevel(); // upgrading the level
                        this.chargePlayer(5000);
                        successUpgrade = true; //returning true after a successful upgrade.
                    }
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
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * To pay a player x money.
     *
     * @param amount
     */
    public void payPlayer(int amount) {
        this.data.game.getPlayers()[this.data.currentPlayer].setMoney(this.data.game.getPlayers()[this.data.currentPlayer].getMoney() + amount);
        this.setChanged();
        this.notifyObservers(data);
    }

    public void payOtherPlayer(int amount, int player) {
        this.data.game.getPlayers()[player].setMoney(this.data.game.getPlayers()[player].getMoney() + amount);
        this.setChanged();
        this.notifyObservers(data);
    }

    /**
     * Checks if the current location is purchasable.
     *
     * @return
     */
    public boolean purchasable() {
        boolean trueIfPurchaseble = false;

        if (this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getClass().toString().contains("class monopoly.HousingLocation")) {
            if (this.data.game.getAssets()[this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getLocationID()].getOwner() == null) {
                trueIfPurchaseble = true;
            }
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

    public void sellAsset(String assetName) {
        for (int i = 0; i < this.data.game.getPlayers()[this.data.currentPlayer].getAsset().length; i++) {
            if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i] != null) {
                if (this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].getBoardPosition().getName().contains(assetName)) { // checking for a match.
                    this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i].setOwner(null);
                    this.data.game.getPlayers()[this.data.currentPlayer].sellAsset(this.data.game.getPlayers()[this.data.currentPlayer].getAsset()[i]);
                    this.payPlayer(5000);
                }
            }
        }
        this.setChanged();
        this.notifyObservers(data);
    }

    public void movePlayerToJail() {
        int currentLocation = this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getLocationID();

        if (currentLocation > 8) {
            this.data.game.getPlayers()[this.data.currentPlayer].setCurrentLocation(this.data.game.getLocations()[8]);
        } else if (currentLocation < 8) {
            this.data.game.getPlayers()[this.data.currentPlayer].setCurrentLocation(this.data.game.getLocations()[4]);
        }
        this.data.game.getPlayers()[this.data.currentPlayer].setJailState(true);
        this.data.game.getPlayers()[this.data.currentPlayer].setJailCounter(0);
        this.setChanged();
        this.notifyObservers(data);
    }

    public void movePlayerOutOfJail() {
        this.movePlayer();
        this.data.game.getPlayers()[this.data.currentPlayer].setJailState(false);
        this.data.game.getPlayers()[this.data.currentPlayer].setJailCounter(0);
        this.setChanged();
        this.notifyObservers(data);
    }

    public boolean playerInJail() {
        boolean inJail = false;

        if (this.data.game.getPlayers()[this.data.currentPlayer].isJailState()) {
            inJail = true;
        } else {
            inJail = false;
        }
        return inJail;
    }

    public boolean playerInChance() {
        boolean chance = false;

        if (this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getClass().toString().contains("class monopoly.ChanceLocation")) {
            chance = true;
        } else {
            chance = false;
        }
        return chance;
    }

    public void payToGetOutOfJail() {
        this.data.paytogetout = true;
        this.chargePlayer(10000);
        this.movePlayerOutOfJail();
        this.setChanged();
        this.notifyObservers(data);
    }

    public void jailCounterIncrease() {
        int jailCounter = this.data.game.getPlayers()[this.data.currentPlayer].getJailCounter();

        if (jailCounter > 3) {
            this.data.game.getPlayers()[this.data.currentPlayer].setJailCounter(jailCounter + 1);
        }
        this.setChanged();
        this.notifyObservers(data);
    }

    public void rollToGetOutOfJail() {
        roll = new DiceRoll();
        int dieRoll = roll.diceRoll();
        this.data.jailDie = dieRoll;
        this.data.hasRolled = true;

        this.setChanged();
        this.notifyObservers(data);
        if (dieRoll == 6) {
            this.movePlayerOutOfJail();
        } else {
            int jailCounter = this.data.game.getPlayers()[this.data.currentPlayer].getJailCounter();

            if (jailCounter == 3) {
                this.movePlayerOutOfJail();
            } else {
                this.jailCounterIncrease();
            }
        }
    }

    public void rollForChance() {
        DiceRoll die = new DiceRoll();

        this.data.chanceRoll = die.diceRoll();
        this.data.hasRolled = true;
        this.setChanged();
        this.notifyObservers(data);

        if (this.data.chanceRoll <= 2) {
            this.payPlayer(5000);
            this.movePlayer();
        } else if (this.data.chanceRoll > 2 && this.data.chanceRoll < 6) {
            this.chargePlayer(5000);
            this.movePlayer();
        } else {
            this.movePlayerToJail();
        }
    }

    public String playerHasRolled() {
        String message = "You have already rolled this turn, you will not be allowed to roll again.";

        return message;
    }

    public String notPurchaseble() {
        String message = "This location is either not purchaseble or already owned\n please try a different Menu\n";
        message += this.displayPlayer();
        return message;
    }

    public String upgradeCompleted() {
        String message = "Successful upgraded the location\n";
        message += this.displayPlayer();
        return message;
    }

    public String upgradeFailed() {
        String message = "The upgrade could not complete, either you don't have enough money or it is already at max level (4)\n";
        message += this.displayPlayer();
        return message;
    }
    
    
    public boolean playerPaysRent() {
        boolean pays = false;
        int currentLocationID = this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().getLocationID();
        for (int player = 0; player < this.data.game.getPlayers().length; player++) {
            if (player != this.data.currentPlayer) {
                for (int k = 0; k < 16; k++) {
                    if (this.data.game.getPlayers()[player].getAsset()[k] != null) {
                        if (this.data.game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getLocationID() == currentLocationID) {
                            int amount = this.data.game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getRentPrice();
                            this.chargePlayer(amount);
                            this.payOtherPlayer(amount, player);
                            pays = true;
                        }
                    }
                }
            }
        }
        return pays;
    }
    
    public boolean balanceCheck(){
        boolean check = false;
        if (this.data.game.getPlayers()[this.data.currentPlayer].getMoney() <= 0){
            check = true;
            this.data.game.getPlayers()[this.data.currentPlayer].setInGame(false);
        } else {
            check = false;
        }
        return check;
    }
    
    
    
    public String payRent(){
        String message = "you landed in someone else's property\n your account has been charged 5000\n";
        message += this.displayPlayer();
        return message;
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

    public String displayPlayer() {
        String currentPlayer = this.data.game.getPlayers()[this.data.currentPlayer].getName() + "'s turn \n";
        currentPlayer += "Cash $" + this.data.game.getPlayers()[this.data.currentPlayer].getMoney() + "\n";
        currentPlayer += "Current Location: " + this.data.game.getPlayers()[this.data.currentPlayer].getCurrentLocation().toString();
        return currentPlayer;
    }
}
