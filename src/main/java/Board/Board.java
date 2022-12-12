package Board;

import org.w3c.dom.Text;

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
        int val = 0;
        JPanel jPanel;
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                if (val % 2 == 0) {
                    jPanel = new JPanel();
                    jPanel.setBackground(Color.WHITE);
                } else {
                    jPanel = new JPanel();
                    jPanel.setBackground(Color.BLACK);
                }
                val++;
                jPanel.setPreferredSize(new Dimension(WIDTH / 9, HEIGHT / 9));
                jPanel.add(new Label("" + ((char) x) + y));
                panel.add(jPanel);
            }
            val++;
        }
    }
}
