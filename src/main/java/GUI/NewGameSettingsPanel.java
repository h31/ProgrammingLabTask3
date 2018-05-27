package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class NewGameSettingsPanel extends JPanel {

    private JTextField starNameField, starRadiusField, countOfPlanetsField;

    private JButton ok;

    private JRadioButton but1, but2, but3;

    int numberOfImage;

    private boolean isInputCorrect;

    public NewGameSettingsPanel() {
        super();
        setBackground(Color.BLUE);
        setSize(400, 600);
        setVisible(true);
        ImageIcon icon1 = new ImageIcon("files/stars/1icon.png");
        ImageIcon icon2 = new ImageIcon("files/stars/2icon.png");
        ImageIcon icon3 = new ImageIcon("files/stars/3icon.png");
        but1 = new JRadioButton();
        but2 = new JRadioButton();
        but3 = new JRadioButton();
        ButtonGroup bg = new ButtonGroup();
        bg.add(but1);
        bg.add(but2);
        bg.add(but3);
        JLabel starName = new JLabel("Введите имя звезды");
        JLabel starRadius = new JLabel("Введите радиус звезды");
        JLabel starImage = new JLabel("Выберите картинку для звезды");
        JLabel countOfPlanets = new JLabel("Введите кол-во планет");
        starNameField = new JTextField(100);
        starRadiusField = new JTextField(100);
        countOfPlanetsField = new JTextField(100);
        ok = new JButton("OK");
        ok.addActionListener(e -> onPressOK());
        add(starName);
        add(starNameField);
        add(starRadius);
        add(starRadiusField);
        add(starImage);
        add(but1);
        add(but2);
        add(but3);
        add(countOfPlanets);
        add(countOfPlanetsField);
        add(ok);
    }

    public void onPressOK() {
        String starName = "";
        int starRadius = 0;
        int countOfPlanets = 0;
        try {
            starName = starNameField.getText();
            starRadius = Integer.parseInt(starRadiusField.getText());
            countOfPlanets = Integer.parseInt(countOfPlanetsField.getText());
            if (starName.equals("") || starRadius <= 0 || starRadius > 100 || countOfPlanets <= 0 || countOfPlanets > 10)
                throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Введены некорректные данные, повторите ввод");
        }
        if (!starName.equals("") && starRadius > 0 && starRadius < 100 && countOfPlanets > 0 && countOfPlanets < 11) {
            isInputCorrect = true;
        }
        if (isInputCorrect) {
            File f = new File("files/data");
            try {
                if (but1.isSelected()) numberOfImage = 1;
                if (but2.isSelected()) numberOfImage = 2;
                if (but3.isSelected()) numberOfImage = 3;
                PrintWriter pw = new PrintWriter(f);
                pw.close();
                FileWriter fr = new FileWriter(f);
                fr.write(starName + " " + starRadius + " " + numberOfImage + " " + countOfPlanets);
                fr.close();
            } catch (Exception e) {
                System.out.print("FILENOTFOUND");
            }
            PlanetConfigurationFrame.main(new String[]{});
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }
    }
}