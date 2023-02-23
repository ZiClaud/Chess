package Player;

import Pieces.Piece;
import Pieces.PieceColor;

import java.util.HashSet;

public abstract class Player {
    protected static HashSet<Piece> pieces = new HashSet<>();
    protected boolean turn;

    public abstract PieceColor getColor();

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(HashSet<Piece> pieces) {
        this.pieces = pieces;
    }
}
