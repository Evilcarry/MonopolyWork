package monopoly;

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
    private PlayerForGameActions playerForGameActions;
    private DiceRoll roll;
    private GameSavingAndLoading saveLoad;

    /**
     * This method starts the game, it offers the option of quitting starting a
     * new game or loading a game from a save.
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
        this.roll = new DiceRoll();
        this.instructions();//to display instructions on the game
        int amountOfPlayers = this.setPlayerNumber(); // sets the number of players
        this.newGame = new GameCreator(amountOfPlayers); //gamecreator instance that pases the number of players
        this.newGame.createLocations(); //creates all the locations, possibly going to read them off a file
        this.newGame.createPlayers(amountOfPlayers, this.setPlayerName(amountOfPlayers)); //creates the names of each player
        this.newGame.createAssets(); //creates all the assets for each location.
        this.menu = new MenuForGame(this.newGame, amountOfPlayers);
        this.playerForGameActions = new PlayerForGameActions(this.newGame);
        this.saveLoad = new GameSavingAndLoading(this.newGame, amountOfPlayers);
        return amountOfPlayers;
    }

    /**
     * This method is to start the game from a saved file
     *
     * @return
     */
    @Override
    public int startWithLoading() {
        this.roll = new DiceRoll();
        this.instructions();//to display instructions on the game
        int amountOfPlayers = this.setPlayerNumber(); // sets the number of players
        this.newGame = new GameCreator(amountOfPlayers); //gamecreator instance that pases the number of players
        this.newGame.createLocations(); //creates all the locations, possibly going to read them off a file
        try {
            this.saveLoad = new GameSavingAndLoading(this.newGame, amountOfPlayers);
            this.newGame.createPlayers(amountOfPlayers, this.saveLoad.loadPlayer(amountOfPlayers)); //Here we will create players from the save
            System.out.println("Just to remind everyone, of their stats");
            for (int i = 0; i < amountOfPlayers; i++) {
                System.out.println(this.newGame.getPlayers()[i]);
            }
        } catch (NullPointerException e) {
            System.out.println("It seems that your save had less players than the current amount");
            System.out.println("The game will start without loading the save.");
            this.newGame.createPlayers(amountOfPlayers, this.setPlayerName(amountOfPlayers));
        }
        this.newGame.createAssets(); //creates all the assets for each location.
        this.menu = new MenuForGame(this.newGame, amountOfPlayers);
        this.playerForGameActions = new PlayerForGameActions(this.newGame);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        return amountOfPlayers;
    }

    /**
     * This method allows the userOption to set their name
     *
     * @param numberOfPlayers is the number of players that will be playing.
     * @return an array of names.
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
     * This method moves a player across the board. It also makes sure that when
     * a player reaches the last place it restarts at the first spot.
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

    public void exitGame() {
        System.out.println("---------------------------------------------------------------------GOOD-BYE!------------------------------------------------------------------------------------------------");
        System.out.println("Thank you for playing the game. It will automatically save");
        System.exit(0);
    }

    /**
     * This is in charge of running the turn of each player
     *
     * @param game GameCrator object
     * @param player integer stating which player
     * @param amountOfPlayers
     */
    @Override
    public void gameTurn(GameCreator game, int player, int amountOfPlayers) {
        for (int i = 0; i < amountOfPlayers; i++) {
            if (game.getPlayers()[i].getRounds() == 10) {
                this.gameWinner(game, i);
            }
        }
        if (game.getPlayers()[player].isJailState()) {
            playerForGameActions.playerInJailAction(game, player);
        } else {
            if (game.getPlayers()[player].getRounds() >= 1 && !game.getPlayers()[player].isPaidThisRound()) {
                game.getPlayers()[player].setPaidThisRound(true);
                playerForGameActions.playerPassesThroughGO(game, player);
            }
            boolean playerMove = false;
            if ((game.getPlayers()[player].getCurrentLocation().getLocationID() % 3) == 0 && !game.getPlayers()[player].isJailState() && game.getPlayers()[player].getCurrentLocation().getLocationID() != 0 && game.getPlayers()[player].getCurrentLocation().getLocationID() != 6 && game.getPlayers()[player].getCurrentLocation().getLocationID() != 12) {
                playerMove = playerForGameActions.playerLandsOnChance(game, player);
            }

            if (!playerMove) {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(game.getPlayers()[player]);
                int userOption = this.menu.menuLoader(game, player, playerForGameActions.playerMessage(game, player));
                if (userOption == 2) {
                    System.out.println("Now the die will roll!");
                    this.saveLoad.savePlayer(game, amountOfPlayers);
                    playerForGameActions.playerEngagement();
                    int diceRoll = this.roll.diceRoll();
                    this.movePlayerAround(game, player, diceRoll);
                    playerForGameActions.playerPaysRent(game, amountOfPlayers, player);
                    if (game.getPlayers()[player].getCurrentLocation() == game.getLocations()[6] || game.getPlayers()[player].getCurrentLocation() == game.getLocations()[12]) {
                        if (!game.getPlayers()[player].isJailState()) {
                            game.getPlayers()[player].setJailState(true);
                            System.out.println(game.getPlayers()[player].getName() + " it seems that you have commited a crime! you are now in jail for 3 turns!");
                        }
                    }
                } else if (userOption == 1) {
                    this.saveLoad.savePlayer(game, amountOfPlayers);
                    //playerForGameActions.playerEngagement();
                    int diceRoll = this.roll.diceRoll();
                    this.movePlayerAround(game, player, diceRoll);
                    playerForGameActions.playerPaysRent(game, amountOfPlayers, player);
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
                            this.gameTurn(game, playerOne, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerOne].getName() + " has ran out of money! you've lost the game buddy.");
                            this.gameWinner(game, playerTwo);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) {
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.gameTurn(game, playerTwo, amountOfPlayers);
                        } else {
                            System.out.println(game.getPlayers()[playerTwo].getName() + " has ran out of money! you've lost the game buddy.");
                            this.gameWinner(game, playerOne);
                        }
                    }
                }
                break;

            case 3:
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) { //PLAYER 1
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.gameTurn(game, playerOne, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerOne);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) { //PLAYER 2
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.gameTurn(game, playerTwo, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerTwo);
                        }
                    }
                    if (game.getPlayers()[playerThree].isInGame()) { //PLAYER 3
                        if (game.getPlayers()[playerThree].getMoney() > 0) {
                            this.gameTurn(game, playerThree, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerThree);
                        }
                    }
                    if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame()) {
                        this.gameWinner(game, playerThree); //if player 3 wins
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        this.gameWinner(game, playerTwo); // if player 2 wins
                    } else if (!game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        this.gameWinner(game, playerOne); // if player 1 wins
                    }
                }
                break;

            case 4:
                while (gameRun) {
                    if (game.getPlayers()[playerOne].isInGame()) { //PLAYER 1
                        if (game.getPlayers()[playerOne].getMoney() > 0) {
                            this.gameTurn(game, playerOne, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerOne);
                        }
                    }
                    if (game.getPlayers()[playerTwo].isInGame()) { //PLAYER 2
                        if (game.getPlayers()[playerTwo].getMoney() > 0) {
                            this.gameTurn(game, playerTwo, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerTwo);
                        }
                    }
                    if (game.getPlayers()[playerThree].isInGame()) { //PLAYER 3
                        if (game.getPlayers()[playerThree].getMoney() > 0) {
                            this.gameTurn(game, playerThree, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerThree);
                        }
                    }
                    if (game.getPlayers()[playerFour].isInGame()) { //PLAYER 4
                        if (game.getPlayers()[playerFour].getMoney() > 0) {
                            this.gameTurn(game, playerFour, amountOfPlayers);
                        } else {
                            this.gameLoser(game, playerFour);
                        }
                    }
                    if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame()) {
                        this.gameWinner(game, playerFour); //If player 4 wins
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        this.gameWinner(game, playerThree); // if player 3 wins
                    } else if (!game.getPlayers()[playerOne].isInGame() && !game.getPlayers()[playerThree].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        this.gameWinner(game, playerTwo); // if player 2 wins
                    } else if (!game.getPlayers()[playerTwo].isInGame() && !game.getPlayers()[playerThree].isInGame() && !game.getPlayers()[playerFour].isInGame()) {
                        this.gameWinner(game, playerOne); // if player 1 wins
                    }
                }
                break;
        }
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
        System.out.println("Game will automatically save after each dice roll.");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void gameWinner(GameCreator game, int player) {
        System.out.println(game.getPlayers()[player].getName() + " has won the game! Congratulations");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        this.exitGame();
    }

    public void gameLoser(GameCreator game, int player) {
        System.out.println(game.getPlayers()[player].getName() + " has ran out of money! you've lost the game buddy.");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        game.getPlayers()[player].setInGame(false);
    }

    public GameCreator getNewGame() {
        return newGame;
    }

    public void setNewGame(GameCreator newGame) {
        this.newGame = newGame;
    }
}
