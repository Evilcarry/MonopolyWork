package monopoly;

import java.util.Observable;

/**
 *
 * @author benjh
 */
public class MonopolyModel extends Observable {

    private GameCreator newGame;
    private MenuForGame menu;
    private PlayerForGameActions playerForGameActions;
    private DiceRoll roll;
    private GameSavingAndLoading saveLoad;

    public void gameStart(int amountOfPlayers, String[] playerNames) {
        newGame = new GameCreator(amountOfPlayers);                             //Initializing newGame
        newGame.createLocations();                                              //Creating all the locations.
        newGame.createAssets();                                                 //Creating all the assets
        newGame.createPlayers(amountOfPlayers, playerNames);                    //Creating all the players with names.
    }
    
    
    
    
    
    
    
    //Instructions.
    public String instructions() {
        String instructions = "----------------------------------------------------------------------INSTRUCTIONS--------------------------------------------------------------------------------------------\n";
        instructions += "The rules are simple, the player that gets to round 10 first wins, or if you all agree to end the game earlie, no one wins, or until everyone runs out of money\n";
        instructions += "Each player has to move across the 24 different locations\n";
        instructions += "Each player gets given $200000 at the start of the game, a player can get more money by landing on a chance card, by owning a property and other players paying rent \n";
        instructions += "Also whenever they complete a round and go through the starting location 'GO'\n";
        instructions += "A player can also sell an asset, only allowed to sell one asset per turn\n";
        instructions += "Each player can purchase the location they landed on but they must have the necessary funds to purchase it, be careful! if you run out of money you instantly lose the game\n";
        instructions += "If a player goes to jail they have to either roll a 6, if they fail to do so in 3 turns, the player will be freed from prison\n";
        instructions += "Chance cards can be good and dangerous so be careful.\n";
        instructions += "Game will automatically save after each dice roll.\n";
        instructions += "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
        return instructions;
    }
}
