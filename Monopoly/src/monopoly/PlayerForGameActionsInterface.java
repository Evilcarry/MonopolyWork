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
public interface PlayerForGameActionsInterface {
    public int playerMessage(GameCreator game, int playerNum); //this to player for game
    public void playerEngagement(); // //this to player for game
    public void playerInJailAction(GameCreator game, int player); // //this to player for game
    public void playerPassesThroughGO(GameCreator game, int player); //this to player for game
    public boolean playerLandsOnChance(GameCreator game, int player); //this to player for game
    public void playerPaysRent(GameCreator game, int amountOfPlayers, int currentPlayer); // this to player for game
}
