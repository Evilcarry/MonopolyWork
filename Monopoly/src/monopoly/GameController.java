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
        
        this.instructions();
        
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
    
    public void instructions()
    {
        System.out.println("Welcome to Monopoly!");
        System.out.println("The rules are simple, the player with the most assets by round 15 wins, or if you all agree to end the game earlier, the player with the most money wins.");
        System.out.println("Each player has to move across the 24 different locations");
        System.out.println("Each player gets given $20000 at the start of the game, a player can get more money by landing on a chance card, by owning a property and other players paying rent and also whenever they complete a round and go through the starting location 'GO'");
        System.out.println("Each player can purchase the location they landed on but they must have the necessary funds to purchase it, be careful! if you run out of money you instantly lose the game");
        System.out.println("If a player goes to jail they have to either roll a 6, if they fail to do so in 3 turns, the player will be freed from prison");
        System.out.println("Chance cards can be good and dangerous so be careful.");
    }
    public GameCreator getNewGame() {
        return newGame;
    }

    public void setNewGame(GameCreator newGame) {
        this.newGame = newGame;
    }
}
