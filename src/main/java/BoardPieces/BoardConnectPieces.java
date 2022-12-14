package BoardPieces;

import Board.WindowBoard;
import Pieces.BlackKing;
import Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

/**
 * Class that connects Board with Pieces
 */
public class BoardConnectPieces {
    private final WindowBoard windowBoard;
    private final HashSet<Piece> pieces = new HashSet<>();

    public BoardConnectPieces(WindowBoard windowBoard) {
        this.windowBoard = windowBoard;
        setupPieces();
    }

    private static void placePieceOnPanel(Piece piece, JPanel panel) {
        BufferedImage myPicture = piece.getImg();
        Image scaledImage = myPicture.getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH);

        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        panel.add(picLabel);
    }

    // TODO: Put starting position here, with all the pieces
    private void setupPieces() {
        Piece p1 = new BlackKing('a', 1);
        Piece p2 = new BlackKing('b', 1);
        Piece p3 = new BlackKing('c', 1);
        Piece p4 = new BlackKing('d', 1);
        Piece p5 = new BlackKing('e', 1);
        Piece p6 = new BlackKing('f', 1);
        Piece p7 = new BlackKing('g', 1);
        Piece p8 = new BlackKing('h', 1);

        pieces.add(p1);
        pieces.add(p2);
        pieces.add(p3);
        pieces.add(p4);
        pieces.add(p5);
        pieces.add(p6);
        pieces.add(p7);
        pieces.add(p8);

        /*
        matrixPieces[0][0] = PieceType.Tower;
        matrixPieces[0][1] = PieceType.Knight;
        matrixPieces[0][2] = PieceType.Bishop;
        matrixPieces[0][3] = PieceType.Queen;
        matrixPieces[0][4] = PieceType.King;
        matrixPieces[0][5] = PieceType.Bishop;
        matrixPieces[0][6] = PieceType.Knight;
        matrixPieces[0][7] = PieceType.Tower;

        matrixPieces[7][0] = PieceType.Tower;
        matrixPieces[7][1] = PieceType.Knight;
        matrixPieces[7][2] = PieceType.Bishop;
        matrixPieces[7][3] = PieceType.Queen;
        matrixPieces[7][4] = PieceType.King;
        matrixPieces[7][5] = PieceType.Bishop;
        matrixPieces[7][6] = PieceType.Knight;
        matrixPieces[7][7] = PieceType.Tower;

        matrixPieces[1][0] = PieceType.Pawn;
        matrixPieces[1][1] = PieceType.Pawn;
        matrixPieces[1][2] = PieceType.Pawn;
        matrixPieces[1][3] = PieceType.Pawn;
        matrixPieces[1][4] = PieceType.Pawn;
        matrixPieces[1][5] = PieceType.Pawn;
        matrixPieces[1][6] = PieceType.Pawn;
        matrixPieces[1][7] = PieceType.Pawn;

        matrixPieces[6][0] = PieceType.Pawn;
        matrixPieces[6][1] = PieceType.Pawn;
        matrixPieces[6][2] = PieceType.Pawn;
        matrixPieces[6][3] = PieceType.Pawn;
        matrixPieces[6][4] = PieceType.Pawn;
        matrixPieces[6][5] = PieceType.Pawn;
        matrixPieces[6][6] = PieceType.Pawn;
        matrixPieces[6][7] = PieceType.Pawn;
        */
    }

    public void drawPiecesOnBoard() {
        for (Piece p : pieces) {
            placePieceOnPanel(p, windowBoard.getMatrixPanels()[p.getPosY() - 1][p.getPosX() - 'a']);
        }
    }

    public WindowBoard getWindowBoard() {
        return windowBoard;
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }
}
