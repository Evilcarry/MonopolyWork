package monopoly;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author benjh
 */
public class MonopolyView extends JFrame implements Observer {

    //JPanels
    private JPanel panelOne = new JPanel();
    private JPanel panelTwo = new JPanel();
    private JPanel panelThree = new JPanel();
    private JPanel panelFour = new JPanel();
    private JPanel panelFive = new JPanel();
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
    
    
    public JLabel textDisplay;
    //JTextComponents
    public JTextField fieldOne = new JTextField();
    public JTextField fieldTwo = new JTextField();
    public JTextField fieldThree = new JTextField();
    public JTextField fieldFour = new JTextField();
    public JTextArea textArea = new JTextArea();

    public MonopolyView() {
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

    public void menuRoll() {
        //TODO roll menu buttons implementation
        panelOne.removeAll();

        buttonOne.setText("Roll");
        buttonTwo.setText("Back");

        panelOne.add(diceRollIcon, BorderLayout.NORTH);
        panelOne.add(buttonOne, BorderLayout.SOUTH);
        panelOne.add(buttonTwo, BorderLayout.SOUTH);

        this.getContentPane().removeAll();
        panelOne.setVisible(true);
        this.add(panelOne, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void menuSell() {
        panelTwo.removeAll();
        
        buttonOne.setText("Back");
        buttonTwo.setText("Sell");
        
        panelTwo.add(buttonOne, BorderLayout.CENTER);
        panelTwo.add(buttonTwo, BorderLayout.CENTER);
        
        
        this.getContentPane().removeAll();
        panelTwo.setVisible(true);
        this.add(panelTwo, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void menuUpgrade() {
        panelThree.removeAll();
        
        buttonOne.setText("Back");
        buttonTwo.setText("Upgrade");
        
        panelThree.add(buttonOne, BorderLayout.CENTER);
        panelThree.add(buttonTwo, BorderLayout.CENTER);
        
        
        this.getContentPane().removeAll();
        panelThree.setVisible(true);
        this.add(panelThree, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void menuBuy() {
        panelTwo.removeAll();
        
        buttonOne.setText("Back");
        buttonTwo.setText("Buy");
        
        panelTwo.add(buttonOne, BorderLayout.CENTER);
        panelTwo.add(buttonTwo, BorderLayout.CENTER);
        
        
        this.getContentPane().removeAll();
        panelTwo.setVisible(true);
        this.add(panelTwo, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void gameBoard() {
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
        
        //Buttons for Center Panel
        buttonOne.setText("Buy Menu");
        buttonTwo.setText("Sell Menu");
        buttonThree.setText("Upgrade Menu");
        buttonFour.setText("Roll menu");
        
        //Center Panel
        panelFive.removeAll();
        panelFive.setBorder(new LineBorder(Color.BLACK, 3));
        panelFive.setLayout(new GridLayout());
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

    public void loadGamePanel() {
        //TODO: Need to think what to do here, Ideally a user has a save with a name.
        //Would need to pull the data from the db send it to here thru the controller.
        //This would only send back the saves, We can put them in a button.
        //After that it would call a different panel for the stats of the players.
        //https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
        //For more info on JTable
    }

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

    private void setScreenSize(JFrame panel) {
        //Toolkit kit = Toolkit.getDefaultToolkit();
        //Dimension screenSize = kit.getScreenSize();

        panel.setSize(1030, 827);
        panel.setResizable(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO: figure out what to do with the observable.
    }

    public void addActionListener(ActionListener listen) {
        //JButton
        buttonOne.addActionListener(listen);
        buttonTwo.addActionListener(listen);
        buttonThree.addActionListener(listen);
        buttonFour.addActionListener(listen);
    }

}
