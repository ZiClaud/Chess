package Player;

import Pieces.PieceColor;

public class PlayerBlack extends Player{
    public PlayerBlack() {
        super.color = PieceColor.WHITE;
        super.turn = false;
    }
}
