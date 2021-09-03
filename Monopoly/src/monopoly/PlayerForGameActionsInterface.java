package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public interface PlayerForGameActionsInterface {
    public int playerMessage(GameCreator game, int playerNum); //this to player for game
    public void playerEngagement(); // //this to player for game
    public void playerInJailAction(GameCreator game, int player); // //this to player for game
    public void playerPassesThroughGO(GameCreator game, int player); //this to player for game
    public boolean playerLandsOnChance(GameCreator game, int player); //this to player for game
    public void playerPaysRent(GameCreator game, int amountOfPlayers, int currentPlayer); // this to player for game
    public void playerDisplayBalance(GameCreator game, int player);
}
