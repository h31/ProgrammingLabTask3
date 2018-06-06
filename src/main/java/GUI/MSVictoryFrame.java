package GUI;

import javax.swing.*;
import java.awt.*;

public class MSVictoryFrame extends JFrame {

    public MSVictoryFrame() {
        add(new VictoryComponent());
        pack();
    }

    class VictoryComponent extends JComponent {
        public static final int message_X = 50;
        public static final int message_Y = 50;

        private static final int default_width = 200;
        private static final int default_height = 100;

        @Override
        public void paintComponent(Graphics graphics) {
          graphics.drawString("You win!", message_X, message_Y);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(default_width, default_height);
        }
    }
}
