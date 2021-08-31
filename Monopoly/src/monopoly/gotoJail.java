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

     /**
     * This method sends a player to jail, it also sets the player jail counter to 0 and the jail state to true.
     * @param player
     */
    @Override
    public void sendToJail(Player player) {
        player.movePlayer(this);
        player.setJailState(true);
        player.setJailCounter(0);
    }

    /**
     * This method removes a player from jail if they meet the requirements, it moves the player to the location after jail.
     * @param player
     * @param locations
     */
    @Override
    public void removeFromJail(Player player, GlobalLocation locations[]) {
        player.movePlayer(locations[this.getLocationID()+1]);
    }
/*
        if (player.isJailState())
        {
            if(player.getJailCounter() >= 3)
            {
                player.movePlayer(locations[this.getLocationID()+1]);
            }
            else if (player.getJailCounter() < 3)
            {
                System.out.println("Sorry the "+ player.getName() + " has to serve the jail time");
            }
        }
    }   
*/

    @Override
    public HousingLocation cloneObject() {
        return null;
    }
    
}