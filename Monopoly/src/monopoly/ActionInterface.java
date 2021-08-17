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
    public void returnThreeSpaces(); //moves player 3 spaces back
    public void winMoney(int money); //gives x amount of money to the player
}
