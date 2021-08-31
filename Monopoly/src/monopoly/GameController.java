/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * @author benjh
 */
public class GameController {

    private GameCreator newGame;

    public void gameStart() {
        this.instructions();//to display instructions on the game

        int playerNumber = this.setPlayerNumber(); // sets the number of players

        this.newGame = new GameCreator(playerNumber); //gamecreator instance that pases the number of players

        newGame.createLocations(); //creates all the locations, possibly going to read them off a file

        newGame.createPlayers(playerNumber, this.setPlayerName(playerNumber)); //creates the names of each player

        newGame.getPlayers()[1].movePlayer(newGame.getLocations()[2]);
        
        newGame.createAssets(); //creates all the assets for each location.

        int userInput;
        boolean stop = false;
        Scanner scan = new Scanner(System.in);
        while (!stop) {

            System.out.println("Welcome to monopoly");
            System.out.println("Press 1 to start 2 to end");
            userInput = scan.nextInt();

            if (userInput == 2) {
                stop = true;
            } else {
                System.out.println("This is a list of all the players");
                for (int i = 0; i < playerNumber; i++) {
                    System.out.println(newGame.getPlayers()[i]);
                }
            }
        }
    }

    /**
     * This method checks to see how many players will play the game. Between 2
     * and 4 only, it also checks to see if the user correctly input a number
     * between 2 and 4.
     *
     * @return the number of players, between 2 and 4.
     */
    public int setPlayerNumber() {
        Scanner playerNumScan = new Scanner(System.in);
        int numberOfPlayers = 2; // initiated to the default number.

        System.out.println("Please select how many players you want in the game, minimum 2 and maximum 4");

        boolean trueTillRightInput = true;

        while (trueTillRightInput) {
            if (playerNumScan.hasNextInt()) {
                numberOfPlayers = playerNumScan.nextInt();
                if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                    System.out.println("You've entered a number lower than 2 and greater than 4! Please input the correct number of players (between 2 and 4)");
                } else {
                    trueTillRightInput = false;
                }
            } else {
                System.out.println("You've entered the wrong input please try again. The input must be an integer between 2 and 4");
                playerNumScan.next();
            }
        }
        return numberOfPlayers;
    }

    /**
     * This method allows the user to set their name
     *
     * @param numberOfPlayers is the number of players that will be playing.
     * @return names.
     */
    public String[] setPlayerName(int numberOfPlayers) {
        Scanner playerNameScan = new Scanner(System.in);
        String[] names = new String[numberOfPlayers];

        for (int i = 0; i < names.length; i++) {
            System.out.println("Please input the name of the player number " + (i + 1));
            names[i] = playerNameScan.nextLine();
            System.out.println("You have entered " + names[i] + " as your name");
        }
        return names;
    }

    /*
    public Integer[] rollToSeePlayerOrder(int numberOfPlayers, GameCreator game) {
        System.out.println("Now lets see which player will go first");
        System.out.println("If more than one player rolls the same number a second round of rolling will occur");
        System.out.println("A die will roll " + numberOfPlayers + " times.");

        Scanner userConfirmation = new Scanner(System.in);
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Integer[] num = new Integer[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            String userInput;
            RandomNum dieRoll = new RandomNum();
            System.out.println(game.getPlayers()[i].getName() + "  press enter to roll");
            userInput = userConfirmation.nextLine();
        }     
        return num;
    }
    */

    public void buyMenu(GameCreator game, int player)
    {
        System.out.println("This is the buy menu, in here you can purchase a house depending on if you have money or not");
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Here are your options.");
        System.out.println(game.getPlayers()[player].getName()+" is currently standing at location "+ game.getPlayers()[player].getCurrentLocation());
        System.out.println("The price of "+ game.getPlayers()[player].getCurrentLocation() + " is " + game.getLocations()[game.getPlayers()[player].getCurrentLocation().getLocationID()]);
    }
    
    /**
     * This method displays the instructions to the monopoly game.
     */
    public void instructions() {
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
