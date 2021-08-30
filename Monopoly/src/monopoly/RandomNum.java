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

    int first;

    RandomNum() 
    {
        first = 1;
    }

    public void randomNum() 
    {
        Random rand = new Random();

        this.first = rand.nextInt(6);
    }

}
