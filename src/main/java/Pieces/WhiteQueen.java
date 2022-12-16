package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class WhiteQueen extends Piece {
    public WhiteQueen(char posX, int posY) {
        this.pieceType = PieceType.Queen;
        this.pieceColor = PieceColor.WHITE;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }
}
