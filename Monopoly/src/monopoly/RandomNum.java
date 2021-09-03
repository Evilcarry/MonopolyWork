package monopoly;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
import java.util.Random;

public class RandomNum {

    private final int maxDiceNumber;

    public RandomNum() {
        this.maxDiceNumber = 6;
    }

    public int getFirst() {
        return maxDiceNumber;
    }

    public int randomNumGenerator() {
        Random rand = new Random();
        return rand.nextInt(this.maxDiceNumber) + 1;
    }

}
