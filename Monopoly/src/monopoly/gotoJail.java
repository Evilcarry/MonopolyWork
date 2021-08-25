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
public class GotoJail extends GlobalLocation implements JailInterface{
    
    public GotoJail(String name, int locationID) {
        super(name, locationID);
    }

    @Override
    public void sendToJail() {
        //TODO this.setlocationID(jail location ID)
    }

    @Override
    public void removeFromJail() {
        //TODO this.setLocationID(jail location ID)
    }   
    
}