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
public class Main {

    public static void main(String[] args) {
        MainApplication newGame = new MainApplication();

        newGame.createLocations();
        newGame.createPlayers();
        newGame.createAssets();
        for (int i = 0; i < 24; i++) {
            System.out.println(newGame.getLocations()[i]);
            if (i < 4) {
                System.out.println(newGame.getPlayers()[i]);
            }
            System.out.println(newGame.getAssets()[i]);
        }
    }
}
