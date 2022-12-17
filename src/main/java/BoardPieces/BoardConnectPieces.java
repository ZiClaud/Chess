package BoardPieces;

import Board.WindowBoard;
import Pieces.*;
import Rules.Rules;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

/**
 * Class that connects Board with Pieces
 * Draws the Pieces into the Board
 */
public class BoardConnectPieces {
    private final WindowBoard windowBoard;
    private final HashSet<Piece> pieces = new HashSet<>();

    public BoardConnectPieces(WindowBoard windowBoard) {
        this.windowBoard = windowBoard;
        setupPieces();
    }

    public WindowBoard getWindowBoard() {
        return windowBoard;
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }

    public void drawPiecesOnBoard() {
        for (Piece p : pieces) {
            placePieceOnPanel(p, windowBoard.getMatrixPanels()[p.getPosY() - 1][p.getPosX() - 'a']);
        }
    }

    private void cleanPanel(JPanel panel){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    private void placePieceOnPanel(Piece piece, JPanel panel) {
        BufferedImage myPicture = piece.getImg();
        Image scaledImage = myPicture.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);

        JButton picLabel = new JButton(new ImageIcon(scaledImage));
        picLabel.setOpaque(false);
        picLabel.setContentAreaFilled(false);
        picLabel.setBorderPainted(false);
        picLabel.addActionListener(e -> {
            cleanPanel(panel);
            piecePressed(piece);
        });
        cleanPanel(panel);
        panel.add(picLabel);
        windowBoard.refreshFrame();
    }

    private void piecePressed(Piece piece) {
        showPossibleMoves(piece);
        askForCoordinates(piece);
        placePieceOnPanel(piece, windowBoard.getMatrixPanels()[piece.getPosY() - 1][piece.getPosX() - 'a']);
    }

    private void showPossibleMoves(Piece piece) {
        for (int y = 8; y >= 1; y--) {
            for (char x = 'a'; x <= 'h'; x++) {
                if (Rules.canPieceMoveHere(piece, x, y)) {
                    windowBoard.getMatrixPanels()[y - 1][x - 'a'].setBackground(Color.red);
                }
            }
        }

    }

    private void askForCoordinates(Piece piece) {
        Character[] optionsX = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        Character x;

        // TODO: If X/Y == NULL it means player pressed "cancel", so undo move
        x = (Character) JOptionPane.showInputDialog(null, "Board size", "Choose the board size", JOptionPane.QUESTION_MESSAGE, null, optionsX, optionsX[0]);

        if (x == null) {
            return;
        }

        Integer[] optionsY = {1, 2, 3, 4, 5, 6, 7, 8};
        Integer y;
        y = (Integer) JOptionPane.showInputDialog(null, "Board size", "Choose the board size", JOptionPane.QUESTION_MESSAGE, null, optionsY, optionsY[0]);

        if (y == null) {
            return;
        }
        piece.setPosX(x);
        piece.setPosY(y);
    }

    private void setupPieces() {
        Piece wt1 = new WhiteTower('a', 1);
        Piece wb1 = new WhiteKnight('b', 1);
        Piece wn1 = new WhiteBishop('c', 1);
        Piece wq1 = new WhiteQueen('d', 1);
        Piece wk1 = new WhiteKing('e', 1);
        Piece wn2 = new WhiteBishop('f', 1);
        Piece wb2 = new WhiteKnight('g', 1);
        Piece wt2 = new WhiteTower('h', 1);

        Piece wp1 = new WhitePawn('a', 2);
        Piece wp2 = new WhitePawn('b', 2);
        Piece wp3 = new WhitePawn('c', 2);
        Piece wp4 = new WhitePawn('d', 2);
        Piece wp5 = new WhitePawn('e', 2);
        Piece wp6 = new WhitePawn('f', 2);
        Piece wp7 = new WhitePawn('g', 2);
        Piece wp8 = new WhitePawn('h', 2);

        Piece bp1 = new BlackPawn('a', 7);
        Piece bp2 = new BlackPawn('b', 7);
        Piece bp3 = new BlackPawn('c', 7);
        Piece bp4 = new BlackPawn('d', 7);
        Piece bp5 = new BlackPawn('e', 7);
        Piece bp6 = new BlackPawn('f', 7);
        Piece bp7 = new BlackPawn('g', 7);
        Piece bp8 = new BlackPawn('h', 7);

        Piece bt1 = new BlackTower('a', 8);
        Piece bb1 = new BlackKnight('b', 8);
        Piece bn1 = new BlackBishop('c', 8);
        Piece bq1 = new BlackQueen('d', 8);
        Piece bk1 = new BlackKing('e', 8);
        Piece bn2 = new BlackBishop('f', 8);
        Piece bb2 = new BlackKnight('g', 8);
        Piece bt2 = new BlackTower('h', 8);


        pieces.add(wt1);
        pieces.add(wb1);
        pieces.add(wn1);
        pieces.add(wq1);
        pieces.add(wk1);
        pieces.add(wn2);
        pieces.add(wb2);
        pieces.add(wt2);

        pieces.add(wp1);
        pieces.add(wp2);
        pieces.add(wp3);
        pieces.add(wp4);
        pieces.add(wp5);
        pieces.add(wp6);
        pieces.add(wp7);
        pieces.add(wp8);

        pieces.add(bp1);
        pieces.add(bp2);
        pieces.add(bp3);
        pieces.add(bp4);
        pieces.add(bp5);
        pieces.add(bp6);
        pieces.add(bp7);
        pieces.add(bp8);

        pieces.add(bt1);
        pieces.add(bb1);
        pieces.add(bn1);
        pieces.add(bq1);
        pieces.add(bk1);
        pieces.add(bn2);
        pieces.add(bb2);
        pieces.add(bt2);
    }
}
