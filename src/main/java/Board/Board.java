package Board;

import javax.swing.*;
import java.awt.*;

public class Board {
    JFrame frame = new JFrame("Chess");
    JPanel panel = new JPanel();
    JPanel[][] matrixPanels = new JPanel[8][8];

    public Board(int WIDTH, int HEIGHT) {
        addTiles(WIDTH, HEIGHT);
        setFrame(WIDTH, HEIGHT);
    }

    public JPanel[][] getMatrixPanels() {
        return matrixPanels;
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
        int val = 0;
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                JPanel jPanel = new JPanel();
                if (val % 2 == 0) {
                    jPanel.setBackground(Color.WHITE);
                } else {
                    jPanel.setBackground(Color.BLACK);
                }
                val++;
                jPanel.setPreferredSize(new Dimension(WIDTH / 9, HEIGHT / 9));
                jPanel.add(new Label("" + ((char) x) + y));
                matrixPanels[y - 1][x - 'a'] = jPanel;
                panel.add(jPanel);
            }
            val++;
        }
    }
}
