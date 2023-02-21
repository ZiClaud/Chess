package Board;

import Game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Board - Graphic UI that appears with all the tiles
 */
public class WindowBoard {
    private static WindowBoard instance;
    private final BoardSize boardSize = new BoardSize('h', 8);
    private final JFrame frame = new JFrame("Chess");
    private final JPanel panel = new JPanel();
    private final JPanel[][] matrixPanels = new JPanel[8][8];

    private WindowBoard(int WIDTH, int HEIGHT) {
        addTiles(WIDTH, HEIGHT);
        colorTiles();
        setFrame(WIDTH, HEIGHT);
    }

    public static WindowBoard getInstance() {
        if (instance == null) {
            instance = new WindowBoard(Game.boardSize, Game.boardSize);
        }
        return instance;
    }

    public static WindowBoard getNewInstance() {
        instance = new WindowBoard(Game.boardSize, Game.boardSize);
        return instance;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }


    private void setFrame(int WIDTH, int HEIGHT) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.pack();
        frame.setLocationRelativeTo(null);
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void addTiles(int WIDTH, int HEIGHT) {
        int val = 0;
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                JPanel jPanel = new JPanel(new CardLayout());
                jPanel.setPreferredSize(new Dimension(WIDTH / 9, HEIGHT / 9));
                matrixPanels[y - 1][x - 'a'] = jPanel;
                panel.add(jPanel);
                val++;
            }
            val++;
        }
    }

    public JPanel[][] getMatrixPanels() {
        return matrixPanels;
    }

    public void colorTiles() {
        int val = 0;
        for (int y = 8; y >= 1; y--) {
            for (int x = 'a'; x <= 'h'; x++) {
                if (val % 2 == 0) {
                    matrixPanels[y - 1][x - 'a'].setBackground(Color.WHITE);
                } else {
                    matrixPanels[y - 1][x - 'a'].setBackground(Color.DARK_GRAY);
                }
                val++;
            }
            val++;
        }
    }

    public void colorThisTileRed(char x, int y) {
        matrixPanels[y - 1][x - 'a'].setBackground(Color.RED);
    }

    public void removeWindow() {
        frame.setVisible(false);
    }
}
