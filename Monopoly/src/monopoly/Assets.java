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
        return "Assets{" + "location=" + location + ", level=" + level + '}';
    }

}
