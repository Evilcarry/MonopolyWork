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
    private Assets asset;

    public Player(String name,GlobalLocation currentLocation)
    {
        this.money = 200000;
        this.name = name;
        this.currentLocation = currentLocation;
        this.jailCounter = 0;
        this.jailState = false;
        this.asset = null;
    }
    
    public Assets getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset = asset;
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
}