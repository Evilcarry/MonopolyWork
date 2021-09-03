package monopoly;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public interface ActionInterface {

    public void gameStart();

    public int setPlayerNumber();

    public String[] setPlayerName(int numberOfPlayers);

    public void movePlayerAround(GameCreator game, int player, int diceRoll);

    public void gameTurn(GameCreator game, int player, int amountOfPlayers);

    public void gameForDifferentPlayer(int amountOfPlayers, GameCreator game);

    public void instructions();

    public int startWithoutLoading();

    public int startWithLoading();
}
