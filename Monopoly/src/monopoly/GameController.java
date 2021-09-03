package monopoly;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class GameController implements ActionInterface {

    private GameCreator newGame;
    private MenuForGame menu;

    /**
     * This method starts the game
     */
    @Override
    public void gameStart() {
        int userInput;
        boolean runTillStop = true;
        Scanner userScan = new Scanner(System.in);

        while (runTillStop) {
            System.out.println("--------------------------------------------------------------------MONOPOLY!-------------------------------------------------------------------------------------------------");
            System.out.println("Welcome to monopoly");
            System.out.println("Press 0 to end, 1 to start, 2 to load game from a save");

            if (userScan.hasNextInt()) {
                userInput = userScan.nextInt();
                if (userInput <= 2 && userInput >= 0) {
                    if (userInput == 0) {
                        runTillStop = false;
                    } else if (userInput == 1) {
                        int amountOfPlayers = this.startWithoutLoading();
                        this.gameForDifferentPlayer(amountOfPlayers, newGame);
                    } else if (userInput == 2) {
                        int amountOfPlayers = this.startWithLoading();
                        this.gameForDifferentPlayer(amountOfPlayers, newGame);
                    } else {
                        System.out.println("Something wrong apparently");
                    }
                }
            } else {
                System.out.println("It appears you have entered the wrong input, please try again");
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
    @Override
    public int setPlayerNumber() {
        Scanner playerNumScan = new Scanner(System.in);
        int numberOfPlayers = 2; // initiated to the default number.

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return numberOfPlayers;
    }

    /**
     * this method is to start the game without loading.
     *
     * @return amount of players in the game.
     */
    @Override
    public int startWithoutLoading() {
        this.instructions();//to display instructions on the game
        int amountOfPlayers = this.setPlayerNumber(); // sets the number of players
        this.newGame = new GameCreator(amountOfPlayers); //gamecreator instance that pases the number of players
        this.newGame.createLocations(); //creates all the locations, possibly going to read them off a file
        this.newGame.createPlayers(amountOfPlayers, this.setPlayerName(amountOfPlayers)); //creates the names of each player
        this.newGame.createAssets(); //creates all the assets for each location.
        this.menu = new MenuForGame(this.newGame);
        return amountOfPlayers;
    }

    /**
     * This method is to start the game from a saved file
     *
     * @return
     */
    @Override
    public int startWithLoading() {
        this.instructions();//to display instructions on the game
        int amountOfPlayers = this.setPlayerNumber(); // sets the number of players
        this.newGame = new GameCreator(amountOfPlayers); //gamecreator instance that pases the number of players
        this.newGame.createLocations(); //creates all the locations, possibly going to read them off a file
        this.newGame.createPlayers(amountOfPlayers, this.loadPlayer(amountOfPlayers)); //Here we will create players from the save
        this.newGame.createAssets(); //creates all the assets for each location.
        this.menu = new MenuForGame(this.newGame);
        System.out.println("Just to remind everyone, of their stats");
        for (int i = 0; i < amountOfPlayers; i++) {
            System.out.println(this.newGame.getPlayers()[i]);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return amountOfPlayers;
    }

    /**
     * This method allows the userOption to set their name
     *
     * @param numberOfPlayers is the number of players that will be playing.
     * @return names.
     */
    @Override
    public String[] setPlayerName(int numberOfPlayers) {
        Scanner playerNameScan = new Scanner(System.in);
        String[] names = new String[numberOfPlayers];

        for (int i = 0; i < names.length; i++) {
            System.out.println("Please input the name of the player number " + (i + 1));
            names[i] = playerNameScan.nextLine();
            System.out.println("You have entered " + names[i] + " as your name");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return names;
    }

    /**
     * This method asks the player for an input from 1 to 4 to see what they
     * want to do if the player inputs 0 the program skips the user turn
     *
     * @param game
     * @param playerNum
     * @return the number the user input.
     */
    @Override
    public int playerMessage(GameCreator game, int playerNum) {
        Scanner userScan = new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(game.getPlayers()[playerNum].getName() + " you are currently standing in " + game.getPlayers()[playerNum].getCurrentLocation().getName());
        System.out.println(game.getPlayers()[playerNum].getName() + " here are your options, Press 1 to roll your die, press 2 for the buy meny, press 3 for the sell menu, press 4 for the upgrade menu, to skip your turn press 0");
        System.out.println("If you access a menu, you will lose the chance to access a different menu.");
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
     * This method moves a player across the board.
     *
     * @param game
     * @param player
     * @param diceRoll
     */
    @Override
    public void movePlayerAround(GameCreator game, int player, int diceRoll) {
        System.out.println(game.getPlayers()[player].getName() + " has rolled a " + diceRoll + " , you will move " + diceRoll + " spaces");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int counter = game.getPlayers()[player].getCurrentLocation().getLocationID() + diceRoll;
        if (counter > 23) {
            counter = counter - 23;
            game.getPlayers()[player].setRounds(game.getPlayers()[player].getRounds() + 1);
            game.getPlayers()[player].movePlayer(game.getLocations()[counter]);
            game.getPlayers()[player].setPaidThisRound(false);
        } else {
            game.getPlayers()[player].movePlayer(game.getLocations()[game.getPlayers()[player].getCurrentLocation().getLocationID() + diceRoll]);
        }
        System.out.println(game.getPlayers()[player]);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * This method rolls a die
     *
     * @return the random number generated.
     */
    @Override
    public int diceRoll() {
        RandomNum roll = new RandomNum();
        return roll.randomNumGenerator();
    }

    /**
     * This is just to engage the player.
     */
    @Override
    public void playerEngagement() {
        Scanner engagementScan = new Scanner(System.in);

        boolean trueTillRightInput = true;
        while (trueTillRightInput) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("input a 0 to quit the game, any other number will roll your die");
            if (engagementScan.hasNextInt()) {
                int userInput = engagementScan.nextInt();

                if (userInput == 0) {
                    this.exitGame();
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

    public void exitGame() {
        System.out.println("---------------------------------------------------------------------GOOD-BYE!------------------------------------------------------------------------------------------------");
        System.out.println("Thank you for playing the game. It will automatically save");
        System.exit(0);
    }

    /**
     * This method will make it so if a player is in jail he only has the option
     * to either roll or pay their way out.
     *
     * @param game
     * @param player
     */
    @Override
    public void playerInJailAction(GameCreator game, int player) {
        System.out.println("--------------------------------------------------------------------------JAIL------------------------------------------------------------------------------------------------");
        System.out.println(game.getPlayers()[player].getName() + " it appears you've landed in jail");
        System.out.println("you are currently locked in jail for " + (3 - game.getPlayers()[player].getJailCounter()) + " more turns, if you roll a 6 you will be released from jail, or you can buy your way out for $10000");
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
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        if (game.getPlayers()[player].getCurrentLocation().getLocationID() == 6) {
                            game.getPlayers()[player].moveOutOfJail(game.getLocations()[6]);
                            trueTillRightInput = false;
                        } else {
                            game.getPlayers()[player].moveOutOfJail(game.getLocations()[12]);
                            trueTillRightInput = false;
                        }
                    } else {
                        game.getPlayers()[player].setJailCounter(game.getPlayers()[player].getJailCounter() + 1);
                        if (game.getPlayers()[player].getJailCounter() == 3) {
                            System.out.println("You are out of jail!, in your next turn you will be able to play again");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            if (game.getPlayers()[player].getCurrentLocation().getLocationID() == 6) {
                                game.getPlayers()[player].moveOutOfJail(game.getLocations()[6]);
                                trueTillRightInput = false;
                            } else {
                                game.getPlayers()[player].moveOutOfJail(game.getLocations()[12]);
                                trueTillRightInput = false;
                            }
                        } else {
                            System.out.println("unfortunately you didnt roll a 6, you are still in jail for " + (3 - game.getPlayers()[player].getJailCounter()) + " more turns");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            trueTillRightInput = false;
                        }
                    }
                } else if (userInput == 2) {
                    game.getPlayers()[player].chargePlayer(10000);
                    if (game.getPlayers()[player].getMoney() > 0) {
                        System.out.println("You have successfully purchased your way out of jail");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        if (game.getPlayers()[player].getCurrentLocation().getLocationID() == 6) {
                            game.getPlayers()[player].moveOutOfJail(game.getLocations()[6]);
                            trueTillRightInput = false;
                        } else {
                            game.getPlayers()[player].moveOutOfJail(game.getLocations()[12]);
                            trueTillRightInput = false;
                        }
                    } else {
                        System.out.println("It seems that you tried to purchase your way out of jail but you can't afford it");
                        System.out.println("Unfortunately you are now in the negative.");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        trueTillRightInput = false;
                    }
                } else {
                    System.out.println("It apears you have entered the wrong input! please try again");
                    jailScan.next();
                }
            } else {
                System.out.println("It apears you have entered the wrong input! please try again");
                jailScan.next();
            }
        }
    }

    /**
     * This is in charge of running the turn of each player
     *
     * @param game GameCrator object
     * @param player integer stating which player
     * @param amountOfPlayers
     */
    @Override
    public void playerTurn(GameCreator game, int player, int amountOfPlayers) {
        if (game.getPlayers()[player].isJailState()) {
            this.playerInJailAction(game, player);
        } else {
            if (game.getPlayers()[player].getRounds() >= 1 && !game.getPlayers()[player].isPaidThisRound()) {
                game.getPlayers()[player].setPaidThisRound(true);
                this.playerPassesThroughGO(game, player);
            }
            boolean playerMove = false;
            if ((game.getPlayers()[player].getCurrentLocation().getLocationID() % 3) == 0 && !game.getPlayers()[player].isJailState() && game.getPlayers()[player].getCurrentLocation().getLocationID() != 0) {
                playerMove = this.playerLandsOnChance(game, player);
            }

            if (!playerMove) {
                int userOption = this.menu.menuLoader(game, player, this.playerMessage(game, player));
                if (userOption == 0) {
                    System.out.println("Since you skipped your turn, the die will roll");
                    this.savePlayer(game, amountOfPlayers);
                    this.playerEngagement();
                    int diceRoll = this.diceRoll();
                    this.movePlayerAround(game, player, diceRoll);
                    this.playerPaysRent(game, amountOfPlayers, player);
                    if (game.getPlayers()[player].getCurrentLocation() == game.getLocations()[6] || game.getPlayers()[player].getCurrentLocation() == game.getLocations()[12]) {
                        if (!game.getPlayers()[player].isJailState()) {
                            game.getPlayers()[player].setJailState(true);
                            System.out.println(game.getPlayers()[player].getName() + " it seems that you have commited a crime! you are now in jail for 3 turns!");
                        }
                    }
                } else if (userOption == 2) {
                    System.out.println("Now you can roll you die!");
                    this.savePlayer(game, amountOfPlayers);
                    this.playerEngagement();
                    int diceRoll = this.diceRoll();
                    this.movePlayerAround(game, player, diceRoll);
                    this.playerPaysRent(game, amountOfPlayers, player);
                    if (game.getPlayers()[player].getCurrentLocation() == game.getLocations()[6] || game.getPlayers()[player].getCurrentLocation() == game.getLocations()[12]) {
                        if (!game.getPlayers()[player].isJailState()) {
                            game.getPlayers()[player].setJailState(true);
                            System.out.println(game.getPlayers()[player].getName() + " it seems that you have commited a crime! you are now in jail for 3 turns!");
                        }
                    }
                } else if (userOption == 1) {
                    this.savePlayer(game, amountOfPlayers);
                    this.playerEngagement();
                    int diceRoll = this.diceRoll();
                    this.movePlayerAround(game, player, diceRoll);
                    this.playerPaysRent(game, amountOfPlayers, player);
                    if (game.getPlayers()[player].getCurrentLocation() == game.getLocations()[6] || game.getPlayers()[player].getCurrentLocation() == game.getLocations()[12]) {
                        if (!game.getPlayers()[player].isJailState()) {
                            game.getPlayers()[player].setJailState(true);
                            System.out.println(game.getPlayers()[player].getName() + " it seems that you have commited a crime! you are now in jail for 3 turns!");
                        }
                    }
                }
            }
        }

    }

    /**
     * This will run a different game depending on the number of players.
     *
     * @param amountOfPlayers
     * @param game GameCreator
     */
    @Override
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
                            this.playerTurn(game, playerOne, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has won the game! Congratulatios");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerOne].setInGame(false);
                            game.getPlayers()[playerTwo].setInGame(false);
                            gameRun = false;
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) {
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.playerTurn(game, playerTwo, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println(game.getPlayers()[playerOne].getName() + " has won the game! Congratulatios");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerOne].setInGame(false);
                            game.getPlayers()[playerTwo].setInGame(false);
                            gameRun = false;
                        }
                    }
                }
                break;

            case 3:
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) { //PLAYER 1
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.playerTurn(game, playerOne, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerOne].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) { //PLAYER 2
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.playerTurn(game, playerTwo, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerTwo].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerThree].isInGame()) { //PLAYER 3
                        if (game.getPlayers()[playerThree].getMoney() > 0) {
                            this.playerTurn(game, playerThree, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerThree].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerThree].setInGame(false);
                        }
                    }
                    if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame()) {
                        System.out.println(game.getPlayers()[playerThree].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        System.out.println(game.getPlayers()[playerTwo].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        System.out.println(game.getPlayers()[playerOne].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    }
                }
                break;

            case 4:
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) { //PLAYER 1
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.playerTurn(game, playerOne, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerOne].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) { //PLAYER 2
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.playerTurn(game, playerTwo, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerTwo].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerThree].isInGame()) { //PLAYER 3
                        if (game.getPlayers()[playerThree].getMoney() > 0) {
                            this.playerTurn(game, playerThree, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerThree].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerThree].setInGame(false);
                        }
                    }
                    if (game.getPlayers()[playerFour].isInGame()) { //PLAYER 4
                        if (game.getPlayers()[playerFour].getMoney() > 0) {
                            this.playerTurn(game, playerFour, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerFour].getName() + " has ran out of money! you've lost the game buddy.");
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[playerFour].setInGame(false);
                        }
                    }
                    if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        System.out.println(game.getPlayers()[playerFour].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        System.out.println(game.getPlayers()[playerThree].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerThree].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        System.out.println(game.getPlayers()[playerTwo].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    } else if (!game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        System.out.println(game.getPlayers()[playerOne].getName() + " has won the game! Congratulations");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        gameRun = false;
                    }
                }
                break;
        }
    }

    /**
     * This will get triggered every time a player completes a full round.
     *
     * @param game GameCreator object
     * @param player the player number
     */
    @Override
    public void playerPassesThroughGO(GameCreator game, int player) {
        System.out.println("----------------------------------------------------------------------PLAYER-PASSES-THROUGH-GO--------------------------------------------------------------------------------");
        System.out.println("You have completed one full round and you crossed go, you will be paid 10000");
        System.out.println(game.getPlayers()[player].getName() + " your current balance is: " + game.getPlayers()[player].getMoney());
        game.getPlayers()[player].payPlayer(10000);
        System.out.println(game.getPlayers()[player].getName() + " your new balance is: " + game.getPlayers()[player].getMoney());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * This method is to see if a player needs to pay rent.
     *
     * @param game
     * @param amountOfPlayers
     * @param currentPlayer
     */
    public void playerPaysRent(GameCreator game, int amountOfPlayers, int currentPlayer) {
        for (int player = 0; player < amountOfPlayers; player++) {
            if (player != currentPlayer) {
                for (int k = 0; k < 24; k++) {
                    if (game.getPlayers()[player].getAsset()[k] != null) {
                        if (game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getLocationID() == game.getPlayers()[currentPlayer].getCurrentLocation().getLocationID()) {
                            System.out.println("-----------------------------------------------------------------LANDED-ON-AN-ASSET-OWNED-BY-A-PLAYER-------------------------------------------------------------------------");
                            System.out.println("Oh no! " + game.getPlayers()[currentPlayer].getName() + " has landed on the asset of " + game.getPlayers()[player].getName());
                            System.out.println(game.getPlayers()[currentPlayer].getName() + " has to pay " + game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getRentPrice() + " to " + game.getPlayers()[player].getName());
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            game.getPlayers()[currentPlayer].chargePlayer(game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getRentPrice());
                            game.getPlayers()[player].payPlayer(game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getRentPrice());
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will check to see if the player can purchase the asset or not
     *
     * @param game
     * @param amountOfPlayers
     * @param currentPlayer
     * @return true or false;
     */
    public boolean checkIfAssetIsAlreadyOwned(GameCreator game, int amountOfPlayers, int currentPlayer) {
        for (int player = 0; player < amountOfPlayers; player++) {
            if (player != currentPlayer) {
                for (int k = 0; k < 24; k++) {
                    if (game.getPlayers()[player].getAsset()[k] != null) {
                        if (game.getPlayers()[player].getAsset()[k].getBoardPosition().cloneObject().getLocationID() == game.getPlayers()[currentPlayer].getCurrentLocation().getLocationID()) {
                            System.out.println("Sorry it appears that this house is already owned by someone else");
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * When a player lands on chance this get triggered.
     *
     * @param game
     * @param player
     * @return if it returns false that means the player didn't go jail.
     */
    @Override
    public boolean playerLandsOnChance(GameCreator game, int player
    ) {
        System.out.println("--------------------------------------------------------LANDED-ON-CHANCE------------------------------------------------------------------------------------------------------");
        System.out.println(game.getPlayers()[player].getName() + " has landed on a chance card, lets roll the dioe to see what you will get");
        this.playerEngagement();
        int diceRoll = this.diceRoll();
        System.out.println(game.getPlayers()[player].getName() + " has rolled a " + diceRoll);
        boolean check = false;

        switch (diceRoll) {
            case 1://fall through
            case 2:
                System.out.println("Congratulations you have won 10000! this will be added to you account");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                game.getPlayers()[player].payPlayer(10000);
                break;
            case 3://fall through
            case 4:
                System.out.println("Oh no! the bank is asking you to pay 5000 for taxes! 5000 will be charged from your account");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                game.getPlayers()[player].chargePlayer(5000);
                break;
            case 5://fall through
            case 6:
                System.out.println("Well it seems that youre quite unlucky today! the police has just notified us that you were seen speeding, you will go to jail!");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                game.getPlayers()[player].moveToJail(game.getLocations()[6]);
                check = true;
                break;
        }
        return check;
    }

    /**
     * This method saves all players.
     *
     * @param game
     * @param amountOfPlayers
     */
    @Override
    public void savePlayer(GameCreator game, int amountOfPlayers
    ) {
        for (int i = 0; i < amountOfPlayers; i++) {
            try {
                String filename = "player" + i + ".ser";
                FileOutputStream file = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(file);

                out.writeObject(game.getPlayers()[i]);

            } catch (IOException ex) {
                System.out.println("IOException is caught");
            }
        }
    }

    /**
     * This method loads the players and returns them in an array.
     *
     * @param amountOfPlayers
     * @return array of Player with the size of amountOfPlayers
     */
    @Override
    public Player[] loadPlayer(int amountOfPlayers
    ) {
        Player[] players = new Player[amountOfPlayers];

        for (int i = 0; i < amountOfPlayers; i++) {
            try {
                String filename = "player" + i + ".ser";
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                players[i] = (Player) in.readObject();

            } catch (IOException e) {
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException is caught");
            }
        }

        return players;
    }

    /**
     * This method displays the instructions to the monopoly game.
     */
    @Override
    public void instructions() {
        System.out.println("----------------------------------------------------------------------INSTRUCTIONS--------------------------------------------------------------------------------------------");
        System.out.println("The rules are simple, the player that gets to round 10 first wins, or if you all agree to end the game earlie, no one wins, or until everyone runs out of money");
        System.out.println("Each player has to move across the 24 different locations");
        System.out.println("Each player gets given $200000 at the start of the game, a player can get more money by landing on a chance card, by owning a property and other players paying rent \nAlso whenever they complete a round and go through the starting location 'GO'");
        System.out.println("A player can also sell an asset, only allowed to sell one asset per turn");
        System.out.println("Each player can purchase the location they landed on but they must have the necessary funds to purchase it, be careful! if you run out of money you instantly lose the game");
        System.out.println("If a player goes to jail they have to either roll a 6, if they fail to do so in 3 turns, the player will be freed from prison");
        System.out.println("Chance cards can be good and dangerous so be careful.");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public GameCreator getNewGame() {
        return newGame;
    }

    public void setNewGame(GameCreator newGame) {
        this.newGame = newGame;
    }
}
