package monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author benjh
 */
public class MonopolyController implements ActionListener {

    MonopolyView view;
    MonopolyModel model;
    private int amountOfPlayers = 2;

    public MonopolyController(MonopolyView view, MonopolyModel model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }

    /**
     *
     * @param amountOfPlayers
     * @return
     */
    public void setPlayName(int amountOfPlayers) {
        //TODO: validate player names.
        //We can call this.view.ChooseNamePanel again if the name is wrong.
        String[] playersName = new String[amountOfPlayers];

        playersName[0] = this.view.fieldOne.getText();
        playersName[1] = this.view.fieldTwo.getText();

        if (amountOfPlayers == 3) {
            playersName[2] = this.view.fieldThree.getText();
        } else if (amountOfPlayers == 4) {
            playersName[2] = this.view.fieldThree.getText();
            playersName[3] = this.view.fieldFour.getText();
        }

        this.model.gameStart(amountOfPlayers, playersName);
    }
    
    public String getAssetToUpgrade(){
        String assetName = "";
        
        assetName = this.view.assetSelect.getSelectedItem().toString();
        
        return assetName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userClick = e.getActionCommand();

        switch (userClick) {
            case "New Game":
                System.out.println("New Game button pressed");
                this.view.newGamePanel();
                //this.view.menuSell();
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
                this.amountOfPlayers = 3;
                this.view.chooseNamePanel(amountOfPlayers);
                break;
            case "4":
                System.out.println("4 Players button pressed");
                this.amountOfPlayers = 4;
                this.view.chooseNamePanel(4);
                break;
            case "Confirm":
                System.out.println("Confirm button pressed");
                //TODO: Give this to gameController but we need to modify it first so it gets the Array.
                this.view.instructionPanel(this.model.instructions());
                this.setPlayName(amountOfPlayers);
                break;
            case "Roll menu":
                System.out.println("Die roll button pressed");
                this.view.menuRoll();
                break;
            case "Roll":
                System.out.println("Roll button pressed");
                this.model.rollToMove();
                break;
            case "Sell Menu":
                System.out.println("sell menu button pressed");
                this.view.menuSell();
                break;
            case "Buy Menu":
                System.out.println("buy menu button pressed");
                if (this.model.purchaseble()){
                    this.view.menuBuy(this.model.buyMessage());
                }else{
                    //TODO display a message telling the player that the location is not purchaseble.
                }
                break;
            case "Upgrade Menu":
                System.out.println("upgrade menu button pressed");
                this.model.upgradeAssetMenu();
                this.view.menuUpgrade();
                break;
            case "Upgrade":
                System.out.println("Upgrade button pressed");
                //TODO call upgrade methods.
            case "Back":
                System.out.println("Back button pressed");
                this.view.gameBoard();
                break;
            case "I've read the instructions":
                System.out.println("Instructions read button");
                this.view.gameBoard();
                this.model.dataReference();
                break;
        }
    }

}
