package monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String getAssetName() {
        String assetName = this.view.assetSelect.getSelectedItem().toString();
        return assetName;
    }

    public void rollAction() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
        } else {
            this.model.rollToMove();
        }
    }

    public void nextPlayerAction() {
        this.model.nextPlayer();
        this.model.resetCounters();
        this.model.saveGame();
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

    public void buyMenu() {
        if (this.model.purchasable()) {
            this.view.menuBuy(this.model.buyMessage());
        } else {
            this.view.changeText(this.model.notPurchaseble());
        }
    }

    public void rollForFreedom() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
        } else {
            this.model.rollToGetOutOfJail();
        }
    }

    public void payYourWayOut() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
        } else {
            this.model.payToGetOutOfJail();
        }
    }

    public void upgrade() {
        if (this.model.upgradeAssetConfirm(this.getAssetName())) {
            this.view.changeText(this.model.upgradeCompleted());
        } else {
            this.view.changeText(this.model.upgradeFailed());
        }
    }

    public void buy() {
        if (this.model.purchasable()) {
            this.model.buyAsset();
        } else {
            this.view.changeText(this.model.notPurchaseble());
        }
    }

    public void rollForChance() {
        if (this.model.data.hasRolled) {
            this.view.changeText(this.model.playerHasRolled());
            this.model.nextPlayer();
            this.model.resetCounters();
            this.view.gameBoard(this.model.displayPlayer());
        } else {
            this.model.rollForChance();
            //TODO: message after rolling
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userClick = e.getActionCommand();

        switch (userClick) {
            case "New Game":
                System.out.println("New Game button pressed");
                this.view.newGamePanel();
                break;
            case "Load Game":
                System.out.println("Load Game button pressed");
                this.model.loadGame();
                this.model.dataReference();
                this.view.gameBoard(this.model.displayPlayer());
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
                this.view.instructionPanel(this.model.instructions());
                this.setPlayName(amountOfPlayers);
                break;
            case "Roll menu":
                System.out.println("Die roll button pressed");
                this.view.menuRoll();
                break;
            case "Roll":
                System.out.println("Roll button pressed");
                this.rollAction();
                break;
            case "Next Player":
                this.nextPlayerAction();
                break;
            case "Roll for chance":
                this.rollForChance();
                break;
            case "Sell Menu":
                System.out.println("sell menu button pressed");
                this.view.menuSell();
                this.model.sellAssetMenu();
                break;
            case "Sell":
                System.out.println("sell button pressed");
                this.model.sellAsset(this.getAssetName());
                break;
            case "Buy Menu":
                System.out.println("buy menu button pressed");
                this.buyMenu();
                break;
            case "Buy":
                System.out.println("Buy button pressed");
                this.buy();
                break;
            case "Upgrade Menu":
                System.out.println("upgrade menu button pressed");
                this.model.upgradeAssetMenu();
                this.view.menuUpgrade();
                break;
            case "Upgrade":
                System.out.println("Upgrade button pressed");
                this.upgrade();
            case "Back":
                System.out.println("Back button pressed");
                this.view.gameBoard(this.model.displayPlayer());
                break;
            case "I've read the instructions":
                System.out.println("Instructions read button");
                this.model.dataReference();
                this.view.gameBoard(this.model.displayPlayer());
                break;
            case "Roll for freedom":
                System.out.println("roll for freedom button pressed");
                this.rollForFreedom();
                break;
            case "Pay your way out":
                System.out.println("Pay your way out button pressed");
                this.payYourWayOut();
                break;
        }
    }

}
