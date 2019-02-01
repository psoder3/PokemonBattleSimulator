package pokemonbattlesimulator;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class BattleFrame extends JFrame {
    
    PokemonBattleSimulator simulator;
    JTextField fileInput;
    JLabel label;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem loadMenuItem;
    JMenu helpMenu;
    JMenuItem howToMenuItem;
    JMenuItem exampleMenuItem;
    JMenu testMenu;
    JMenuItem testMenuItem;
    JTextArea outputPane;
    JPanel gamePanel;
    int image_width = 200;
    int image_height = 200;
    File lastDirectory;
    JLabel p1HealthLabel;
    JLabel p2HealthLabel;
    JComboBox p1Combo;
    JComboBox p2Combo;
    
    JButton goButton;

    JPanel p1Image;
    JPanel p2Image;
    
    JPanel ArenaPane;
    JProgressBar p1Bar;
    JProgressBar p2Bar;
    
    public BattleFrame (PokemonBattleSimulator simulator) throws MalformedURLException, IOException
    {
        this.simulator = simulator;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Pokemon Battle Arena and Test Simulator");
        this.setSize(600,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double x = (screenSize.getWidth()/2) - (getWidth()/2);
        double y = (screenSize.getHeight()/2) - (getHeight()/2);
        this.setLocation((int)x, (int)y);
        //this.setResizable(false);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadMenuItem = new JMenuItem("Load Pokemon File");
        helpMenu = new JMenu("Help");
        howToMenuItem = new JMenuItem("How to use this program");
        exampleMenuItem = new JMenuItem("Example Text");
        testMenu = new JMenu("Test");
        testMenuItem = new JMenuItem("Run many random battles");
        
        
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    if (lastDirectory != null);
                    fc.setCurrentDirectory(lastDirectory);
                    int result = fc.showOpenDialog(BattleFrame.this);
                    lastDirectory = fc.getCurrentDirectory();
                    
                    if (result == JFileChooser.CANCEL_OPTION) {
                        
                    }
                    if (fc.getSelectedFile() == null) {
                        
                    }
                    File data_file = fc.getSelectedFile();
                    simulator.importDataFromFile(data_file);
                    
                    JPanel p1BarPanel = new JPanel();
                    p1Bar = new JProgressBar();
                    JLabel p1NameLabel = new JLabel(simulator.pokemon1.name + "    ");
                    p1BarPanel.setLayout(new GridLayout(8,1));
                    
                    JPanel lblPanel1 = new JPanel();
                    lblPanel1.add(p1NameLabel);
                    p1HealthLabel = new JLabel(simulator.pokemon1.current_health + "/"
                            + simulator.pokemon1.max_health);
                    lblPanel1.add(p1HealthLabel);
                    
                    
                    p1Combo = new JComboBox();
                    for (int i = 0; i < 4; i++)
                    {
                        p1Combo.addItem(simulator.pokemon1.moves.get(i));
                    }
                    
                    p2Combo = new JComboBox();
                    for (int i = 0; i < 4; i++)
                    {
                        p2Combo.addItem(simulator.pokemon2.moves.get(i));
                    }
                    
                    p1BarPanel.add(new JLabel(""));
                    p1BarPanel.add(new JLabel(""));
                    p1BarPanel.add(new JLabel(""));
                    p1BarPanel.add(p1Combo);
                    
                    p1BarPanel.add(lblPanel1);
                    p1BarPanel.add(p1Bar);
                    
                    
                    // ------------------
                    
                    JPanel p2BarPanel = new JPanel();
                    p2Bar = new JProgressBar();
                    JLabel p2NameLabel = new JLabel(simulator.pokemon2.name + "    ");
                    p2BarPanel.setLayout(new GridLayout(8,1));
                    
                    JPanel lblPanel2 = new JPanel();
                    lblPanel2.add(p2NameLabel);
                    p2HealthLabel = new JLabel(simulator.pokemon2.current_health + "/"
                            + simulator.pokemon2.max_health);
                    lblPanel2.add(p2HealthLabel);
                    
                    p2BarPanel.add(new JLabel(""));
                    p2BarPanel.add(new JLabel(""));
                                        
                    p2BarPanel.add(lblPanel2);
                    p2BarPanel.add(p2Bar);
                    p2BarPanel.add(p2Combo);
                    
                    
                    p1Bar.setValue(100);
                    p2Bar.setValue(100);
                    
                    p1Image = new JPanel();
                    p2Image = new JPanel();
                    
                    
                    BufferedImage myPicture = ImageIO.read(new URL(simulator.pokemon1.imageURL));
                    JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(image_width, image_height, Image.SCALE_FAST)));
                    p1Image.add(picLabel);
                    BufferedImage myPicture2 = ImageIO.read(new URL(simulator.pokemon2.imageURL));
                    JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2.getScaledInstance(image_width, image_height, Image.SCALE_FAST)));
                    p2Image.add(picLabel2);
                    
                    
                    
                    JPanel p1Panel = new JPanel();
                    JPanel p2Panel = new JPanel();
                    p1Panel.setLayout(new GridLayout(2,1));
                    p2Panel.setLayout(new GridLayout(2,1));
                    
                    p1Panel.add(p2BarPanel);
                    p1Panel.add(p1Image);
                    
                    p2Panel.add(p2Image);
                    p2Panel.add(p1BarPanel);
                    
                    ArenaPane = new JPanel();
                    ArenaPane.setLayout(new GridLayout(1,2));
                    
                    ArenaPane.add(p1Panel);
                    ArenaPane.add(p2Panel);
                    
                    gamePanel.removeAll();
                    gamePanel.add(ArenaPane,BorderLayout.CENTER);
                    
                    
                    goButton = new JButton("GO");
                    goButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String outputText = outputPane.getText();
                                
                                goButton.setEnabled(false);
                                
                                int first = simulator.checkWhoGoesFirst();
                                Pokemon p1;
                                Pokemon p2;
                                
                                Move m1;
                                Move m2;
                                
                                JProgressBar pBar1;
                                JProgressBar pBar2;
                                
                                JLabel l1;
                                JLabel l2;
                                
                                if (first == 1)
                                {
                                    // p1 goes first
                                    p1 = simulator.pokemon1;
                                    p2 = simulator.pokemon2;
                                    m1 = (Move)p1Combo.getSelectedItem();
                                    m2 = (Move)p2Combo.getSelectedItem();
                                    pBar1 = p1Bar;
                                    pBar2 = p2Bar;
                                    l1 = p1HealthLabel;
                                    l2 = p2HealthLabel;
                                }
                                else
                                {
                                    // p2 goes first
                                    p1 = simulator.pokemon2;
                                    p2 = simulator.pokemon1;
                                    m1 = (Move)p2Combo.getSelectedItem();
                                    m2 = (Move)p1Combo.getSelectedItem();
                                    pBar1 = p2Bar;
                                    pBar2 = p1Bar;
                                    l1 = p2HealthLabel;
                                    l2 = p1HealthLabel;
                                }
                                outputText += p1.name + " used " + m1.name + "!\n";
                                
                                int damage = simulator.attack(p1,p2);
                                if (damage > 0)
                                {
                                    p2.current_health -= damage;
                                    outputText += "It caused " + damage + " damage.\n\n";
                                }
                                else
                                {
                                    outputText += "...but the attack missed.\n\n";
                                }
                                
                                pBar2.setValue(p2.current_health);
                                if (p2.current_health < 0)
                                {
                                    p2.current_health = 0;
                                }
                                l2.setText(p2.current_health + "/" + p2.max_health);
                                BattleFrame.this.revalidate();
                                BattleFrame.this.repaint();
                                
                                
                                if (simulator.pokemon1.current_health > 0 && simulator.pokemon2.current_health > 0)
                                {
                                    outputText += p2.name + " used " + m2.name + "!\n";
                                
                                    damage = simulator.attack(p2,p1);
                                    if (damage > 0)
                                    {
                                        p1.current_health -= damage;
                                        outputText += "It caused " + damage + " damage.\n\n";
                                    }
                                    else
                                    {
                                        outputText += "...but the attack missed.\n\n";
                                    }
                                    pBar1.setValue(p1.current_health);
                                    if (p1.current_health < 0)
                                    {
                                        p1.current_health = 0;
                                    }
                                    l1.setText(p1.current_health + "/" + p1.max_health);
                                    BattleFrame.this.revalidate();
                                    BattleFrame.this.repaint();
                                        
                                }
                                if (simulator.pokemon1.current_health <= 0)
                                {
                                    
                                    outputText += simulator.pokemon1.name + " fainted!\n\n";
                                    BattleFrame.this.revalidate();
                                    BattleFrame.this.repaint();
                                         
                                }
                                else if (simulator.pokemon2.current_health <= 0)
                                {
                                    
                                    outputText += simulator.pokemon2.name + " fainted!\n";
                                    BattleFrame.this.revalidate();
                                    BattleFrame.this.repaint();
                                    
                                }
                                outputPane.setText(outputText);
                                Thread.sleep(500);                                

                            } catch (InterruptedException ex) {
                                Logger.getLogger(BattleFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (simulator.pokemon1.current_health > 0 && simulator.pokemon2.current_health > 0)
                            {
                                goButton.setEnabled(true);
                            }

                        }
                    });
                    
                    gamePanel.add(goButton);
                    
                    outputPane = new JTextArea(6,45);
                    outputPane.setEditable(false);
                    gamePanel.add(new JScrollPane(outputPane),BorderLayout.SOUTH);
                    
                    BattleFrame.this.revalidate();
                    BattleFrame.this.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(BattleFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        howToMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHowToText();
            }
        });
        
        exampleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExampleText();
            }
        });
        
        testMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulator.runManyBattles();
            }
        });
        
        gamePanel = new JPanel();
        helpMenu.add(howToMenuItem);
        helpMenu.add(exampleMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        testMenu.add(testMenuItem);
        menuBar.add(testMenu);
        this.add(menuBar,BorderLayout.NORTH);
        
        
        
        this.add(gamePanel,BorderLayout.CENTER);

        
        
        
        this.setVisible(true);
    }
    
    String getHelpText()
    {
        String aboutHTML = 
                "<html>"+
                "<p>" +
                "This program allows you to test fight 2 Pokemon either manually or many times automatically using random moves" +
                "<br>" +
                "<br>" +
                "To use program, simply load a file that fits the following format:" +
                "<br>" +
                "<br>" +
                "<p>" +
                "name"+""+
                "<br>" +
                "image url"+
                "<br>" +
                "health value"+
                "<br>" +
                "level"+
                "<br>" +
                "attack" +
                "<br>" +
                "speed" +
                "<br>" +
                "move 1 name" +
                "<br>" +
                "move 1 accuracy" +
                "<br>" +
                "move 1 damage" +
                "<br>" +
                "..." +
                "<br>" +
                "..." +
                "<br>" +
                "..." +
                "<br>" +
                "move 4 accuracy" +
                "<br>" +
                "move 4 damage" +
                "<br>" +
                "(All of the above again for Pokemon 2)" +
                "</p>" +
                "<br>" +
                "<br>" +
                "See example in Help Menu labeled 'Example text'" +
                "<br>" +
                "<br>" +
                "Contact: paulsoderquist3@gmail.com" +
                "</p>" +
                "</html>";

        return aboutHTML;
    }
    
    
    private void showHowToText()
    {
        new AboutDialog(this,"How to Use Battle Simulator",getHelpText());        
    }
    
    private String getExampleText()
    {
        String example_text = "";
        
        example_text = 
            "Charmander\n" +
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png\n" +
            "FIRE\n" +
            "100\n" +
            "5\n" +
            "11\n" +
            "11\n" +
            "Scratch\n" +
            ".8\n" +
            "4\n" +
            "Ember\n" +
            ".6\n" +
            "5\n" +
            "Tackle\n" +
            ".9\n" +
            "3\n" +
            "Double kick\n" +
            ".6\n" +
            "5\n" +
            "Bulbasaur\n" +
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png\n" +
            "GRASS\n" +
            "100\n" +
            "5\n" +
            "11\n" +
            "11\n" +
            "Vine whip\n" +
            ".8\n" +
            "4\n" +
            "Razor leaf\n" +
            ".6\n" +
            "5\n" +
            "Tackle\n" +
            ".9\n" +
            "3\n" +
            "Head butt\n" +
            ".6\n" +
            "5";
        
        return example_text;
    }
    
    private void showExampleText()
    {
        JEditorPane editorPane= new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));


        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorPane.setText(getExampleText());
        editorPane.setCaretPosition(0);
        
        JDialog helpDialog = new JDialog();
        helpDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        helpDialog.add(editorScrollPane, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int help_width = (int)(screenSize.getWidth()*.5);
        int help_height = (int)(screenSize.getHeight()*.35);

        helpDialog.setSize(help_width,help_height);

        double x = (screenSize.getWidth()/2) - (helpDialog.getWidth()/2);
        double y = (screenSize.getHeight()/2) - (helpDialog.getHeight()/2);
        helpDialog.setLocation((int)x, (int)y);

        helpDialog.setVisible(true);
    }
    
    class AboutDialog extends JDialog implements ActionListener {
        public AboutDialog(JFrame parent, String title, String message) {
          super(parent, title, true);
          JPanel messagePane = new JPanel();
          messagePane.add(new JLabel(message));
          messagePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
          getContentPane().add(messagePane);
          JPanel buttonPane = new JPanel();
          JButton button = new JButton("OK"); 
          buttonPane.add(button); 
          button.addActionListener(this);
          getContentPane().add(buttonPane, BorderLayout.SOUTH);
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          this.getRootPane().setDefaultButton(button);


          pack(); 
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          double x = (screenSize.getWidth()/2) - (getWidth()/2);
          double y = (screenSize.getHeight()/2) - (getHeight()/2);
          this.setLocation((int)x, (int)y);
          setVisible(true);
          this.requestFocus();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
          setVisible(false); 
          dispose(); 
        }
      }
    
    
    
}
