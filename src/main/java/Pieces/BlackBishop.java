package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class BlackBishop extends Piece {
    public BlackBishop(char posX, int posY) {
        this.pieceType = PieceType.Bishop;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }
}
