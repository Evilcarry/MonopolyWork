package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public interface MenuInterface {

    public void buyMenu(GameCreator game, int player);

    public void sellMenu(GameCreator game, int player);

    public void updgradeMenu(GameCreator game, int player);

    public int menuLoader(GameCreator game, int player, int userAnswer);
    
    public boolean ownsProperty(GameCreator game, int amountOfPlayers);
}
