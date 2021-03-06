package monopoly;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class MonopolyView extends JFrame implements Observer {

    //JPanels
    private JPanel panelOne = new JPanel();
    private JPanel panelTwo = new JPanel();
    private JPanel panelThree = new JPanel();
    private JPanel panelFour = new JPanel();
    private JPanel panelFive = new JPanel();
    private JPanel textAreaPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    //JButtons
    public JButton buttonOne = new JButton();
    public JButton buttonTwo = new JButton();
    public JButton buttonThree = new JButton();
    public JButton buttonFour = new JButton();
    //JImages
    public ImageIcon diceRoll = new ImageIcon("./resources/diceroll.gif");
    public ImageIcon monopolyIcon = new ImageIcon("./resources/MonopolyLogo.jpg");
    public ImageIcon goIcon = new ImageIcon("./resources/Go.jpg");
    public ImageIcon chanceIcon = new ImageIcon("./resources/Chance.jpg");
    public ImageIcon jailIcon = new ImageIcon("./resources/Jail.jpg");

    public ImageIcon aucklandHouse = new ImageIcon("./resources/Auckland.jpg");
    public ImageIcon rotoruaHouse = new ImageIcon("./resources/Rotorua.jpg");
    public ImageIcon coromandelHouse = new ImageIcon("./resources/Coromandel.jpg");
    public ImageIcon taupoHouse = new ImageIcon("./resources/Taupo.jpg");
    public ImageIcon mountCookHouse = new ImageIcon("./resources/MountCook.jpg");
    public ImageIcon wellingtonHouse = new ImageIcon("./resources/Wellington.jpg");
    public ImageIcon christchurchHouse = new ImageIcon("./resources/Christchurch.jpg");
    public ImageIcon queenstownHouse = new ImageIcon("./resources/Queenstown.jpg");
    public ImageIcon fiorlandsHouse = new ImageIcon("./resources/Fiorlands.jpg");

    //JLabels
    public JLabel monopolyImageIcon = new JLabel(monopolyIcon);
    public JLabel diceRollIcon = new JLabel(diceRoll);
    public JLabel goLocationIcon = new JLabel(goIcon);

    public JLabel monopolyChanceOneIcon = new JLabel(chanceIcon);
    public JLabel monopolyChanceTwoIcon = new JLabel(chanceIcon);
    public JLabel monopolyChanceThreeIcon = new JLabel(chanceIcon);
    public JLabel monopolyChanceFourIcon = new JLabel(chanceIcon);

    public JLabel monopolyJailOneIcon = new JLabel(jailIcon);
    public JLabel monopolyJailTwoIcon = new JLabel(jailIcon);

    public JLabel aucklandHouseIcon = new JLabel(aucklandHouse);
    public JLabel rotoruaHouseIcon = new JLabel(rotoruaHouse);
    public JLabel coromandelHouseIcon = new JLabel(coromandelHouse);
    public JLabel taupoHouseIcon = new JLabel(taupoHouse);
    public JLabel mountCookHouseIcon = new JLabel(mountCookHouse);
    public JLabel wellingtonHouseIcon = new JLabel(wellingtonHouse);
    public JLabel christchurchHouseIcon = new JLabel(christchurchHouse);
    public JLabel queenstownHouseIcon = new JLabel(queenstownHouse);
    public JLabel fiorlandsHouseIcon = new JLabel(fiorlandsHouse);

    //Combo box
    public JComboBox assetSelect = new JComboBox();

    public JLabel textDisplay;
    //JTextComponents
    public JTextField fieldOne = new JTextField();
    public JTextField fieldTwo = new JTextField();
    public JTextField fieldThree = new JTextField();
    public JTextField fieldFour = new JTextField();
    public JTextArea textArea = new JTextArea();

    /**
     * This constructor sets up the main Jframe.
     */
    public MonopolyView() {
        super("Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setScreenSize(this);
        this.setLocationRelativeTo(null);

        textDisplay = new JLabel("Choose if you want to start a new game or load a save");
        buttonOne.setText("New Game");
        buttonTwo.setText("Load Game");

        panelFour.add(textDisplay, BorderLayout.NORTH);
        panelFour.add(buttonOne, BorderLayout.LINE_END);
        panelFour.add(buttonTwo, BorderLayout.LINE_END);
        this.add(monopolyImageIcon, BorderLayout.NORTH);
        this.add(panelFour, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * This panel is responsible of allowing the user to select the amount of players.
     */
    public void newGamePanel() {
        textDisplay = new JLabel("Choose the amount of players you want in your game");
        buttonOne.setText("2");
        buttonTwo.setText("3");
        buttonThree.setText("4");
        panelOne.add(textDisplay, BorderLayout.NORTH);
        panelOne.add(buttonOne, BorderLayout.AFTER_LAST_LINE);
        panelOne.add(buttonTwo, BorderLayout.AFTER_LAST_LINE);
        panelOne.add(buttonThree, BorderLayout.AFTER_LAST_LINE);

        this.getContentPane().remove(panelFour);
        panelOne.setVisible(true);
        this.add(panelOne, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is to be displayed when a player goes to jail.
     */
    public void jailPanel() {
        textAreaPanel.removeAll();
        buttonPanel.removeAll();

        buttonOne.setText("Roll for freedom");
        buttonTwo.setText("Pay your way out");
        buttonThree.setText("Next Player");
        textArea.setText("You are in Jail\n Roll for freedom, if you land a 6 you're out \n Or you can pay your way out, it costs $10000\n After 3 rolls you're out of jail");

        textAreaPanel.add(textArea, BorderLayout.PAGE_START);
        buttonPanel.add(buttonOne, BorderLayout.CENTER);
        buttonPanel.add(buttonTwo, BorderLayout.CENTER);
        buttonPanel.add(buttonThree, BorderLayout.CENTER);

        this.getContentPane().removeAll();
        textAreaPanel.setVisible(true);
        buttonPanel.setVisible(true);
        this.add(textAreaPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is to be displayed when a player lands on a chance card.
     */
    public void chancePanel() {
        textAreaPanel.removeAll();
        buttonPanel.removeAll();

        textArea.setText("You have landed in a chance location, roll to see your destiny");
        buttonTwo.setText("Roll for chance");

        textAreaPanel.add(textArea, BorderLayout.PAGE_START);
        buttonPanel.add(buttonTwo, BorderLayout.CENTER);

        this.getContentPane().removeAll();
        textAreaPanel.setVisible(true);
        buttonPanel.setVisible(true);
        this.add(textAreaPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is displayed when a player wants to roll the die.
     */
    public void menuRoll() {
        textAreaPanel.removeAll();
        buttonPanel.removeAll();

        buttonOne.setText("Roll");
        buttonTwo.setText("Next Player");
        textArea.setText("Press the Roll button to roll");
        textArea.setSize(new Dimension(200, 100));

        textAreaPanel.add(diceRollIcon, BorderLayout.LINE_START);
        textAreaPanel.add(textArea, BorderLayout.CENTER);
        buttonPanel.add(buttonOne, BorderLayout.CENTER);
        buttonPanel.add(buttonTwo, BorderLayout.CENTER);

        this.getContentPane().removeAll();
        textAreaPanel.setVisible(true);
        buttonPanel.setVisible(true);
        this.add(textAreaPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is for selling a property.
     */
    public void menuSell() {
        panelTwo.removeAll();

        buttonOne.setText("Back");
        buttonTwo.setText("Sell");

        panelTwo.setLayout(new GridBagLayout());

        panelTwo.add(assetSelect);
        panelTwo.add(buttonOne);
        panelTwo.add(buttonTwo);

        this.getContentPane().removeAll();
        panelTwo.setVisible(true);
        this.add(panelTwo, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is for upgrading a property.
     */
    public void menuUpgrade() {
        panelThree.removeAll();

        buttonOne.setText("Back");
        buttonTwo.setText("Upgrade");

        panelThree.setLayout(new GridBagLayout());

        panelThree.add(assetSelect);
        panelThree.add(buttonOne);
        panelThree.add(buttonTwo);

        this.getContentPane().removeAll();
        panelThree.setVisible(true);
        this.add(panelThree, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is for buying a property.
     * @param stats it needs the player details.
     */
    public void menuBuy(String stats) {
        panelTwo.removeAll();

        buttonOne.setText("Back");
        buttonTwo.setText("Buy");
        textArea.setText(stats);

        panelTwo.add(buttonOne, BorderLayout.CENTER);
        panelTwo.add(buttonTwo, BorderLayout.CENTER);
        panelTwo.add(textArea, BorderLayout.PAGE_END);

        this.getContentPane().removeAll();
        panelTwo.setVisible(true);
        this.add(panelTwo, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * This panel is to be displayed when all players run out of money.
     * @param winner 
     */
    public void winnerPanel(String winner){
        panelThree.removeAll();
        
        textArea.setText(winner);
        
        this.getContentPane().removeAll();
        panelThree.setVisible(true);
        this.add(panelThree, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This is the main panel board, sets up the board to display the monopoly board.
     * @param player 
     */
    public void gameBoard(String player) {
        this.getContentPane().removeAll();
        this.setLayout(new BorderLayout(8, 6));
        this.setBackground(Color.CYAN);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));

        //Top Panel
        panelOne.removeAll();
        panelOne.setBorder(new LineBorder(Color.BLACK, 3));
        panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.X_AXIS));
        panelOne.add(monopolyJailOneIcon);
        panelOne.add(taupoHouseIcon);
        panelOne.add(monopolyChanceOneIcon);
        panelOne.add(rotoruaHouseIcon);
        panelOne.add(monopolyJailTwoIcon);
        panelOne.setVisible(true);

        //Left Panel
        panelTwo.removeAll();
        panelTwo.setBorder(new LineBorder(Color.BLACK, 3));
        panelTwo.setLayout(new BoxLayout(panelTwo, BoxLayout.Y_AXIS));
        panelTwo.add(coromandelHouseIcon);
        panelTwo.add(monopolyChanceTwoIcon);
        panelTwo.add(aucklandHouseIcon);
        panelTwo.setVisible(true);

        //Right Panel
        panelThree.removeAll();
        panelThree.setBorder(new LineBorder(Color.BLACK, 3));
        panelThree.setLayout(new BoxLayout(panelThree, BoxLayout.Y_AXIS));
        panelThree.add(mountCookHouseIcon);
        panelThree.add(monopolyChanceThreeIcon);
        panelThree.add(wellingtonHouseIcon);
        panelThree.setVisible(true);

        //Bottom Panel
        panelFour.removeAll();
        panelFour.setBorder(new LineBorder(Color.BLACK, 3));
        panelFour.setLayout(new BoxLayout(panelFour, BoxLayout.X_AXIS));
        panelFour.add(goLocationIcon);
        panelFour.add(fiorlandsHouseIcon);
        panelFour.add(monopolyChanceFourIcon);
        panelFour.add(queenstownHouseIcon);
        panelFour.add(christchurchHouseIcon);
        panelFour.setVisible(true);

        //for Center Panel
        buttonOne.setText("Buy Menu");
        buttonTwo.setText("Sell Menu");
        buttonThree.setText("Upgrade Menu");
        buttonFour.setText("Roll menu");

        //Center Panel
        panelFive.removeAll();
        panelFive.setBorder(new LineBorder(Color.BLACK, 3));
        panelFive.setLayout(new GridBagLayout());
        textArea.setText(player);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;

        panelFive.add(textArea, c);
        panelFive.add(buttonOne);
        panelFive.add(buttonTwo);
        panelFive.add(buttonThree);
        panelFive.add(buttonFour);

        //Adding everything to the JFrame
        this.add(panelOne, BorderLayout.PAGE_START);
        this.add(panelTwo, BorderLayout.LINE_START);
        this.add(panelThree, BorderLayout.LINE_END);
        this.add(panelFour, BorderLayout.PAGE_END);
        this.add(panelFive, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is to be shown after the users chooses the amount of players.
     * @param numberOfPlayers 
     */
    public void chooseNamePanel(int numberOfPlayers) {
        textDisplay = new JLabel("Write down the names of each player");
        buttonOne.setText("Confirm");
        fieldOne.setText("Player 1 name");
        fieldTwo.setText("Player 2 name");

        panelTwo.add(textDisplay, BorderLayout.NORTH);
        panelTwo.add(fieldOne, BorderLayout.AFTER_LAST_LINE);
        panelTwo.add(fieldTwo, BorderLayout.AFTER_LAST_LINE);

        if (numberOfPlayers == 3) {
            fieldThree.setText("Player 3 name");
            panelTwo.add(fieldThree, BorderLayout.AFTER_LAST_LINE);
        } else if (numberOfPlayers == 4) {
            fieldThree.setText("Player 3 name");
            fieldFour.setText("Player 4 name");
            panelTwo.add(fieldThree, BorderLayout.AFTER_LAST_LINE);
            panelTwo.add(fieldFour, BorderLayout.AFTER_LAST_LINE);
        }
        panelTwo.add(buttonOne);

        this.getContentPane().remove(panelOne);
        panelTwo.setVisible(true);
        this.add(panelTwo, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * This panel is to display the instructions.
     * @param instructions 
     */
    public void instructionPanel(String instructions) {
        textArea = new JTextArea(instructions);
        textArea.setEditable(false);
        buttonOne.setText("I've read the instructions");
        panelThree.add(textArea, BorderLayout.CENTER);
        panelThree.add(buttonOne, BorderLayout.SOUTH);

        this.getContentPane().removeAll();
        panelThree.setVisible(true);
        this.add(panelThree, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    /**
     * Method to set the screen size.
     * @param panel 
     */
    private void setScreenSize(JFrame panel) {
        panel.setSize(1030, 827);
        panel.setResizable(false);
    }

    /**
     * This method helps to display the assets.
     * @param game 
     */
    public void displayAssets(DataReference game) {
        ArrayList<String> assetList = new ArrayList<>();

        for (int i = 0; i < game.game.getPlayers()[game.currentPlayer].getAsset().length; i++) {
            if (game.game.getPlayers()[game.currentPlayer].getAsset()[i] != null) {
                assetList.add(game.game.getPlayers()[game.currentPlayer].getAsset()[i].getBoardPosition().getName());
            }
        }
        DefaultComboBoxModel mod = (DefaultComboBoxModel) assetSelect.getModel();
        mod.removeAllElements();

        for (String asset : assetList) {
            mod.addElement(asset);
        }
        assetSelect.setModel(mod);

    }

    /**
     * This is to display the roll.
     * @param game
     * @return 
     */
    public String displayRoll(DataReference game) {
        String playerName = game.game.getPlayers()[game.currentPlayer].getName();
        String dieRoll = playerName + " has Rolled a " + game.die + "\n";
        int currentLocation = game.game.getPlayers()[game.currentPlayer].getCurrentLocation().getLocationID();
        String nextLocation = game.game.getLocations()[currentLocation].getName();
        dieRoll += "The player will move to " + nextLocation;
        return dieRoll;
    }

    /**
     * Update message to let the user know the have purchased a property.
     * @param game
     * @return 
     */
    public String successfulPurchase(DataReference game) {
        String player = game.game.getPlayers()[game.currentPlayer].getName();
        String money = Integer.toString(game.game.getPlayers()[game.currentPlayer].getMoney());
        String location = game.game.getPlayers()[game.currentPlayer].getCurrentLocation().getName();
        String message = player + " has successfully purchased " + location + ".\n" + player + " currently has " + money + " left after making this purchase.";

        return message;
    }

    /**
     * this is the message to be displayed in jail, it returns the string.
     * @param game
     * @return 
     */
    public String displayJail(DataReference game) {
        String message = "";
        String player = game.game.getPlayers()[game.currentPlayer].getName();

        int dieRoll = game.jailDie;
        message = player + " has rolled a ";

        if (dieRoll == 6) {
            message += dieRoll + " you are free to go";
        } else {
            message += dieRoll;
            int jailCounter = game.game.getPlayers()[game.currentPlayer].getJailCounter();

            if (jailCounter == 3) {
                message += ". Since you have rolled 3 times unsuccesfully you are not free to go, next turn you are out.";
            } else {
                message += ". you are unlucky, try again next turn";
            }
        }
        return message;
    }

    /**
     * This facilitates displaying messages through the controller.
     * @param message 
     */
    public void changeText(String message) {
        textArea.setText(message);
    }

    /**
     * this method returns a string, to be displayed if a player pays their way out of jail.
     * @param game
     * @return 
     */
    public String payToGetOutMessage(DataReference game) {
        String message = "You have succesfully payed your way out of jail";
        return message;
    }

    /**
     * This method returns a string, to be displayed when a player rolls for a chance.
     * @param game
     * @return 
     */
    public String chanceMessage(DataReference game) {
        String player = game.game.getPlayers()[game.currentPlayer].getName();
        String message = player + " has rolled a " + game.chanceRoll + "\n";

        return message;
    }

    /**
     * updates panels through the observer.
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        DataReference game = (DataReference) arg;
        if (game.sellAsset) {
            displayAssets(game);
        } else if (game.upgradeAsset) {
            displayAssets(game);
        } else if (game.dieRoll) {
            textArea.setText(displayRoll(game));
        } else if (game.successPurchase) {
            textArea.setText(successfulPurchase(game));
        } else if (game.game.getPlayers()[game.currentPlayer].isJailState()) {
            textArea.setText(displayJail(game));
        } else if (game.paytogetout) {
            textArea.setText(payToGetOutMessage(game));
        } else if (game.playerChance) {

        }
    }

    /**
     * Action listeners for the buttons.
     * @param listen 
     */
    public void addActionListener(ActionListener listen) {
        buttonOne.addActionListener(listen);
        buttonTwo.addActionListener(listen);
        buttonThree.addActionListener(listen);
        buttonFour.addActionListener(listen);
    }

}
