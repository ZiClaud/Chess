package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class BlackQueen extends Piece {
    public BlackQueen(char posX, int posY) {
        this.pieceType = PieceType.Queen;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getBlackQueen();
    }
}
