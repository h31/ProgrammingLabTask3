package Gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame {
    final GameSettings gs = new GameSettings();
    public MyFrame() {
        this.setSize(500, 600);
        this.setTitle("Gomoku");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - 500) / 2, (height - 500) / 2);

        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        JMenu menu1 = new JMenu("menu");
        JMenu menu2 = new JMenu("settings");
        JMenu menu3 = new JMenu("help");
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);

        JMenuItem item1 = new JMenuItem("start");
        menu1.add(item1);
        JMenuItem item2 = new JMenuItem("restart");
        menu1.add(item2);
        JMenuItem item3 = new JMenuItem("exit");
        menu1.add(item3);

        //setting of start
        item1.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                gs.startGame();
            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }
        });

        //setting of restart
        item2.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                Icon icon = new Icon() {
                    public void paintIcon(Component c, Graphics g, int x, int y) {

                    }

                    public int getIconWidth() {
                        return 0;
                    }

                    public int getIconHeight() {
                        return 0;
                    }
                };
                Object[] options = { "Restart", "No, thanks" };
                int n = JOptionPane.showOptionDialog(null, "Whether restart the game?",
                        "News", 0, 1, icon, options, "");
                if (n == 0) {
                    gs.startGame();
                }
            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
            }
        });

        //settings of exit
        item3.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                Icon icon = new Icon() {
                    public void paintIcon(Component c, Graphics g, int x, int y) {

                    }

                    public int getIconWidth() {
                        return 0;
                    }

                    public int getIconHeight() {
                        return 0;
                    }
                };
                Object[] options = { "Exit", "No, thanks" };
                int n = JOptionPane.showOptionDialog(null, "Whether exit the game?",
                        "News", 0, 1, icon, options, "");
                if (n == 0) {
                    System.exit(0);
                }
            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }
        });

        final JRadioButtonMenuItem item4 = new JRadioButtonMenuItem("Black first");
        final JRadioButtonMenuItem item5 = new JRadioButtonMenuItem("White first");
        menu2.add(item4);
        menu2.add(item5);

        //setting of "black first"
        item4.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                Icon icon = new Icon() {
                    public void paintIcon(Component c, Graphics g, int x, int y) {

                    }

                    public int getIconWidth() {
                        return 0;
                    }

                    public int getIconHeight() {
                        return 0;
                    }
                };
                Object[] options = { "Yes", "No, thanks" };
                int n = JOptionPane.showOptionDialog(null, "whether save and restart the game?",
                        "settings", 0, 1, icon, options, "");
                if (n == 0) {
                    gs.setBlackTurns(true);
                    gs.startGame();
                    item4.setSelected(true);
                }
            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }
        });

        //"setting of "white first"
        item5.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                Icon icon = new Icon() {
                    public void paintIcon(Component c, Graphics g, int x, int y) {

                    }

                    public int getIconWidth() {
                        return 0;
                    }

                    public int getIconHeight() {
                        return 0;
                    }
                };
                Object[] options = { "Yes", "No, thanks" };
                int n = JOptionPane.showOptionDialog(null, "whether save and restart the game?",
                        "settings", 0, 1, icon, options, "");
                if (n == 0) {
                    gs.setBlackTurns(false);
                    gs.startGame();
                    item5.setSelected(true);
                }

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }
        });

        JMenuItem item6 = new JMenuItem("help message");
        menu3.add(item6);

        item6.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                Icon icon = new Icon() {

                    public void paintIcon(Component c, Graphics g, int x, int y) {

                    }

                    public int getIconWidth() {

                        return 0;
                    }

                    public int getIconHeight() {
                        return 0;
                    }
                };
                JOptionPane.showMessageDialog(null,
                        "Please google Gomoku yourself if you don't know how to play");

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseClicked(MouseEvent e) {

            }
        });

        Container c = this.getContentPane();
        c.add(gs);

    }
}
