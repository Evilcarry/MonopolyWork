/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author Sean Simpkins
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
