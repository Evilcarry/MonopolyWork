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
public class MenuForGame implements MenuInterface{

    private GameCreator game;

    public MenuForGame(GameCreator game) {
        this.game = game;
    }

    /**
     * This is the buyMenu method, it will display a menu for the userOption so
     * they can purchase assets.
     *
     * @param game which is a GameCreator object.
     * @param player is the number of the player.
     */
    @Override
    public void buyMenu(GameCreator game, int player) {
        if (game.getPlayers()[player].getCurrentLocation().getLocationID() % 3 == 0) {
            System.out.println("Sorry this property is not for sale!");
        } else {

            System.out.println("------------------------------------------------------------------------------BUY-MENU----------------------------------------------------------------------------------------");
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
                        System.out.println(game.getPlayers()[player].getName()+" your current balane is " + game.getPlayers()[player].getMoney());
                    } else {
                        System.out.println("Sorry you do not have sufficient funds to purchase this properly");
                    }
                }
            } else {
                System.out.println("Thank you for accessing the buy menu!");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
    @Override
    public void sellMenu(GameCreator game, int player) {
        System.out.println("---------------------------------------------------------------------------SELL-MENU------------------------------------------------------------------------------------------");
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        while (trueTillRightInput) {
            System.out.println("Would you like to sell any assets? if so please input the location ID of the asset you would like to sell");
            System.out.println("if you do not want to sell any of your assets please input the number 0");
            Scanner sellScan = new Scanner(System.in);
            if (sellScan.hasNextInt()) {
                int locationOfSell = sellScan.nextInt();

                if (game.getPlayers()[player].getAsset()[locationOfSell] != null) {
                    System.out.println("congratulations you've sold your asset");
                    System.out.println(game.getPlayers()[player].getName()+" your current balane is " + game.getPlayers()[player].getMoney());
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    game.getPlayers()[player].sellAsset(game.getPlayers()[player].getAsset()[locationOfSell]);
                    trueTillRightInput = false;
                } else if (locationOfSell == 0) {
                    System.out.println("Thank you for accessing the sell menu!");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
    @Override
    public void updgradeMenu(GameCreator game, int player) {
        System.out.println("-----------------------------------------------------------------------------UPGRADE-MENU-------------------------------------------------------------------------------------");
        System.out.println("Welcome to the upgrade menu");
        System.out.println("Here you can upgrade your currently owned assets!");
        System.out.println("You can only upgrade one Asset per turn");
        System.out.println(game.getPlayers()[player].getName()+" your current balane is " + game.getPlayers()[player].getMoney());
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
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
                                System.out.println(game.getPlayers()[player].getAsset()[locationOfUpgrade] + " will be upgraded to level " + levelOfUpgrade);
                                game.getPlayers()[player].upgradeAsset(game.getPlayers()[player].getAsset()[locationOfUpgrade], levelOfUpgrade);
                                System.out.println(game.getPlayers()[player].getName()+" your current balane is " + game.getPlayers()[player].getMoney());
                                System.out.println("Thank you for accessing the upgrade menu!");
                                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                trueTillRightInputTwo = false;
                                trueTillRightInput = false;
                            }
                        } else {
                            System.out.println("It appears you've entered the wrong input");
                            System.out.println("Please try again");
                        }
                    }
                } else if (locationOfUpgrade == 0) {
                    System.out.println("Thank you for accessing the upgrade menu!");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
     * This method reacts differently based on the user input. It calls a dice
     * roll, opens the buy menu, the sell menu, the upgrade menu.
     *
     * @param game
     * @param player
     * @param userAnswer
     * @return
     */
    @Override
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

    public GameCreator getGame() {
        return game;
    }

    public void setGame(GameCreator game) {
        this.game = game;
    }
}
