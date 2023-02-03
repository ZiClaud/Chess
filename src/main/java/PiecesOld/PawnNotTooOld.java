package PiecesOld;

/**
 * Piece: Black King
 *
public class PawnNotTooOld extends PieceDecoratorOld {
    private Position previousPos;

    public PawnNotTooOld(PieceImpl piece, Position position) {
        this.piece = piece;
        this.position = position;
        isValidPosition();
    }

    @Override
    public String getPieceName() {
        return piece.getPieceName() + ", Pawn";
    }

    public boolean allowsElPassant() {
        return (position.getY() == previousPos.getY() + 2 ||
                position.getY() == previousPos.getY() - 2);
    }

    public void updatePreviousPos() {
        previousPos = position;
    }
}

 */