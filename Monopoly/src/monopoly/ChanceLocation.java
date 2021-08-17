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
public class ChanceLocation extends GlobalLocation implements ActionInterface{
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
    public void returnThreeSpaces() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void winMoney(int money) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
