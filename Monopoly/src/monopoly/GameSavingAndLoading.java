/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author benjh
 */
public class GameSavingAndLoading {
    private GameCreator game;
    private int amountOfPlayers;
    
    public GameSavingAndLoading(GameCreator game,int amountOfPlayers)
    {
        this.game = game;
        this.amountOfPlayers = amountOfPlayers;
    }
    
    /**
     * This method saves all players.
     *
     * @param game
     * @param amountOfPlayers
     */
    public void savePlayer(GameCreator game, int amountOfPlayers) {
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
    public Player[] loadPlayer(int amountOfPlayers) {
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
}
