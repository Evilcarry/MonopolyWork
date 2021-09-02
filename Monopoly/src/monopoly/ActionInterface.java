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
public interface ActionInterface {

    public void gameStart();
    public int setPlayerNumber();
    public String[] setPlayerName(int numberOfPlayers);
    public void buyMenu(GameCreator game, int player);
    public void sellMenu(GameCreator game, int player);
    public void updgradeMenu(GameCreator game, int player);
    public int playerMessage(GameCreator game, int playerNum);
    public int menuLoader(GameCreator game, int player, int userAnswer);
    public void movePlayerAround(GameCreator game, int player, int diceRoll);
    public int diceRoll();
    public void playerEngagement();
    public void playerInJailAction(GameCreator game, int player);
    public void playerTurn(GameCreator game, int player, int amountOfPlayers);
    public void gameForDifferentPlayer(int amountOfPlayers, GameCreator game);
    public void playerPassesThroughGO(GameCreator game, int player);
    public void playerLandsOnChance(GameCreator game, int player);
    public void instructions();
}
