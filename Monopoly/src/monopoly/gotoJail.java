package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class GoToJail extends GlobalLocation{
    
    public GoToJail(String name, int locationID) {
        super(name, locationID);
    }

    @Override
    public HousingLocation cloneObject() {
        return null;
    }
    
}