package Pieces;

/**
 * Piece: Bishop
 * Decorator
 */
public class Bishop extends PieceDecorator {
    public Bishop(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Bishop";
    }
}
