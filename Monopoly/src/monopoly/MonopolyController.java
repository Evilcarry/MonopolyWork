package monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class MonopolyController implements ActionListener {

    MonopolyView view;
    MonopolyModel model;
    private int amountOfPlayers = 2;

    /**
     * This is a constructor to initialize things.
     * @param view
     * @param model 
     */
    public MonopolyController(MonopolyView view, MonopolyModel model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }

    /**
     *  returns the name of the players to the model.
     * @param amountOfPlayers
     * @return
     */
    public void setPlayName(int amountOfPlayers) {
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

    /**
     * This gets the name of the selected asset and returns it to the model.
     * @return 
     */
    public String getAssetName() {
        String assetName = this.view.assetSelect.getSelectedItem().toString();
        return assetName;
    }

    /**
     * this is to display the text after a player rolls the die.
     */
    public void rollAction() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
        } else {
            this.model.rollToMove();
        }
    }

    /**
     * when a player uses the next player button, the controller checks this methods.
     */
    public void nextPlayerAction() {
        this.model.nextPlayer();
        this.model.resetCounters();
        this.model.saveGame();
        if (this.model.inGameCheck()){
            this.view.winnerPanel(this.model.winnerMessage());
        } else{
            if (this.model.playerInJail()) {
                this.view.jailPanel();
            } else if (this.model.playerInChance()) {
                this.view.chancePanel();
            } else if (this.model.playerPaysRent()) {
                this.view.gameBoard(this.model.payRent());
            } else {
                this.view.gameBoard(this.model.displayPlayer());
            }
        }
    }

    /**
     * This calls for a buy menu panel.
     */
    public void buyMenu() {
        if (this.model.purchasable()) {
            this.view.menuBuy(this.model.buyMessage());
        } else {
            this.view.changeText(this.model.notPurchaseble());
        }
    }

    /**
     * this makes sure the user only rolls one time when they are in jail.
     */
    public void rollForFreedom() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
        } else {
            this.model.rollToGetOutOfJail();
        }
    }

    /**
     * if a player pays their way out of jail.
     */
    public void payYourWayOut() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
        } else {
            this.model.payToGetOutOfJail();
        }
    }

    /**
     * this is to display wether an asset was purchased or not.
     */
    public void upgrade() {
        if (this.model.upgradeAssetConfirm(this.getAssetName())) {
            this.view.changeText(this.model.upgradeCompleted());
        } else {
            this.view.changeText(this.model.upgradeFailed());
        }
    }

    /**
     * this method checks to see if the asset is purchasable.
     */
    public void buy() {
        if (this.model.purchasable()) {
            this.model.buyAsset();
        } else {
            this.view.changeText(this.model.notPurchaseble());
        }
    }

    /**
     * when a player rolls for chance, this method calls the panels and the model.
     */
    public void rollForChance() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
            this.model.nextPlayer();
            this.model.resetCounters();
            this.view.gameBoard(this.model.displayPlayer());
        } else {
            this.model.rollForChance();
            this.view.changeText(this.model.rollMessage());
        }
    }

    /**
     * Actions for the buttons.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String userClick = e.getActionCommand();

        switch (userClick) {
            case "New Game":
                this.view.newGamePanel();
                break;
            case "Load Game":
                this.model.loadGame();
                this.model.dataReference();
                this.view.gameBoard(this.model.displayPlayer());
                break;
            case "2":
                this.view.chooseNamePanel(amountOfPlayers);
                break;
            case "3":
                this.amountOfPlayers = 3;
                this.view.chooseNamePanel(amountOfPlayers);
                break;
            case "4":
                this.amountOfPlayers = 4;
                this.view.chooseNamePanel(4);
                break;
            case "Confirm":
                this.view.instructionPanel(this.model.instructions());
                this.setPlayName(amountOfPlayers);
                break;
            case "Roll menu":
                this.view.menuRoll();
                break;
            case "Roll":
                this.rollAction();
                break;
            case "Next Player":
                this.nextPlayerAction();
                break;
            case "Roll for chance":
                this.rollForChance();
                break;
            case "Sell Menu":
                this.view.menuSell();
                this.model.sellAssetMenu();
                break;
            case "Sell":
                this.model.sellAsset(this.getAssetName());
                break;
            case "Buy Menu":
                this.buyMenu();
                break;
            case "Buy":
                this.buy();
                break;
            case "Upgrade Menu":
                this.model.upgradeAssetMenu();
                this.view.menuUpgrade();
                break;
            case "Upgrade":
                this.upgrade();
            case "Back":
                this.view.gameBoard(this.model.displayPlayer());
                break;
            case "I've read the instructions":
                this.model.dataReference();
                this.view.gameBoard(this.model.displayPlayer());
                break;
            case "Roll for freedom":
                this.rollForFreedom();
                break;
            case "Pay your way out":
                this.payYourWayOut();
                break;
        }
    }

}
