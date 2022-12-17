package Pieces;


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
}
