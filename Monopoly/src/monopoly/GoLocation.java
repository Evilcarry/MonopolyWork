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
public class GoLocation extends GlobalLocation implements GoInterface{
    
    private int rounds;
    
    public GoLocation(String name, int locationID)
    {
        super(name, locationID);
        this.rounds = 0;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }
    
    @Override
    public HousingLocation cloneObject() {
        return null;
    }
    
    @Override
    public void givesMoney() {
        //TODO give money to player
    }
    
}
