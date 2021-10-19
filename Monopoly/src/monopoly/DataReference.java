package monopoly;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class DataReference {
    public GameCreator game;
    public int die;
    public int currentPlayer;
    public boolean dieRoll;         // Check if the player wants to roll the die.
    public boolean hasRolled;       // Check if player has rolled
    public boolean sellAsset;       // Check if the player wants to sell assets.
    public boolean upgradeAsset;    // Check if the player wants to upgrade assets.
    public int jailDie;
    public boolean successPurchase;
    public boolean playerChance;
    public boolean paytogetout;
    public int chanceRoll;
    public boolean winner;
    
    public DataReference(GameCreator game){
        this.game = game;
        this.die = 0;
        this.currentPlayer = 0;
        this.dieRoll = false;
        this.hasRolled = false;
        this.sellAsset = false;
        this.upgradeAsset = false;
        this.successPurchase = false;
        this.playerChance = false;
        this.chanceRoll = 0;
        this.jailDie = 0;
        this.paytogetout = false;
        this.winner = false;
    }
}
