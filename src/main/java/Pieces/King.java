package Pieces;

/**
 * Piece: King
 * Decorator
 */
public class King extends PieceDecorator {
    public King(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", King";
    }
}
