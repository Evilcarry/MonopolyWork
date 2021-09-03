package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class Assets implements java.io.Serializable{

    private GlobalLocation location;
    private int level;

    public Assets(GlobalLocation boardPosition, int level) {
        this.location = boardPosition;
        this.level = level;
    }

    public GlobalLocation getBoardPosition() {
        return location;
    }

    public void setBoardPosition(GlobalLocation boardPosition) {
        this.location = boardPosition;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        if (this.getLevel() > 4) {
            System.out.println("Trying to upgrade to higher than level 4?");
            System.out.println("Nice try, but that is not allowed");
        } else {
            this.level = this.getLevel()+1;
            this.location.cloneObject().setRentPrice(this.location.cloneObject().getRentPrice() + this.location.cloneObject().getRentPrice());
        }

    }

    @Override
    public String toString() {
        return "Assets:" + " " + location.getName() + ", level:" + level ;
    }

}
