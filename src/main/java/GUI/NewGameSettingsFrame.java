package GUI;

import javax.swing.*;

public class NewGameSettingsFrame extends JFrame {

    public NewGameSettingsFrame(String s) {
        super(s);
        setSize(400,600);
        setContentPane(new NewGameSettingsPanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewGameSettingsFrame("Planet System Simulation Settings"));
    }
}
