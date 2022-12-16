package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class WhiteKnight extends Piece {
    public WhiteKnight(char posX, int posY) {
        this.pieceType = PieceType.Knight;
        this.pieceColor = PieceColor.WHITE;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }
}
