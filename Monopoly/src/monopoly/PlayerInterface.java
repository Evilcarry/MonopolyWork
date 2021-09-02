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
