package Player;

import Pieces.PieceColor;

public abstract class Player {
    protected boolean turn;

    public abstract PieceColor getColor();

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}
