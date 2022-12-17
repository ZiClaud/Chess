package Pieces;

/**
 * Piece: Black King
 */
public class BlackPawn extends Piece {
    public BlackPawn(char posX, int posY) {
        this.pieceType = PieceType.Pawn;
        this.pieceColor = PieceColor.BLACK;
        this.posX = posX;
        this.posY = posY;
        isPositionAvailable();
    }
}
