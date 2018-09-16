package Game;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;

public class Quest {
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame("Текстовый квест");
    private static JPanel variantPanel = new JPanel();
    private static JPanel textPanel = new JPanel();
    private static JButton startButton = new JButton("Начать игру");
    private static JButton rulesButton = new JButton("Правила");
    private static JButton exitButton = new JButton("Выход");
    private static JButton backButton = new JButton("Назад");
    private static JButton firstRoom = new JButton("Войти в дверь с цифрой 1");
    private static JButton secondRoom = new JButton("Войти в дверь с цифрой 2");
    private static JButton thirdRoom = new JButton("Войти в дверь с цифрой 3");
    private static JButton doorWithCode = new JButton("Войти в дверь с цифрой 4");
    private static JButton backFromRooms = new JButton("Вернуться");
    private static JButton inputButton = new JButton("Ввод");

    private static Box environmentStart = Box.createVerticalBox();
    private static Box environmentFirstRoom = Box.createVerticalBox();
    private static Box environmentSecondRoom = Box.createVerticalBox();
    private static Box environmentThirdRoom = Box.createVerticalBox();
    private static Box environmentDoorWithCode = Box.createVerticalBox();
    private static Box error = Box.createVerticalBox();
    private static Box rules = Box.createVerticalBox();
    private static Box inputLocation = Box.createHorizontalBox();
    private static Box inputLabel = Box.createVerticalBox();
    private static JPasswordField inputCode = new JPasswordField(6);

    public static void main(String[] args) {
        int heightOfFrame;
        int widthOfFrame;
        char[] correctCode = {'6', '8', '4', '7', '3', '1'};
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 800;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        heightOfFrame = frame.getHeight();
        widthOfFrame = frame.getWidth();

        inputCode.setEchoChar((char) 0);
        inputCode.setFont(new Font("TimesRoman", Font.PLAIN, 19));
        ((AbstractDocument) inputCode.getDocument()).setDocumentFilter(new NewFilter());

        Color panelColor = new Color(233, 227, 175);
        variantPanel.setBackground(panelColor);

        buttonInitialization(startButton);
        buttonInitialization(rulesButton);
        buttonInitialization(exitButton);
        buttonInitialization(firstRoom);
        buttonInitialization(secondRoom);
        buttonInitialization(thirdRoom);
        buttonInitialization(doorWithCode);
        buttonInitialization(backFromRooms);
        buttonInitialization(inputButton);
        buttonInitialization(backButton);
        inputButton.setFont(new Font("TimesRoman", Font.PLAIN, 26));

        reader(environmentStart, "data\\environmentStart.txt");
        reader(environmentFirstRoom, "data\\environmentFirstRoom.txt");
        reader(environmentSecondRoom, "data\\environmentSecondRoom.txt");
        reader(environmentThirdRoom, "data\\environmentThirdRoom.txt");
        reader(environmentDoorWithCode, "data\\environmentDoorWithCode.txt");
        reader(error, "data\\error.txt");
        reader(rules, "data\\rules.txt");
        reader(inputLabel, "data\\inputLabel.txt");

        backButton.setVisible(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        variantPanel.setLayout(new GridBagLayout());
        inputLocation.add(inputLabel);
        inputLocation.add(inputCode);
        inputLocation.add(inputButton);
        inputLocation.setVisible(false);
        Box lastLocation = Box.createVerticalBox();
        lastLocation.add(environmentDoorWithCode);
        lastLocation.add(inputLocation);
        lastLocation.add(error);
        error.setVisible(false);
        textPanel.add(lastLocation);

        Dimension minSize = new Dimension(widthOfFrame, heightOfFrame / 3);
        Dimension prefSize = new Dimension(widthOfFrame, heightOfFrame / 3);
        Dimension maxSize = new Dimension(widthOfFrame, heightOfFrame / 3);
        panel.add(new Box.Filler(minSize, prefSize, maxSize));

        panel.add(startButton);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(rulesButton);
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backButton);
        panel.add(rules);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(backButton);

        GridBagConstraints notBox = new GridBagConstraints();

        inputCode.setVisible(false);
        environmentDoorWithCode.setVisible(false);
        textPanel.add(environmentStart);
        textPanel.add(environmentFirstRoom);
        environmentFirstRoom.setVisible(false);
        textPanel.add(environmentSecondRoom);
        environmentSecondRoom.setVisible(false);
        textPanel.add(environmentThirdRoom);
        environmentThirdRoom.setVisible(false);
        variantPanel.add(backFromRooms);
        backFromRooms.setVisible(false);

        notBox.gridy = 0;
        variantPanel.add(firstRoom, notBox);
        notBox.gridy = 1;
        variantPanel.add(secondRoom, notBox);
        notBox.gridy = 2;
        variantPanel.add(thirdRoom, notBox);
        notBox.gridy = 3;
        variantPanel.add(doorWithCode, notBox);

        rules.setVisible(false);
        environmentDoorWithCode.setVisible(false);

        rules.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        variantPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel);
        panel.setBackground(panelColor);
        frame.setVisible(true);

