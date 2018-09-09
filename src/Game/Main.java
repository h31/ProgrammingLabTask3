package Game;
        import javax.swing.*;
        import javax.swing.text.AbstractDocument;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.KeyAdapter;
        import java.awt.event.KeyEvent;
        import java.util.Arrays;

public class Main {
    public static int heightOfFrame;
    public static int widthOfFrame;
    public static void main(String[] args ) {
        char[] correctCode = {'6', '8', '4', '7', '3', '1'};
        JFrame frame = new JFrame("Текстовый квест");
        JPanel panel = new JPanel();
        JPanel variantPanel = new JPanel();
        JPanel textPanel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 800;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        heightOfFrame = frame.getHeight();
        widthOfFrame = frame.getWidth();

        JButton startButton = new JButton("Начать игру");
        JButton rulesButton = new JButton("Правила");
        JButton exitButton = new JButton("Выход");
        JButton backButton = new JButton("Назад");
        JButton firstRoom = new JButton("Войти в дверь с цифрой 1");
        JButton secondRoom = new JButton("Войти в дверь с цифрой 2");
        JButton thirdRoom = new JButton("Войти в дверь с цифрой 3");
        JButton doorWithCode = new JButton("Войти в дверь с цифрой 4");
        JButton backFromRooms = new JButton("Вернуться");
        JButton inputButton = new JButton("Ввод");

        JLabel rules = new JLabel("В этой игре вам нужно выбраться из ловушки.");
        JLabel environmentStart = new JLabel("<html>Вы по-прежнему не помните, как оказались взаперти.<br>" +
        "Тут темно и холодно. Из-за этого вам поскорее хочется выбраться</html>");
        JLabel environmentFirstRoom = new JLabel("<html>Вы заходите в комнату. Здесь посветлее. На стене вы видите картину.<br>" +
        "На ней изображены зеленый паук, красный конь и синий жук.<br>" +
                "Больше тут ничего нет.</html>");
        JLabel environmentSecondRoom = new JLabel("<html>В этой комнате довольно светло, по центру комнаты стоит стол.<br>" +
                "На нём лежат ручки разных цветов. Вы решаете посчитать их,<br>" +
                "три фиолетовых, семь желтых, одна белая. Брать их бессмысленно, они не пишут.</html>");
        JLabel environmentThirdRoom = new JLabel("<html>Эта комната почти также пуста, как и две другие.<br>" +
                "Здесь вы видите только цветную пирамидку, сверху синий конус,<br>" +
                "далее зеленый бублик, затем красный, желтый, фиолетовый и белый.<br>" +
                "Кроме пирамидки тут ничего нет.</html>");
        JLabel environmentDoorWithCode = new JLabel("Вы подходите к двери. На ней шестизначный кодовый замок.");
        JLabel error = new JLabel("Пароль не подошёл");
        JLabel inputLabel = new JLabel("Попробовать ввести код:");

        JPasswordField inputCode = new JPasswordField(6);
        inputCode.setEchoChar((char)0);
        ((AbstractDocument) inputCode.getDocument()).setDocumentFilter(new NewFilter());

        Color buttonColor = new Color(233, 179, 97);
        Color panelColor = new Color(233, 227, 175);
        startButton.setBackground(buttonColor);
        startButton.setForeground(Color.BLACK);
        rulesButton.setBackground(buttonColor);
        rulesButton.setForeground(Color.BLACK);
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.BLACK);
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.BLACK);
        firstRoom.setForeground(Color.BLACK);
        firstRoom.setBackground(buttonColor);
        secondRoom.setForeground(Color.BLACK);
        secondRoom.setBackground(buttonColor);
        thirdRoom.setForeground(Color.BLACK);
        thirdRoom.setBackground(buttonColor);
        doorWithCode.setForeground(Color.BLACK);
        doorWithCode.setBackground(buttonColor);
        backFromRooms.setForeground(Color.BLACK);
        backFromRooms.setBackground(buttonColor);
        inputButton.setForeground(Color.BLACK);
        inputButton.setBackground(buttonColor);
        error.setForeground(Color.RED);


        variantPanel.setBackground(panelColor);
        Font BigFontTR = new Font("TimesRoman",Font.PLAIN ,26);
        Font rulesFont = new Font("TimesRoman", Font.PLAIN, 19);

        startButton.setFont(BigFontTR);
        rulesButton.setFont(BigFontTR);
        exitButton.setFont(BigFontTR);
        firstRoom.setFont(BigFontTR);
        secondRoom.setFont(BigFontTR);
        thirdRoom.setFont(BigFontTR);
        doorWithCode.setFont(BigFontTR);
        backFromRooms.setFont(BigFontTR);
        inputButton.setFont(rulesFont);
        inputLabel.setFont(rulesFont);
        inputCode.setFont(rulesFont);
        rules.setFont(rulesFont);
        environmentStart.setFont(rulesFont);
        environmentFirstRoom.setFont(rulesFont);
        environmentSecondRoom.setFont(rulesFont);
        environmentThirdRoom.setFont(rulesFont);
        environmentDoorWithCode.setFont(rulesFont);
        error.setFont(rulesFont);
        backButton.setFont(BigFontTR);

        backButton.setVisible(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        variantPanel.setLayout(new GridBagLayout());
        Box inputLocation = Box.createHorizontalBox();
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

        Dimension minSize = new Dimension(widthOfFrame/200, heightOfFrame/100);
        Dimension prefSize = new Dimension(widthOfFrame, heightOfFrame/3);
        Dimension maxSize = new Dimension(widthOfFrame, heightOfFrame/3);
        panel.add(new Box.Filler(minSize, prefSize, maxSize));

        rulesButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.red),
                rulesButton.getBorder()));

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

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.updateUI();
                frame.remove(panel);
                frame.add(variantPanel, BorderLayout.SOUTH);
                frame.add(textPanel,BorderLayout.NORTH);
                frame.revalidate();
                frame.repaint();
            }
        });

        firstRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                environmentStart.setVisible(false);
                firstRoom.setVisible(false);
                secondRoom.setVisible(false);
                thirdRoom.setVisible(false);
                doorWithCode.setVisible(false);
                environmentFirstRoom.setVisible(true);
                backFromRooms.setVisible(true);
            }
        });

        secondRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                environmentStart.setVisible(false);
                firstRoom.setVisible(false);
                secondRoom.setVisible(false);
                thirdRoom.setVisible(false);
                doorWithCode.setVisible(false);
                environmentSecondRoom.setVisible(true);
                backFromRooms.setVisible(true);
            }
        });

        thirdRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                environmentStart.setVisible(false);
                firstRoom.setVisible(false);
                secondRoom.setVisible(false);
                thirdRoom.setVisible(false);
                doorWithCode.setVisible(false);
                environmentThirdRoom.setVisible(true);
                backFromRooms.setVisible(true);
            }
        });

        doorWithCode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                environmentStart.setVisible(false);
                firstRoom.setVisible(false);
                secondRoom.setVisible(false);
                thirdRoom.setVisible(false);
                doorWithCode.setVisible(false);
                inputLocation.setVisible(true);
                environmentDoorWithCode.setVisible(true);
                inputCode.setVisible(true);
                backFromRooms.setVisible(true);
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(false);
                exitButton.setVisible(false);
                rulesButton.setVisible(false);
                backButton.setVisible(true);
                rules.setVisible(true);
                }
            });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButton.setVisible(true);
                exitButton.setVisible(true);
                rulesButton.setVisible(true);
                backButton.setVisible(false);
                rules.setVisible(false);
            }
        });

        backFromRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                environmentStart.setVisible(true);
                firstRoom.setVisible(true);
                secondRoom.setVisible(true);
                thirdRoom.setVisible(true);
                doorWithCode.setVisible(true);
                inputCode.setVisible(false);
                environmentFirstRoom.setVisible(false);
                environmentSecondRoom.setVisible(false);
                environmentThirdRoom.setVisible(false);
                environmentDoorWithCode.setVisible(false);
                inputLocation.setVisible(false);
                backFromRooms.setVisible(false);
                error.setVisible(false);
            }
        });

        inputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (Arrays.equals((inputCode).getPassword(), correctCode)){
                    JOptionPane.showMessageDialog(null, "Вы выиграли!");
                    System.exit(0);
                }else {
                    error.setVisible(true);
                }
            }
        });

        inputCode.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (inputCode.getText().length() >= 6 ) // limit to 3 characters
                    e.consume();
            }
        });
    }
}
