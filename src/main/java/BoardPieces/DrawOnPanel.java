package BoardPieces;

import Board.WindowBoard;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import static BoardPieces.DrawOnBoard.moveClicked;
import static BoardPieces.DrawOnBoard.pieceClicked;

public class DrawOnPanel {
    protected static void cleanPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    protected static void placePieceOnPanel(Piece piece, JPanel panel) {
        BufferedImage myPicture = piece.getImg();
        Image scaledImage = myPicture.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);

        JButton picLabel = new JButton(new ImageIcon(scaledImage));
        picLabel.setOpaque(false);
        picLabel.setContentAreaFilled(false);
        picLabel.setBorderPainted(false);
        if (piece.isTurn()) {
            picLabel.addActionListener(e -> {
                pieceClicked(piece);
            });
        }
        cleanPanel(panel);
        panel.add(picLabel);
    }

    protected static void placeMoveOnPanel(Piece piece, char x, int y, HashSet<JButton> jMovesButtons) {
        JButton moveHereBT = new JButton();
        moveHereBT.setOpaque(true);
        moveHereBT.setContentAreaFilled(false);
        moveHereBT.setBorderPainted(false);
        moveHereBT.addActionListener(actionEvent -> {
            moveClicked(piece, x, y);
        });

        WindowBoard.getInstance().getMatrixPanels()[y - 1][x - 'a'].setBackground(Color.GRAY);
        WindowBoard.getInstance().getMatrixPanels()[y - 1][x - 'a'].add(moveHereBT);
        jMovesButtons.add(moveHereBT);

        // TODO: Clean this code -> It makes picLabel button do the same thing of moveHereBT
        if (WindowBoard.getInstance().getMatrixPanels()[y - 1][x - 'a'].getComponentCount() > 1) {
            ((JButton) WindowBoard.getInstance().getMatrixPanels()[y - 1][x - 'a'].getComponents()[0]).addActionListener(actionEvent -> {
                moveClicked(piece, x, y);
            });
        }
    }
}
