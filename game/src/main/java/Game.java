import javax.swing.*;
import java.awt.*;


public class Game extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        Game game = new Game();
    }

    private Game() {
        super();
        this.init();
    }

    private int Numbers2D[][] = new int[4][4];

    private int FontSize = 30;
    private int MoveX = 0, MoveY = 0;

    private void init() {
        this.setTitle("Cute-2048");
        this.setLocation(config.LOCATION_X, config.LOCATION_Y);
        this.setSize(config.SIZE_WIDTH, config.SIZE_HEIGHT);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(config.START_PATH);
        JButton start = new JButton(imageIcon);
        start.setFocusable(false);
        start.setBounds(5, 10, config.START_WIDTH, config.START_HEIGHT);
        this.getContentPane().add(start);

        ImageIcon imageIcon1 = new ImageIcon(config.HELP_PATH);
        JButton help = new JButton(imageIcon1);
        help.setFocusable(false);
        help.setBounds(288, 10, config.HELP_WIDTH, config.HELP_HEIGHT);
        this.getContentPane().add(help);

        JLabel jLabel = new JLabel("SCORE£º0");
        jLabel.setBounds(50, 45, config.JLABEL_WIDTH, config.JLABEL_HEIGHT);
        jLabel.setFont(new Font("Ó×Ô²", Font.CENTER_BASELINE, 18));
        jLabel.setForeground(Color.BLACK);
        this.getContentPane().add(jLabel);

        JLabel label = new JLabel(new ImageIcon(config.BACKGROUND_PATH));
        label.setBounds(0, 0, config.SIZE_WIDTH, config.SIZE_HEIGHT);
        this.getContentPane().add(label);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        Listeners cl = new Listeners(this, Numbers2D, jLabel, start, help);
        start.addActionListener(cl);
        help.addActionListener(cl);
        this.addKeyListener(cl);
    }

    private void colorSetting(Graphics g, Color color, int x, int size) {
        g.setColor(color);
        FontSize = size;
        MoveX = x;
        MoveY = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.lightGray);
        g.fillRoundRect(15, 110, config.BIGBLOCK_SIZE_WIDTH,
                config.BIGBLOCK_SIZE_HEIGHT, 15, 15);

        g.setColor( new Color(238, 224, 198));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRoundRect(25 + i * 90, 120 + j * 90, config.SMALLBLOCK_SIZE_WIDTH,
                        config.SMALLBLOCK_SIZE_HEIGHT, 15, 15);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Numbers2D[j][i] != 0) {
                    switch (Numbers2D[j][i]) {
                        case 2:
                            this.colorSetting(g, new Color(240, 240, 238), 0, 30);
                            break;
                        case 4:
                            this.colorSetting(g, new Color(238, 224, 160), 0, 30);
                            break;
                        case 8:
                            this.colorSetting(g, new Color(243, 177, 116), 0, 30);
                            break;
                        case 16:
                            this.colorSetting(g, new Color(243, 177, 116), -5, 29);
                            break;
                        case 32:
                            this.colorSetting(g, new Color(248, 149, 90), -5, 29);
                            break;
                        case 64:
                            this.colorSetting(g, new Color(249, 94, 50), -5, 29);
                            break;
                        case 128:
                            this.colorSetting(g, new Color(239, 207, 108), -10, 28);
                            break;
                        case 256:
                            this.colorSetting(g, new Color(239, 207, 99), -10, 28);
                            break;
                        case 512:
                            this.colorSetting(g, new Color(239, 203, 82), -10, 28);
                            break;
                        case 1024:
                            this.colorSetting(g, new Color(239, 199, 57), -15, 27);
                            break;
                        case 2048:
                            this.colorSetting(g, new Color(239, 195, 41), -15, 27);
                            break;
                        case 4096:
                            this.colorSetting(g,new Color(255, 60, 57),-15,27);
                        default:
                            g.setColor(new Color(config.TEXT_COLOR));
                            break;
                    }

                    g.fillRoundRect(25 + i * 90, 120 + j * 90, 80, 80, 15, 15);
                    g.setColor(new Color(config.TEXT_COLOR));
                    g.setFont(new Font("ËÎÌå", Font.PLAIN, FontSize));
                    g.drawString(Numbers2D[j][i] + "", 25 + i * 90 + 30 + MoveX,
                            120 + j * 90 + 50 + MoveY);
                }
            }
        }
    }
}