        someActionWithButton(startButton, false, false, true, 0);
        someActionWithButton(firstRoom, false, false, false, 1);
        someActionWithButton(secondRoom, false, false, false, 2);
        someActionWithButton(thirdRoom, false, false, false, 3);
        someActionWithButton(doorWithCode, false, false, false, 4);
        someActionWithButton(rulesButton, true, false, false, 0);
        someActionWithButton(backButton, false, true, false, 0);
        someActionWithButton(backFromRooms, false, false, true, 0);

        startButton.addActionListener(e -> {
            panel.removeAll();
            panel.updateUI();
            frame.remove(panel);
            frame.add(variantPanel, BorderLayout.SOUTH);
            frame.add(textPanel, BorderLayout.NORTH);
            frame.revalidate();
            frame.repaint();
        });

        exitButton.addActionListener(e -> System.exit(0));

        inputButton.addActionListener(e -> {
            if (Arrays.equals((inputCode).getPassword(), correctCode)) {
                JOptionPane.showMessageDialog(null, "Вы выиграли!");
                System.exit(0);
            } else {
                error.setVisible(true);
            }
        });

        inputCode.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (inputCode.getText().length() >= 6)
                    e.consume();
            }
        });
    }

    private static void someActionWithButton(JButton button, boolean toRules, boolean startMenu, boolean isEnvironmentStart, int numberOfScene) {
        button.addActionListener(e -> {
            startButton.setVisible(startMenu);
            exitButton.setVisible(startMenu);
            rulesButton.setVisible(startMenu);
            backButton.setVisible(toRules);
            rules.setVisible(toRules);
            environmentStart.setVisible(isEnvironmentStart);
            firstRoom.setVisible(isEnvironmentStart);
            secondRoom.setVisible(isEnvironmentStart);
            thirdRoom.setVisible(isEnvironmentStart);
            doorWithCode.setVisible(isEnvironmentStart);
            environmentFirstRoom.setVisible(numberOfScene == 1);
            environmentSecondRoom.setVisible(numberOfScene == 2);
            environmentThirdRoom.setVisible(numberOfScene == 3);
            inputLocation.setVisible(numberOfScene == 4);
            environmentDoorWithCode.setVisible(numberOfScene == 4);
            inputCode.setVisible(numberOfScene == 4);
            backFromRooms.setVisible(numberOfScene != 0);
            error.setVisible(false);
        });
    }

    private static void buttonInitialization(JButton button) {
        Color buttonColor = new Color(233, 179, 97);
        Font BigFontTR = new Font("TimesRoman", Font.PLAIN, 26);
        button.setBackground(buttonColor);
        button.setForeground(Color.BLACK);
        button.setFont(BigFontTR);
    }

    private static void reader(Box box, String inputFile) {
        try {
            Font rulesFont = new Font("TimesRoman", Font.PLAIN, 19);
            File text = new File(inputFile);
            FileReader readText = new FileReader(text);
            BufferedReader readerOfText = new BufferedReader(readText);
            String line;
            while ((line = readerOfText.readLine()) != null) {
                JLabel label = new JLabel(line);
                if (box.equals(error)) label.setForeground(Color.RED);
                label.setFont(rulesFont);
                box.add(label);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}