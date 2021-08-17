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
public interface HousingInterface {
    public void purchaseProperty(); //the player can purchase a property
    public void sellProperty(); //the player can sell a property
    public void upgradeProperty(); //the player can upgrade a property
    public void downgradeProperty(); //the player can downgrade a property
    public void payRent(); //the player can pay rent
}
