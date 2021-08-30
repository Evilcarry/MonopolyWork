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
public interface JailInterface {
    public void sendToJail(Player player); //Sends player to jail 
    public void removeFromJail(Player player, GlobalLocation locations[]); //remove player from jail
}
