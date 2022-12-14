package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class WhiteTower extends Piece {
    public WhiteTower(char posX, int posY) {
        this.pieceType = PieceType.Tower;
        this.pieceColor = PieceColor.WHITE;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getWhiteTower();
    }
}
