package monopoly;

/**
 *
 * @author benjh
 */
public class GlobalLocation {
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
}
