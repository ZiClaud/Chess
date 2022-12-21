package Pieces;

/**
 * Piece: Black King
 */
public class Pawn extends PieceDecorator {
    public Pawn(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Pawn";
    }
}
