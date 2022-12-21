package Pieces;

/**
 * Piece: King
 * Decorator
 */
public class King extends PieceDecorator {
    private boolean castle = true;
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

    public boolean canCastle() {
        return castle;
    }

    public void setCastle(boolean canCastle) {
        this.castle = canCastle;
    }
}
