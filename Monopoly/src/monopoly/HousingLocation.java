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
public class HousingLocation extends GlobalLocation implements HousingInterface{
    private int price;
    private int upgradeLevel;
    private int rentPrice;
    private int sellPrice;
   
    public HousingLocation(String name, int locationID, int price, int upgradeLevel, int rentPrice, int sellPrice) {
        super(name, locationID);
        this.price = price;
        this.upgradeLevel = upgradeLevel;
        this.rentPrice = rentPrice;
        this.sellPrice = sellPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
}
