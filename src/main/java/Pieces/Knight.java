package Pieces;

/**
 * Piece: Knight
 * Decorator
 */
public class Knight extends PieceDecorator {
    public Knight(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Knight";
    }
}
