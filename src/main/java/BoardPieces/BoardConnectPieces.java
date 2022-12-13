package BoardPieces;

import Board.Board;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that connects Board with Pieces
 */
public class BoardConnectPieces {
    private final Board board;
    private final ArrayList<Piece> pieces;

    public BoardConnectPieces(Board board, ArrayList<Piece> pieces) {
        this.board = board;
        this.pieces = pieces;
    }

    public void setupMatrixBoard(char x, int y) {
        for (Piece piece : pieces) {
            placePieceOnPanel(piece, board.getMatrixPanels()[y - 1][x - 'a']);
            MatrixUtils.readMatrixBoard(board.getMatrixPanels(), x, y);
        }
    }

    // TODO: UNDARSTAND WHY THIS DOESN'T WORK AND FIX IT
    public static void placePieceOnPanel(Piece piece, JPanel panel) {
        BufferedImage img = piece.getImg();
        Graphics g = img.getGraphics();
        panel.paint(g);
        g.dispose();
    }

}
