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

        newGame.createAssets(); //creates all the assets for each location.
        /*
        this.buyMenu(newGame, 1); //buy menu
        this.sellMenu(newGame, 1); //sell menu
        this.updgradeMenu(newGame, 1); //upgrade menu
         */

        int userInput;
        boolean runTillStop = true;
        Scanner userScan = new Scanner(System.in);

        while (runTillStop) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Welcome to monopoly");
            System.out.println("Press 0 to end, 1 to start, 2 to load game from a save");

            if (userScan.hasNextInt()) {
                userInput = userScan.nextInt();

                if (userInput == 0) {
                    runTillStop = true;
                } else if (userInput == 1) {
                    boolean gameRun = true;

                    switch (playerNumber) {
                        case 2:
                            while (gameRun) {
                                if (newGame.getPlayers()[0].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                } else if (newGame.getPlayers()[0].getMoney() < 0) {
                                    System.out.println(newGame.getPlayers()[0].getName() + " has ran out of money! you've lost the game buddy.");
                                    System.out.println(newGame.getPlayers()[1].getName() + " has won the game! Congratulatios");
                                    gameRun = false;
                                    runTillStop = false;
                                }
                                if (newGame.getPlayers()[1].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                } else if (newGame.getPlayers()[1].getMoney() < 0) {
                                    System.out.println(newGame.getPlayers()[1].getName() + " has ran out of money! you've lost the game buddy.");
                                    System.out.println(newGame.getPlayers()[0].getName() + " has won the game! Congratulatios");
                                    gameRun = false;
                                    runTillStop = false;
                                }
                            }
                            break;
                        case 3:
                            while (gameRun) {
                                if (newGame.getPlayers()[0].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }
                                if (newGame.getPlayers()[1].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }
                                if (newGame.getPlayers()[2].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }
                            }
                            break;
                        case 4:
                            while (gameRun) {
                                if (newGame.getPlayers()[0].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }
                                if (newGame.getPlayers()[1].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }
                                if (newGame.getPlayers()[2].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }
                                if (newGame.getPlayers()[3].getMoney() > 0) {
                                    System.out.println("---------------------------------------------------------------------------------------------");
                                    //TODO: Offer menu options, buy menu, sell menu, upgrade menu, etc.
                                }

                            }
                            break;
                        default:
                            System.out.println("we havent implemented this part yet");
                            break;
                    }
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
    /**
     * This is the buyMenu method, it will display a menu for the user so they
     * can purchase assets.
     *
     * @param game which is a GameCreator object.
     * @param player is the number of the player.
     */
    public void buyMenu(GameCreator game, int player) {
        if (game.getPlayers()[player].getCurrentLocation().getLocationID() % 3 == 0) {
            System.out.println("Sorry this property is not for sale!");
        } else {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("This is the buy menu, in here you can purchase a house depending on if you have money or not");

            Scanner scan = new Scanner(System.in);
            System.out.println("Here are your options.");
            System.out.println(game.getPlayers()[player].getName() + " is currently standing at location " + game.getPlayers()[player].getCurrentLocation());
            System.out.println("The price of " + game.getPlayers()[player].getCurrentLocation() + " is " + game.getLocations()[game.getPlayers()[player].getCurrentLocation().getLocationID()].cloneObject().getPrice());
            System.out.println("You currently have " + game.getPlayers()[player].getMoney());
            System.out.println("Would you like to purchase it? press 1 to buy, press anything else to exit the buy menu");
            if (scan.hasNextInt()) {
                int userAnswer = scan.nextInt();
                if (userAnswer == 1) {
                    if (game.getPlayers()[player].getMoney() >= game.getLocations()[game.getPlayers()[player].getCurrentLocation().getLocationID()].cloneObject().getPrice()) {
                        game.getPlayers()[player].purchaseAsset(game.getAssets()[game.getPlayers()[player].getCurrentLocation().getLocationID()]);
                        System.out.println("You have successfully purchased this asset");
                    } else {
                        System.out.println("Sorry you do not have sufficient funds to purchase this properly");
                    }
                }
            } else {
                System.out.println("Thank you for accessing the buy menu!");
                System.out.println("---------------------------------------------------------------------------------------------");
            }
        }
    }

    /**
     * This method displays a sell menu for the players. It checks to see if the
     * player has any assets, the location of the assets and it asks the user to
     * see if they will like to sell it or not.
     *
     * @param game GameCreator object
     * @param player the number of the player.
     */
    public void sellMenu(GameCreator game, int player) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Welcome to the sell menu");
        System.out.println("Here you can sell your assets!");
        System.out.println("Here is a list of all the assets you currently own");

        boolean trueTillRightInput = false;
        for (int i = 0; i < 24; i++) {
            if (game.getPlayers()[player].getAsset()[i] != null) {
                System.out.println(game.getPlayers()[player].getAsset()[i]);
                trueTillRightInput = true;
            }
        }
        if (!trueTillRightInput) {
            System.out.println("It appears you do not own any assets at the moment. Access the sell menu when you own something!");
            System.out.println("---------------------------------------------------------------------------------------------");
        }
        while (trueTillRightInput) {
            System.out.println("Would you like to sell any assets? if so please input the location ID of the asset you would like to sell");
            System.out.println("if you do not want to sell any of your assets please input the number 0");
            Scanner sellScan = new Scanner(System.in);
            if (sellScan.hasNextInt()) {
                int locationOfSell = sellScan.nextInt();

                if (game.getPlayers()[player].getAsset()[locationOfSell] != null) {
                    System.out.println("congratulations you've sold your asset");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    game.getPlayers()[player].sellAsset(game.getPlayers()[player].getAsset()[locationOfSell]);
                    trueTillRightInput = false;
                } else if (locationOfSell == 0) {
                    System.out.println("Thank you for accessing the sell menu!");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    trueTillRightInput = false;
                } else {
                    System.out.println("youve entered the wrong location, please try again");
                }
            } else {
                System.out.println("It appears that you've entered the wrong input, please try again");
            }
        }
    }

    /**
     * This method displays an upgrade menu for the players. It checks to see if
     * the player has any assets, the location of the assets and it asks the
     * user to see if they will like to upgrade it or not.
     *
     * @param game GameCreator object
     * @param player the number of the player.
     */
    public void updgradeMenu(GameCreator game, int player) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Welcome to the upgrade menu");
        System.out.println("Here you can upgrade your currently owned assets!");
        System.out.println("Here is a list of all the assets you currently own");

        boolean trueTillRightInput = false;

        for (int i = 0; i < 24; i++) {
            if (game.getPlayers()[player].getAsset()[i] != null) {
                System.out.println(game.getPlayers()[player].getAsset()[i]);
                trueTillRightInput = true;
            }
        }
        if (!trueTillRightInput) {
            System.out.println("It appears you do not own any assets at the moment. Access the upgrade menu when you own something!");
            System.out.println("---------------------------------------------------------------------------------------------");
        }
        while (trueTillRightInput) {
            System.out.println("Would you like to upgrade any assets? if so please input the location ID of the asset you would like to upgrade");
            System.out.println("if you do not want to upgrade any of your assets please input the number 0");
            System.out.println("Each upgrade costs the same amount as buying the property, keep in mind that if you spend all your money you will lose the game");
            Scanner upgradeScan = new Scanner(System.in);
            if (upgradeScan.hasNextInt()) {
                int locationOfUpgrade = upgradeScan.nextInt();
                if (game.getPlayers()[player].getAsset()[locationOfUpgrade] != null) {
                    boolean trueTillRightInputTwo = true;
                    while (trueTillRightInputTwo) {
                        System.out.println("Please input the level of upgrade you want to upgrade it to, ranges from 1 to 4");
                        if (upgradeScan.hasNextInt()) {
                            int levelOfUpgrade = upgradeScan.nextInt();
                            if (levelOfUpgrade < 1 || levelOfUpgrade > 4) {
                                System.out.println("It appears that the number you've entered is not between 1 and 4");
                            } else if ((game.getPlayers()[player].getAsset()[locationOfUpgrade].getLevel() + levelOfUpgrade) > 4) {
                                System.out.println("The maximum upgrade level is 4, You cannot upgrade further than that");
                                System.out.println("Please input a number that will make the upgrade level a maximum of 4");
                                System.out.println("Or if you chose the wrong asset please press 0");
                                if (upgradeScan.hasNextInt()) {
                                    levelOfUpgrade = upgradeScan.nextInt();
                                    if (levelOfUpgrade == 0) {
                                        trueTillRightInputTwo = false;
                                    }
                                }
                            } else {
                                System.out.println(game.getPlayers()[player].getAsset()[locationOfUpgrade] + " will be upgraded to level" + levelOfUpgrade);
                                game.getPlayers()[player].upgradeAsset(game.getPlayers()[player].getAsset()[locationOfUpgrade], levelOfUpgrade);
                                System.out.println("Thank you for accessing the upgrade menu!");
                                System.out.println("---------------------------------------------------------------------------------------------");
                                trueTillRightInputTwo = false;
                            }
                        } else {
                            System.out.println("It appears you've entered the wrong input");
                            System.out.println("Please try again");
                        }
                    }
                } else if (locationOfUpgrade == 0) {
                    System.out.println("Thank you for accessing the upgrade menu!");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    trueTillRightInput = false;
                } else {
                    System.out.println("youve entered the wrong location, please try again");
                }
            } else {
                System.out.println("It appears that you've entered the wrong input, please try again");
            }
        }
    }

    /**
     * This method displays the instructions to the monopoly game.
     */
    public void instructions() {
        System.out.println("Welcome to Monopoly!");
        System.out.println("The rules are simple, the player with the most assets by round 15 wins, or if you all agree to end the game earlier, the player with the most money wins.");
        System.out.println("Each player has to move across the 24 different locations");
        System.out.println("Each player gets given $20000 at the start of the game, a player can get more money by landing on a chance card, by owning a property and other players paying rent and also whenever they complete a round and go through the starting location 'GO'");
        System.out.println("A player can also sell an asset, only allowed to sell one asset per turn");
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
