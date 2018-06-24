import units.teamTwo.Sectoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame{
    private JButton button = new JButton("Начать");
    private JLabel label = new JLabel("Выберите размер карты:");
    private JRadioButton radio1 = new JRadioButton("Маленькая");
    private JRadioButton radio2 = new JRadioButton("Средняя");
    private JRadioButton radio3 = new JRadioButton("Большая");

    public Launcher() {
        super("Launcher");
        this.setBounds(0,0,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(label);
        ButtonGroup groupMapSelect = new ButtonGroup();
        groupMapSelect.add(radio1);
        groupMapSelect.add(radio2);
        groupMapSelect.add(radio3);
        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        container.add(radio3);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if(radio1.isSelected()) {
                Game game = new Game(1280, 720,10,10);
                game.start();
            } else if(radio2.isSelected()){
                Game game = new Game(1280, 720,15,15);
                game.start();
            } else if (radio3.isSelected()){
                Game game = new Game(1280, 720, 20, 20);
            }
        }
    }
}
