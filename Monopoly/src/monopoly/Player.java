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
public class Player implements PlayerInterface{
    private int money;
    private String name;
    private GlobalLocation currentLocation;
    private int jailCounter;
    private boolean jailState;
    private Assets[] asset;

    public Player(String name,GlobalLocation currentLocation)
    {
        this.money = 200000;
        this.name = name;
        this.currentLocation = currentLocation;
        this.jailCounter = 0;
        this.jailState = false;
        this.asset = new Assets[24];
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
     * This method moves a player to a different location, based on the dice roll or if they are sent to jail.
     * @param location
     */
    @Override
    public void movePlayer(GlobalLocation location) {
        this.currentLocation = location;
    }

    /**
     * This method pays the player a certain amount of money, based on a chance card or go location.
     * @param money
     */
    @Override
    public void payPlayer(int money) {
        this.money+= money;
    }
    
    /**
     * This method charges the player a certain amount of money, based on a chance card or if they purchased an asset..
     * @param money
     */
    @Override
    public void chargePlayer(int money)
    {
        this.money-= money;
    }
    
    /**
     * This method is used when the player purchases a new asset.
     * @param asset
     */
    @Override
    public void purchaseAsset(Assets asset) {
        this.asset[this.currentLocation.getLocationID()] = asset;
        this.setMoney(money - this.currentLocation.cloneObject().getPrice());
    }
    
    @Override
    public void sellAsset(Assets asset)
    {
        this.asset[asset.getBoardPosition().getLocationID()] = null;
        this.setMoney(money + this.currentLocation.cloneObject().getSellPrice());
    }
    
    @Override
    public String toString() {
        return "Name: " + name +  ", money: "+ money + ", currentLocation: " + currentLocation + ", jailCounter: " + jailCounter + ", jailState: " + jailState + ", asset: " + asset.toString();
    }
}