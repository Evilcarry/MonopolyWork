package monopoly;

import java.util.Scanner;

/**
 * Project ID: 10 - Monopoly
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class PlayerForGameActions implements PlayerForGameActionsInterface {

    private GameCreator game;
    private DiceRoll roll;

    public PlayerForGameActions(GameCreator game) {
        this.game = game;
        this.roll = new DiceRoll();
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
        this.playerDisplayBalance(game, playerNum);
        System.out.println(game.getPlayers()[playerNum].getName() + " here are your options, Press 1 to roll your die, press 2 for the buy meny, press 3 for the sell menu, press 4 for the upgrade menu");
        System.out.println("If you access a menu, you will lose the chance to access a different menu.");
        boolean TrueTillRightInput = true;
        int userInput = 0;
        while (TrueTillRightInput) {
            if (userScan.hasNextInt()) {
                userInput = userScan.nextInt();
                if (userInput > 4 || userInput < 1) {
                    System.out.println("It appears you've entered the wrong input!");
                    System.out.println("Please try again");
                } else {
                    TrueTillRightInput = false;
                }
            } else {
                System.out.println("you have entered the wrong input please try again");
                userScan.next();
            }
        }
        return userInput;
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
            System.out.println("0 will terminate the game, any other number will roll the die");
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
                    int diceRoll = this.roll.diceRoll();
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
                        this.playerDisplayBalance(game, player);
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
     * This will get triggered every time a player completes a full round.
     *
     * @param game GameCreator object
     * @param player the player number
     */
    @Override
    public void playerPassesThroughGO(GameCreator game, int player) {
        System.out.println("----------------------------------------------------------------------PLAYER-PASSES-THROUGH-GO--------------------------------------------------------------------------------");
        System.out.println("You have completed one full round and you crossed go, you will be paid 10000");
        this.playerDisplayBalance(game, player);
        game.getPlayers()[player].payPlayer(10000);
        System.out.println(game.getPlayers()[player].getName() + " your new balance is: " + game.getPlayers()[player].getMoney());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
        System.out.println(game.getPlayers()[player].getName() + " has landed on a chance card, lets roll the die to see what you will get");
        this.playerEngagement();
        int diceRoll = this.roll.diceRoll();
        System.out.println(game.getPlayers()[player].getName() + " has rolled a " + diceRoll);
        boolean check = false;

        switch (diceRoll) {
            case 1://fall through
            case 2:
                System.out.println("Congratulations you have won 10000! this will be added to you account");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                game.getPlayers()[player].payPlayer(10000);
                this.playerDisplayBalance(game, player);
                break;
            case 3://fall through
            case 4:
                System.out.println("Oh no! the bank is asking you to pay 5000 for taxes! 5000 will be charged from your account");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                game.getPlayers()[player].chargePlayer(5000);
                this.playerDisplayBalance(game, player);
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
     * This method is to see if a player needs to pay rent.
     *
     * @param game
     * @param amountOfPlayers
     * @param currentPlayer
     */
    @Override
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

    @Override
    public void playerDisplayBalance(GameCreator game, int player) {
        System.out.println(game.getPlayers()[player].getName()+" your current balane is " + game.getPlayers()[player].getMoney());
    }
    
    /**
     * This method exits the game.
     */
    public void exitGame() {
        System.out.println("---------------------------------------------------------------------GOOD-BYE!------------------------------------------------------------------------------------------------");
        System.out.println("Thank you for playing");
        System.exit(0);
    }

    public GameCreator getGame() {
        return game;
    }

    public void setGame(GameCreator game) {
        this.game = game;
    }

    public DiceRoll getRoll() {
        return roll;
    }

    public void setRoll(DiceRoll roll) {
        this.roll = roll;
    }

}
