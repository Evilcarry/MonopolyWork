package monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author benjh
 */
public class MonopolyController implements ActionListener {

    MonopolyView view;
    GameController game;

    public MonopolyController(MonopolyView view) {
        this.view = view;
        //this.game = game;
        this.view.addActionListener(this);
    }

    /**
     * 
     * @param amountOfPlayers
     * @return 
     */
    public String[] setPlayName(int amountOfPlayers) {
        //TODO: validate player names.
        //We can call this.view.ChooseNamePanel again if the name is wrong.
        String[] playersName = new String[amountOfPlayers];

        playersName[0] = this.view.fieldOne.getText();
        playersName[1] = this.view.fieldTwo.getText();

        if (amountOfPlayers == 3) {
            playersName[3] = this.view.fieldThree.getText();
        } else if (amountOfPlayers == 4) {
            playersName[3] = this.view.fieldThree.getText();
            playersName[4] = this.view.fieldFour.getText();
        }

        return playersName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userClick = e.getActionCommand();
        int amountOfPlayers = 2;

        switch (userClick) {
            case "New Game":
                System.out.println("New Game button pressed");
                this.view.newGamePanel();
                break;
            case "Load Game":
                System.out.println("Load Game button pressed");
                this.view.loadGamePanel();
                break;
            case "2":
                System.out.println("2 Players button pressed");
                this.view.chooseNamePanel(amountOfPlayers);
                break;
            case "3":
                System.out.println("3 Players button pressed");
                amountOfPlayers = 3;
                this.view.chooseNamePanel(amountOfPlayers);
                break;
            case "4":
                System.out.println("4 Players button pressed");
                amountOfPlayers = 4;
                this.view.chooseNamePanel(amountOfPlayers);
                break;
            case "Confirm":
                System.out.println("Confirm button pressed");
                //TODO: Give this to gameController but we need to modify it first so it gets the Array.
                this.setPlayName(amountOfPlayers);
                break;
        }
    }

}
