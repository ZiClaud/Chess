package PiecesOld;

/**
 * Piece: King
 * Decorator
 *
public class KingOld extends PieceDecoratorOld {
    private boolean castle = true;
    public KingOld(PieceImpl piece, char posX, int posY) {
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
 */