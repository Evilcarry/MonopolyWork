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
    public boolean upgradeAsset;    // Check if the player wants to upgrade assets.
    
    public DataReference(GameCreator game){
        this.game = game;
        this.die = 0;
        this.currentPlayer = 0;
        this.dieRoll = false;
        this.sellAsset = false;
        this.upgradeAsset = false;
    }
}
