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

    private JButton ok;

    private JRadioButton but1, but2, but3, but4, but5, but6;

    private boolean isInputCorrect;

    int countOfPlanets = readCountOfPlanets();

    int currentPlanet = 1;

    int numberOfImage;

    private int readCountOfPlanets(){
        try {
            File f = new File("files/data");
            Scanner sc = new Scanner(f);
            sc.next();
            sc.next();
            sc.next();
            return Integer.parseInt(sc.next());
        } catch (FileNotFoundException e) {
            System.out.print("fdfs");
        }
        return 0;
    }

    public PlanetConfigurationPanel() {
        super();
        setBackground(Color.BLUE);
        setSize(400, 600);
        setVisible(true);
        planetNumber = new JLabel("Введите данные для планеты номер " + currentPlanet);
        JLabel planetName = new JLabel("Введите имя планеты");
        JLabel planetRadius = new JLabel("Введите радиус планеты");
        JLabel planetA = new JLabel("Введите большую полуось орбиты");
        JLabel planetB = new JLabel("Введите меньшую полуось орбиты");
        JLabel planetImage = new JLabel("Выберете картинку планеты");
        planetNameField = new JTextField(100);
        planetRadiusField = new JTextField(100);
        planetAField = new JTextField(100);
        planetBField = new JTextField(100);
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
        ok = new JButton("OK");
        ok.addActionListener(e -> onPressOK());
        add(planetNumber);
        add(planetName);
        add(planetNameField);
        add(planetRadius);
        add(planetRadiusField);
        add(planetA);
        add(planetAField);
        add(planetB);
        add(planetImage);
        add(but1);
        add(but2);
        add(but3);
        add(but4);
        add(but5);
        add(but6);
        add(planetBField);
        add(ok);
    }

    public void onPressOK() {
        String planetName = "";
        int planetRadius = 0;
        int planetA = 0;
        int planetB = 0;
        isInputCorrect = false;
        try {
            planetName = planetNameField.getText();
            planetRadius = Integer.parseInt(planetRadiusField.getText());
            planetA = Integer.parseInt(planetAField.getText());
            planetB = Integer.parseInt(planetBField.getText());
            if (planetName.equals("") || planetRadius <= 0 || planetRadius > 100 || planetA <= 0
                    || planetA > 600 || planetB <= 0 || planetB > planetA) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Введены некорректные данные, повторите ввод");
        }
        if (!planetName.equals("") && planetRadius > 0 && planetRadius <= 100 && planetA > 0
                && planetA <= 600 && planetB > 0 && planetB <= planetA) {
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
                System.out.print("FILENOTFOUND");
            }
            planetNumber.setText("Введите данные для планеты номер " + currentPlanet);
            planetNameField.setText(null);
            planetRadiusField.setText(null);
            planetAField.setText(null);
            planetBField.setText(null);
            if (currentPlanet > countOfPlanets) {
                System.out.println("if3");
                MetaFrame.main(new String[]{});
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.dispose();
            }
        }
    }
}