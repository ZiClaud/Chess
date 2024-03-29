package Pieces;

/**
 * Types of PiecesOld
 */
public enum PieceType {
    Pawn("Pawn"),
    Knight("Knight"),
    Bishop("Bishop"),
    Tower("Tower"),
    Queen("Queen"),
    King("King");

    private final String pieceName;

    PieceType(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }
}
