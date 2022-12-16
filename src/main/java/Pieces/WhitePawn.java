package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class WhitePawn extends Piece {
    public WhitePawn(char posX, int posY) {
        this.pieceType = PieceType.Pawn;
        this.pieceColor = PieceColor.WHITE;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }
}
