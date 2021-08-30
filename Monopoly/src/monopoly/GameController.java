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
public class GameController {

    private GameCreator newGame;

    public GameController() {
        this.newGame = new GameCreator();
    }
    
    public void gameStart()
    {
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
            else
            {
                System.out.println("This is a list of all the players");
                for (int i = 0; i < 4; i++)
                {
                System.out.println(newGame.getPlayers()[i]);
                }
            } 
        }
    }
    public GameCreator getNewGame() {
        return newGame;
    }

    public void setNewGame(GameCreator newGame) {
        this.newGame = newGame;
    }
}
