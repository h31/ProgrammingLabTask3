import units.teamTwo.Sectoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame{
    private JButton button = new JButton("Начать");
    private JTextField input = new JTextField("Ник",5);
    private JLabel label = new JLabel("Введите ник:");
    private JLabel label2 = new JLabel("Выберите размер карты:");
    private JRadioButton radio1 = new JRadioButton("Маленькая");
    private JRadioButton radio2 = new JRadioButton("Большая");

    public Launcher() {
        super("Launcher");
        this.setBounds(0,0,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4,3,2,2));
        container.add(label);
        container.add(input);

        ButtonGroup groupMapSelect = new ButtonGroup();
        groupMapSelect.add(radio1);
        groupMapSelect.add(radio2);
        container.add(label2);
        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if(radio1.isSelected()) {
                Game game = new Game(1280, 720,10,10);
                game.start();
            } else if(radio2.isSelected()){
                Game game = new Game(1280, 720,20,20);
                game.start();
            }
        }
    }
}
