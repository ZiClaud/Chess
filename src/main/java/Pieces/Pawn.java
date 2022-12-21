package Pieces;

/**
 * Piece: Black King
 */
public class Pawn extends PieceDecorator {
    private boolean theMove = false;

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

    public boolean didTheMove() {
        return theMove;
    }

    public void setTheMove(boolean theMove) {
        this.theMove = theMove;
    }
}
