package monopoly;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author benjh
 */
public class MonopolyView extends JFrame implements Observer{
    
    //JPanels
    private JPanel numPlayerMenuPanel = new JPanel();
    private JPanel namePlayerMenuPanel = new JPanel();
    private JPanel instructionsPanel = new JPanel();
    private JPanel startingPanel = new JPanel();
    private JPanel loadFromSavePanel = new JPanel();
    //JButtons
    public JButton buttonOne = new JButton();
    public JButton buttonTwo = new JButton();
    public JButton buttonThree = new JButton();
    //JImages
    public ImageIcon icon = new ImageIcon("./resources/MonopolyLogo.jpg");
    //JLabels
    public JLabel monopolyImageIcon = new JLabel(icon);
    public JLabel textDisplay;
    //JTextFields
    public JTextField fieldOne = new JTextField();
    public JTextField fieldTwo = new JTextField();
    public JTextField fieldThree = new JTextField();
    public JTextField fieldFour = new JTextField();
    
    public MonopolyView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setScreenSize(this);
        this.setLocationRelativeTo(null);
        
        textDisplay = new JLabel("Choose if you want to start a new game or load a save");
        buttonOne.setText("New Game");
        buttonTwo.setText("Load Game");
        
        startingPanel.add(textDisplay, BorderLayout.NORTH);
        startingPanel.add(buttonOne, BorderLayout.LINE_END); 
        startingPanel.add(buttonTwo, BorderLayout.LINE_END);
        this.add(monopolyImageIcon, BorderLayout.NORTH);
        this.add(startingPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    public void newGamePanel(){
        textDisplay = new JLabel("Choose the amount of players you want in your game");
        buttonOne.setText("2");
        buttonTwo.setText("3");
        buttonThree.setText("4");
        numPlayerMenuPanel.add(textDisplay, BorderLayout.NORTH);
        numPlayerMenuPanel.add(buttonOne, BorderLayout.AFTER_LAST_LINE);
        numPlayerMenuPanel.add(buttonTwo, BorderLayout.AFTER_LAST_LINE);
        numPlayerMenuPanel.add(buttonThree, BorderLayout.AFTER_LAST_LINE);
        
        this.getContentPane().remove(startingPanel);
        numPlayerMenuPanel.setVisible(true);
        this.add(numPlayerMenuPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    
    public void chooseNamePanel(int numberOfPlayers){
        textDisplay = new JLabel("Write down the names of each player");
        buttonOne.setText("Confirm");
        fieldOne.setText("Player 1 name");
        fieldTwo.setText("Player 2 name");
        
        namePlayerMenuPanel.add(textDisplay, BorderLayout.NORTH);
        namePlayerMenuPanel.add(fieldOne, BorderLayout.AFTER_LAST_LINE);
        namePlayerMenuPanel.add(fieldTwo, BorderLayout.AFTER_LAST_LINE);
        
        if (numberOfPlayers == 3){
            fieldThree.setText("Player 3 name");
            namePlayerMenuPanel.add(fieldThree, BorderLayout.AFTER_LAST_LINE);
        } else if (numberOfPlayers == 4){
            fieldThree.setText("Player 3 name");
            fieldFour.setText("Player 4 name");
            namePlayerMenuPanel.add(fieldThree, BorderLayout.AFTER_LAST_LINE);
            namePlayerMenuPanel.add(fieldFour, BorderLayout.AFTER_LAST_LINE);
        }
        namePlayerMenuPanel.add(buttonOne);
        
        this.getContentPane().remove(numPlayerMenuPanel);
        namePlayerMenuPanel.setVisible(true);
        this.add(namePlayerMenuPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();   
    }
    
    public void loadGamePanel(){
        //TODO: Need to think what to do here, Ideally a user has a save with a name.
        //Would need to pull the data from the db send it to here thru the controller.
        //This would only send back the saves, We can put them in a button.
        //After that it would call a different panel for the stats of the players.
        //https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
        //For more info on JTable
    }

    private void setScreenSize(JFrame panel){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        panel.setSize(screenSize.width/2, screenSize.height/2);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        //TODO: figure out what to do with the observable.
    }
    
    public void addActionListener(ActionListener listen){
        //JButton
        buttonOne.addActionListener(listen);
        buttonTwo.addActionListener(listen);
        buttonThree.addActionListener(listen);
    }
    
}
