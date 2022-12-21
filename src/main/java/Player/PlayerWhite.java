package Player;

import Pieces.PieceColor;

public class PlayerWhite extends Player {
    public PlayerWhite() {
        super.turn = true;
    }

    @Override
    public PieceColor getColor() {
        return PieceColor.WHITE;
    }
}
