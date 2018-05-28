package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class PlanetConfigurationPanel extends JPanel {

    private JTextField planetNameField, planetRadiusField, planetAField, planetBField;

    private JLabel planetNumber;

    private JRadioButton but1, but2, but3, but4, but5, but6;

    private int countOfPlanets = readCountOfPlanets();

    private int currentPlanet = 1;

    private int numberOfImage;

    public int readCountOfPlanets(){
        try {
            File f = new File("files/data");
            Scanner sc = new Scanner(f);
            sc.next();
            sc.next();
            sc.next();
            return Integer.parseInt(sc.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public PlanetConfigurationPanel() {
        super();
        setSize(400, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(true);
        planetNumber = new JLabel("Введите данные для планеты номер " + currentPlanet);
        planetNumber.setForeground(new Color(0x3A1BFB));
        planetNumber.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel planetName = new JLabel("Введите имя планеты (Без пробелов)");
        planetName.setForeground(new Color(0x3A1BFB));
        planetName.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel planetRadius = new JLabel("Введите радиус планеты (От 1 до 30)");
        planetRadius.setForeground(new Color(0x3A1BFB));
        planetRadius.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel planetA = new JLabel("Введите большую полуось орбиты (От 100 до 600)");
        planetA.setForeground(new Color(0x3A1BFB));
        planetA.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel planetB = new JLabel("Введите меньшую полуось орбиты (От 100 до 600)");
        planetB.setForeground(new Color(0x3A1BFB));
        planetB.setFont(new Font("Serif", Font.ITALIC, 20));
        JLabel planetImage = new JLabel("Выберите картинку планеты");
        planetImage.setForeground(new Color(0x3A1BFB));
        planetImage.setFont(new Font("Serif", Font.ITALIC, 20));
        planetNameField = new JTextField(100);
        planetNameField.setForeground(new Color(0xFFDF1A));
        planetNameField.setBackground(new Color(0x1C16FB));
        planetNameField.setMaximumSize(new Dimension(300, 25));
        planetRadiusField = new JTextField(100);
        planetRadiusField.setForeground(new Color(0xFFDF1A));
        planetRadiusField.setBackground(new Color(0x1C16FB));
        planetRadiusField.setMaximumSize(new Dimension(300, 25));
        planetAField = new JTextField(100);
        planetAField.setForeground(new Color(0xFFDF1A));
        planetAField.setBackground(new Color(0x1C16FB));
        planetAField.setMaximumSize(new Dimension(300, 25));
        planetBField = new JTextField(100);
        planetBField.setForeground(new Color(0xFFDF1A));
        planetBField.setBackground(new Color(0x1C16FB));
        planetBField.setMaximumSize(new Dimension(300, 25));
        but1 = new JRadioButton();
        but2 = new JRadioButton();
        but3 = new JRadioButton();
        but4 = new JRadioButton();
        but5 = new JRadioButton();
        but6 = new JRadioButton();
        ButtonGroup bg = new ButtonGroup();
        bg.add(but1);
        bg.add(but2);
        bg.add(but3);
        bg.add(but4);
        bg.add(but5);
        bg.add(but6);
        JButton ok = new JButton("OK");
        Component rigidArea = Box.createRigidArea(new Dimension(10, 10));
        ok.addActionListener(e -> onPressOK());
        add(planetNumber);
        add(planetName);
        add(planetNameField);
        add(planetRadius);
        add(planetRadiusField);
        add(planetA);
        add(planetAField);
        add(planetB);
        add(planetBField);
        add(planetImage);
        add(but1);
        add(but2);
        add(but3);
        add(but4);
        add(but5);
        add(but6);
        add(rigidArea);
        add(ok);
    }

    private void onPressOK() {
        String planetName = "";
        int planetRadius = 0;
        int planetA = 0;
        int planetB = 0;
        boolean isInputCorrect = false;
        try {
            planetName = planetNameField.getText();
            planetRadius = Integer.parseInt(planetRadiusField.getText());
            planetA = Integer.parseInt(planetAField.getText());
            planetB = Integer.parseInt(planetBField.getText());
            if (planetName.equals("") || planetName.contains(" ")
                    || planetRadius < 1 || planetRadius > 30 || planetA < 100
                    || planetA > 600 || planetB < 100 || planetB > planetA) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Введены некорректные данные, повторите ввод");
        }
        if (!planetName.equals("") && !planetName.contains(" ")
                && planetRadius > 0 && planetRadius <= 30 && planetA >= 100
                && planetA <= 600 && planetB >= 100 && planetB <= planetA) {
            isInputCorrect = true;
            currentPlanet++;
        }
        if (isInputCorrect) {
            if (but1.isSelected()) numberOfImage = 1;
            if (but2.isSelected()) numberOfImage = 2;
            if (but3.isSelected()) numberOfImage = 3;
            if (but4.isSelected()) numberOfImage = 4;
            if (but5.isSelected()) numberOfImage = 5;
            if (but6.isSelected()) numberOfImage = 6;
            File f = new File("files/data");
            try {
                FileWriter fr = new FileWriter(f, true);
                fr.write(" " + planetName + " " + planetRadius + " " + planetA + " " + planetB + " " + numberOfImage);
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            planetNumber.setText("Введите данные для планеты номер " + currentPlanet);
            planetNameField.setText(null);
            planetRadiusField.setText(null);
            planetAField.setText(null);
            planetBField.setText(null);
            if (currentPlanet > countOfPlanets) {
                PlanetSystemSimulatorFrame.main(new String[]{});
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image1 = new ImageIcon("files/planets/1icon.png").getImage();
        Image image2 = new ImageIcon("files/planets/2icon.png").getImage();
        Image image3 = new ImageIcon("files/planets/3icon.png").getImage();
        Image image4 = new ImageIcon("files/planets/4icon.png").getImage();
        Image image5 = new ImageIcon("files/planets/5icon.png").getImage();
        Image image6 = new ImageIcon("files/planets/6icon.png").getImage();
        Image image = new ImageIcon("files/backgrounds/space3.png").getImage();
        g.drawImage(image, 0, 0, 800, 600, null);
        g.drawImage(image1, 50, but2.getY(), 50, 50, null);
        g.drawImage(image2, 110, but2.getY(), 50, 50, null);
        g.drawImage(image3, 170, but2.getY(), 50, 50, null);
        g.drawImage(image4, 230, but2.getY(), 50, 50, null);
        g.drawImage(image5, 290, but2.getY(), 50, 50, null);
        g.drawImage(image6, 350, but2.getY(), 50, 50, null);
        g.setColor(new Color(28, 22, 251));
        g.setFont(new Font("Serif", Font.ITALIC, 20));
        g.drawString("1", 30, but1.getY() + 18);
        g.drawString("2", 30, but2.getY() + 18);
        g.drawString("3", 30, but3.getY() + 18);
        g.drawString("4", 30, but4.getY() + 18);
        g.drawString("5", 30, but5.getY() + 18);
        g.drawString("6", 30, but6.getY() + 18);
        g.drawString("1", 50, but2.getY());
        g.drawString("2", 110, but2.getY());
        g.drawString("3", 170, but2.getY());
        g.drawString("4", 230, but2.getY());
        g.drawString("5", 290, but2.getY());
        g.drawString("6", 350, but2.getY());
    }
}