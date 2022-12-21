package Player;

import Pieces.PieceColor;

public class PlayerBlack extends Player{
    public PlayerBlack() {
        super.turn = false;
    }

    @Override
    public PieceColor getColor() {
        return PieceColor.BLACK;
    }
}
