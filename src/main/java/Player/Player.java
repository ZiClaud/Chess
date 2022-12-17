package Player;

import Pieces.PieceColor;

public abstract class Player {
    protected PieceColor color;
    protected boolean turn;

    public PieceColor getColor() {
        return color;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
