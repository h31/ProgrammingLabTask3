import units.teamTwo.Sectoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {
    private JButton startButton = new JButton("Начать");
    private JLabel choiceMapSize = new JLabel("Выберите размер карты:");
    private JLabel choicePlayerOne = new JLabel("Выберите :");
    private JLabel choicePlayerTwo = new JLabel("Выберите :");
    private JRadioButton small = new JRadioButton("Маленькая");
    private JRadioButton middle = new JRadioButton("Средняя");
    private JRadioButton lardge = new JRadioButton("Большая");
    private JRadioButton mage = new JRadioButton("Mage");
    private JRadioButton knight = new JRadioButton("Knight");
    private JRadioButton warrior = new JRadioButton("Warrior");
    private JRadioButton sectoid = new JRadioButton("Sectoid");
    private JRadioButton demon = new JRadioButton("Demon");
    private JRadioButton witch = new JRadioButton("Witch");


    public Launcher() {
        super("Launcher");
        this.setBounds(0, 0, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 4, 2, 2));
        container.add(choiceMapSize);
        ButtonGroup groupMapSelect = new ButtonGroup();
        groupMapSelect.add(small);
        groupMapSelect.add(middle);
        groupMapSelect.add(lardge);
        ButtonGroup groupTeamOne = new ButtonGroup();
        groupTeamOne.add(mage);
        groupTeamOne.add(knight);
        groupTeamOne.add(warrior);
        ButtonGroup groupTeamTwo = new ButtonGroup();
        groupTeamTwo.add(sectoid);
        groupTeamTwo.add(demon);
        groupTeamTwo.add(witch);
        container.add(small);
        small.setSelected(true);
        container.add(middle);
        container.add(lardge);
        container.add(choicePlayerOne);
        container.add(mage);
        mage.setSelected(true);
        container.add(knight);
        container.add(warrior);
        container.add(choicePlayerTwo);
        container.add(demon);
        demon.setSelected(true);
        container.add(witch);
        container.add(sectoid);
        startButton.addActionListener(new ButtonEventListener());
        container.add(startButton);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String playerOne = "";
            String playerTwo = "";
            int size;
            if (knight.isSelected()) {
                playerOne = "Knight";
            }
            if (mage.isSelected()) {
                playerOne = "Mage";
            }
            if (warrior.isSelected()) {
                playerOne = "Warrior";
            }
            if (demon.isSelected()) {
                playerTwo = "Demon";
            }
            if (witch.isSelected()) {
                playerTwo = "Witch";
            }
            if (sectoid.isSelected()) {
                playerTwo = "Sectoid";
            }
            if (small.isSelected()) {
                size = 10;
            } else if (middle.isSelected()) {
                size = 15;
            } else {
                size = 20;
            }
            Game game = new Game(1280, 720, size, size, playerOne, playerTwo);
            game.start();
        }
    }
}
