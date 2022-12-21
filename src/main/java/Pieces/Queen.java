package Pieces;

/**
 * Piece: Black King
 */
public class Queen extends PieceDecorator {
    public Queen(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Queen";
    }
}
