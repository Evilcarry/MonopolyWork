package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class HousingLocation extends GlobalLocation {
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
    
    /**
     * This method returns a clone copy of the HousingLocation object.
     * @return a HousingLocation object.
     */
    @Override
    public HousingLocation cloneObject()
    {
        return this;
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
