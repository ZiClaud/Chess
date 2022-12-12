package Board;

import javax.swing.*;
import java.awt.*;

public class Board {
    JFrame frame = new JFrame("Chess");
    JPanel panel = new JPanel();

    public Board(int WIDTH, int HEIGHT) {
        addTiles(WIDTH, HEIGHT);
        setFrame(WIDTH, HEIGHT);
    }

    private void setFrame(int WIDTH, int HEIGHT) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void addTiles(int WIDTH, int HEIGHT) {
        int x = 0;
        JPanel jPanel;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (x % 2 == 0) {
                    jPanel = new JPanel();
                    jPanel.setBackground(Color.WHITE);
                } else {
                    jPanel = new JPanel();
                    jPanel.setBackground(Color.BLACK);
                }
                x++;
                jPanel.setPreferredSize(new Dimension(WIDTH / 9, HEIGHT / 9));
                panel.add(jPanel);

            }
            x++;
        }
    }
}
