/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Scanner;

/**
 *
 * @author benjh
 */
public class Main {

    public static void main(String[] args) {
        GameCreator newGame = new GameCreator();

        newGame.createLocations();
        newGame.createPlayers();
        newGame.createAssets();
        int userInput;
        boolean stop = false;
        Scanner scan = new Scanner(System.in);
        while (!stop)
        {
            System.out.println("Welcome to monopoly");
            System.out.println("Press 1 to start 2 to end");
            userInput = scan.nextInt();
            
            if (userInput == 2)
            {
                stop = true;
            }
            else if (userInput != 2){
                System.out.println("This is a list of all the players");
                for (int i = 0; i < 4; i++)
                {
                System.out.println(newGame.getPlayers()[i]);
                }
            }
            System.out.println("player 0 rolls the dice and it lands on 3, player 0 will move to location 3");
            newGame.getPlayers()[0].setCurrentLocation(newGame.getLocations()[3]);
            System.out.println(newGame.getPlayers()[0]);
        }
        
        //THIS CLASS IS USED FOR TESTING AT THE MOMENT.
        /*
        for (int i = 0; i < 24; i++) {
            System.out.println(newGame.getLocations()[i]);
            if (i < 4) {
                System.out.println(newGame.getPlayers()[i]);
            }
            System.out.println(newGame.getAssets()[i]);
        }
        */
        System.out.println(newGame.getPlayers()[3].getName()+" has "+newGame.getPlayers()[3].getJailCounter()+" assets");
    }
}
