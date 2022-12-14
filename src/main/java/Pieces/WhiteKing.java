package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class WhiteKing extends Piece {
    public WhiteKing(char posX, int posY) {
        this.pieceType = PieceType.King;
        this.pieceColor = PieceColor.WHITE;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getWhiteKing();
    }
}
