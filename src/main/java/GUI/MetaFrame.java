package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MetaFrame extends JFrame {

    private MainPanel planetSystemPanel;
    private InfoPanel infoPanel;

    private ActionListener pauseListener, playListener, forwardListener;

    private void initToolBar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setBorder(new BevelBorder(BevelBorder.RAISED));
        JButton pauseButton = new JButton(new ImageIcon("files/pause.png"));
        pauseButton.addActionListener(pauseListener);
        toolbar.add(pauseButton);
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

    private void initListeners() {
        pauseListener = e -> pause();
        playListener = e -> play();
        forwardListener = e -> forward();
    }

    void pause() {
        planetSystemPanel.pS.pause();
    }

    void play() {
        planetSystemPanel.pS.play();
    }

    void forward() {
        pause();
        String input = JOptionPane.showInputDialog("Введите кол-во дней на которые хотитие промотать вперед");
        planetSystemPanel.pS.forward(Integer.parseInt(input));
        play();
    }

    private MetaFrame(String s) {
        super(s);
        setSize(800, 600);
        this.setLayout(new BorderLayout());
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
