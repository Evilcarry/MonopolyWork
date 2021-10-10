package monopoly;

/**
 *
 * @author benjh
 */
public class DataReference {
    public GameCreator game;
    public int die;
    public int currentPlayer;
    public boolean dieRoll;         // Check if the player wants to roll the die.
    public boolean sellAsset;       // Check if the player wants to sell assets.
    public int sellAssetCounter;
    public boolean upgradeAsset;    // Check if the player wants to upgrade assets.
    public int upgradeAssetCounter;
    
    public DataReference(GameCreator game){
        this.game = game;
        this.die = 0;
        this.currentPlayer = 0;
        this.dieRoll = false;
        this.sellAsset = false;
        this.sellAssetCounter = 0;
        this.upgradeAsset = false;
        this.upgradeAssetCounter = 0;
    }
}
