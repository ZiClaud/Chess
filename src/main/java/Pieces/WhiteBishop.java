package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class WhiteBishop extends Piece {
    public WhiteBishop(char posX, int posY) {
        this.pieceType = PieceType.Bishop;
        this.pieceColor = PieceColor.WHITE;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getWhiteBishop();
    }
}
