package BoardPieces;

import Board.WindowBoard;
import Pieces.*;

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

    private static void placePieceOnPanel(Piece piece, JPanel panel) {
        BufferedImage myPicture = piece.getImg();
        Image scaledImage = myPicture.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);

        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        panel.add(picLabel);
    }

    public WindowBoard getWindowBoard() {
        return windowBoard;
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }

    // TODO: Put starting position here, with all the pieces
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
}
