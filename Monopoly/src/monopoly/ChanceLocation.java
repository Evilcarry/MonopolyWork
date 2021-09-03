package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class ChanceLocation extends GlobalLocation{

    private int chanceID;
    private String actionMessage;

    public ChanceLocation(String name, int locationID, int chanceID, String actionMessage) {
        super(name, locationID);
        this.chanceID = chanceID;
        this.actionMessage = actionMessage;
    }

    public int getChanceID() {
        return chanceID;
    }

    public void setChanceID(int chanceID) {
        this.chanceID = chanceID;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    @Override
    public HousingLocation cloneObject() {
        return null;
    }
}
