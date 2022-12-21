package Pieces;


/**
 * Piece: Black King
 */
public class Tower extends PieceDecorator {
    public Tower(Piece piece, char posX, int posY) {
        this.piece = piece;
        this.posX = posX;
        this.posY = posY;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Tower";
    }
}
