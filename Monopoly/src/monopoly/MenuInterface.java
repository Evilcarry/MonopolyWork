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
public interface MenuInterface {

    public void buyMenu(GameCreator game, int player);

    public void sellMenu(GameCreator game, int player);

    public void updgradeMenu(GameCreator game, int player);

    public int menuLoader(GameCreator game, int player, int userAnswer);
}
