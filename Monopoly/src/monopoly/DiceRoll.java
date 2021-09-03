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
public class DiceRoll {

    private RandomNum roll;

    public DiceRoll() {
        this.roll = new RandomNum();
    }

    /**
     * This method rolls a die
     *
     * @return the random number generated.
     */
    public int diceRoll() {
        RandomNum roll = new RandomNum();
        return roll.randomNumGenerator();
    }
}
