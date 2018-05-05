package planetSystem;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;


/**
 * Главный фрейм
 *
 * @author Михаил Глухих
 */
public class MetaFrame extends JFrame {
    /**
     * Компоненты-дети
     */
    private MainPanel planetSystemPanel;
    private InfoPanel infoPanel;
    boolean isStarInitialisation = false;
    /**
     * Слушатели
     */
    private ActionListener addListener, pauseListener, playListener, forwardListener;

    private void initToolBar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setBorder(new BevelBorder(BevelBorder.RAISED));
        toolbar.addSeparator();
        JButton addButton = new JButton(new ImageIcon("files/add.png"));
        addButton.addActionListener(addListener);
        toolbar.add(addButton);
        JButton pauseButton = new JButton(new ImageIcon("files/pause.png"));
        pauseButton.addActionListener(pauseListener);
        toolbar.add(pauseButton);
        toolbar.addSeparator();
        JButton playButton = new JButton(new ImageIcon("files/play.png"));
        playButton.addActionListener(playListener);
        toolbar.add(playButton);
        JButton forwardButton = new JButton(new ImageIcon("files/forward.png"));
        forwardButton.addActionListener(forwardListener);
        toolbar.add(forwardButton);
        toolbar.addSeparator();
        this.add(toolbar, BorderLayout.NORTH);
    }

    private void initMainPanel() {
        planetSystemPanel = new MainPanel();
        planetSystemPanel.setBackground(Color.BLACK);
        planetSystemPanel.setPreferredSize(new Dimension(1000, 1000));
        planetSystemPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    private void initInfoPanel() {
        infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new Dimension(200, 500));
        infoPanel.setMinimumSize(new Dimension(150, 500));
        infoPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    /**
     * Инициализация всех слушателей
     */
    private void initListeners() {
        addListener = e -> addSomething();
        // Режим добавления пути
        pauseListener = e -> pause();
        // Режим выбора
        playListener = e -> play();
        // Открыть файл
        forwardListener = e -> forward();
    }

    private void addSomething(){
        pause();
        if (!isStarInitialisation) {
            GetInformationBox gIP = new GetInformationBox();
            gIP.setPreferredSize(new Dimension(200, 500));
            gIP.setMinimumSize(new Dimension(150, 500));
            setVisible(true);
            planetSystemPanel.pS.setStar(new Star(gIP.name, gIP.radius));
        }
        play();
    }

    public void pause() {
        planetSystemPanel.pS.pause();
    }

    public void play() {
        planetSystemPanel.pS.play();
    }

    public void forward() {
        pause();
        String input = JOptionPane.showInputDialog("Введите кол-во дней на которые хотитие промотать вперед");
        planetSystemPanel.pS.forward(Integer.parseInt(input));
        play();
    }

    /**
     * Конструктор
     *
     * @param s заголовок фрейма
     */
    private MetaFrame(String s) {
        super(s);
        setSize(800, 600);
        // Выбор BorderLayout
        this.setLayout(new BorderLayout());
        // Инициализация панелей
        initInfoPanel();
        initMainPanel();
        JScrollPane scrollPanel = new JScrollPane(planetSystemPanel);
        scrollPanel.setMinimumSize(new Dimension(200, 200));
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPanel, infoPanel),
                BorderLayout.CENTER);
        initListeners();
        initToolBar();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MetaFrame("Planet System Simulation"));
    }
}
