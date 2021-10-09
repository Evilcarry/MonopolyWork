package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public abstract class GlobalLocation implements java.io.Serializable{
    private String name;
    private int locationID;
    
    public GlobalLocation(String name, int locationID)
    {
        this.name = name;
        this.locationID = locationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
    
    abstract public HousingLocation cloneObject();

    @Override
    public String toString() {
        return name;
    }
    
}
