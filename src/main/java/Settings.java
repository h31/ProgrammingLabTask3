import javax.swing.*;
import java.awt.*;

class Settings extends JFrame {
    private JTextField cols = new JTextField("9");
    private JTextField bombs = new JTextField("10");

    Settings() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setTitle("Settings");
        setLayout(new GridLayout(3, 2));

        JButton button = new JButton("Start game!");

        JLabel height = new JLabel("Cols x Rows (5..20) :");
        JLabel bomb = new JLabel("Bombs :");

        height.setHorizontalAlignment(SwingConstants.CENTER);
        bomb.setHorizontalAlignment(SwingConstants.CENTER);

        add(height);
        add(cols);
        add(bomb);
        add(bombs);
        add(button);

        button.addActionListener(
                 e -> {
                     try {
                         final int col = Integer.parseInt(cols.getText());
                         final int mine = Integer.parseInt(bombs.getText());
                         if (col < 5 || col > 20)
                             JOptionPane.showMessageDialog(null, "Enter the correct data",
                                     "Error",  JOptionPane.ERROR_MESSAGE);
                         else {
                             dispose();
                             new MineSweeper(col, col, mine);
                         }
                     } catch (NumberFormatException ex){
                         JOptionPane.showMessageDialog(null, "Enter the correct data",
                                 "Error",  JOptionPane.ERROR_MESSAGE);
                     }
                 }
        );
     }
}
