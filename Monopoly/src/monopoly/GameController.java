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

    public void gameStart() {
        this.instructions();//to display instructions on the game
        int playerNumber = this.setPlayerNumber(); // sets the number of players
        this.newGame = new GameCreator(playerNumber); //gamecreator instance that pases the number of players
        newGame.createLocations(); //creates all the locations, possibly going to read them off a file
        newGame.createPlayers(playerNumber, this.setPlayerName(playerNumber)); //creates the names of each player
        newGame.createAssets(); //creates all the assets for each location.

        int userInput;
        boolean runTillStop = true;
        Scanner userScan = new Scanner(System.in);

        while (runTillStop) {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("Welcome to monopoly");
            System.out.println("Press 0 to end, 1 to start, 2 to load game from a save");

            if (userScan.hasNextInt()) {
                userInput = userScan.nextInt();
                if (userInput < 2 && userInput >= 0) {
                    if (userInput == 0) {
                        runTillStop = false;
                    } else if (userInput == 1) {
                        this.gameForDifferentPlayer(playerNumber, newGame);
                    } else if (userInput == 2) {
                        //TODO: read file and load from here?
                    } else {
                        System.out.println("Something wrong apparently");
                    }
                }
            } else {
                System.out.println("something wrong apparently");
                userScan.next();
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
     * This method allows the userOption to set their name
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

    /**
     * This is the buyMenu method, it will display a menu for the userOption so
     * they can purchase assets.
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
     * This method asks the player for an input from 1 to 4 to see what they
     * want to do if the player inputs 0 the program skips the user turn
     *
     * @param game
     * @param playerNum
     * @return the number the user input.
     */
    public int playerMessage(GameCreator game, int playerNum) {
        Scanner userScan = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(game.getPlayers()[playerNum].getName() + " here are your options, Press 1 to roll your dice, press 2 for the buy meny, press 3 for the sell menu, press 4 for the upgrade menu, to skip your turn press 0");
        boolean TrueTillRightInput = true;
        int userInput = 0;
        while (TrueTillRightInput) {
            if (userScan.hasNextInt()) {
                userInput = userScan.nextInt();
                if (userInput > 4 && userInput < 0) {
                    System.out.println("It appears you've entered the wrong input!");
                    System.out.println("Please try again");
                    userScan.next();
                } else {
                    TrueTillRightInput = false;
                }
            }
        }
        return userInput;
    }

    /**
     * This method reacts differently based on the user input. It calls a dice
     * roll, opens the buy menu, the sell menu, the upgrade menu.
     *
     * @param game
     * @param player
     * @param userAnswer
     * @return
     */
    public int menuLoader(GameCreator game, int player, int userAnswer) {
        switch (userAnswer) {
            case 1:
                return 1;
            case 2:
                this.buyMenu(game, player);
                return 2;
            case 3:
                this.sellMenu(game, player);
                return 2;
            case 4:
                this.updgradeMenu(game, player);
                return 2;
        }
        return 0;
    }

    /**
     * This method moves a player across the board.
     *
     * @param game
     * @param player
     * @param diceRoll
     */
    public void movePlayerAround(GameCreator game, int player, int diceRoll) {
        System.out.println(game.getPlayers()[player].getName() + " has rolled a " + diceRoll + " , you will move " + diceRoll + " spaces");
        int counter = game.getPlayers()[player].getCurrentLocation().getLocationID() + diceRoll;
        if (counter > 23) {
            counter = counter - 23;
            game.getPlayers()[player].setRounds(game.getPlayers()[player].getRounds() + 1);
            game.getPlayers()[player].setCurrentLocation(game.getLocations()[counter]);
        } else {
            game.getPlayers()[player].setCurrentLocation(game.getLocations()[game.getPlayers()[player].getCurrentLocation().getLocationID() + diceRoll]);
        }
        System.out.println(game.getPlayers()[player]);
    }

    /**
     * This method rolls a die
     *
     * @return the random number generated.
     */
    public int diceRoll() {
        RandomNum roll = new RandomNum();
        return roll.randomNumGenerator();
    }

    public void playerEngagement() {
        Scanner engagementScan = new Scanner(System.in);

        boolean trueTillRightInput = true;
        while (trueTillRightInput) {
            System.out.println("input a 0 to quit the game, any other number will roll your die");
            if (engagementScan.hasNextInt()) {
                int userInput = engagementScan.nextInt();

                if (userInput == 0) {
                    //TODO: QUIT GAME
                } else {
                    System.out.println("Rolling your die!");
                    trueTillRightInput = false;
                }
            } else {
                System.out.println("It appears you have entered the wrong input, please try again");
                engagementScan.next();
            }
        }
    }

    /**
     * This method will make it so if a player is in jail he only has the option
     * to either roll or pay their way out.
     *
     * @param game
     * @param playerNumber
     */
    public void playerInJailAction(GameCreator game, int player) {
        System.out.println("It appears you've landed in jail");
        System.out.println("you are currently locked in jail for 3 turns, if you roll a 6 you will be released from jail, or you can buy your way out for $10000");
        System.out.println("you currently have: " + game.getPlayers()[player].getMoney() + ". Remember that if you go negative you will lose the game");
        System.out.println("Press 1 to roll the die or press 2 to pay your way out.");

        Scanner jailScan = new Scanner(System.in);
        boolean trueTillRightInput = true;
        while (trueTillRightInput) {
            if (jailScan.hasNextInt()) {
                int userInput = jailScan.nextInt();
                if (userInput == 1) {
                    int diceRoll = this.diceRoll();
                    System.out.println("you have rolled a " + diceRoll);
                    if (diceRoll == 6) {
                        System.out.println("Congratulations, you are out of jail");
                        game.getPlayers()[player].setJailState(false);
                        game.getPlayers()[player].setJailCounter(0);
                        trueTillRightInput = false;
                    } else {
                        game.getPlayers()[player].setJailCounter(game.getPlayers()[player].getJailCounter() + 1);
                        if (game.getPlayers()[player].getJailCounter() == 3) {
                            System.out.println("You are out of jail!, in your next turn you will be able to play again");
                            game.getPlayers()[player].setJailState(false);
                            game.getPlayers()[player].setJailCounter(0);
                            trueTillRightInput = false;
                        } else {
                            System.out.println("unfortunately you didnt roll a 6, you are still in jail for " + (3 - game.getPlayers()[player].getJailCounter()) + " more turns");
                            trueTillRightInput = false;
                        }
                    }
                } else if (userInput == 2) {
                    game.getPlayers()[player].chargePlayer(10000);
                    if (game.getPlayers()[player].getMoney() > 0) {
                        System.out.println("You have successfully purchased your way out of jail");
                        game.getPlayers()[player].setJailState(false);
                        game.getPlayers()[player].setJailCounter(0);
                        trueTillRightInput = false;
                    } else {
                        System.out.println("It seems that you tried to purchase your way out of jail but you can't afford it");
                        System.out.println("Unfortunately you are now in the negative.");
                        trueTillRightInput = false;
                    }
                }
            }
        }
    }

    /**
     * This is in charge of running the turn of each player
     * @param game GameCrator object
     * @param player integer stating which player
     */
    public void playerTurn(GameCreator game, int player) {
        if (game.getPlayers()[player].isJailState()) {
            this.playerInJailAction(game, player);
        } else {
            int userOption = this.menuLoader(game, player, this.playerMessage(game, player));
            if (userOption == 0) {
                System.out.println("Since you skipped your turn, the die will roll");
                this.playerEngagement();
                int diceRoll = this.diceRoll();
                this.movePlayerAround(game, player, diceRoll);
                if (game.getPlayers()[player].getCurrentLocation() == game.getLocations()[6] || game.getPlayers()[player].getCurrentLocation() == game.getLocations()[12]) {
                    if (!game.getPlayers()[player].isJailState()) {
                        game.getPlayers()[player].setJailState(true);
                    }
                }
            } else if (userOption == 2) {
                System.out.println("Now you can roll you die!");
                this.playerEngagement();
                int diceRoll = this.diceRoll();
                this.movePlayerAround(game, player, diceRoll);
            } else if (userOption == 1) {
                this.playerEngagement();
                int diceRoll = this.diceRoll();
                this.movePlayerAround(game, player, diceRoll);
            }
        }

    }

    /**
     * This will run a different game depending on the number of players.
     * @param amountOfPlayers
     * @param game GameCreator
     */
    public void gameForDifferentPlayer(int amountOfPlayers, GameCreator game) {
        boolean gameRun = true;
        int playerOne = 0;
        int playerTwo = 1;
        int playerThree = 2;
        int playerFour = 3;

        switch (amountOfPlayers) {
            case 2:
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) {
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.playerTurn(game, playerOne);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has won the game! Congratulatios");
                            game.getPlayers()[playerOne].setInGame(false);
                            game.getPlayers()[playerTwo].setInGame(false);
                            gameRun = false;
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) {
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.playerTurn(game, playerTwo);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println(game.getPlayers()[playerOne].getName() + " has won the game! Congratulatios");
                            game.getPlayers()[playerOne].setInGame(false);
                            game.getPlayers()[playerTwo].setInGame(false);
                            gameRun = false;
                        }
                    }
                }
                break;

            case 3: //Still need to know how to handle one player losing.
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) { //PLAYER 1
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.playerTurn(game, playerOne);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerOne].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) { //PLAYER 2
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.playerTurn(game, playerTwo);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerTwo].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerThree].isInGame()) { //PLAYER 3
                        if (game.getPlayers()[playerThree].getMoney() > 0) {
                            this.playerTurn(game, playerThree);
                        } else {
                            System.out.println(game.getPlayers()[playerThree].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerThree].setInGame(false);
                        }
                    }
                    if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame()) {
                        System.out.println(game.getPlayers()[playerThree].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        System.out.println(game.getPlayers()[playerTwo].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        System.out.println(game.getPlayers()[playerOne].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    }
                }
                break;

            case 4: //Still need to know how to handle one player losing.
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) { //PLAYER 1
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.playerTurn(game, playerOne);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerOne].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) { //PLAYER 2
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.playerTurn(game, playerTwo);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerTwo].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerThree].isInGame()) { //PLAYER 3
                        if (game.getPlayers()[playerThree].getMoney() > 0) {
                            this.playerTurn(game, playerThree);
                        } else {
                            System.out.println(game.getPlayers()[playerThree].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerThree].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerFour].isInGame()) { //PLAYER 4
                        if (game.getPlayers()[playerFour].getMoney() > 0) {
                            this.playerTurn(game, playerFour);
                        } else {
                            System.out.println(game.getPlayers()[playerFour].getName() + " has ran out of money! you've lost the game buddy.");
                            game.getPlayers()[playerFour].setInGame(false);
                        }
                    }
                    if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        System.out.println(game.getPlayers()[playerFour].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        System.out.println(game.getPlayers()[playerThree].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerThree].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        System.out.println(game.getPlayers()[playerTwo].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        System.out.println(game.getPlayers()[playerOne].getName() + " has won the game! Congratulations");
                        gameRun = false;
                    }
                }
                break;
        }
    }

    /**
     * This method displays the instructions to the monopoly game.
     */
    public void instructions() {
        System.out.println("Welcome to Monopoly!");
        System.out.println("The rules are simple, the player with the most assets by round 15 wins, or if you all agree to end the game earlier, the player with the most money wins.");
        System.out.println("Each player has to move across the 24 different locations");
        System.out.println("Each player gets given $200000 at the start of the game, a player can get more money by landing on a chance card, by owning a property and other players paying rent and also whenever they complete a round and go through the starting location 'GO'");
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
