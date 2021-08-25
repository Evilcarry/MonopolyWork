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
public class Assets {
    private GlobalLocation location;
    private int level;

    public Assets(GlobalLocation boardPosition, int level)
    {
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

    public void setLevel(int level) {
        this.level = level;
    }
}
