package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class BlackTower extends Piece {
    public BlackTower(char posX, int posY) {
        this.pieceType = PieceType.Tower;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }
}
