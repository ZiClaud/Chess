package Pieces;

import java.awt.image.BufferedImage;

/**
 * Piece: Black King
 */
public class BlackKing extends Piece {
    public BlackKing(char posX, int posY) {
        this.pieceType = PieceType.King;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }

    @Override
    public BufferedImage getImg() {
        return PieceImg.getBlackKing();
    }
}
