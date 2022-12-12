package Pieces;

import java.awt.image.BufferedImage;

public class BlackKing extends Piece {
    public BlackKing(char posX, int posY) {
        this.pieceType = PieceType.King;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    BufferedImage getImg() {
        return PieceImg.getBlackKnight();
    }
}
