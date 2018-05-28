package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class NewGameSettingsPanel extends JPanel {

    private JTextField starNameField, starRadiusField, countOfPlanetsField;

    private JRadioButton but1, but2, but3;

    private int numberOfImage;

    private boolean isInputCorrect;

    public NewGameSettingsPanel() {
        super();
        setSize(400, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
        but1 = new JRadioButton();
        but2 = new JRadioButton();
        but3 = new JRadioButton();
        ButtonGroup bg = new ButtonGroup();
        bg.add(but1);
        bg.add(but2);
        bg.add(but3);
        JLabel starName = new JLabel("Введите имя звезды (Без пробелов)");
        starName.setForeground(new Color(0x3A1BFB));
        starName.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel starRadius = new JLabel("Введите радиус звезды (От 1 до 50)");
        starRadius.setForeground(new Color(0x3A1BFB));
        starRadius.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel starImage = new JLabel("Выберите картинку для звезды");
        starImage.setForeground(new Color(0x3A1BFB));
        starImage.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel countOfPlanets = new JLabel("Введите кол-во планет (От 1 до 10)");
        countOfPlanets.setForeground(new Color(0x3A1BFB));
        countOfPlanets.setFont(new Font("Serif", Font.ITALIC, 20));
        starNameField = new JTextField(100);
        starNameField.setForeground(new Color(0xFFED00));
        starNameField.setBackground(new Color(0x1C16FB));
        starNameField.setMaximumSize(new Dimension(300, 25));
        starRadiusField = new JTextField(100);
        starRadiusField.setForeground(new Color(0xFFED00));
        starRadiusField.setBackground(new Color(0x1C16FB));
        starRadiusField.setMaximumSize(new Dimension(300, 25));
        countOfPlanetsField = new JTextField(100);
        countOfPlanetsField.setForeground(new Color(0xFFED00));
        countOfPlanetsField.setBackground(new Color(0x381BFB));
        countOfPlanetsField.setMaximumSize(new Dimension(300, 25));
        JButton ok = new JButton("OK");
        Component rigidArea = Box.createRigidArea(new Dimension(10, 10));
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
        add(rigidArea);
        add(ok);
    }

    private void onPressOK() {
        String starName = "";
        int starRadius = 0;
        int countOfPlanets = 0;
        try {
            starName = starNameField.getText();
            starRadius = Integer.parseInt(starRadiusField.getText());
            countOfPlanets = Integer.parseInt(countOfPlanetsField.getText());
            if (starName.equals("") || starName.contains(" ") || starRadius <= 0 || starRadius > 50
                    || countOfPlanets <= 0 || countOfPlanets > 10)
                throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Введены некорректные данные, повторите ввод");
        }
        if (!starName.equals("") && !starName.contains(" ") && starRadius > 0 && starRadius < 51
                && countOfPlanets > 0 && countOfPlanets < 11) {
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
                e.printStackTrace();
            }
            PlanetConfigurationFrame.main(new String[]{});
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image1 = new ImageIcon("files/stars/1icon.png").getImage();
        Image image2 = new ImageIcon("files/stars/2icon.png").getImage();
        Image image3 = new ImageIcon("files/stars/3icon.png").getImage();
        Image background = new ImageIcon("files/backgrounds/space2.png").getImage();
        g.drawImage(background, 0, 0, 800, 600, null);
        g.drawImage(image1, 50, but2.getY(), 50, 50, null);
        g.drawImage(image2, 100, but2.getY(), 50, 50, null);
        g.drawImage(image3, 150, but2.getY(), 50, 50, null);
        g.setColor(new Color(58, 27, 251));
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("1", 30, but1.getY() + 18);
        g.drawString("2", 30, but2.getY() + 18);
        g.drawString("3", 30, but3.getY() + 18);
        g.drawString("1", 60, but2.getY());
        g.drawString("2", 120, but2.getY());
        g.drawString("3", 180, but2.getY());
    }
}