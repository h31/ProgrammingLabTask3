package GUI;

import Logic.Planet;
import Logic.PlanetSystem;
import Logic.Star;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlanetSystemSimulatorFrame extends JFrame {

    private MainPanel mainPanel;

    private InfoPanel infoPanel;

    private ActionListener pauseListener, playListener, forwardListener;

    private PlanetSystem pS = readPlanetSystem();

    public PlanetSystem readPlanetSystem() {
        List<String> list = new ArrayList<>();
        try {
            File f = new File("files/data");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) list.add(sc.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PlanetSystem pS = new PlanetSystem(new Star(list.get(0),
                Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))));
        int countOfPlanets = Integer.parseInt(list.get(3));
        list = list.subList(4, list.size());
        for (int i = 1; i <= countOfPlanets; i++) {
            Planet p = new Planet(list.get(0), Integer.parseInt(list.get(1)),
                    Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)),
                    Integer.parseInt(list.get(4)));
            pS.addPlanet(p);
            list = list.subList(5, list.size());
        }
        return pS;
    }

    private void initToolBar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setBorder(new BevelBorder(BevelBorder.RAISED));
        JButton pauseButton = new JButton(new ImageIcon("files/buttons/pause.png"));
        pauseButton.addActionListener(pauseListener);
        toolbar.add(pauseButton);
        JButton playButton = new JButton(new ImageIcon("files/buttons/play.png"));
        playButton.addActionListener(playListener);
        toolbar.add(playButton);
        JButton forwardButton = new JButton(new ImageIcon("files/buttons/forward.png"));
        forwardButton.addActionListener(forwardListener);
        toolbar.add(forwardButton);
        toolbar.addSeparator();
        this.add(toolbar, BorderLayout.NORTH);

    }

    private void initMainPanel() {
        mainPanel = new MainPanel(pS);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        mainPanel.setMaximumSize(new Dimension(800, 600));
        mainPanel.setMinimumSize(new Dimension(800, 600));
        mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    private void initInfoPanel() {
        infoPanel = new InfoPanel(pS);
        infoPanel.setPreferredSize(new Dimension(200, 600));
        infoPanel.setMinimumSize(new Dimension(200, 600));
        infoPanel.setMaximumSize(new Dimension(200, 600));
        infoPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    private void initListeners() {
        pauseListener = e -> pause();
        playListener = e -> play();
        forwardListener = e -> forward();
    }

    private void pause() {
        mainPanel.pS.pause();
    }

    private void play() {
        mainPanel.pS.play();
    }

    private void forward() {
        pause();
        String input = JOptionPane.showInputDialog("Введите кол-во дней на которые хотите промотать вперед");
        mainPanel.pS.forward(Integer.parseInt(input));
        play();
    }

    public PlanetSystemSimulatorFrame(String s) {
        super(s);
        setSize(1000, 600);
        this.setLayout(new BorderLayout());
        initInfoPanel();
        initMainPanel();
        JScrollPane scrollPanel = new JScrollPane(mainPanel);
        scrollPanel.setMinimumSize(new Dimension(600, 800));
        scrollPanel.setPreferredSize(new Dimension(600, 800));
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
        SwingUtilities.invokeLater(() -> new PlanetSystemSimulatorFrame("Planet System Simulator"));
    }
}