package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class Player implements PlayerInterface, java.io.Serializable {

    private int money;
    private String name;
    private GlobalLocation currentLocation;
    private int jailCounter;
    private boolean jailState;
    private Assets[] asset;
    private boolean inGame;
    private boolean paidThisRound;

    public Player(String name, GlobalLocation currentLocation) {
        this.money = 200000;
        this.name = name;
        this.currentLocation = currentLocation;
        this.jailCounter = 0;
        this.jailState = false;
        this.asset = new Assets[16];
        this.inGame = true;
        this.paidThisRound = false;
    }
    
    public Player(String name, int money, GlobalLocation currentLocation, int jailCounter, boolean jailState, Assets[] asset, boolean inGame, boolean paidThisRound){
        this.name = name;
        this.money = money;
        this.currentLocation = currentLocation;
        this.jailCounter = jailCounter;
        this.jailState = jailState;
        this.asset = asset;

        this.inGame = inGame;
        this.paidThisRound = paidThisRound;
    }

    public boolean isPaidThisRound() {
        return paidThisRound;
    }

    public void setPaidThisRound(boolean paidThisRound) {
        this.paidThisRound = paidThisRound;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Assets[] getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset[this.currentLocation.getLocationID()] = asset;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GlobalLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(GlobalLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getJailCounter() {
        return jailCounter;
    }

    public void setJailCounter(int jailCounter) {
        this.jailCounter = jailCounter;
    }

    public boolean isJailState() {
        return jailState;
    }

    public void setJailState(boolean jailState) {
        this.jailState = jailState;
    }

    /**
     * This method is used when selling an asset.
     * @param asset 
     */
    @Override
    public void sellAsset(Assets asset) {
        this.asset[asset.getBoardPosition().getLocationID()] = null;
        this.setMoney(this.getMoney() + this.currentLocation.cloneObject().getSellPrice());
    }

    @Override
    public String toString() {
        return name + " currently has " + money + " dollars. Currently standing at " + currentLocation;
    }
}
