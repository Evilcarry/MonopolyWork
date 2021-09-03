package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public interface PlayerInterface {
    public void movePlayer(GlobalLocation location);
    public void payPlayer(int money);
    public void chargePlayer(int money);
    public void purchaseAsset(Assets asset);
    public void sellAsset(Assets asset);
    public void upgradeAsset(Assets asset, int level);
    public void moveToJail(GlobalLocation location);
    public void moveOutOfJail(GlobalLocation location);
}
