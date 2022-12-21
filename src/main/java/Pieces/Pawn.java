package Pieces;

/**
 * Piece: Black King
 */
public class Pawn extends PieceDecorator {
    private char previousX = posX;
    private int previousY = posY;

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

    public boolean allowElPassant() {
        return (posY == previousY + 2 || posY == previousY - 2);
    }

    public void updatePreviousPos() {
        previousX = posX;
        previousY = posY;
    }
}
