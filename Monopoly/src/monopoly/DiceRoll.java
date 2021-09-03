package monopoly;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
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
        roll = new RandomNum();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(DiceRoll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roll.randomNumGenerator();
    }
}
