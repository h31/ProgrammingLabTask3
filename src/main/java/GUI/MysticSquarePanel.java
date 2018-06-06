package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class MysticSquarePanel extends JPanel {
    private JPanel panel;
    private Desk desk = new Desk();

    public MysticSquarePanel() {
        setLayout(new BorderLayout());
        JButton display = new JButton("Chek");
        display.addActionListener(new WinAction());
        add(display, BorderLayout.NORTH);
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        ActionListener listener = new MSController();
        for (String key : desk.getKeys()) {
            MSButton button = new MSButton();
            button.setName(key);
            button.setText(desk.getTable().get(key));
            button.addActionListener(listener);
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);
    }

    public boolean chekTheVictory() {
        if (Integer.parseInt(desk.getTable().get("a:1")) + Integer.parseInt(desk.getTable().get("b:2"))
                +  Integer.parseInt(desk.getTable().get("c:3")) +  Integer.parseInt(desk.getTable().get("d:4")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("a:4")) + Integer.parseInt(desk.getTable().get("b:3"))
                +  Integer.parseInt(desk.getTable().get("c:2")) +  Integer.parseInt(desk.getTable().get("d:1")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("a:1")) + Integer.parseInt(desk.getTable().get("b:1"))
                +  Integer.parseInt(desk.getTable().get("c:1")) +  Integer.parseInt(desk.getTable().get("d:1")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("a:2")) + Integer.parseInt(desk.getTable().get("b:2"))
                +  Integer.parseInt(desk.getTable().get("c:2")) +  Integer.parseInt(desk.getTable().get("d:2")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("a:3")) + Integer.parseInt(desk.getTable().get("b:3"))
                +  Integer.parseInt(desk.getTable().get("c:3")) +  Integer.parseInt(desk.getTable().get("d:3")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("a:4")) + Integer.parseInt(desk.getTable().get("b:4"))
                +  Integer.parseInt(desk.getTable().get("c:4")) +  Integer.parseInt(desk.getTable().get("d:4")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("a:1")) + Integer.parseInt(desk.getTable().get("a:2"))
                +  Integer.parseInt(desk.getTable().get("a:3")) +  Integer.parseInt(desk.getTable().get("a:4")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("b:1")) + Integer.parseInt(desk.getTable().get("b:2"))
                +  Integer.parseInt(desk.getTable().get("b:3")) +  Integer.parseInt(desk.getTable().get("b:4")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("c:1")) + Integer.parseInt(desk.getTable().get("c:2"))
                +  Integer.parseInt(desk.getTable().get("c:3")) +  Integer.parseInt(desk.getTable().get("c:4")) != 34)
            return false;
        if (Integer.parseInt(desk.getTable().get("d:1")) + Integer.parseInt(desk.getTable().get("d:2"))
                +  Integer.parseInt(desk.getTable().get("d:3")) +  Integer.parseInt(desk.getTable().get("d:4")) != 34)
            return false;
        return true;
    }

    public class WinAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (chekTheVictory()) {
                JFrame victoryFrame = new MSVictoryFrame();
                victoryFrame.setTitle("Victory!");
                victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                victoryFrame.setVisible(true);
            }
        }
    }

    public class MSController implements ActionListener {
        private MSButton firstCell;
        private MSButton secondCell;
        private boolean buttonPress = false;

        public Map<String, String> changeCell(String key, String value) {
            desk.getTable().put(key, value);
            return desk.getTable();
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (!buttonPress) {
                firstCell = (MSButton) event.getSource();
                buttonPress = true;
            } else {
                secondCell = (MSButton) event.getSource();
                if (firstCell.compareCell(secondCell.getName())) {
                    String firstName = firstCell.getText();
                    String secondName = secondCell.getText();
                    firstCell.setText(secondName);
                    changeCell(firstCell.getName(), secondName);
                    secondCell.setText(firstName);
                    changeCell(secondCell.getName(), firstName);
                    buttonPress = false;
                }
                else {
                    buttonPress = false;
                }
            }
        }
    }
}

