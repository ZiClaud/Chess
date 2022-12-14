package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class BlackKnight extends Piece {
    public BlackKnight(char posX, int posY) {
        this.pieceType = PieceType.Knight;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getBlackKnight();
    }
}
